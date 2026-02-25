package net.denanu.geckoboyarrowtweaks.datagen.itemModels;

import net.denanu.geckoboyarrowtweaks.items.ModItems;
import net.denanu.geckoboyarrowtweaks.renderer.item.properties.conditional.ContainsItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.renderer.item.ItemModel;

public class QuiverModel extends FabricModelProvider {
	public QuiverModel(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
	}

	@Override
	public void generateItemModels(ItemModelGenerators gen) {
		String baseTexture = "item/quiver";
		String withArrowTexture = "item/quiver_with_arrows";

		ItemModel.Unbaked base = ModItemModelUtils.getLayer(gen, baseTexture);
		ItemModel.Unbaked withArrows = ModItemModelUtils.getLayers(gen, withArrowTexture, baseTexture, withArrowTexture);

		gen.generateBooleanDispatch(ModItems.QUIVER, new ContainsItems(),
				withArrows,
				base
				);
	}

	@Override
	public String getName() {
		return "quiver item model";
	}
}
