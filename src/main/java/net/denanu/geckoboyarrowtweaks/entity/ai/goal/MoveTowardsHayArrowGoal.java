package net.denanu.geckoboyarrowtweaks.entity.ai.goal;

import java.util.EnumSet;
import java.util.List;

import org.jspecify.annotations.Nullable;

import com.google.common.collect.Lists;

import net.denanu.geckoboyarrowtweaks.entity.ModEntities;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class MoveTowardsHayArrowGoal extends Goal {
	private final PathfinderMob mob;
	@Nullable
	private Entity target;
	private double wantedX;
	private double wantedY;
	private double wantedZ;
	private final double speedModifier;
	private final float within;
	private float tickCooldown;

	public MoveTowardsHayArrowGoal(PathfinderMob pathfinderMob, double speed, float range) {
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
		if (this.target == null) {
			return false;
		}
		if (this.target.distanceToSqr(this.mob) > this.within * this.within) {
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
		double distance = this.target.distanceToSqr(this.mob);
		return !this.mob.getNavigation().isDone() && distance < this.within * this.within && distance > 2;
	}

	@Override
	public void stop() {
		if (this.target.distanceToSqr(mob) < 3) {
			this.tickCooldown = 150;
		}
		this.target = null;
	}

	@Override
	public void start() {
		this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
	}
}
