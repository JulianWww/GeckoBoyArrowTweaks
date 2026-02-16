package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing;

import org.jetbrains.annotations.Nullable;

import com.ibm.icu.impl.Pair;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public interface HomingTarget {
	boolean isPresent();
	@Nullable
	Pair<Vec3, Vec3> getAimData(Level level);
	HomingTargetType<?> getType();
	void encode(RegistryFriendlyByteBuf buff);


}
