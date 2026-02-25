package net.denanu.geckoboyarrowtweaks.entity.player;

import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTarget;
import net.denanu.geckoboyarrowtweaks.serializers.ModEntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.AttackRange;

public class PlayerModifications {
	public static final AttackRange HOMING_TARGET_RANGE = new AttackRange(
			0, 128,
			0, 128,
			0, 1);


	public static final EntityDataAccessor<HomingTarget> HOMING_TARGET = SynchedEntityData.defineId(
			Player.class, ModEntityDataSerializers.HOMING_TARGET
			);
	public static final EntityDataAccessor<ItemStack> QUIVER_ITEM = SynchedEntityData.defineId(
			Player.class, EntityDataSerializers.ITEM_STACK
			);


	public static void load() {

	}
}
