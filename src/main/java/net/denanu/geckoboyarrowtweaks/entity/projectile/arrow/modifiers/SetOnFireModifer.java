package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class SetOnFireModifer extends DefaultArrowModifier {
	@Override
	public boolean onHitBlock(BlockHitResult hit, Level level, CustomizableArrow arrow) {
		BlockPos landing_pos = hit.getBlockPos();
		BlockState landing_state = level.getBlockState(landing_pos);
		if (!CampfireBlock.canLight(landing_state) && !CandleBlock.canLight(landing_state) && !CandleCakeBlock.canLight(landing_state)) {
			BlockPos ignition_pos = landing_pos.relative(hit.getDirection());
			if (BaseFireBlock.canBePlacedAt(level, ignition_pos, hit.getDirection())) {
				//this.level().playSound(this.owner, blockPos2, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, this.level().getRandom().nextFloat() * 0.4F + 0.8F);
				BlockState blockState2 = BaseFireBlock.getState(level, ignition_pos);
				level.setBlock(ignition_pos, blockState2, 11);
				level.gameEvent(arrow, GameEvent.BLOCK_PLACE, landing_pos);
			}
		} else {
			level.setBlock(landing_pos, landing_state.setValue(BlockStateProperties.LIT, true), 11);
			level.gameEvent(arrow, GameEvent.BLOCK_CHANGE, landing_pos);
		}
		return false;
	}

	@Override
	public void onHitEntity(EntityHitResult hit, CustomizableArrow arrow) {
		hit.getEntity().igniteForSeconds(5f);
	}
}
