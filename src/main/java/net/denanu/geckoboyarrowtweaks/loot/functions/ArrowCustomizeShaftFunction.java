package net.denanu.geckoboyarrowtweaks.loot.functions;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class ArrowCustomizeShaftFunction extends LootItemConditionalFunction {
	private static Reference<Item> AIR_KEY = Items.AIR.builtInRegistryHolder();
	public static final MapCodec<ArrowCustomizeShaftFunction> CODEC = RecordCodecBuilder.mapCodec(
			instance -> commonFields(instance)
			.and(
					instance.group(
							WeightedList.codec(Item.CODEC).optionalFieldOf("shaft").forGetter(fun -> fun.shaftMods),
							WeightedList.codec(Item.CODEC).optionalFieldOf("tip").forGetter(fun -> fun.tipMods),
							WeightedList.codec(Item.CODEC).optionalFieldOf("fletching").forGetter(fun -> fun.fletchingMods),
							WeightedList.codec(Item.CODEC).optionalFieldOf("addition").forGetter(fun -> fun.additionMods)
							)
					)
			.apply(instance, ArrowCustomizeShaftFunction::new)
			);

	private Optional<WeightedList<Holder<Item>>> shaftMods;
	private Optional<WeightedList<Holder<Item>>> tipMods;
	private Optional<WeightedList<Holder<Item>>> fletchingMods;
	private Optional<WeightedList<Holder<Item>>> additionMods;

	protected ArrowCustomizeShaftFunction(List<LootItemCondition> list, Optional<WeightedList<Holder<Item>>> shaftMods, Optional<WeightedList<Holder<Item>>> tipMods,
			Optional<WeightedList<Holder<Item>>> fletchingMods, Optional<WeightedList<Holder<Item>>> additionMods) {
		super(list);
		this.shaftMods = shaftMods;
		this.tipMods = tipMods;
		this.fletchingMods = fletchingMods;
		this.additionMods = additionMods;
	}

	@Override
	public LootItemFunctionType<ArrowCustomizeShaftFunction> getType() {
		return ModLootItemFunctions.CUSTOMIZE_ARROWS;
	}

	@Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		if (!stack.is(ItemTags.ARROWS)) {
			return stack;
		}
		applyModifiers(stack, context, this.additionMods, ModDataComponents.ARROW_ADDITION);
		applyTipModifiers(stack, context);
		applyModifiers(stack, context, this.shaftMods, ModDataComponents.ARROW_SHAFT);
		applyModifiers(stack, context, this.fletchingMods, ModDataComponents.ARROW_FLETCHING);
		return stack;
	}

	protected void applyTipModifiers(ItemStack stack, LootContext context) {
		if (this.tipMods.isEmpty()) {
			return;
		}
		Holder<Item> mod = this.tipMods.get().getRandomOrThrow(context.getRandom());
		if (mod.is(AIR_KEY)) {
			stack.set(ModDataComponents.HAS_TIP, false);
			return;
		}
		stack.set(ModDataComponents.ARROW_TIP, mod);
	}

	protected void applyModifiers(ItemStack stack, LootContext context, Optional<WeightedList<Holder<Item>>> mods, DataComponentType<Holder<Item>> component) {
		if (mods.isEmpty()) {
			return;
		}

		Holder<Item> mod = mods.get().getRandomOrThrow(context.getRandom());
		stack.set(component, mod);
	}

	public static Builder make() {
		return new Builder();
	}

	public static class Builder {
		public WeightedList.Builder<Holder<Item>> shaft = WeightedList.builder();
		public WeightedList.Builder<Holder<Item>> tip = WeightedList.builder();
		public WeightedList.Builder<Holder<Item>> fletching = WeightedList.builder();
		public WeightedList.Builder<Holder<Item>> additions = WeightedList.builder();
		public ImmutableList.Builder<LootItemCondition> conditions = ImmutableList.builder();

		public ArrowCustomizeShaftFunction build() {
			return new ArrowCustomizeShaftFunction(this.conditions.build(), buildOrEmpty(shaft), buildOrEmpty(tip), buildOrEmpty(fletching), buildOrEmpty(additions));
		}

		public Builder addShaft(Item item, int weight) {
			shaft.add(ModItemUtils.getHolder(item), weight);
			return this;
		}
		public Builder addTip(Item item, int weight) {
			tip.add(ModItemUtils.getHolder(item), weight);
			return this;
		}
		public Builder addFletching(Item item, int weight) {
			fletching.add(ModItemUtils.getHolder(item), weight);
			return this;
		}
		public Builder addAddition(Item item, int weight) {
			additions.add(ModItemUtils.getHolder(item), weight);
			return this;
		}

		private Optional<WeightedList<Holder<Item>>> buildOrEmpty(WeightedList.Builder<Holder<Item>> list) {
			WeightedList<Holder<Item>> built = list.build();
			return built.isEmpty() ? Optional.empty() : Optional.of(built);
		}
	}

}
