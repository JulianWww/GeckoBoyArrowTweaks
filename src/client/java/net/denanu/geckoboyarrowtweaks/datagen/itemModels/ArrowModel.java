package net.denanu.geckoboyarrowtweaks.datagen.itemModels;

import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.conditional.HasComponent;
import net.minecraft.client.renderer.item.properties.select.ComponentContents;
import net.minecraft.world.item.Items;

public class ArrowModel extends FabricModelProvider {
	public ArrowModel(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
	}

	@Override
	public void generateItemModels(ItemModelGenerators gen) {
		ItemModel.Unbaked feather_fletching = ModItemModelUtils.getLayer(gen, "item/arrow/feathers_fletching");
		ItemModel.Unbaked wheat_fletching = ModItemModelUtils.getLayer(gen, "item/arrow/wheat_fletching");
		ItemModel.Unbaked membrane_fletching = ModItemModelUtils.getLayer(gen, "item/arrow/membrane_fletching");

		ItemModel.Unbaked stick_shaft = ModItemModelUtils.getLayer(gen, "item/arrow/stick_shaft");
		ItemModel.Unbaked blaze_rod_shaft = ModItemModelUtils.getLayer(gen, "item/arrow/blaze_rod_shaft");
		ItemModel.Unbaked breeze_rod_shaft = ModItemModelUtils.getLayer(gen, "item/arrow/breeze_rod_shaft");

		ItemModel.Unbaked no_tip = ModItemModelUtils.getLayer(gen, "item/arrow/no_tip");
		ItemModel.Unbaked flint_tip = ModItemModelUtils.getLayer(gen, "item/arrow/flint_tip");
		ItemModel.Unbaked prismarine_tip = ModItemModelUtils.getLayer(gen, "item/arrow/prismarine_tip");
		ItemModel.Unbaked echo_tip = ModItemModelUtils.getLayer(gen, "item/arrow/echo_tip");
		ItemModel.Unbaked amatist_tip = ModItemModelUtils.getLayer(gen, "item/arrow/amatist_tip");

		ItemModel.Unbaked slime_addition = ModItemModelUtils.getLayer(gen, "item/arrow/slime_mod");
		ItemModel.Unbaked glowstone_addition = ModItemModelUtils.getLayer(gen, "item/arrow/glowstone_mod");
		ItemModel.Unbaked gunpowder_addition = ModItemModelUtils.getLayer(gen, "item/arrow/gunpowder_mod");
		ItemModel.Unbaked potion_addition = ModItemModelUtils.getTinted(gen, "item/arrow/potion_mod");
		ItemModel.Unbaked splash_potion_addition = ModItemModelUtils.getTinted(gen, "item/arrow/splash_potion_mod");
		ItemModel.Unbaked lingering_potion_addition = ItemModelUtils.composite(
				splash_potion_addition,
				ModItemModelUtils.getLayer(gen, "item/arrow/lingering_potion_mod_overlay")
				);

		ItemModel.Unbaked fletching_selector = ItemModelUtils.conditional(
				new HasComponent(ModDataComponents.ARROW_FLETCHING, true),
				ItemModelUtils.select(
						new ComponentContents<>(ModDataComponents.ARROW_FLETCHING),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.FEATHER), feather_fletching),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.WHEAT), wheat_fletching),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.PHANTOM_MEMBRANE), membrane_fletching)
						),
				feather_fletching
				);

		ItemModel.Unbaked shaft_selector = ItemModelUtils.conditional(
				new HasComponent(ModDataComponents.ARROW_SHAFT, true),
				ItemModelUtils.select(
						new ComponentContents<>(ModDataComponents.ARROW_SHAFT),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.STICK), stick_shaft),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.BLAZE_ROD), blaze_rod_shaft),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.BREEZE_ROD), breeze_rod_shaft)
						),
				stick_shaft
				);


		ItemModel.Unbaked tip_selector = ItemModelUtils.conditional(
				new HasComponent(ModDataComponents.ARROW_TIP, true),
				ItemModelUtils.select(
						new ComponentContents<>(ModDataComponents.ARROW_TIP),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.FLINT), flint_tip),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.PRISMARINE_SHARD), prismarine_tip),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.ECHO_SHARD), echo_tip),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.AMETHYST_SHARD), amatist_tip)
						),
				flint_tip
				);

		tip_selector = ItemModelUtils.select(
				new ComponentContents<Boolean>(ModDataComponents.HAS_TIP),
				ItemModelUtils.when(false, no_tip),
				ItemModelUtils.when(true, tip_selector)
				);

		ItemModel.Unbaked addition_selector = ItemModelUtils.conditional(
				new HasComponent(ModDataComponents.ARROW_ADDITION, true),
				ItemModelUtils.select(
						new ComponentContents<>(ModDataComponents.ARROW_ADDITION),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.SLIME_BALL), slime_addition),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.GLOWSTONE_DUST), glowstone_addition),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.GUNPOWDER), gunpowder_addition),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.POTION), potion_addition),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.SPLASH_POTION), splash_potion_addition),
						ItemModelUtils.when(ModItemUtils.getHolder(Items.LINGERING_POTION), lingering_potion_addition)
						),
				no_tip
				);

		gen.itemModelOutput.accept(Items.ARROW,
				ItemModelUtils.composite(fletching_selector, shaft_selector, tip_selector, addition_selector)
				);
	}

	@Override
	public String getName() {
		return "arrow item model";
	}
}