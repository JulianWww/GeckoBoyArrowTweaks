package net.denanu.geckoboyarrowtweaks.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;

import net.denanu.geckoboyarrowtweaks.mixin_ducts.EatUtilsProvider;
import net.denanu.geckoboyarrowtweaks.mixin_ducts.HeadEatPositionScaleHolder;
import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.client.renderer.entity.state.CowRenderState;
import net.minecraft.world.entity.animal.cow.Cow;

@Mixin(CowRenderer.class)
public class CowRendererMixin {
	@Inject(method="extractRenderState(Lnet/minecraft/world/entity/animal/cow/Cow;Lnet/minecraft/client/renderer/entity/state/CowRenderState;F)V", at=@At(value="TAIL"))
	public void extractRenderStateMixin(CallbackInfo ci, @Local Cow cow, @Local LocalRef<CowRenderState> stateRef, @Local float partial) {
		EatUtilsProvider animalMixin = (EatUtilsProvider)(Object)cow;
		CowRenderState state = stateRef.get();
		HeadEatPositionScaleHolder stateMixin = (HeadEatPositionScaleHolder)(Object)state;
		
		if (animalMixin.getEatUtils().doEatAngleAnim()) {
			state.xRot = animalMixin.getEatUtils().getHeadEatAngleScale(partial);
		}
		stateMixin.setHeadEatPositionScale(animalMixin.getEatUtils().getHeadEatPositionScale(partial)*0.9f);
		
		stateRef.set(state);
	}
}
