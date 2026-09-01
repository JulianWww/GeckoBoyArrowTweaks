package net.denanu.geckoboyarrowtweaks.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import net.denanu.geckoboyarrowtweaks.mixin_ducts.HeadEatPositionScaleHolder;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Mixin(QuadrupedModel.class)
public class QuadrupedModelMixin {
	@Inject(method="setupAnim(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;)V", at=@At(value="TAIL"))
	public void setupAnimMixin(CallbackInfo ci, @Local LivingEntityRenderState state) {
		ModelPart head = ((QuadrupedModelAccessor)(Object)this).geckoboyarrowtewaks$getHead();
		HeadEatPositionScaleHolder headPosState = (HeadEatPositionScaleHolder)(Object)state;
		head.y = head.y + headPosState.getHeadEatPositionScale() * 9.0F * state.ageScale;
	}
}
