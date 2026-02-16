package net.denanu.geckoboyarrowtweaks.blocks;

import com.mojang.serialization.MapCodec;

import net.denanu.geckoboyarrowtweaks.inventory.FletchingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class FletchingTableBlock extends CraftingTableBlock {
	public static final MapCodec<FletchingTableBlock> CODEC = simpleCodec(FletchingTableBlock::new);
	private static final Component CONTAINER_TITLE = Component.translatable("container.fletching");

	@Override
	public MapCodec<FletchingTableBlock> codec() {
		return CODEC;
	}

	public FletchingTableBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos blockPos) {
		return new SimpleMenuProvider((i, inventory, player) -> new FletchingMenu(i, inventory, ContainerLevelAccess.create(level, blockPos)), CONTAINER_TITLE);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
		if (!level.isClientSide()) {
			player.openMenu(blockState.getMenuProvider(level, blockPos));
			player.awardStat(Stats.INTERACT_WITH_SMITHING_TABLE);
		}

		return InteractionResult.SUCCESS;
	}
}
