package net.denanu.geckoboyarrowtweaks.loot.functions;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.denanu.geckoboyarrowtweaks.data.tag.ModItemTags;
import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class ArrowPotionEffectFunction extends LootItemConditionalFunction {
	public static final MapCodec<ArrowPotionEffectFunction> CODEC = RecordCodecBuilder.mapCodec(
			instance -> commonFields(instance)
			.and(
					WeightedList.codec(Potion.CODEC).fieldOf("effect").forGetter(fun -> fun.effects)
					)
			.apply(instance, ArrowPotionEffectFunction::new)
			);

	private WeightedList<Holder<Potion>> effects;

	protected ArrowPotionEffectFunction(List<LootItemCondition> list, WeightedList<Holder<Potion>> effects) {
		super(list);
		this.effects = effects;
	}

	@Override
	public LootItemFunctionType<ArrowPotionEffectFunction> getType() {
		return ModLootItemFunctions.ARROW_POTION_EFFECTS;
	}

	@Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		if (!stack.is(ItemTags.ARROWS) || !stack.get(ModDataComponents.ARROW_ADDITION).is(ModItemTags.POTIONS)) {
			return stack;
		}
		stack.update(DataComponents.POTION_CONTENTS, PotionContents.EMPTY, this.effects.getRandom(context.getRandom()).get(), PotionContents::withPotion);
		return stack;
	}

	public static Builder make() {
		return new Builder();
	}

	public static class Builder {
		public WeightedList.Builder<Holder<Potion>> effects = WeightedList.builder();
		public ImmutableList.Builder<LootItemCondition> conditions = ImmutableList.builder();

		public Builder addEffect(Holder<Potion> effect, int weight) {
			this.effects.add(effect, weight);
			return this;
		}

		public ArrowPotionEffectFunction build() {
			return new ArrowPotionEffectFunction(
					conditions.build(), effects.build()
					);
		}
	}

}
