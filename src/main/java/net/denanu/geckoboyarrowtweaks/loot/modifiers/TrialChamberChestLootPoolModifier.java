package net.denanu.geckoboyarrowtweaks.loot.modifiers;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.denanu.geckoboyarrowtweaks.loot.functions.ArrowCustomizeShaftFunction;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public class TrialChamberChestLootPoolModifier extends LootPoolModifier {

	public TrialChamberChestLootPoolModifier() {
		super(ImmutableList.of(
				BuiltInLootTables.TRIAL_CHAMBERS_ENTRANCE,
				BuiltInLootTables.TRIAL_CHAMBERS_CORRIDOR_POT,
				BuiltInLootTables.TRIAL_CHAMBERS_REWARD,
				BuiltInLootTables.TRIAL_CHAMBERS_REWARD_COMMON,
				BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS,
				BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_COMMON,
				BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_RARE,
				BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE,
				BuiltInLootTables.TRIAL_CHAMBERS_REWARD_RARE,
				BuiltInLootTables.TRIAL_CHAMBERS_REWARD_UNIQUE,
				BuiltInLootTables.TRIAL_CHAMBERS_SUPPLY
				));
	}

	@Override
	protected List<LootItemFunction> getModifierFunction() {
		return ImmutableList.of(
				ArrowCustomizeShaftFunction.make()
				.addFletching(Items.FEATHER, 10)
				.addFletching(Items.PHANTOM_MEMBRANE, 3)

				.addShaft(Items.STICK, 3)
				.addShaft(Items.BREEZE_ROD, 13)

				.addTip(Items.FLINT, 10)
				.addTip(Items.AMETHYST_SHARD, 4)

				.addAddition(Items.AIR, 10)
				.addAddition(Items.SLIME_BALL, 9)
				.addAddition(Items.GLOWSTONE_DUST, 2)
				.addAddition(Items.GUNPOWDER, 1)

				.build()
				);
	}

}
