package net.denanu.geckoboyarrowtweaks.data.tag;
import java.util.concurrent.CompletableFuture;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.references.ItemIds;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
	public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}
	
	@Override
	protected void addTags(Provider wrapperLookup) {
		this.builder(ModItemTags.ARROW_FLETCHING)
		.add(ItemIds.FEATHER)
		.add(ItemIds.PHANTOM_MEMBRANE)
		.add(ItemIds.WHEAT);

		this.builder(ModItemTags.ARROW_SHAFT)
		.add(ItemIds.STICK)
		.add(ItemIds.BLAZE_ROD)
		.add(ItemIds.BREEZE_ROD);

		this.builder(ModItemTags.ARROW_TIP)
		.add(ItemIds.FLINT)
		.add(ItemIds.PRISMARINE_SHARD)
		.add(ItemIds.ECHO_SHARD)
		.add(ItemIds.AMETHYST_SHARD);

		this.builder(ModItemTags.ARROW_ADDITION)
		.add(ItemIds.SLIME_BALL)
		.add(ItemIds.GUNPOWDER)
		.add(ItemIds.GLOWSTONE_DUST)
		.add(ItemIds.POTION)
		.add(ItemIds.SPLASH_POTION)
		.add(ItemIds.LINGERING_POTION);

		this.builder(ModItemTags.POTIONS)
		.add(ItemIds.POTION)
		.add(ItemIds.LINGERING_POTION)
		.add(ItemIds.SPLASH_POTION);

		this.builder(ModItemTags.HOMING_ARROW_CAPABLE_WEAPONS)
		.add(ItemIds.BOW)
		.add(ItemIds.CROSSBOW);
	}	
}