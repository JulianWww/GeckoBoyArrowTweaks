package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing;


import org.jetbrains.annotations.Nullable;

import com.google.common.base.Function;
import com.ibm.icu.impl.Pair;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.denanu.geckoboyarrowtweaks.utils.Newton;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class HomingController {
	public static void tick(CustomizableArrow arrow) {
		HomingTarget target = arrow.getTarget();
		if (!target.isPresent() || !arrow.doHoming()) {
			return;
		}
		double g = arrow.getGravity();
		@Nullable
		Pair<Vec3, Vec3> homingData = target.getAimData(arrow.level());
		if (homingData == null) {
			return;
		}
		Vec3 dist = homingData.first.subtract(arrow.position());
		Vec3 vel = homingData.second;

		double time = getTime(g, dist, vel, arrow.getDeltaMovement().lengthSqr());
		if (time == Double.MAX_VALUE || time < 1) {
			return;
		}
		Vec3 targetVel = getIdealTrajectory(g, dist, vel, time);

		arrow.setDeltaMovement(lerpMotion(targetVel, arrow));
	}

	private static float getYRot(Vec3 vec) {
		return (float)Mth.atan2(vec.x, vec.z);
	}

	private static float getXRot(Vec3 vec) {
		return (float)Mth.atan2(vec.y, vec.horizontalDistance());
	}

	private static float lerpRotation(float strenght, float f, float g) {
		while (g - f < -Math.PI) {
			f -= Math.PI*2;
		}

		while (g - f >= Math.PI) {
			f += Math.PI*2;
		}

		return Mth.lerp(strenght, f, g);
	}

	private static Vec3 lerpMotion(Vec3 targetVel, CustomizableArrow arrow) {
		return Mth.lerp(0.1, arrow.getDeltaMovement(), targetVel);
		/*float currenty = getYRot(arrow.getDeltaMovement());
		float currentx = getXRot(arrow.getDeltaMovement());

		float targety = getYRot(targetVel);
		float targetx = getXRot(targetVel);

		float nexty = lerpRotation(0.2F, currenty, targety);
		float nextx = lerpRotation(0.2F, currentx, targetx);


		float length = (float) arrow.getDeltaMovement().length();
		GeckoBoyArrowTweaks.LOGGER.info(Float.toString(length) + "  " + Float.toString(nexty));
		float vely = Mth.sin(nextx)*length;
		length = length * Math.abs(Mth.cos(nextx));
		float velx = Mth.sin(nexty)*length;
		float velz = Mth.cos(nexty)*length;

		return new Vec3(velx, vely, velz);*/
	}

	private static Vec3 interpolateTargetPos(Vec3 dist, Vec3 vel, double time) {
		return dist.add(vel.scale(time));
	}

	private static Function<Double, Double> timeError(double targetSpeedSquare, double g, Vec3 dist, Vec3 vel) {
		return time -> {
			Vec3 arrowVel = getIdealTrajectory(g, dist, vel, time);
			double value = targetSpeedSquare - arrowVel.lengthSqr();
			return value;
		};
	}

	private static Function<Double, Double> timeErrorDerivative(double targetSpeedSquare, double g, Vec3 dist, Vec3 vel) {
		return time -> {
			double t_cube = time*time*time;
			double t_4 = t_cube*time;
			double deriv = - (
					g*g*t_4
					+ 2*g*vel.y*t_cube
					- 4*(vel.z*dist.z + vel.y*dist.y + vel.x*dist.x)*time
					- 4*dist.lengthSqr()
					)/(2*t_cube);
			return deriv;
		};
	}

	private static double getTime(double g, Vec3 dist, Vec3 vel, double targetSeedSquare) {
		return Math.abs(Newton.solve(1, timeError(targetSeedSquare, g, dist, vel), timeErrorDerivative(targetSeedSquare, g, dist, vel)));
	}

	private static Vec3 getIdealTrajectory(double g, Vec3 dist, Vec3 vel, double time) {
		Vec3 targetPos = interpolateTargetPos(dist, vel, time);
		return targetPos.scale(1/time).add(0, g*time/2, 0);
	}
}
