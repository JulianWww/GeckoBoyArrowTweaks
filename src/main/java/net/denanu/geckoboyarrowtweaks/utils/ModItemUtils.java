package net.denanu.geckoboyarrowtweaks.utils;

import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;

public class ModItemUtils {
	public static Holder<Item> getHolder(Item item) {
		return BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getId(item)).get();
	}

	public static Item.Properties getArrowProperties() {
		return new Item.Properties()
				.component(ModDataComponents.ARROW_TIP, ModItemUtils.getHolder(Items.FLINT))
				.component(ModDataComponents.ARROW_SHAFT, ModItemUtils.getHolder(Items.STICK))
				.component(ModDataComponents.ARROW_FLETCHING, ModItemUtils.getHolder(Items.FEATHER))
				.component(ModDataComponents.HAS_TIP, true)
				.component(ModDataComponents.ARROW_ADDITION, ModItemUtils.getHolder(Items.AIR))
				.component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
				.component(DataComponents.POTION_DURATION_SCALE, 0.125F);
	}
}