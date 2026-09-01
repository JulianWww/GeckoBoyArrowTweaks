package net.denanu.geckoboyarrowtweaks.mixin.client;

import org.spongepowered.asm.mixin.Mixin;

import net.denanu.geckoboyarrowtweaks.mixin_ducts.HeadEatPositionScaleHolder;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateMixin extends EntityRenderState implements HeadEatPositionScaleHolder {
	public float headEatPositionScale;

	@Override
	public void setHeadEatPositionScale(float val) {
		this.headEatPositionScale = val;
	}

	@Override
	public float getHeadEatPositionScale() {
		return this.headEatPositionScale;
	}
}
