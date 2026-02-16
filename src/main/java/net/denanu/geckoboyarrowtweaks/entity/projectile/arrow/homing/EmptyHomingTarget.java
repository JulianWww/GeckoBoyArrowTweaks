package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing;

import java.util.stream.Stream;

import com.ibm.icu.impl.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.RecordBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EmptyHomingTarget implements HomingTarget {
	public static final MapCodec<EmptyHomingTarget> CODEC = new MapCodec<EmptyHomingTarget>() {
		@Override
		public <T> DataResult<EmptyHomingTarget> decode(DynamicOps<T> ops, MapLike<T> input) {
			return DataResult.success(EMPTY_TARGET);
		}

		@Override
		public <T> RecordBuilder<T> encode(EmptyHomingTarget input, DynamicOps<T> ops, RecordBuilder<T> prefix) {
			return prefix;
		}

		@Override
		public <T> Stream<T> keys(DynamicOps<T> ops) {
			return Stream.empty();
		}
	};

	public static final StreamCodec<RegistryFriendlyByteBuf, EmptyHomingTarget> STREAM_CODEC = new StreamCodec<RegistryFriendlyByteBuf, EmptyHomingTarget>() {

		@Override
		public EmptyHomingTarget decode(RegistryFriendlyByteBuf buff) {
			return EMPTY_TARGET;
		}

		@Override
		public void encode(RegistryFriendlyByteBuf buff, EmptyHomingTarget target) {
		}
	};

	public static final EmptyHomingTarget EMPTY_TARGET = new EmptyHomingTarget();

	@Override
	public boolean isPresent() {
		return false;
	}

	@Override
	public Pair<Vec3, Vec3> getAimData(Level level) {
		return null;
	}

	@Override
	public HomingTargetType<EmptyHomingTarget> getType() {
		return HomingTargetTypes.EMPTY_TARGET;
	}

	@Override
	public void encode(RegistryFriendlyByteBuf buff) {
	}

}
