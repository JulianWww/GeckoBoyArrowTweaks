package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.EntityHitResult;

public class EntityGlowModifier extends DefaultArrowModifier {
	@Override
	public void onHitEntity(EntityHitResult hit, CustomizableArrow arrow) {
		if (hit.getEntity() instanceof LivingEntity living) {
			MobEffectInstance mobEffectInstance = new MobEffectInstance(MobEffects.GLOWING, 200, 0);
			living.addEffect(mobEffectInstance, arrow.getEffectSource());
		}
	}
}
