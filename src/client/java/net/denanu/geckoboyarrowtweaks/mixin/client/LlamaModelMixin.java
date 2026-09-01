package net.denanu.geckoboyarrowtweaks.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import net.denanu.geckoboyarrowtweaks.mixin_ducts.HeadEatPositionScaleHolder;
import net.minecraft.client.model.animal.llama.LlamaModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.LlamaRenderState;
import net.minecraft.util.Mth;

@Mixin(LlamaModel.class)
public class LlamaModelMixin {
	//ModelPart neck;
	
	@Inject(method="<init>(Lnet/minecraft/client/model/geom/ModelPart;)V", at=@At("Tail"))
	void init(CallbackInfo ci, @Local ModelPart root) {
		//this.neck = root.getChild("head").getChild("neck");
	}
	
	@Inject(method="setupAnim(Lnet/minecraft/client/renderer/entity/state/LlamaRenderState;)V", at=@At("TAIL"))
	void setupAnim(CallbackInfo ci, @Local LlamaRenderState state) {
		LlamaModelAccessor accessor = (LlamaModelAccessor)this;
		HeadEatPositionScaleHolder stateMixin = (HeadEatPositionScaleHolder)state;
		ModelPart head = accessor.getHead();
		
		float eating = stateMixin.getHeadEatPositionScale();
		
		float baseHeadAngle = (1.0F - eating) * head.xRot;
		
		head.xRot = eating * (2.1816616F + Mth.sin(state.ageInTicks) * 0.05F) + baseHeadAngle;
		head.yRot = (1.0F - eating) * head.yRot;
		//this.animateHeadPartsPlacement(eating, standing);
		
		
	}
}
