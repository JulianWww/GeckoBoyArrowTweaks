package net.denanu.geckoboyarrowtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.entity.player.PlayerModifications;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.EmptyHomingTarget;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTargetTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;


@Mixin(Player.class)
public class PlayerMixin {
	@Inject(method="defineSynchedData(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V", at=@At("TAIL"))
	public void defineSynchedData(CallbackInfo ci, @Local SynchedEntityData.Builder builder) {
		builder.define(PlayerModifications.HOMING_TARGET, EmptyHomingTarget.EMPTY_TARGET);
		builder.define(PlayerModifications.QUIVER_ITEM, ItemStack.EMPTY);
	}

	@Inject(method="addAdditionalSaveData(Lnet/minecraft/world/level/storage/ValueOutput;)V", at=@At("TAIL"))
	public void addAdditionalSaveData(CallbackInfo ci, @Local ValueOutput nbt) {
		Player player = (Player)(Object)this;
		ValueOutput namespace = nbt.child(GeckoBoyArrowTweaks.MOD_ID);
		namespace.store("target", HomingTargetTypes.CODEC, player.getEntityData().get(PlayerModifications.HOMING_TARGET));
		namespace.store("quiver", ItemStack.CODEC, player.getEntityData().get(PlayerModifications.QUIVER_ITEM));
	}

	@Inject(method="readAdditionalSaveData(Lnet/minecraft/world/level/storage/ValueInput;)V", at=@At("TAIL"))
	public void readAdditionalSaveData(CallbackInfo ci, @Local ValueInput nbt) {
		Player player = (Player)(Object)this;
		nbt.child(GeckoBoyArrowTweaks.MOD_ID).ifPresent(
				namespace -> {
					SynchedEntityData data = player.getEntityData();
					data.set(PlayerModifications.HOMING_TARGET, namespace.read("target", HomingTargetTypes.CODEC).orElse(EmptyHomingTarget.EMPTY_TARGET));
					data.set(PlayerModifications.QUIVER_ITEM,   namespace.read("quiver", ItemStack.CODEC).orElse(ItemStack.EMPTY));
				}
				);
	}
}
