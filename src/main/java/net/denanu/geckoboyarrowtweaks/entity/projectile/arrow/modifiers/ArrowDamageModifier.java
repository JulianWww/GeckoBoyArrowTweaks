package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.world.phys.EntityHitResult;

public abstract class ArrowDamageModifier extends DefaultArrowModifier {
	@Override
	public void onHitEntity(EntityHitResult hit, CustomizableArrow arrow) {
		arrow.setBaseDamage(
				arrow.getBaseDamage() * getDamageModifier()
				);
	}

	protected abstract double getDamageModifier();
}
