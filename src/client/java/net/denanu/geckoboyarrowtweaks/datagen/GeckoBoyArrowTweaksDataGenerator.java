package net.denanu.geckoboyarrowtweaks.datagen;


import net.denanu.geckoboyarrowtweaks.data.tag.ModItemTagProvider;
import net.denanu.geckoboyarrowtweaks.datagen.itemModels.ArrowModel;
import net.denanu.geckoboyarrowtweaks.datagen.lang.ModEnglishLangProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class GeckoBoyArrowTweaksDataGenerator implements DataGeneratorEntrypoint {
	static {
		ModEnglishLangProvider.reset();
	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModItemTagProvider::new);

		pack.addProvider(ArrowModel::new);
		pack.addProvider(ModEnglishLangProvider::new);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				ModEnglishLangProvider.moveFile();
			}
		});
	}

}