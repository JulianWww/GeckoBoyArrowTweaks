package net.denanu.geckoboyarrowtweaks.networking;

import net.denanu.geckoboyarrowtweaks.networking.c2s.SelectTargetC2SPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ModPackets {
	public static void load() {
		PayloadTypeRegistry.playC2S().register(SelectTargetC2SPayload.ID, SelectTargetC2SPayload.STREAM_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(SelectTargetC2SPayload.ID, SelectTargetC2SPayload::handel);
	}
}
