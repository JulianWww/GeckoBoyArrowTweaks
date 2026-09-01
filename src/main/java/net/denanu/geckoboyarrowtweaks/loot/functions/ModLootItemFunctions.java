package net.denanu.geckoboyarrowtweaks.loot.functions;

import com.mojang.serialization.MapCodec;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public class ModLootItemFunctions {
	public static final MapCodec<ArrowCustomizeShaftFunction> CUSTOMIZE_ARROWS = register("enchant_with_levels", ArrowCustomizeShaftFunction.CODEC);
	public static final MapCodec<ArrowPotionEffectFunction> ARROW_POTION_EFFECTS = register("potion_effects", ArrowPotionEffectFunction.CODEC);

	private static <T extends LootItemFunction> MapCodec<T> register(String name, MapCodec<T> codec) {
		return Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, name), codec);
	}

	public static void load() {}
}
