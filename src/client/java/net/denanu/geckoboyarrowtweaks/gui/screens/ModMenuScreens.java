package net.denanu.geckoboyarrowtweaks.gui.screens;

import net.denanu.geckoboyarrowtweaks.gui.screens.inventory.FletchingScreen;
import net.denanu.geckoboyarrowtweaks.inventory.ModMenuType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class ModMenuScreens {
	static {
		MenuScreens.register(ModMenuType.FLETCHING, FletchingScreen::new);
	}

	public static void load() {

	}
}
