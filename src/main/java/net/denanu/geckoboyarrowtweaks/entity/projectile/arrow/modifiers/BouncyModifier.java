package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MoverType;
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
		arrow.setDeltaMovement(
				arrow.getDeltaMovement().add(normal
						.scale(1.5).multiply(Math.abs(motion.x), Math.abs(motion.y), Math.abs(motion.z))
						).scale(0.9)
				);
		arrow.move(MoverType.SELF, arrow.getDeltaMovement());
		return true;
	}
}
