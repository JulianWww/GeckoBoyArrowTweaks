package net.denanu.geckoboyarrowtweaks.items;

import org.jspecify.annotations.Nullable;

import dev.emi.trinkets.api.TrinketItem;
import net.denanu.geckoboyarrowtweaks.inventory.QuiverMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;

public class QuiverItem extends TrinketItem {

	public QuiverItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (player instanceof ServerPlayer splayer) {
			splayer.openMenu(new QuiverMenuProvider(hand, this.getName()));

			return InteractionResult.SUCCESS;
		}
		return InteractionResult.SUCCESS_SERVER;

	}

	static class QuiverMenuProvider implements MenuProvider {
		private InteractionHand hand;
		private Component name;

		QuiverMenuProvider(InteractionHand hand, Component name)  {
			this.hand = hand;
			this.name = name;
		}


		@Override
		public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
			return new QuiverMenu(i, inventory, player.getItemInHand(hand));
		}

		@Override
		public Component getDisplayName() {
			return this.name;
		}
	}

}
