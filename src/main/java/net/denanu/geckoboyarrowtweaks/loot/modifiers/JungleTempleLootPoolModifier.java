package net.denanu.geckoboyarrowtweaks.loot.modifiers;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.denanu.geckoboyarrowtweaks.loot.functions.ArrowCustomizeShaftFunction;
import net.denanu.geckoboyarrowtweaks.loot.functions.ArrowPotionEffectFunction;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public class JungleTempleLootPoolModifier extends LootPoolModifier {

	public JungleTempleLootPoolModifier() {
		super(ImmutableList.of(BuiltInLootTables.JUNGLE_TEMPLE_DISPENSER));
	}

	@Override
	protected List<LootItemFunction> getModifierFunction() {
		return List.of(
				ArrowCustomizeShaftFunction.make()
				.addAddition(Items.AIR, 1)
				.addAddition(Items.SLIME_BALL, 4)
				.addAddition(Items.POTION, 4)
				.addAddition(Items.SPLASH_POTION, 2)
				.addFletching(Items.FEATHER, 10)
				.addFletching(Items.PHANTOM_MEMBRANE, 1)
				.build(),
				ArrowPotionEffectFunction.make()
				.addEffect(Potions.POISON, 10)
				.addEffect(Potions.LONG_POISON, 1)
				.addEffect(Potions.STRONG_POISON, 1)

				.addEffect(Potions.HARMING, 15)
				.addEffect(Potions.STRONG_HARMING, 3)
				.build()
				);
	}

}
