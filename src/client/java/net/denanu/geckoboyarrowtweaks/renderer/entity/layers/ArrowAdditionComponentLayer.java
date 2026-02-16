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

public class ArrowAdditionComponentLayer extends ArrowComponentLayer {
	public static final Identifier GLOWSTONE = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/glowstone_addition.png");
	public static final ResourceKey<Item> GLOWSTONE_KEY = BuiltInRegistries.ITEM.getResourceKey(Items.GLOWSTONE_DUST).get();
	public static final Identifier GUNPOWDER = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/gunpowder_addition.png");
	public static final ResourceKey<Item> GUNPOWDER_KEY = BuiltInRegistries.ITEM.getResourceKey(Items.GUNPOWDER).get();
	public static final Identifier SLIME = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/slime_addition.png");
	public static final ResourceKey<Item> SLIME_KEY = BuiltInRegistries.ITEM.getResourceKey(Items.SLIME_BALL).get();
	public static final ResourceKey<Item> AIR_KEY = BuiltInRegistries.ITEM.getResourceKey(Items.AIR).get();

	public ArrowAdditionComponentLayer(RenderLayerParent<CustomizableArrowRenderState, ArrowModel> renderLayerParent) {
		super(renderLayerParent);
	}

	@Override
	public Identifier getTexture(CustomizableArrowRenderState state) {
		if (state.composition.addition().is(GLOWSTONE_KEY)) {
			return GLOWSTONE;
		}
		if (state.composition.addition().is(GUNPOWDER_KEY)) {
			return GUNPOWDER;
		}
		if (state.composition.addition().is(SLIME_KEY)) {
			return SLIME;
		}
		return null;
	}

	@Override
	public int order() {
		return 2;
	}

	@Override
	public boolean shouldRender(CustomizableArrowRenderState state) {
		return !state.composition.addition().is(AIR_KEY);
	}

}
