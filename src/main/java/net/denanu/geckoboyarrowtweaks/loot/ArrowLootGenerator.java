package net.denanu.geckoboyarrowtweaks.loot;

import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ArrowLootGenerator {
	public static WeightedList<Holder<Item>> POTION_TYPES = new WeightedList.Builder<Holder<Item>>()
			.add(ModItemUtils.getHolder(Items.POTION), 10)
			.add(ModItemUtils.getHolder(Items.SPLASH_POTION), 3)
			.build();

	public static ItemStack makeArrowStack(ItemStack source, RandomSource rnd) {
		if (source.is(Items.SPECTRAL_ARROW)) {
			return makeSpectralArrow(source);
		}
		if (source.is(Items.TIPPED_ARROW)) {
			return makeTippedArrow(source, rnd);
		}
		return source;
	}

	private static ItemStack makeSpectralArrow(ItemStack source) {
		ItemStack stack = new ItemStack(Items.ARROW, source.getCount());
		stack.set(ModDataComponents.ARROW_ADDITION, ModItemUtils.getHolder(Items.GLOWSTONE_DUST));
		return stack;
	}

	private static ItemStack makeTippedArrow(ItemStack orignal, RandomSource rnd) {
		ItemStack result = new ItemStack(Items.ARROW, orignal.getCount());
		result.set(DataComponents.POTION_CONTENTS, orignal.get(DataComponents.POTION_CONTENTS));
		result.set(DataComponents.POTION_DURATION_SCALE, orignal.get(DataComponents.POTION_DURATION_SCALE));
		result.set(ModDataComponents.ARROW_ADDITION, ArrowLootGenerator.POTION_TYPES.getRandom(rnd).get());
		return result;
	}
}
