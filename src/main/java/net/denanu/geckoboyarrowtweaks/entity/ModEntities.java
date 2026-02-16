package net.denanu.geckoboyarrowtweaks.entity;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
	public static final EntityType<CustomizableArrow> CUSTOMIZABLE_ARROW = register(
			"arrow",
			EntityType.Builder.<CustomizableArrow>of(CustomizableArrow::new, MobCategory.MISC).noLootTable().sized(0.5F, 0.5F).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20)
			);

	private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> resourceKey, EntityType.Builder<T> builder) {
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, resourceKey, builder.build(resourceKey));
	}

	private static <T extends Entity> EntityType<T> register(String string, EntityType.Builder<T> builder) {
		return register(EntityId(string), builder);
	}

	private static ResourceKey<EntityType<?>> EntityId(String string) {
		return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID ,string));
	}

	public static void load() {
	}
}
