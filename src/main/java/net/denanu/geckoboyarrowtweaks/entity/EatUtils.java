package net.denanu.geckoboyarrowtweaks.entity;

import java.util.Optional;

import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class EatUtils {
	private int eatAnimationTick = 0;
	public Optional<Runnable> startEatingCallback = Optional.empty();
	public Optional<Runnable> stopEatingCallback = Optional.empty();
	
	public EatUtils() {
	}
	
	public void aiStep(Level level) {
		if (level.isClientSide()) {
			if (this.eatAnimationTick == 1 && this.stopEatingCallback.isPresent()) {
				this.stopEatingCallback.get().run();
			}
			this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
		}
	}
	
	public void handleEntityEvent(final byte id) {
		if (id == 10) {
			this.eatAnimationTick = 40;
			if (this.startEatingCallback.isPresent()) {
				this.startEatingCallback.get().run();
			}
		}
	}
	
	public float getHeadEatPositionScale(final float a) {
		if (this.eatAnimationTick <= 0) {
			return 0.0F;
		} else if (this.eatAnimationTick >= 4 && this.eatAnimationTick <= 36) {
			return 1.0F;
		} else {
			return this.eatAnimationTick < 4 ? (this.eatAnimationTick - a) / 4.0F : -(this.eatAnimationTick - 40 - a) / 4.0F;
		}
	}

	public float getHeadEatAngleScale(final float a) {
		float scale = (this.eatAnimationTick - 4 - a) / 32.0F;
		return 75 + 10 * Mth.sin(scale * 28.7F);
	}

	public boolean doEatAngleAnim() {
		return this.eatAnimationTick >= 4 && this.eatAnimationTick <= 36;
	}
}
