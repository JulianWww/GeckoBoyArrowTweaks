package net.denanu.geckoboyarrowtweaks;

import net.denanu.geckoboyarrowtweaks.events.BowTarget;
import net.denanu.geckoboyarrowtweaks.gui.screens.ModMenuScreens;
import net.denanu.geckoboyarrowtweaks.item.tooltip.ArrowTooltips;
import net.denanu.geckoboyarrowtweaks.renderer.entity.ModEntityRenderers;
import net.denanu.geckoboyarrowtweaks.renderer.item.properties.conditional.ModConditionalItemModelProperties;
import net.fabricmc.api.ClientModInitializer;

public class GeckoBoyArrowTweaksClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModMenuScreens.load();
		ModEntityRenderers.load();
		ArrowTooltips.load();
		ModConditionalItemModelProperties.load();
		BowTarget.load();
	}
}