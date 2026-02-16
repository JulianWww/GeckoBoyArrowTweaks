package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class PotionModifier extends DefaultArrowModifier {
	@Override
	public boolean onHitBlock(BlockHitResult hit, Level level, CustomizableArrow arrow) {
		onHit(hit, level, arrow);
		return false;
	}

	@Override
	public void onHitEntity(EntityHitResult hit, CustomizableArrow arrow) {
		onHit(hit, arrow.level(), arrow);
	}
	protected void onHit(HitResult hit, Level level, CustomizableArrow arrow) {
		ItemStack stack = arrow.getPickupItemStackOrigin();
		stack.set(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
		stack.set(ModDataComponents.ARROW_ADDITION, ModItemUtils.getHolder(Items.AIR));
	}
}
