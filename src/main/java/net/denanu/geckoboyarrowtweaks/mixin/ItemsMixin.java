package net.denanu.geckoboyarrowtweaks.mixin;

import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.sugar.Local;

import net.denanu.geckoboyarrowtweaks.items.CustomizableArrowItem;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

@Mixin(Items.class)
public class ItemsMixin {
	@Inject(method="registerItem(Ljava/lang/String;Ljava/util/function/Function;)Lnet/minecraft/world/item/Item;", at=@At(value="HEAD"), cancellable=true)
	private static void registerItem(CallbackInfoReturnable<Item> ci, @Local String id, @Local Function<Item.Properties, Item> builder) {
		if (id == "arrow") {
			ci.setReturnValue(
					Items.registerItem(
							ResourceKey.create(Registries.ITEM, Identifier.withDefaultNamespace(id)),
							CustomizableArrowItem::new,
							ModItemUtils.getArrowProperties()
							));
			ci.cancel();
		}
	}
}
