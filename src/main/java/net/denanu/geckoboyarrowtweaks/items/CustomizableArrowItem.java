package net.denanu.geckoboyarrowtweaks.items;

import org.jspecify.annotations.Nullable;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

public class CustomizableArrowItem extends ArrowItem implements ProjectileItem {
	public CustomizableArrowItem(Properties properties) {
		super(properties);
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity livingEntity, @Nullable ItemStack itemStack2) {
		return new CustomizableArrow(level, livingEntity, itemStack.copyWithCount(1), itemStack2);
	}

	@Override
	public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
		CustomizableArrow arrow = new CustomizableArrow(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1), null);
		arrow.pickup = AbstractArrow.Pickup.ALLOWED;
		return arrow;
	}

	/*private static String getPartNameMod(ItemStack stack, DataComponentType<Holder<Item>> component, String fallback) {
		if (stack.hasNonDefault(component)) {
			return stack.get(component)  .unwrapKey().map(key -> key.identifier().toLanguageKey()).get();
		}
		return "minecraft." + fallback;
	}

	@Override
	public Component getName(ItemStack stack) {
		String key = "item.minecraft.arrow.fletching." + getPartNameMod(stack, ModDataComponents.ARROW_FLETCHING, "feather") + ".shaft." + getPartNameMod(stack, ModDataComponents.ARROW_SHAFT, "stick");
		if (stack.get(ModDataComponents.HAS_TIP)) {
			key = key + ".tip." + getPartNameMod(stack, ModDataComponents.ARROW_TIP, "flint");
		}
		if (stack.hasNonDefault(DataComponents.POTION_CONTENTS)) {
			return stack.get(DataComponents.POTION_CONTENTS).getName(key + ".effect.");
		}
		return Component.translatable(key);
	}*/

	public static void addSkeletonEffects(Entity ent, Holder<Potion> potion, ItemStack arrowItem) {
		if (!arrowItem.is(Items.ARROW)) {
			return;
		}
		if (ent.getRandom().nextInt(5) == 0) {
			arrowItem.set(ModDataComponents.ARROW_ADDITION, ModItemUtils.getHolder(Items.SPLASH_POTION));
		}
		else {
			arrowItem.set(ModDataComponents.ARROW_ADDITION, ModItemUtils.getHolder(Items.POTION));
		}
		arrowItem.update(DataComponents.POTION_CONTENTS, PotionContents.EMPTY, potion, PotionContents::withPotion);
	}

}
