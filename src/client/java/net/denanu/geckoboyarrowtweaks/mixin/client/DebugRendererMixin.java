package net.denanu.geckoboyarrowtweaks.mixin.client;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.denanu.geckoboyarrowtweaks.renderer.DEBUG.ArrowPathRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.debug.DebugRenderer;

@Mixin(DebugRenderer.class)@Environment(EnvType.CLIENT)
public class DebugRendererMixin {
	@Shadow
	private List<DebugRenderer.SimpleDebugRenderer> renderers;

	@Inject(method = "refreshRendererList()V", at = @At("TAIL"))
	private void init(CallbackInfo info) {
		this.renderers.add(new ArrowPathRenderer());
	}
}