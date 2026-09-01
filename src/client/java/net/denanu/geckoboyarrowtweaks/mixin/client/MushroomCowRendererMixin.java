package net.denanu.geckoboyarrowtweaks.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;

import net.denanu.geckoboyarrowtweaks.mixin_ducts.EatUtilsProvider;
import net.denanu.geckoboyarrowtweaks.mixin_ducts.HeadEatPositionScaleHolder;
import net.minecraft.client.renderer.entity.MushroomCowRenderer;
import net.minecraft.client.renderer.entity.state.MushroomCowRenderState;
import net.minecraft.world.entity.animal.cow.MushroomCow;

@Mixin(MushroomCowRenderer.class)
public class MushroomCowRendererMixin {
	@Inject(method = "extractRenderState(Lnet/minecraft/world/entity/animal/cow/MushroomCow;Lnet/minecraft/client/renderer/entity/state/MushroomCowRenderState;F)V", at = @At(value = "TAIL"))
	public void extractRenderStateMixin(CallbackInfo ci, @Local MushroomCow cow,
			@Local LocalRef<MushroomCowRenderState> stateRef, @Local float partial) {
		EatUtilsProvider animalMixin = (EatUtilsProvider) cow;
		MushroomCowRenderState state = stateRef.get();
		HeadEatPositionScaleHolder stateMixin = (HeadEatPositionScaleHolder) state;

		if (animalMixin.getEatUtils().doEatAngleAnim()) {
			state.xRot = animalMixin.getEatUtils().getHeadEatAngleScale(partial);
		}
		stateMixin.setHeadEatPositionScale(animalMixin.getEatUtils().getHeadEatPositionScale(partial) * 0.9f);

		stateRef.set(state);
	}
}
