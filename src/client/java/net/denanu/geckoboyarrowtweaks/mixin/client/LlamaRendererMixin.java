package net.denanu.geckoboyarrowtweaks.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;

import net.denanu.geckoboyarrowtweaks.mixin_ducts.HeadEatPositionScaleHolder;
import net.minecraft.client.renderer.entity.LlamaRenderer;
import net.minecraft.client.renderer.entity.state.LlamaRenderState;
import net.minecraft.world.entity.animal.equine.Llama;

@Mixin(LlamaRenderer.class)
public class LlamaRendererMixin {
	@Inject(method="extractRenderState(Lnet/minecraft/world/entity/animal/equine/Llama;Lnet/minecraft/client/renderer/entity/state/LlamaRenderState;F)V", at=@At(value="TAIL"))
	public void extractRenderStateMixin(CallbackInfo ci, @Local Llama llama, @Local LocalRef<LlamaRenderState> stateRef, @Local float partial) {
		LlamaRenderState state = stateRef.get();
		HeadEatPositionScaleHolder stateMixin = (HeadEatPositionScaleHolder)state;
		
		stateMixin.setHeadEatPositionScale(llama.getEatAnim(partial));
	}
}
