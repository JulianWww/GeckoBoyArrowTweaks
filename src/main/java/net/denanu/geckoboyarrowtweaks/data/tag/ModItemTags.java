package net.denanu.geckoboyarrowtweaks.data.tag;

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

	private static TagKey<Item> register(String path) {
		return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, path));
	}
}
