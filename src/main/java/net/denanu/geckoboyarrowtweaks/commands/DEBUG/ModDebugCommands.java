package net.denanu.geckoboyarrowtweaks.commands.DEBUG;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ModDebugCommands {
	public static LiteralArgumentBuilder<CommandSourceStack> build() {
		return Commands.literal("debug")
				.then(SetTargetCommand.build());
	}
}
