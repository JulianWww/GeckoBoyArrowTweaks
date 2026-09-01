package net.denanu.geckoboyarrowtweaks.datagen.itemModels;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.minecraft.client.color.item.Potion;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModel.Unbaked;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class ModItemModelUtils {

	public static ItemModel.Unbaked getLayer(ItemModelGenerators gen, String id) {
		return ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(GeckoBoyArrowTweaks.getId(id), TextureMapping.layer0(getMat(id)), gen.modelOutput));
	}

	public static ItemModel.Unbaked getLayers(ItemModelGenerators gen, String id, Identifier layer1, Identifier layer2) {
		return ItemModelUtils.plainModel(ModelTemplates.TWO_LAYERED_ITEM.create(GeckoBoyArrowTweaks.getId(id), TextureMapping.layered(new Material(layer1), new Material(layer2)), gen.modelOutput));
	}

	public static ItemModel.Unbaked getTinted(ItemModelGenerators gen, String id) {
		return ItemModelUtils.tintedModel(ModelTemplates.FLAT_ITEM.create(GeckoBoyArrowTweaks.getId(id), TextureMapping.layer0(getMat(id)), gen.modelOutput), new Potion());
	}
	
	public static Material getMat(String id) {
		return new Material(GeckoBoyArrowTweaks.getId(id));
	}
}
