package net.denanu.geckoboyarrowtweaks.networking.c2s;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.entity.player.PlayerModifications;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTarget;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTargetTypes;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record SelectTargetC2SPayload(HomingTarget target) implements CustomPacketPayload {
	private static final Identifier KEY = Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "choose_target");
	public static final CustomPacketPayload.Type<SelectTargetC2SPayload> ID = new CustomPacketPayload.Type<>(KEY);
	public static final StreamCodec<RegistryFriendlyByteBuf, SelectTargetC2SPayload> STREAM_CODEC = StreamCodec.composite(HomingTargetTypes.STREAM_CODEC, SelectTargetC2SPayload::target, SelectTargetC2SPayload::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return ID;
	}

	public static void handel(SelectTargetC2SPayload payload, ServerPlayNetworking.Context context) {
		context.player().getEntityData().set(PlayerModifications.HOMING_TARGET, payload.target);
	}
}
