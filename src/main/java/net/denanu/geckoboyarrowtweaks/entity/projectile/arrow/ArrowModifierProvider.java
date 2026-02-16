package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow;

import java.util.HashMap;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.DefaultArrowModifier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ArrowModifierProvider {
	private DefaultArrowModifier DEFAULT = new DefaultArrowModifier();
	private HashMap<Holder<Item>, DefaultArrowModifier> modifiers = new HashMap<Holder<Item>, DefaultArrowModifier>();

	public ArrowModifierProvider register(Item item, DefaultArrowModifier mod) {
		return this.register(BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getId(item)).get(), mod);
	}

	public ArrowModifierProvider register(Holder<Item> key, DefaultArrowModifier mod) {
		this.modifiers.put(key, mod);
		return this;
	}

	public DefaultArrowModifier get(Holder<Item> key, ItemStack stack) {
		DefaultArrowModifier mod = this.modifiers.getOrDefault(key, DEFAULT);
		return mod.modify(stack);
	}
}
