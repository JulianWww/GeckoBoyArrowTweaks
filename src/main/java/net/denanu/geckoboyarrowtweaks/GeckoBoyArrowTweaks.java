package net.denanu.geckoboyarrowtweaks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.denanu.geckoboyarrowtweaks.commands.ModCommands;
import net.denanu.geckoboyarrowtweaks.entity.ModEntities;
import net.denanu.geckoboyarrowtweaks.entity.player.PlayerModifications;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTargetTypes;
import net.denanu.geckoboyarrowtweaks.inventory.ModMenuType;
import net.denanu.geckoboyarrowtweaks.items.ModItems;
import net.denanu.geckoboyarrowtweaks.loot.Events;
import net.denanu.geckoboyarrowtweaks.loot.functions.ModLootItemFunctions;
import net.denanu.geckoboyarrowtweaks.networking.ModPackets;
import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.serializers.ModEntityDataSerializers;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;

public class GeckoBoyArrowTweaks implements ModInitializer {
	public static final String MOD_ID = "geckoboyarrowtweaks";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final boolean DEBUG = true;

	public static Identifier getId(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}

	@Override
	public void onInitialize() {
		ModEntities.load();
		ModDataComponents.load();
		ModMenuType.load();
		ModEntityDataSerializers.load();
		Events.load();
		ModCommands.registerCommands();
		HomingTargetTypes.load();
		PlayerModifications.load();
		ModPackets.load();
		ModLootItemFunctions.load();
		ModItems.load();
	}
}