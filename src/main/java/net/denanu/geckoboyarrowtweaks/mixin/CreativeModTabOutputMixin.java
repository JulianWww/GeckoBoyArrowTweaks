package net.denanu.geckoboyarrowtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

@Mixin(CreativeModeTab.Output.class)
public interface CreativeModTabOutputMixin {
	@Inject(method="accept(Lnet/minecraft/world/level/ItemLike;)V", at=@At("HEAD"), cancellable=true)
	public default void accept(CallbackInfo ci, @Local ItemLike item) {
		if (item != Items.SPECTRAL_ARROW) {
			return;
		}
		CreativeModeTab.Output out = (CreativeModeTab.Output)this;
		ItemStack stack = new ItemStack(Items.ARROW);
		stack.set(ModDataComponents.ARROW_ADDITION, ModItemUtils.getHolder(Items.GLOWSTONE_DUST));
		out.accept(stack);

		ci.cancel();
	}
}
