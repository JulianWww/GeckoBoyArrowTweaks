package net.denanu.geckoboyarrowtweaks.inventory.slots;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.entity.player.PlayerModifications;
import net.denanu.geckoboyarrowtweaks.items.ModItems;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class QuiverEquipmentSlot extends Slot {
	private static final Identifier TEXTURE = GeckoBoyArrowTweaks.getId("container/slot/quiver");

	private Player player;

	public QuiverEquipmentSlot(Container container, int i, int j, int k, Player player) {
		super(container, i, j, k);
		this.player = player;
	}

	@Override
	public Identifier getNoItemIcon() {
		return TEXTURE;
	}

	@Override
	public boolean mayPlace(ItemStack itemStack) {
		return itemStack.is(ModItems.QUIVER);
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public ItemStack getItem() {
		return player.getEntityData().get(PlayerModifications.QUIVER_ITEM);
	}

	@Override
	public void set(ItemStack stack) {
		player.getEntityData().set(PlayerModifications.QUIVER_ITEM, stack);
	}

	@Override
	public ItemStack remove(int i) {
		SynchedEntityData data = player.getEntityData();
		ItemStack stack = data.get(PlayerModifications.QUIVER_ITEM);
		data.set(PlayerModifications.QUIVER_ITEM, ItemStack.EMPTY);
		return stack;
	}

	@Override
	public void setChanged() {}
}
