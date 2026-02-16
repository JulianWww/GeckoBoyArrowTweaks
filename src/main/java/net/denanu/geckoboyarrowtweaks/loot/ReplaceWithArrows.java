package net.denanu.geckoboyarrowtweaks.loot;

import java.util.List;
import java.util.ListIterator;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents.ModifyDrops;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;

public class ReplaceWithArrows implements ModifyDrops {
	@Override
	public void modifyLootTableDrops(Holder<LootTable> entry, LootContext context, List<ItemStack> drops) {
		GeckoBoyArrowTweaks.LOGGER.info(entry.getRegisteredName());
		for (final ListIterator<ItemStack> i = drops.listIterator(); i.hasNext();) {
			ItemStack stack = i.next();
			ItemStack nextStack = ArrowLootGenerator.makeArrowStack(stack, context.getRandom());
			i.set(nextStack);
		}
	}

}
