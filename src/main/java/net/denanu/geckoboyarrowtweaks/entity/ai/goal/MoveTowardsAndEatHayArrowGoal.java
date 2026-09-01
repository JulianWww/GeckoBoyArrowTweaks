package net.denanu.geckoboyarrowtweaks.entity.ai.goal;

import java.util.EnumSet;
import java.util.List;

import org.jspecify.annotations.Nullable;

import com.google.common.collect.Lists;

import net.denanu.geckoboyarrowtweaks.entity.ModEntities;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class MoveTowardsAndEatHayArrowGoal extends Goal {
	private static final int EAT_ANIMATION_TICKS = 40;
	private static final int COOLDOWN = 50;
	private static final float TARGET_DISTANCE_SQUARE = 1f;
	private static final double NAV_SWITCH_DISTANCE = 4;
	private final PathfinderMob mob;
	@Nullable
	private Entity target;
	private double wantedX;
	private double wantedY;
	private double wantedZ;
	private final double speedModifier;
	private final float within;
	private float tickCooldown;
	private int eatAnimationTick;
	private boolean isEating;

	public MoveTowardsAndEatHayArrowGoal(PathfinderMob pathfinderMob, double speed, float range) {
		this.mob = pathfinderMob;
		this.speedModifier = speed;
		this.within = range;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		this.tickCooldown--;
		if (this.tickCooldown > 0) {
			return false;
		}
		this.tickCooldown = 10;

		this.target = this.getTarget();
		if ((this.target == null) || (this.target.distanceToSqr(this.mob) > this.within * this.within)) {
			return false;
		}
		Vec3 vec3 = this.target.getEyePosition();//.getPosTowards(this.mob, 16, 7, this.target.position(), (float) (Math.PI / 2));
		if (vec3 == null) {
			return false;
		}
		this.wantedX = vec3.x;
		this.wantedY = vec3.y;
		this.wantedZ = vec3.z;
		return true;
	}

	private @Nullable Entity getTarget() {
		List<Entity> entities = Lists.<Entity>newArrayList();
		this.mob.level().getEntities(ModEntities.CUSTOMIZABLE_ARROW, new AABB(this.mob.position(), this.mob.position()).inflate(this.within/2, 8, this.within/2), CustomizableArrow::attractsAnimals, entities, 1);
		if (entities.isEmpty()) {
			return null;
		}
		return entities.getFirst();
	}

	@Override
	public boolean canContinueToUse() {		
		return this.isNavigating();
	}

	private boolean isNavigating() {
		double distance = this.target.distanceToSqr(this.mob);
		boolean alive = this.target.isAlive();
		boolean nav = !this.mob.getNavigation().isDone() && distance < this.within * this.within;
		boolean walk = NAV_SWITCH_DISTANCE > distance;
		return alive && (nav || walk);
	}

	@Override
	public void stop() {
		if (this.target.distanceToSqr(mob) < 3) {
			this.tickCooldown = COOLDOWN;
		}
		this.target = null;
	}

	@Override
	public void start() {
		this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
	}
	
	@Override
	public void tick() {
		if (this.mob.getNavigation().isDone() && !this.isEating) {
			this.mob.getMoveControl().setWantedPosition(wantedX, wantedY, wantedZ, speedModifier);
		}
		if (this.canEat()) {
			this.eat();
		}
		else {
			this.isEating = false;
		}
	}
	
	private boolean canEat() {
		double dist = this.mob.distanceToSqr(this.target);
		return dist < MoveTowardsAndEatHayArrowGoal.TARGET_DISTANCE_SQUARE;
	}
	
	private void eat() {
		this.mob.getNavigation().stop();
		if (!this.isEating) {
			this.eatAnimationTick = this.adjustedTickDelay(EAT_ANIMATION_TICKS);
			this.mob.level().broadcastEntityEvent(this.mob, (byte)10);
			this.isEating = true;
		}
		
		this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
		if (this.eatAnimationTick == this.adjustedTickDelay(4) && !this.mob.level().isClientSide()) {
			this.target.kill((ServerLevel)this.mob.level());
		}
		if (this.eatAnimationTick > 0) {
			this.isEating = true;
		}
		else {
			this.isEating = false;
		}
	}
	
	public int getEatAnimationTick() {
		return this.eatAnimationTick;
	}
}
