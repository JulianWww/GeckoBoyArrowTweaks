package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

import java.util.function.Predicate;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public abstract class AbstractAoePotionModifier extends PotionModifier {
	public static final Predicate<LivingEntity> WATER_SENSITIVE_OR_ON_FIRE_PREDICATE = livingEntity -> livingEntity.isSensitiveToWater() || livingEntity.isOnFire();


	@Override
	public boolean onHitBlock(BlockHitResult hit, Level level, CustomizableArrow arrow) {
		super.onHitBlock(hit, level, arrow);
		if (!level.isClientSide()) {
			Direction direction = hit.getDirection();
			BlockPos hitPos = hit.getBlockPos();
			BlockPos hitFacingPos = hitPos.relative(direction);
			PotionContents potionContents = arrow.getPotionContents();
			if (potionContents.is(Potions.WATER)) {
				this.dowseFire(hitFacingPos, level, arrow);
				this.dowseFire(hitPos, level, arrow);

				for (Direction ajacents : Direction.Plane.HORIZONTAL) {
					this.dowseFire(hitFacingPos.relative(ajacents), level, arrow);
				}
			}
		}
		return false;
	}

	@Override
	protected void onHit(HitResult hit, Level level, CustomizableArrow arrow) {
		if (!arrow.getIsFirstImpact()) {
			return;
		}
		if (level instanceof ServerLevel serverLevel) {
			PotionContents potion = arrow.getPotionContents();
			if (potion.is(Potions.WATER)) {
				this.onHitAsWater(serverLevel, arrow);
			} else if (potion.hasEffects()) {
				this.onHitAsPotion(serverLevel, hit, arrow);
			}

			int i = potion.potion().isPresent() && potion.potion().get().value().hasInstantEffects() ? 2007 : 2002;
			serverLevel.levelEvent(i, getEffectPosition(hit, arrow), arrow.getColor());
			//arrow.discard();
		}
		super.onHit(hit, level, arrow);
	}

	private static BlockPos getEffectPosition(HitResult hit, CustomizableArrow arrow) {
		if (hit instanceof BlockHitResult blockHit && blockHit.getDirection() == Direction.UP) {
			return arrow.getOnPos().above();
		}
		return arrow.getOnPos();
	}

	protected abstract void onHitAsPotion(ServerLevel serverLevel, HitResult hit, CustomizableArrow arrow);

	private void onHitAsWater(ServerLevel level, CustomizableArrow arrow) {
		AABB aABB = arrow.getBoundingBox().inflate(4.0, 2.0, 4.0);

		for (LivingEntity effectedEntity : level.getEntitiesOfClass(LivingEntity.class, aABB, WATER_SENSITIVE_OR_ON_FIRE_PREDICATE)) {
			double d = arrow.distanceToSqr(effectedEntity);
			if (d < 16.0) {
				if (effectedEntity.isSensitiveToWater()) {
					effectedEntity.hurtServer(level, arrow.damageSources().indirectMagic(arrow, arrow.getOwner()), 1.0F);
				}

				if (effectedEntity.isOnFire() && effectedEntity.isAlive()) {
					effectedEntity.extinguishFire();
				}
			}
		}

		for (Axolotl axolotl : level.getEntitiesOfClass(Axolotl.class, aABB)) {
			axolotl.rehydrate();
		}
	}

	private void dowseFire(BlockPos blockPos, Level level, CustomizableArrow arrow) {
		BlockState blockState = level.getBlockState(blockPos);
		if (blockState.is(BlockTags.FIRE)) {
			level.destroyBlock(blockPos, false, arrow);
		} else if (AbstractCandleBlock.isLit(blockState)) {
			AbstractCandleBlock.extinguish(null, blockState, level, blockPos);
		} else if (CampfireBlock.isLitCampfire(blockState)) {
			level.levelEvent(null, 1009, blockPos, 0);
			CampfireBlock.dowse(arrow.getOwner(), level, blockPos, blockState);
			level.setBlockAndUpdate(blockPos, blockState.setValue(CampfireBlock.LIT, false));
		}
	}
}
