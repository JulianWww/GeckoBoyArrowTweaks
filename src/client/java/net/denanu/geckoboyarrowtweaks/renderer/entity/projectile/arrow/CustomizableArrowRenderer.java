package net.denanu.geckoboyarrowtweaks.renderer.entity.projectile.arrow;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.CustomizableArrow;
import net.denanu.geckoboyarrowtweaks.renderer.entity.layers.ArrowAdditionComponentLayer;
import net.denanu.geckoboyarrowtweaks.renderer.entity.layers.ArrowFletchingComponentLayer;
import net.denanu.geckoboyarrowtweaks.renderer.entity.layers.ArrowShaftComponentLayer;
import net.denanu.geckoboyarrowtweaks.renderer.entity.layers.ArrowTipComponentLayer;
import net.denanu.geckoboyarrowtweaks.renderer.entity.state.CustomizableArrowRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.projectile.ArrowModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.state.CameraRenderState;

@Environment(EnvType.CLIENT)
public class CustomizableArrowRenderer extends EntityRenderer<CustomizableArrow, CustomizableArrowRenderState> implements RenderLayerParent<CustomizableArrowRenderState, ArrowModel> {
	private final ArrowModel model;
	protected final List<RenderLayer<CustomizableArrowRenderState, ArrowModel>> layers = Lists.<RenderLayer<CustomizableArrowRenderState, ArrowModel>>newArrayList();

	public CustomizableArrowRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new ArrowModel(context.bakeLayer(ModelLayers.ARROW));
		this.addLayer(new ArrowTipComponentLayer(this));
		this.addLayer(new ArrowShaftComponentLayer(this));
		this.addLayer(new ArrowFletchingComponentLayer(this));
		this.addLayer(new ArrowAdditionComponentLayer(this));
	}

	protected final boolean addLayer(RenderLayer<CustomizableArrowRenderState, ArrowModel> renderLayer) {
		return this.layers.add(renderLayer);
	}

	@Override
	public CustomizableArrowRenderState createRenderState() {
		return new CustomizableArrowRenderState();
	}

	@Override
	public void extractRenderState(CustomizableArrow arrow, CustomizableArrowRenderState state, float f) {
		super.extractRenderState(arrow, state, f);
		state.isTipped = false;
		state.xRot = arrow.getXRot(f);
		state.yRot = arrow.getYRot(f);
		state.shake = arrow.shakeTime - f;

		state.composition = arrow.getComposition();
	}

	@Override
	public ArrowModel getModel() {
		return this.model;
	}

	@Override
	public void submit(CustomizableArrowRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
		poseStack.mulPose(Axis.ZP.rotationDegrees(state.xRot));
		for (RenderLayer<CustomizableArrowRenderState, ArrowModel> renderLayer : this.layers) {
			renderLayer.submit(
					poseStack, submitNodeCollector, state.lightCoords, state, state.yRot, state.xRot
					);
		}
		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, cameraRenderState);
	}
}