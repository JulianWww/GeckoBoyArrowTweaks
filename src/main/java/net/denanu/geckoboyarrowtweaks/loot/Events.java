package net.denanu.geckoboyarrowtweaks.loot;

import net.denanu.geckoboyarrowtweaks.loot.modifiers.AncientCityLootPoolModifier;
import net.denanu.geckoboyarrowtweaks.loot.modifiers.BastionLootPoolModifier;
import net.denanu.geckoboyarrowtweaks.loot.modifiers.EndCityLootPoolModifier;
import net.denanu.geckoboyarrowtweaks.loot.modifiers.JungleTempleLootPoolModifier;
import net.denanu.geckoboyarrowtweaks.loot.modifiers.PillagerOutpostLootPoolModifier;
import net.denanu.geckoboyarrowtweaks.loot.modifiers.StrongHoldLootPoolModifier;
import net.denanu.geckoboyarrowtweaks.loot.modifiers.TrialChamberChestLootPoolModifier;
import net.denanu.geckoboyarrowtweaks.loot.modifiers.TrialChamberDispenserLootPoolModifier;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;

public class Events {
	static {
		LootTableEvents.MODIFY_DROPS.register(new ReplaceWithArrows());
		LootTableEvents.MODIFY.register(new BastionLootPoolModifier());
		LootTableEvents.MODIFY.register(new JungleTempleLootPoolModifier());
		LootTableEvents.MODIFY.register(new PillagerOutpostLootPoolModifier());
		LootTableEvents.MODIFY.register(new TrialChamberChestLootPoolModifier());
		LootTableEvents.MODIFY.register(new TrialChamberDispenserLootPoolModifier());
		LootTableEvents.MODIFY.register(new AncientCityLootPoolModifier());
		LootTableEvents.MODIFY.register(new EndCityLootPoolModifier());
		LootTableEvents.MODIFY.register(new StrongHoldLootPoolModifier());
	}


	public static void load() {

	}
}
