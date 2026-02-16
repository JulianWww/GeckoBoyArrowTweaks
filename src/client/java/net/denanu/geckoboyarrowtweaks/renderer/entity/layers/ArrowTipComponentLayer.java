package net.denanu.geckoboyarrowtweaks.renderer.entity.layers;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.renderer.entity.state.CustomizableArrowRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.object.projectile.ArrowModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
@Environment(EnvType.CLIENT)
public class ArrowTipComponentLayer extends ArrowComponentLayer {
	private static final Identifier FLINT  = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/flit_tip.png");
	private static final Identifier ECHO  = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/echo_tip.png");
	public static final ResourceKey<Item> ECHO_KEY = BuiltInRegistries.ITEM.getResourceKey(Items.ECHO_SHARD).get();
	private static final Identifier AMETHIST = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/amethist_tip.png");
	public static final ResourceKey<Item> AMETHIST_KEY = BuiltInRegistries.ITEM.getResourceKey(Items.AMETHYST_SHARD).get();
	private static final Identifier PRISMARINE  = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/entity/projectiles/prismarine_tip.png");
	public static final ResourceKey<Item> PRISMARINE_KEY = BuiltInRegistries.ITEM.getResourceKey(Items.PRISMARINE_SHARD).get();

	public ArrowTipComponentLayer(RenderLayerParent<CustomizableArrowRenderState, ArrowModel> renderLayerParent) {
		super(renderLayerParent);
	}

	@Override
	public Identifier getTexture(CustomizableArrowRenderState state) {
		if (state.composition.tip().is(ECHO_KEY)) {
			return ECHO;
		}
		if (state.composition.tip().is(AMETHIST_KEY)) {
			return AMETHIST;
		}
		if (state.composition.tip().is(PRISMARINE_KEY)) {
			return PRISMARINE;
		}
		return FLINT;
	}

	@Override
	public boolean shouldRender(CustomizableArrowRenderState state) {
		return state.composition.hasTip();
	}
}
