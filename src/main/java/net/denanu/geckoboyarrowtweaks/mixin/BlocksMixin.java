package net.denanu.geckoboyarrowtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.sugar.Local;

import net.denanu.geckoboyarrowtweaks.blocks.FletchingTableBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

@Mixin(Blocks.class)
public class BlocksMixin {
	@Inject(method="register(Ljava/lang/String;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", at=@At(value="HEAD"), cancellable=true)
	private static void register(CallbackInfoReturnable<Block> ci, @Local String id, @Local BlockBehaviour.Properties properties) {
		if (id == "fletching_table") {
			ci.setReturnValue(Blocks.register(ResourceKey.create(Registries.BLOCK, Identifier.withDefaultNamespace(id)), FletchingTableBlock::new, properties));
			ci.cancel();
		}
	}
}
