package net.denanu.geckoboyarrowtweaks.datagen.itemModels;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.minecraft.client.color.item.Potion;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.resources.Identifier;

public class ModItemModelUtils {

	public static ItemModel.Unbaked getLayer(ItemModelGenerators gen, Identifier id) {
		return ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(id, TextureMapping.layer0(id), gen.modelOutput));
	}

	public static ItemModel.Unbaked getLayers(ItemModelGenerators gen, Identifier id, Identifier layer1, Identifier layer2) {
		return ItemModelUtils.plainModel(ModelTemplates.TWO_LAYERED_ITEM.create(id, TextureMapping.layered(layer1, layer2), gen.modelOutput));
	}

	public static ItemModel.Unbaked getTinted(ItemModelGenerators gen, Identifier id) {
		return ItemModelUtils.tintedModel(ModelTemplates.FLAT_ITEM.create(id, TextureMapping.layer0(id), gen.modelOutput), new Potion());
	}

	public static ItemModel.Unbaked getLayer(ItemModelGenerators gen, String name) {
		return getLayer(gen, GeckoBoyArrowTweaks.getId(name));
	}

	public static ItemModel.Unbaked getLayers(ItemModelGenerators gen, String id, String layer1, String layer2) {
		return getLayers(gen, GeckoBoyArrowTweaks.getId(id), GeckoBoyArrowTweaks.getId(layer1), GeckoBoyArrowTweaks.getId(layer2));
	}

	public static ItemModel.Unbaked getTinted(ItemModelGenerators gen, String name) {
		return getTinted(gen, GeckoBoyArrowTweaks.getId(name));
	}
}
