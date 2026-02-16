package net.denanu.geckoboyarrowtweaks.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;

import net.denanu.geckoboyarrowtweaks.renderer.entity.state.CustomizableArrowRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.object.projectile.ArrowModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public abstract class ArrowComponentLayer extends RenderLayer<CustomizableArrowRenderState, ArrowModel> {
	public ArrowComponentLayer(RenderLayerParent<CustomizableArrowRenderState, ArrowModel> renderLayerParent) {
		super(renderLayerParent);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, CustomizableArrowRenderState entityRenderState, float f, float g) {
		if (this.shouldRender(entityRenderState)) {
			Identifier texture = this.getTexture(entityRenderState);
			if (texture == null) {
				return;
			}
			submitNodeCollector.order(this.order())
			.submitModel(
					this.getParentModel(), entityRenderState, poseStack, RenderTypes.entityCutout(texture), entityRenderState.lightCoords, OverlayTexture.NO_OVERLAY, entityRenderState.outlineColor, null
					);
		}
	}

	public int order() {
		return 1;
	}

	public abstract Identifier getTexture(CustomizableArrowRenderState entityRenderState);

	public boolean shouldRender(CustomizableArrowRenderState state) {
		return true;
	}
}
