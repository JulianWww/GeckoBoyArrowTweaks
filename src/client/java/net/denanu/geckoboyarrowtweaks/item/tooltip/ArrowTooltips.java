package net.denanu.geckoboyarrowtweaks.item.tooltip;

import java.util.List;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ArrowTooltips {
	public static void addFletchingTooltip(ItemStack stack, List<Component> tooltips) {
		Holder<Item> fletching = stack.get(ModDataComponents.ARROW_FLETCHING);
		if (fletching.is(ModItemUtils.getHolder(Items.WHEAT))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.fletching.wheat"));
		}
		else if (fletching.is(ModItemUtils.getHolder(Items.PHANTOM_MEMBRANE))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.fletching.phantom_membrane"));
		}
	}

	public static void addShaftTooltip(ItemStack stack, List<Component> tooltips) {
		Holder<Item> shaft = stack.get(ModDataComponents.ARROW_SHAFT);
		if (shaft.is(ModItemUtils.getHolder(Items.BLAZE_ROD))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.blaze_rod"));
		}
		else if (shaft.is(ModItemUtils.getHolder(Items.BREEZE_ROD))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.breeze_rod"));
		}
	}

	public static void addTipTooltip(ItemStack stack, List<Component> tooltips) {
		if (!stack.get(ModDataComponents.HAS_TIP)) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.tip.none"));
			return;
		}
		Holder<Item> shaft = stack.get(ModDataComponents.ARROW_TIP);
		if (shaft.is(ModItemUtils.getHolder(Items.PRISMARINE_SHARD))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.tip.prismarine_shard"));
		}
		else if (shaft.is(ModItemUtils.getHolder(Items.ECHO_SHARD))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.tip.echo_shard"));
		}
		else if (shaft.is(ModItemUtils.getHolder(Items.AMETHYST_SHARD))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.tip.amethist_shard"));
		}
	}

	public static void addAdditionTooltip(ItemStack stack, List<Component> tooltips) {
		Holder<Item> shaft = stack.get(ModDataComponents.ARROW_ADDITION);
		if (shaft.is(ModItemUtils.getHolder(Items.GLOWSTONE_DUST))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.glowstone_dust"));
		}
		else if (shaft.is(ModItemUtils.getHolder(Items.GUNPOWDER))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.gunpowder"));
		}
		else if (shaft.is(ModItemUtils.getHolder(Items.SLIME_BALL))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.slime_ball"));
		}
		else if (shaft.is(ModItemUtils.getHolder(Items.POTION))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.potion"));
		}
		else if (shaft.is(ModItemUtils.getHolder(Items.SPLASH_POTION))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.splash_potion"));
		}
		else if (shaft.is(ModItemUtils.getHolder(Items.LINGERING_POTION))) {
			tooltips.add(Component.translatable(GeckoBoyArrowTweaks.MOD_ID + ".arrow.shaft.lingering_potion"));
		}
	}

	public static void load() {
		ItemTooltipCallback.EVENT.register((stack, context, flag, tooltips) -> {
			if (!stack.is(Items.ARROW)) {
				return;
			}


			addAdditionTooltip(stack, tooltips);
			addFletchingTooltip(stack, tooltips);
			addShaftTooltip(stack, tooltips);
			addTipTooltip(stack, tooltips);
		});
	}
}
