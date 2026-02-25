package net.denanu.geckoboyarrowtweaks.data.tag;

import dev.emi.trinkets.TrinketsMain;
import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

	public static final TagKey<Item> ARROW_TIP = register("arrow_tip");
	public static final TagKey<Item> ARROW_SHAFT = register("arrow_shaft");
	public static final TagKey<Item> ARROW_FLETCHING = register("arrow_fletching");
	public static final TagKey<Item> ARROW_ADDITION = register("arrow_addition");
	public static final TagKey<Item> POTIONS = register("potions");
	public static final TagKey<Item> HOMING_ARROW_CAPABLE_WEAPONS = register("homing");
	public static final TagKey<Item> QIVERS = register(TrinketsMain.MOD_ID, "chest/quiver");

	private static TagKey<Item> register(String path) {
		return register(GeckoBoyArrowTweaks.MOD_ID, path);
	}

	private static TagKey<Item> register(String namespace, String path) {
		return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(namespace, path));
	}
}
