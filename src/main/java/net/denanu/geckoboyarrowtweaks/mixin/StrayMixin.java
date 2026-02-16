package net.denanu.geckoboyarrowtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.sugar.Local;

import net.denanu.geckoboyarrowtweaks.items.CustomizableArrowItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.skeleton.Stray;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potions;

@Mixin(Stray.class)
public class StrayMixin {
	@Inject(method="getArrow(Lnet/minecraft/world/item/ItemStack;FLnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;", at=@At("HEAD"))
	void getArrow(CallbackInfoReturnable<AbstractArrow> ci, @Local(ordinal=0) ItemStack arrowItem) {
		CustomizableArrowItem.addSkeletonEffects((Entity)(Object)this, Potions.SLOWNESS, arrowItem);
	}
}
