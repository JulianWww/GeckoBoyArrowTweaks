package net.denanu.geckoboyarrowtweaks.inventory;

import net.denanu.geckoboyarrowtweaks.data.tag.ModItemTags;
import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FletchingMenu extends ItemCombinerMenu {
	public static final int TEMPLATE_SLOT = 0;
	public static final int BASE_SLOT = 1;
	public static final int ADDITIONAL_SLOT = 2;
	public static final int RESULT_SLOT = 3;
	public static final int TEMPLATE_SLOT_X_PLACEMENT = 8;
	public static final int BASE_SLOT_X_PLACEMENT = 26;
	public static final int ADDITIONAL_SLOT_X_PLACEMENT = 44;
	private static final int RESULT_SLOT_X_PLACEMENT = 98;
	public static final int SLOT_Y_PLACEMENT = 48;
	private final Level level;
	private final DataSlot hasRecipeError = DataSlot.standalone();

	public FletchingMenu(int i, Inventory inventory) {
		this(i, inventory, ContainerLevelAccess.NULL);
	}

	public FletchingMenu(int i, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
		this(i, inventory, containerLevelAccess, inventory.player.level());
	}

	private FletchingMenu(int i, Inventory inventory, ContainerLevelAccess containerLevelAccess, Level level) {
		super(ModMenuType.FLETCHING, i, inventory, containerLevelAccess, createInputSlotDefinitions(level.recipeAccess()));
		this.level = level;
	}

	private static ItemCombinerMenuSlotDefinition createInputSlotDefinitions(RecipeAccess recipeAccess) {
		return ItemCombinerMenuSlotDefinition.create()
				.withSlot(0, 8, 48, stack -> stack.is(ModItemTags.ARROW_FLETCHING))
				.withSlot(1, 26, 48, stack -> stack.is(ModItemTags.ARROW_SHAFT))
				.withSlot(2, 44, 48, stack -> stack.is(ModItemTags.ARROW_TIP))
				.withSlot(3, 62, 48, stack -> stack.is(ModItemTags.ARROW_ADDITION))
				.withResultSlot(4, 116, 48)
				.build();
	}

	@Override
	protected boolean isValidBlock(BlockState blockState) {
		return blockState.is(Blocks.FLETCHING_TABLE);
	}

	@Override
	protected void onTake(Player player, ItemStack itemStack) {
		itemStack.onCraftedBy(player, itemStack.getCount());
		this.shrinkStackInSlot(0);
		this.shrinkStackInSlot(1);
		this.shrinkStackInSlot(2);
		if (itemStack.is(ModItemTags.POTIONS)) {
			this.inputSlots.setItem(3, new ItemStack(Items.GLASS_BOTTLE));
		} else {
			this.shrinkStackInSlot(3);
		}
	}

	private void shrinkStackInSlot(int i) {
		ItemStack itemStack = this.inputSlots.getItem(i);
		if (!itemStack.isEmpty()) {
			itemStack.shrink(1);
			this.inputSlots.setItem(i, itemStack);
		}
	}

	@Override
	public void createResult() {
		ItemStack tip = this.getItems().get(2);
		ItemStack shaft = this.getItems().get(1);
		ItemStack fletching = this.getItems().get(0);
		ItemStack addition = this.getItems().get(3);
		if (shaft.isEmpty() || fletching.isEmpty()) {
			this.resultSlots.setItem(0, ItemStack.EMPTY);
			return;
		}
		ItemStack result_stack = new ItemStack(Items.ARROW, 8);

		result_stack.set(ModDataComponents.ARROW_SHAFT, shaft.getItemHolder());
		result_stack.set(ModDataComponents.ARROW_FLETCHING, fletching.getItemHolder());

		if (!tip.isEmpty()) {
			result_stack.set(ModDataComponents.ARROW_TIP, tip.getItemHolder());
		}
		else {
			result_stack.set(ModDataComponents.HAS_TIP, false);
		}

		if (!addition.isEmpty()) {
			if (tip.isEmpty()) {
				this.resultSlots.setItem(0, ItemStack.EMPTY);
				return;
			}
			result_stack.set(ModDataComponents.ARROW_ADDITION, addition.getItemHolder());
			if (addition.is(ModItemTags.POTIONS)) {
				result_stack.set(DataComponents.POTION_CONTENTS, addition.get(DataComponents.POTION_CONTENTS));
			}
		}

		this.resultSlots.setItem(1, result_stack);

	}
}
