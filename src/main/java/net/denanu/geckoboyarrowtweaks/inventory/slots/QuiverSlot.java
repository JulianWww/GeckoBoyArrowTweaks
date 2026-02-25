package net.denanu.geckoboyarrowtweaks.inventory.slots;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class QuiverSlot extends Slot {

	public QuiverSlot(Container container, int i, int j, int k) {
		super(container, i, j, k);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.is(ItemTags.ARROWS);
	}

}
