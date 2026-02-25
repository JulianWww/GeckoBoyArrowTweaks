package net.denanu.geckoboyarrowtweaks.renderer.item.properties.conditional;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;

@Environment(EnvType.CLIENT)
public class ModConditionalItemModelProperties {
	public static void load() {
		ConditionalItemModelProperties.ID_MAPPER.put(GeckoBoyArrowTweaks.getId("has_items"), ContainsItems.CODEC);
	}
}
