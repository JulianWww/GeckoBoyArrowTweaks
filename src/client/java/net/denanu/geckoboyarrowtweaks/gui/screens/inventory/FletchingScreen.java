package net.denanu.geckoboyarrowtweaks.gui.screens.inventory;

import java.util.List;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.inventory.FletchingMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CyclingSlotBackground;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class FletchingScreen extends ItemCombinerScreen<FletchingMenu> {
	private static final Identifier TABLE_LOCATION = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "textures/gui/container/fletching.png");


	private static final Identifier EMPTY_SLOT_FEATHER = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "container/slot/feather");
	private static final Identifier EMPTY_SLOT_PHANTOM_MEMBRANE = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "container/slot/phantom_membrane");
	private static final Identifier EMPTY_SLOT_WHEAT = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "container/slot/wheat");
	private static final List<Identifier> EMPTY_SLOT_FLETCHING = List.of(
			EMPTY_SLOT_FEATHER, EMPTY_SLOT_PHANTOM_MEMBRANE, EMPTY_SLOT_WHEAT
			);

	private static final Identifier EMPTY_SLOT_SHAFT_STICK = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "container/slot/stick");
	private static final List<Identifier> EMPTY_SLOT_SHAFT = List.of(EMPTY_SLOT_SHAFT_STICK);

	private static final Identifier EMPTY_SLOT_FLINT_TIP = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "container/slot/flint");
	private static final Identifier EMPTY_SLOT_PRISMARINE_TIP = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "container/slot/prismarine_shard");
	private static final Identifier EMPTY_SLOT_ECHO_TIP = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "container/slot/echo_shard");
	private static final Identifier EMPTY_SLOT_AMETHYST_TIP = Identifier.withDefaultNamespace("container/slot/amethyst_shard");
	private static final List<Identifier> EMPTY_SLOT_TIP = List.of(
			EMPTY_SLOT_FLINT_TIP, EMPTY_SLOT_PRISMARINE_TIP, EMPTY_SLOT_ECHO_TIP, EMPTY_SLOT_AMETHYST_TIP
			);

	private static final Identifier EMPTY_SLOT_SLIME_BALL = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "container/slot/slime_ball");
	private static final Identifier EMPTY_SLOT_GUN_POWDER = Identifier.withDefaultNamespace("container/slot/redstone_dust");
	private static final Identifier EMPTY_SLOT_POTION = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "container/slot/potion");
	private static final Identifier EMPTY_SLOT_SPALSH_POTION = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "container/slot/splash_potion");
	private static final Identifier EMPTY_SLOT_LINGERING_POTION = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "container/slot/lingering_potion");
	private static final List<Identifier> EMPTY_SLOT_ADDITION = List.of(
			EMPTY_SLOT_SLIME_BALL, EMPTY_SLOT_GUN_POWDER, EMPTY_SLOT_POTION, EMPTY_SLOT_SPALSH_POTION, EMPTY_SLOT_LINGERING_POTION
			);


	private final CyclingSlotBackground fletchingIcon = new CyclingSlotBackground(0);
	private final CyclingSlotBackground shaftIcon = new CyclingSlotBackground(1);
	private final CyclingSlotBackground tipIcon= new CyclingSlotBackground(2);
	private final CyclingSlotBackground additionIcon= new CyclingSlotBackground(3);


	public FletchingScreen(FletchingMenu itemCombinerMenu, Inventory inventory, Component component) {
		super(itemCombinerMenu, inventory, component, TABLE_LOCATION);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		this.fletchingIcon.tick(EMPTY_SLOT_FLETCHING);
		this.shaftIcon.tick(EMPTY_SLOT_SHAFT);
		this.tipIcon.tick(EMPTY_SLOT_TIP);
		this.additionIcon.tick(EMPTY_SLOT_ADDITION);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
		super.renderBg(guiGraphics, f, i, j);
		this.fletchingIcon.render(this.menu, guiGraphics, f, this.leftPos, this.topPos);
		this.shaftIcon.render(this.menu, guiGraphics, f, this.leftPos, this.topPos);
		this.tipIcon.render(this.menu, guiGraphics, f, this.leftPos, this.topPos);
		this.additionIcon.render(menu, guiGraphics, f, this.leftPos, this.topPos);
	}

	@Override
	protected void renderErrorIcon(GuiGraphics guiGraphics, int i, int j) {
	}

}