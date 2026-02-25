package net.denanu.geckoboyarrowtweaks.datagen.lang;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import com.google.common.io.Files;

import dev.emi.trinkets.TrinketsMain;
import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

public class ModEnglishLangProvider extends FabricLanguageProvider {
	public ModEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, "en_us", registryLookup);
	}

	public static void reset() {
		new File("../../src/main/resources/assets/" + GeckoBoyArrowTweaks.MOD_ID + "/lang/en_us.json").delete();
	}

	public static void moveFile() {
		try {
			Files.move(
					new File("../../src/main/generated/assets/" + GeckoBoyArrowTweaks.MOD_ID + "/lang/en_us.json"),
					new File("../../src/main/resources/assets/" + GeckoBoyArrowTweaks.MOD_ID + "/lang/en_us.json")
					);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void generateTranslations(HolderLookup.Provider wrapper, TranslationBuilder builder) {
		builder.add("container.fletching", "Craft arrow");
		builder.add(TrinketsMain.MOD_ID+".slot.chest.quiver", "Quiver");

		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.fletching.wheat", "Attrackts Animals");
		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.fletching.phantom_membrane", "Swift Flying");

		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.blaze_rod", "Incendiary");
		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.breeze_rod", "Weightless");


		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.tip.none", "Tipless");
		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.tip.prismarine_shard", "Underwater");
		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.tip.echo_shard", "Homing");
		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.tip.amethist_shard", "Piercing");

		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.glowstone_dust", "Spectral");
		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.gunpowder", "Explosive");
		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.slime_ball", "Bouncing");
		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.potion", "Tipped");
		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.splash_potion", "Splashing");
		builder.add(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.lingering_potion", "Lingering");

		builder.add(ModItems.QUIVER, "Quiver");

	}
}