package net.denanu.geckoboyarrowtweaks.loot.modifiers;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.denanu.geckoboyarrowtweaks.loot.functions.ArrowCustomizeShaftFunction;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public class BastionLootPoolModifier extends LootPoolModifier {

	public BastionLootPoolModifier() {
		super(ImmutableList.of(BuiltInLootTables.BASTION_BRIDGE, BuiltInLootTables.BASTION_HOGLIN_STABLE, BuiltInLootTables.BASTION_OTHER));
	}

	@Override
	protected List<LootItemFunction> getModifierFunction() {
		return List.of(
				ArrowCustomizeShaftFunction.make()
				.addShaft(Items.STICK, 6)
				.addShaft(Items.BLAZE_ROD, 4)
				.build()
				);
	}

}
