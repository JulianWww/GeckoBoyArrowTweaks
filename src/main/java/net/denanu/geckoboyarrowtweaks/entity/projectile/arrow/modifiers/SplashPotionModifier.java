package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

import java.util.List;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;

public class SplashPotionModifier extends AbstractAoePotionModifier {

	@Override
	protected void onHitAsPotion(ServerLevel level, HitResult hitResult, CustomizableArrow arrow) {
		PotionContents potionContents = arrow.getPotionContents();
		float durationScale = arrow.getPotionDurationScale();
		Iterable<MobEffectInstance> iterable = potionContents.getAllEffects();
		AABB AABB = arrow.getBoundingBox().move(hitResult.getLocation().subtract(arrow.position())).inflate(4.0, 2.0, 4.0);
		List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, AABB);
		float margin = ProjectileUtil.computeMargin(arrow);
		if (!list.isEmpty()) {
			Entity source = arrow.getEffectSource();

			for (LivingEntity livingEntity : list) {
				if (livingEntity.isAffectedByPotions()) {
					double distance = AABB.distanceToSqr(livingEntity.getBoundingBox().inflate(margin));
					if (distance < 16.0) {
						double effectivity = 1.0 - Math.sqrt(distance) / 4.0;

						for (MobEffectInstance effectInstance : iterable) {
							Holder<MobEffect> effect = effectInstance.getEffect();
							if (effect.value().isInstantenous()) {
								effect.value().applyInstantenousEffect(level, arrow, arrow.getOwner(), livingEntity, effectInstance.getAmplifier(), effectivity);
							} else {
								int duration = effectInstance.mapDuration(ix -> (int)(effectivity * ix * durationScale + 0.5));
								MobEffectInstance durationModifiedEffect = new MobEffectInstance(
										effect, duration, effectInstance.getAmplifier(), effectInstance.isAmbient(), effectInstance.isVisible()
										);
								if (!durationModifiedEffect.endsWithin(20)) {
									livingEntity.addEffect(durationModifiedEffect, source);
								}
							}
						}
					}
				}
			}
		}
	}
}
