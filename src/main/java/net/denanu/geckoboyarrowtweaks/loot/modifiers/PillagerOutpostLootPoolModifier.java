package net.denanu.geckoboyarrowtweaks.loot.modifiers;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.denanu.geckoboyarrowtweaks.loot.functions.ArrowCustomizeShaftFunction;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public class PillagerOutpostLootPoolModifier extends LootPoolModifier {

	public PillagerOutpostLootPoolModifier() {
		super(ImmutableList.of(BuiltInLootTables.PILLAGER_OUTPOST));
	}

	@Override
	protected List<LootItemFunction> getModifierFunction() {
		return ImmutableList.of(
				ArrowCustomizeShaftFunction.make()
				.addFletching(Items.FEATHER, 25)
				.addFletching(Items.PHANTOM_MEMBRANE, 1)

				.addTip(Items.AIR, 1)
				.addTip(Items.AMETHYST_SHARD, 1)
				.addTip(Items.FLINT, 15)

				.addAddition(Items.AIR, 10)
				.addAddition(Items.SLIME_BALL, 1)
				.addAddition(Items.GUNPOWDER, 1)
				.build()
				);
	}

}
