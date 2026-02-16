package net.denanu.geckoboyarrowtweaks.renderer.entity;

import net.denanu.geckoboyarrowtweaks.entity.ModEntities;
import net.denanu.geckoboyarrowtweaks.renderer.entity.projectile.arrow.CustomizableArrowRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderers;

@Environment(EnvType.CLIENT)
public class ModEntityRenderers {
	public static void load() {
		EntityRenderers.register(ModEntities.CUSTOMIZABLE_ARROW, CustomizableArrowRenderer::new);
	}
}
