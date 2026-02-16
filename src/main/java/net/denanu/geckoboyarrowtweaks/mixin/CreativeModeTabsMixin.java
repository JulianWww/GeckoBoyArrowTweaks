package net.denanu.geckoboyarrowtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;

@Mixin(CreativeModeTabs.class)
public class CreativeModeTabsMixin {
	@Inject(method="generatePotionEffectTypes(Lnet/minecraft/world/item/CreativeModeTab$Output;Lnet/minecraft/core/HolderLookup;Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/CreativeModeTab$TabVisibility;Lnet/minecraft/world/flag/FeatureFlagSet;)V",
			at=@At("HEAD"), cancellable=true)
	private static void generatePotionEffectTypes(CallbackInfo ci, @Local CreativeModeTab.Output out, @Local HolderLookup<Potion> potions, @Local Item item, @Local CreativeModeTab.TabVisibility tab, @Local FeatureFlagSet flags) {
		if (item != Items.TIPPED_ARROW) {
			return;
		}
		potions.listElements()
		.filter(reference -> reference.value().isEnabled(flags))
		.map(reference -> {
			ItemStack stack = PotionContents.createItemStack(Items.ARROW, reference);
			stack.set(ModDataComponents.ARROW_ADDITION, ModItemUtils.getHolder(Items.POTION));
			return stack;
		})
		.forEach(itemStack -> out.accept(itemStack, tab));
		ci.cancel();
	}
}
