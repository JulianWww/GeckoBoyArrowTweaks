package net.denanu.geckoboyarrowtweaks.items;

import java.util.function.Function;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemContainerContents;

public class ModItems {
	public static final Item QUIVER = registerItem("quiver", QuiverItem::new, new Item.Properties().stacksTo(1).component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));




	public static Item registerItem(String name, Function<Item.Properties, Item> builder, Item.Properties properties) {
		return registerItem(ItemId(name), builder, properties);
	}

	public static Item registerItem(ResourceKey<Item> key, Function<Item.Properties, Item> builder, Item.Properties properties) {
		Item item = builder.apply(properties.setId(key));
		if (item instanceof BlockItem blockItem) {
			blockItem.registerBlocks(Item.BY_BLOCK, item);
		}

		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

	private static ResourceKey<Item> ItemId(String name) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, name));
	}

	public static void load() {
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT)
		.register(group -> {
			group.accept(QUIVER);
		});
	}
}
