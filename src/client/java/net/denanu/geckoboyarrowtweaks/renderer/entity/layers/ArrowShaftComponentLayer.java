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

public class ArrowShaftComponentLayer extends ArrowComponentLayer {
	public static final Identifier WOOD = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/wood_shaft.png");
	public static final Identifier BLAZE = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/blaze_shaft.png");
	public static final ResourceKey<Item> BLAZE_KEY = BuiltInRegistries.ITEM.getResourceKey(Items.BLAZE_ROD).get();
	public static final Identifier BREEZE = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/breeze_shaft.png");
	public static final ResourceKey<Item> BREEZE_KEY = BuiltInRegistries.ITEM.getResourceKey(Items.BREEZE_ROD).get();

	public ArrowShaftComponentLayer(RenderLayerParent<CustomizableArrowRenderState, ArrowModel> renderLayerParent) {
		super(renderLayerParent);
	}

	@Override
	public Identifier getTexture(CustomizableArrowRenderState state) {
		if (state.composition.shaft().is(BLAZE_KEY)) {
			return BLAZE;
		}
		if (state.composition.shaft().is(BREEZE_KEY)) {
			return BREEZE;
		}
		return WOOD;
	}

}
