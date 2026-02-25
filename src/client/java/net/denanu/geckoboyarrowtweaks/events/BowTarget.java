package net.denanu.geckoboyarrowtweaks.events;

import java.util.Optional;

import net.denanu.geckoboyarrowtweaks.data.tag.ModItemTags;
import net.denanu.geckoboyarrowtweaks.entity.player.PlayerModifications;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.BlockPosHomingTarget;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.EntityHomingTarget;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTarget;
import net.denanu.geckoboyarrowtweaks.networking.c2s.SelectTargetC2SPayload;
import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
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

public class BowTarget {
	private static void sendPacket(HomingTarget target) {
		SelectTargetC2SPayload payload = new SelectTargetC2SPayload(target);
		ClientPlayNetworking.send(payload);
	}

	private static Optional<ItemStack> isValidWeapon(ItemStack stack) {
		return stack.is(ModItemTags.HOMING_ARROW_CAPABLE_WEAPONS) ? Optional.of(stack) : Optional.empty();
	}

	private static Optional<ItemStack> getWeapon(LocalPlayer player) {
		return isValidWeapon(player.getMainHandItem()).or(() -> isValidWeapon(player.getOffhandItem()));
	}

	private static boolean attack(Minecraft client, LocalPlayer player, int clickCount) {
		Optional<ItemStack> weapon = getWeapon(player);
		if (weapon.isEmpty()) {
			return false;
		}
		ItemStack arrow = player.getProjectile(weapon.get());
		if (!arrow.is(Items.ARROW) || !arrow.get(ModDataComponents.ARROW_TIP).is(ModItemUtils.getHolder(Items.ECHO_SHARD))) {
			return false;
		}
		HitResult hit = PlayerModifications.HOMING_TARGET_RANGE.getClosesetHit(player, 1, target -> EntitySelector.LIVING_ENTITY_STILL_ALIVE.test(target) && EntitySelector.NO_SPECTATORS.test(target));
		if (hit == null) {
			return false;
		}

		switch(hit.getType()) {
		case ENTITY:
			EntityHitResult entityHit = (EntityHitResult)hit;
			sendPacket(new EntityHomingTarget(EntityReference.of(entityHit.getEntity())));
			break;
		case BLOCK:
			BlockHitResult blockHit = (BlockHitResult)hit;
			sendPacket(new BlockPosHomingTarget(blockHit.getBlockPos()));
			break;
		default:
			break;
		}

		player.swing(InteractionHand.MAIN_HAND);
		return true;
	}

	public static void load() {
		ClientPreAttackCallback.EVENT.register(BowTarget::attack);
	}
}
