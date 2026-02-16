package net.denanu.geckoboyarrowtweaks.renderer.entity.layers;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.renderer.entity.state.CustomizableArrowRenderState;
import net.minecraft.client.model.object.projectile.ArrowModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ArrowFletchingComponentLayer extends ArrowComponentLayer {
	public static final Identifier FEATHER = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/feather_fletching.png");
	public static final Identifier WHEAT = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/wheat_fletching.png");
	public static final ResourceKey<Item> WHEAT_KEY = BuiltInRegistries.ITEM.getResourceKey(Items.WHEAT).get();
	public static final Identifier MEMBRANE = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/membrane_fletching.png");
	public static final ResourceKey<Item> MEMBRANE_KEY = BuiltInRegistries.ITEM.getResourceKey(Items.PHANTOM_MEMBRANE).get();

	public ArrowFletchingComponentLayer(RenderLayerParent<CustomizableArrowRenderState, ArrowModel> renderLayerParent) {
		super(renderLayerParent);
	}

	@Override
	public Identifier getTexture(CustomizableArrowRenderState state) {
		if (state.composition.fletching().is(WHEAT_KEY)) {
			return WHEAT;
		}
		if (state.composition.fletching().is(MEMBRANE_KEY)) {
			return MEMBRANE;
		}
		return FEATHER;
	}

}
