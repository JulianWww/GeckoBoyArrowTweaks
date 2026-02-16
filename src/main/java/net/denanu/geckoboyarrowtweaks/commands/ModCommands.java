package net.denanu.geckoboyarrowtweaks.commands;

import com.mojang.brigadier.CommandDispatcher;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.commands.DEBUG.ModDebugCommands;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands.CommandSelection;

public class ModCommands implements CommandRegistrationCallback {
	public static void registerCommands() {
		CommandRegistrationCallback.EVENT.register(new ModCommands());
	}

	@Override
	public void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registryAccess,
			CommandSelection environment) {
		if (GeckoBoyArrowTweaks.DEBUG) {
			dispatcher.register(ModDebugCommands.build());
		}

	}
}
