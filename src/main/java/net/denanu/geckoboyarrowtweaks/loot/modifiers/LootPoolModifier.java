package net.denanu.geckoboyarrowtweaks.loot.modifiers;

import java.util.Collection;
import java.util.List;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents.Modify;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public abstract class LootPoolModifier implements Modify {
	private final Collection<ResourceKey<LootTable>> pools;

	protected LootPoolModifier(Collection<ResourceKey<LootTable>> pools) {
		this.pools = pools;
	}

	@Override
	public void modifyLootTable(ResourceKey<LootTable> key, Builder tableBuilder, LootTableSource source,
			Provider registries) {
		if (this.pools.contains(key)) {
			applyEffects(key, tableBuilder, source, registries);
		}

	}

	protected void applyEffects(ResourceKey<LootTable> key, Builder tableBuilder, LootTableSource source,
			Provider registries) {
		tableBuilder.modifyPools(pools -> {
			getModifierFunction().forEach(fun -> pools.apply(fun));
		});
	}

	protected abstract List<LootItemFunction> getModifierFunction();
}
