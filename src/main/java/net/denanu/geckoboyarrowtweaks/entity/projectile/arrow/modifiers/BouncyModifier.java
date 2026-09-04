package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class BouncyModifier extends DefaultArrowModifier {
	@Override
	public boolean onHitBlock(BlockHitResult hit, Level level, CustomizableArrow arrow) {
		Vec3 motion = arrow.getDeltaMovement();
		RandomSource rnd = level.getRandom();
		double roughness = 0.1;
		if (arrow.getDeltaMovement().lengthSqr() < 0.1) {
			return false;
		}
		Vec3 normal = hit.getDirection().getUnitVec3()
				.add(
						rnd.nextFloat() * roughness,
						rnd.nextFloat() * roughness,
						rnd.nextFloat() * roughness
						);
		
		Vec3 newMotion = motion.add(normal
				.scale(2).multiply(Math.abs(motion.x), Math.abs(motion.y), Math.abs(motion.z))
				).scale(0.9);
		
		arrow.setDeltaMovement(newMotion);
		arrow.setPos(arrow.position().subtract(motion.scale(0.3/motion.length())));
		return true;
	}
}
