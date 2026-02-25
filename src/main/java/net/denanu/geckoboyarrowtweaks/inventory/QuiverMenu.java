package net.denanu.geckoboyarrowtweaks.inventory;

import org.jetbrains.annotations.Nullable;

import net.denanu.geckoboyarrowtweaks.inventory.slots.QuiverSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class QuiverMenu extends AbstractContainerMenu {
	private final Container container;

	public QuiverMenu(int i, Inventory inventory) {
		this(i, inventory, null);
	}

	public QuiverMenu(int i, Inventory inventory, @Nullable ItemStack stack) {
		super(ModMenuType.QUIVER, i);
		this.container = new SimpleContainer(18);
		container.startOpen(inventory.player);

		for (int l = 0; l < 2; l++) {
			for (int m = 0; m < 9; m++) {
				this.addSlot(new QuiverSlot(container, m + l * 9, 8 + m * 18, 18 + l * 18));
			}
		}


		this.addStandardInventorySlots(inventory, 8, 66);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int i) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(i);
		if (slot != null && slot.hasItem()) {
			ItemStack itemStack2 = slot.getItem();
			itemStack = itemStack2.copy();
			if (i < this.container.getContainerSize()) {
				if (!this.moveItemStackTo(itemStack2, this.container.getContainerSize(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemStack2, 0, this.container.getContainerSize(), false)) {
				return ItemStack.EMPTY;
			}

			if (itemStack2.isEmpty()) {
				slot.setByPlayer(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		return itemStack;
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	public int getRowCount() {
		return 2;
	}
}
