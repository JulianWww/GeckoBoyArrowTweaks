package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class DefaultArrowModifier {
	public DefaultArrowModifier modify(ItemStack stack) {
		return this;
	}

	public float speedModifier() {
		return 1f;
	};

	public boolean attractsAnimals() {
		return false;
	}

	public boolean isUnderwaterArrow() {
		return false;
	}

	public boolean hasGravity() {
		return true;
	}

	public boolean isHoming() {
		return false;
	}

	public boolean onHitBlock(BlockHitResult hit, Level level, CustomizableArrow arrow) {
		return false;
	}

	public void onHitEntity(EntityHitResult hit, CustomizableArrow arrow) {
	}

	public byte piercing() {
		return 0;
	}
}
