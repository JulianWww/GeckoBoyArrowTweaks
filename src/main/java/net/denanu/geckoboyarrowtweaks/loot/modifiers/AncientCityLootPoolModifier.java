package net.denanu.geckoboyarrowtweaks.loot.modifiers;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.denanu.geckoboyarrowtweaks.loot.functions.ArrowCustomizeShaftFunction;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class AncientCityLootPoolModifier extends LootPoolModifier {

	public AncientCityLootPoolModifier() {
		super(ImmutableList.of(BuiltInLootTables.ANCIENT_CITY));
	}

	@Override
	protected void applyEffects(ResourceKey<LootTable> key, Builder tableBuilder, LootTableSource source,
			Provider registries) {


		tableBuilder.modifyPools(pool -> {
			pool.add(LootItem.lootTableItem(Items.ARROW).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))));
		});

		super.applyEffects(key, tableBuilder, source, registries);
	}

	@Override
	protected List<LootItemFunction> getModifierFunction() {
		return ImmutableList.of(
				ArrowCustomizeShaftFunction.make()
				.addFletching(Items.FEATHER, 10)
				.addFletching(Items.PHANTOM_MEMBRANE, 1)

				.addShaft(Items.STICK, 10)
				.addShaft(Items.BREEZE_ROD, 7)

				.addTip(Items.FLINT, 2)
				.addTip(Items.AMETHYST_SHARD, 2)
				.addTip(Items.ECHO_SHARD, 9)
				.build()
				);
	}

}
