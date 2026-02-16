package net.denanu.geckoboyarrowtweaks.serializers;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.ArrowComposition;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTarget;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTargetTypes;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricTrackedDataRegistry;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.Identifier;

public class ModEntityDataSerializers {
	public static final EntityDataSerializer<Boolean> IS_UNDERWATER_ARROW = EntityDataSerializer.forValueType(ByteBufCodecs.BOOL);
	public static final EntityDataSerializer<Boolean> IS_BOUNCY_ARROW = EntityDataSerializer.forValueType(ByteBufCodecs.BOOL);
	public static final EntityDataSerializer<HomingTarget> HOMING_TARGET = EntityDataSerializer.forValueType(HomingTargetTypes.STREAM_CODEC);
	public static final EntityDataSerializer<ArrowComposition> ARROW_COMPOSITION = EntityDataSerializer.forValueType(ArrowComposition.STREAM_CODEC);


	public static void registerSerializer(String type, EntityDataSerializer<?> entityDataSerializer) {
		FabricTrackedDataRegistry.register(Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, type), entityDataSerializer);
	}

	static {
		registerSerializer("is_underwater_arrow", IS_UNDERWATER_ARROW);
		registerSerializer("is_bouncy_arrow", IS_BOUNCY_ARROW);
		registerSerializer("homing_target", HOMING_TARGET);
		registerSerializer("arrow_type", ARROW_COMPOSITION);
	}

	public static void load () {

	}
}
