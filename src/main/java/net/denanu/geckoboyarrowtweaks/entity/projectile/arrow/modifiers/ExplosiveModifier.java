package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ExplosiveModifier extends DefaultArrowModifier {
	private static ExplosiveModifier FIRE_EXPLOSION_MODIFIER = new ExplosiveModifier(true);
	private boolean fire = false;

	public ExplosiveModifier() {
		this(false);
	}

	private ExplosiveModifier(boolean fire) {
		this.fire = fire;
	}

	@Override
	public DefaultArrowModifier modify(ItemStack stack) {
		boolean hasBlazeShaft = stack.get(ModDataComponents.ARROW_SHAFT).is(ModItemUtils.getHolder(Items.BLAZE_ROD));
		if (hasBlazeShaft) {
			return FIRE_EXPLOSION_MODIFIER;
		}
		return super.modify(stack);
	}

	@Override
	public void onHitEntity(EntityHitResult hit, CustomizableArrow arrow) {
		doExplosion(arrow.level(), arrow);
	}

	@Override
	public boolean onHitBlock(BlockHitResult hit, Level level, CustomizableArrow arrow) {
		doExplosion(level, arrow);
		return false;
	}

	private void doExplosion(Level leve, CustomizableArrow arrow) {
		DamageSource damageSource2 = arrow.damageSources().explosion(arrow, arrow.getOwner());
		leve.explode(arrow, damageSource2, null, arrow.getX(), arrow.getY(), arrow.getZ(), 1.0F, this.fire, Level.ExplosionInteraction.MOB);
		arrow.discard();
	}
}
