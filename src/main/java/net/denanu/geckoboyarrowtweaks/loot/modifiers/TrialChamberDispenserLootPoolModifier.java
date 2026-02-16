package net.denanu.geckoboyarrowtweaks.loot.modifiers;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.denanu.geckoboyarrowtweaks.loot.functions.ArrowCustomizeShaftFunction;
import net.denanu.geckoboyarrowtweaks.loot.functions.ArrowPotionEffectFunction;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public class TrialChamberDispenserLootPoolModifier extends LootPoolModifier {

	public TrialChamberDispenserLootPoolModifier() {
		super(ImmutableList.of(
				BuiltInLootTables.TRIAL_CHAMBERS_CHAMBER_DISPENSER,
				BuiltInLootTables.TRIAL_CHAMBERS_CORRIDOR_DISPENSER
				));
	}

	@Override
	protected List<LootItemFunction> getModifierFunction() {
		return ImmutableList.of(
				ArrowCustomizeShaftFunction.make()
				.addFletching(Items.FEATHER, 1)
				.addFletching(Items.PHANTOM_MEMBRANE, 1)

				.addShaft(Items.STICK, 1)
				.addShaft(Items.BREEZE_ROD, 1)

				.addTip(Items.FLINT, 10)
				.addTip(Items.AMETHYST_SHARD, 4)

				.addAddition(Items.POTION, 10)
				.addAddition(Items.SLIME_BALL, 3)
				.addAddition(Items.GLOWSTONE_DUST, 1)
				.addAddition(Items.SPLASH_POTION, 20)
				.addAddition(Items.LINGERING_POTION, 1)

				.build(),
				ArrowPotionEffectFunction.make()
				.addEffect(Potions.POISON, 10)
				.addEffect(Potions.LONG_POISON, 1)
				.addEffect(Potions.STRONG_POISON, 1)

				.addEffect(Potions.HARMING, 15)
				.addEffect(Potions.STRONG_HARMING, 3)

				.addEffect(Potions.WEAKNESS, 5)
				.addEffect(Potions.LONG_WEAKNESS, 3)

				.addEffect(Potions.SLOWNESS, 5)
				.addEffect(Potions.LONG_SLOWNESS, 3)
				.addEffect(Potions.STRONG_SLOWNESS, 3)

				.build()
				);
	}

}
