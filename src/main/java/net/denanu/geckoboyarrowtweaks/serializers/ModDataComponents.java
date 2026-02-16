package net.denanu.geckoboyarrowtweaks.serializers;

import java.util.function.UnaryOperator;

import com.mojang.serialization.Codec;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class ModDataComponents {
	public static final DataComponentType<Holder<Item>> ARROW_TIP = register("arrow_tip",
			builder -> builder.persistent(Item.CODEC).networkSynchronized(Item.STREAM_CODEC)
			);

	public static final DataComponentType<Holder<Item>> ARROW_SHAFT = register("arrow_shaft",
			builder -> builder.persistent(Item.CODEC).networkSynchronized(Item.STREAM_CODEC)
			);

	public static final DataComponentType<Holder<Item>> ARROW_FLETCHING = register("arrow_fletching",
			builder -> builder.persistent(Item.CODEC).networkSynchronized(Item.STREAM_CODEC)
			);

	public static final DataComponentType<Boolean> HAS_TIP = register(
			"has_tip", builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL)
			);

	public static final DataComponentType<Holder<Item>> ARROW_ADDITION = register("arrow_addition",
			builder -> builder.persistent(Item.CODEC).networkSynchronized(Item.STREAM_CODEC)
			);


	private static <T> DataComponentType<T> register(String  id, UnaryOperator<DataComponentType.Builder<T>> builder) {
		return Registry.register(
				BuiltInRegistries.DATA_COMPONENT_TYPE,
				Identifier.fromNamespaceAndPath(GeckoBoyArrowTweaks.MOD_ID, id),
				builder.apply(DataComponentType.builder()).build()
				);
	}

	public static void load() {

	}
}
