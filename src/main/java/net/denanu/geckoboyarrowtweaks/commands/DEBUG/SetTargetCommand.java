package net.denanu.geckoboyarrowtweaks.commands.DEBUG;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;

public class SetTargetCommand {
	public static EntityReference<Entity> TARGET = null;
	public static LiteralArgumentBuilder<CommandSourceStack> build() {
		return Commands.literal("setTarget")
				.then(Commands.argument("target", EntityArgument.entity())
						.executes(SetTargetCommand::run)
						);
	}

	private static int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		TARGET = EntityReference.of(EntityArgument.getEntity(context, "target"));
		return 1;

	}
}
