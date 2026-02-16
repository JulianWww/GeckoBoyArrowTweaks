package net.denanu.geckoboyarrowtweaks.loot.modifiers;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.denanu.geckoboyarrowtweaks.loot.functions.ArrowCustomizeShaftFunction;
import net.denanu.geckoboyarrowtweaks.loot.functions.ArrowPotionEffectFunction;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public class EndCityLootPoolModifier extends LootPoolModifier {

	public EndCityLootPoolModifier() {
		super(ImmutableList.of(BuiltInLootTables.END_CITY_TREASURE));
	}

	@Override
	protected List<LootItemFunction> getModifierFunction() {
		return ImmutableList.of(
				ArrowCustomizeShaftFunction.make()
				.addFletching(Items.FEATHER, 10)
				.addFletching(Items.PHANTOM_MEMBRANE, 1)

				.addAddition(Items.AIR, 2)
				.addAddition(Items.LINGERING_POTION, 5)
				.build()
				,ArrowPotionEffectFunction.make()
				.addEffect(Potions.HEALING, 5)
				.addEffect(Potions.STRONG_HEALING, 3)

				.addEffect(Potions.FIRE_RESISTANCE, 1)
				.addEffect(Potions.LONG_FIRE_RESISTANCE, 1)

				.addEffect(Potions.REGENERATION, 5)
				.addEffect(Potions.LONG_REGENERATION, 2)
				.addEffect(Potions.STRONG_REGENERATION, 2)

				.addEffect(Potions.STRENGTH, 5)
				.addEffect(Potions.LONG_STRENGTH, 2)
				.addEffect(Potions.STRONG_STRENGTH, 2)

				.addEffect(Potions.SWIFTNESS, 5)
				.addEffect(Potions.LONG_SWIFTNESS, 2)
				.addEffect(Potions.STRONG_SWIFTNESS, 2)

				.addEffect(Potions.NIGHT_VISION, 5)
				.addEffect(Potions.LONG_NIGHT_VISION, 3)

				.addEffect(Potions.INVISIBILITY, 5)
				.addEffect(Potions.LONG_INVISIBILITY, 3)

				.addEffect(Potions.WATER_BREATHING, 2)
				.addEffect(Potions.WATER_BREATHING, 1)

				.addEffect(Potions.LEAPING, 5)
				.addEffect(Potions.LONG_LEAPING, 2)
				.addEffect(Potions.STRONG_LEAPING, 2)

				.addEffect(Potions.SLOW_FALLING, 5)
				.addEffect(Potions.LONG_SLOW_FALLING, 3)

				.addEffect(Potions.TURTLE_MASTER, 5)
				.addEffect(Potions.LONG_TURTLE_MASTER, 2)
				.addEffect(Potions.STRONG_TURTLE_MASTER, 2)

				.addEffect(Potions.POISON, 10)
				.addEffect(Potions.LONG_POISON, 1)
				.addEffect(Potions.STRONG_POISON, 1)

				.addEffect(Potions.HARMING, 15)
				.addEffect(Potions.STRONG_HARMING, 3)

				.addEffect(Potions.WEAKNESS, 5)
				.addEffect(Potions.LONG_WEAKNESS, 3)

				.addEffect(Potions.SLOWNESS, 5)
				.addEffect(Potions.LONG_SLOWNESS, 3)
				.addEffect(Potions.STRONG_SLOWNESS, 3)
				.build()
				);
	}

}
