package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.HitResult;

public class LingeringPotionModifier extends AbstractAoePotionModifier {
	@Override
	protected void onHitAsPotion(ServerLevel level, HitResult hitResult, CustomizableArrow arrow) {
		AreaEffectCloud areaEffectCloud = new AreaEffectCloud(arrow.level(), arrow.getX(), arrow.getY(), arrow.getZ());
		if (arrow.getOwner() instanceof LivingEntity livingEntity) {
			areaEffectCloud.setOwner(livingEntity);
		}

		areaEffectCloud.setRadius(3.0F);
		areaEffectCloud.setRadiusOnUse(-0.5F);
		areaEffectCloud.setDuration(600);
		areaEffectCloud.setWaitTime(10);
		areaEffectCloud.setRadiusPerTick(-areaEffectCloud.getRadius() / areaEffectCloud.getDuration());
		areaEffectCloud.applyComponentsFromItemStack(arrow.getPickupItemStackOrigin());
		level.addFreshEntity(areaEffectCloud);
	}
}
