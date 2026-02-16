package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow;

import net.denanu.geckoboyarrowtweaks.utils.ModItemUtils;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public record ArrowComposition(Holder<Item> fletching, Holder<Item> shaft, Holder<Item> tip, Holder<Item> addition, boolean hasTip) {
	public static final StreamCodec<RegistryFriendlyByteBuf, ArrowComposition> STREAM_CODEC = StreamCodec.composite(
			Item.STREAM_CODEC, ArrowComposition::fletching,
			Item.STREAM_CODEC, ArrowComposition::shaft,
			Item.STREAM_CODEC, ArrowComposition::tip,
			Item.STREAM_CODEC, ArrowComposition::addition,
			ByteBufCodecs.BOOL, ArrowComposition::hasTip,
			ArrowComposition::new
			);

	public static ArrowComposition getDefault() {
		return new ArrowComposition(ModItemUtils.getHolder(Items.FEATHER), ModItemUtils.getHolder(Items.STICK), ModItemUtils.getHolder(Items.FLINT), ModItemUtils.getHolder(Items.AIR), true);
	}
}
