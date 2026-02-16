package net.denanu.geckoboyarrowtweaks.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.model.object.projectile.ArrowModel;
import net.minecraft.client.renderer.entity.ArrowRenderer;

@Mixin(ArrowRenderer.class)
public interface ArrowRendererAccessor {
	@Accessor("model")
	public ArrowModel geckoboyarrowtweaks$getModel();
}
