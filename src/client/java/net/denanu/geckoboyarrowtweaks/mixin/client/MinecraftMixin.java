package net.denanu.geckoboyarrowtweaks.mixin.client;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.data.tag.ModItemTags;
import net.denanu.geckoboyarrowtweaks.entity.player.PlayerModifications;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.BlockPosHomingTarget;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.EntityHomingTarget;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTarget;
import net.denanu.geckoboyarrowtweaks.networking.c2s.SelectTargetC2SPayload;
import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

@Mixin(Minecraft.class)@Environment(EnvType.CLIENT)
public class MinecraftMixin {
	@Inject(method="startAttack()B", at=@At("HEAD"), cancellable=true)
	public void startAttack(CallbackInfoReturnable<Boolean> ci) {
		LocalPlayer player = Minecraft.getInstance().player;
		ItemStack mainHandStack = player.getItemInHand(InteractionHand.MAIN_HAND);
		if (!mainHandStack.is(ModItemTags.HOMING_ARROW_CAPABLE_WEAPONS)) {
			return;
		}
		ItemStack projectile = player.getProjectile(mainHandStack);
		if (!projectile.is(Items.ARROW) || !projectile.get(ModDataComponents.ARROW_TIP).is(ModItemUtils.getHolder(Items.ECHO_SHARD))) {
			return;
		}

		HitResult hit = PlayerModifications.HOMING_TARGET_RANGE.getClosesetHit(player, 1, target -> EntitySelector.LIVING_ENTITY_STILL_ALIVE.test(target) && EntitySelector.NO_SPECTATORS.test(target));
		if (hit == null) {
			return;
		}
		Consumer<HomingTarget> setTarget = target -> {
			SelectTargetC2SPayload payload = new SelectTargetC2SPayload(target);
			ClientPlayNetworking.send(payload);
		};
		switch(hit.getType()) {
		case ENTITY:
			EntityHitResult entityHit = (EntityHitResult)hit;
			setTarget.accept(new EntityHomingTarget(EntityReference.of(entityHit.getEntity())));
			GeckoBoyArrowTweaks.LOGGER.info("set target to entity " + entityHit.getEntity().getUUID());
			break;
		case BLOCK:
			BlockHitResult blockHit = (BlockHitResult)hit;
			setTarget.accept(new BlockPosHomingTarget(blockHit.getBlockPos()));
			GeckoBoyArrowTweaks.LOGGER.info("set target to block " + blockHit.getBlockPos().toShortString());
			break;
		default:
			break;
		}

		player.swing(InteractionHand.MAIN_HAND);
		ci.setReturnValue(true);
		ci.cancel();
	}
}
