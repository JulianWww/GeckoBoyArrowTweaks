package net.denanu.geckoboyarrowtweaks.inventory;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class ModMenuType {
	public static final MenuType<FletchingMenu> FLETCHING = register("fleching", FletchingMenu::new);

	private static <T extends AbstractContainerMenu> MenuType<T> register(String string, MenuType.MenuSupplier<T> menuSupplier) {
		return Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, string), new MenuType<>(menuSupplier, FeatureFlags.VANILLA_SET));
	}

	public static void load() {

	}
}
