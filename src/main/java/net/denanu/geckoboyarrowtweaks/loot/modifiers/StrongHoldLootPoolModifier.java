package net.denanu.geckoboyarrowtweaks.loot.modifiers;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.denanu.geckoboyarrowtweaks.loot.functions.ArrowCustomizeShaftFunction;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public class StrongHoldLootPoolModifier extends LootPoolModifier {

	public StrongHoldLootPoolModifier() {
		super(ImmutableList.of(BuiltInLootTables.STRONGHOLD_CORRIDOR, BuiltInLootTables.STRONGHOLD_CROSSING, BuiltInLootTables.STRONGHOLD_LIBRARY));
	}

	@Override
	protected List<LootItemFunction> getModifierFunction() {
		return ImmutableList.of(
				ArrowCustomizeShaftFunction.make()
				.addFletching(Items.FEATHER, 25)
				.addFletching(Items.PHANTOM_MEMBRANE, 1)

				.addTip(Items.AMETHYST_SHARD, 1)
				.addTip(Items.FLINT, 15)

				.addAddition(Items.AIR, 10)
				.addAddition(Items.SLIME_BALL, 1)
				.addAddition(Items.GLOWSTONE_DUST, 1)
				.build()
				);
	}

}
