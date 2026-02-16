package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing;

import com.mojang.serialization.Codec;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

public class HomingTargetTypes {
	public static final Codec<HomingTarget> CODEC = HomingTargetType.REGISTRY.byNameCodec().dispatch("type", HomingTarget::getType, HomingTargetType::codec);
	public static final StreamCodec<RegistryFriendlyByteBuf, HomingTarget> STREAM_CODEC = ByteBufCodecs.registry(HomingTargetType.KEY).dispatch(HomingTarget::getType, HomingTargetType::stream_codec);

	public static final HomingTargetType<BlockPosHomingTarget> BLOCK_TARGET = register("block_target",
			new HomingTargetType<BlockPosHomingTarget>(BlockPosHomingTarget.CODEC, BlockPosHomingTarget.STREAM_CODEC)
			);
	public static final HomingTargetType<EntityHomingTarget> ENTITY_TARGET = register("entity_target",
			new HomingTargetType<EntityHomingTarget>(EntityHomingTarget.CODEC, EntityHomingTarget.STREAM_CODEC)
			);
	public static final HomingTargetType<EmptyHomingTarget> EMPTY_TARGET = register("empty_target",
			new HomingTargetType<EmptyHomingTarget>(EmptyHomingTarget.CODEC, EmptyHomingTarget.STREAM_CODEC)
			);

	private static <T extends HomingTarget> HomingTargetType<T> register(String name, HomingTargetType<T> type) {
		return Registry.register(HomingTargetType.REGISTRY, Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, name), type);
	}

	public static void load() {

	}
}
