package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing;

import com.mojang.serialization.MapCodec;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public record HomingTargetType<T extends HomingTarget>(MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, ? extends HomingTarget> stream_codec) {

	public static final ResourceKey<Registry<HomingTargetType<?>>> KEY = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, "homing_type"));
	public static final Registry<HomingTargetType<?>> REGISTRY = FabricRegistryBuilder.createSimple(KEY).attribute(RegistryAttribute.SYNCED).buildAndRegister();
}
