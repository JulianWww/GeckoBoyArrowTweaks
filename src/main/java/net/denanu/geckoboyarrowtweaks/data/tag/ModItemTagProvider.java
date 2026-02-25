package net.denanu.geckoboyarrowtweaks.data.tag;
import java.util.concurrent.CompletableFuture;

import net.denanu.geckoboyarrowtweaks.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.item.Items;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public ModItemTagProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(Provider wrapperLookup) {
		this.valueLookupBuilder(ModItemTags.ARROW_FLETCHING)
		.add(Items.FEATHER)
		.add(Items.PHANTOM_MEMBRANE)
		.add(Items.WHEAT);

		this.valueLookupBuilder(ModItemTags.ARROW_SHAFT)
		.add(Items.STICK)
		.add(Items.BLAZE_ROD)
		.add(Items.BREEZE_ROD);

		this.valueLookupBuilder(ModItemTags.ARROW_TIP)
		.add(Items.FLINT)
		.add(Items.PRISMARINE_SHARD)
		.add(Items.ECHO_SHARD)
		.add(Items.AMETHYST_SHARD);

		this.valueLookupBuilder(ModItemTags.ARROW_ADDITION)
		.add(Items.SLIME_BALL)
		.add(Items.GUNPOWDER)
		.add(Items.GLOWSTONE_DUST)
		.add(Items.POTION)
		.add(Items.SPLASH_POTION)
		.add(Items.LINGERING_POTION);

		this.valueLookupBuilder(ModItemTags.POTIONS)
		.add(Items.POTION)
		.add(Items.LINGERING_POTION)
		.add(Items.SPLASH_POTION);

		this.valueLookupBuilder(ModItemTags.HOMING_ARROW_CAPABLE_WEAPONS)
		.add(Items.BOW)
		.add(Items.CROSSBOW);

		this.valueLookupBuilder(ModItemTags.QIVERS)
		.add(ModItems.QUIVER);
	}
}