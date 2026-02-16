package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing;

import com.ibm.icu.impl.Pair;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BlockPosHomingTarget implements HomingTarget {
	public static final MapCodec<BlockPosHomingTarget> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			BlockPos.CODEC.fieldOf("pos").forGetter(val -> val.pos)
			).apply(instance, BlockPosHomingTarget::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, BlockPosHomingTarget> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC, target -> target.pos, BlockPosHomingTarget::new
			);

	private BlockPos pos;

	public BlockPosHomingTarget(BlockPos pos) {
		this.pos = pos;
	}

	@Override
	public boolean isPresent() {
		return true;
	}

	@Override
	public Pair<Vec3, Vec3> getAimData(Level level) {
		return Pair.of(pos.getCenter(), Vec3.ZERO);
	}

	@Override
	public HomingTargetType<BlockPosHomingTarget> getType() {
		return HomingTargetTypes.BLOCK_TARGET;
	}

	@Override
	public void encode(RegistryFriendlyByteBuf buff) {
		buff.writeBlockPos(pos);
	}
}
