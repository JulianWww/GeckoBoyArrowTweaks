package net.denanu.geckoboyarrowtweaks.renderer.item.properties.conditional;

import org.jspecify.annotations.Nullable;

import com.google.common.collect.Iterables;
import com.mojang.serialization.MapCodec;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public record ContainsItems() implements ConditionalItemModelProperty {
	public static final MapCodec<ContainsItems> CODEC = MapCodec.unit(new ContainsItems());

	@Override
	public boolean get(ItemStack stack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity,
			int i, ItemDisplayContext itemDisplayContext) {

		return !Iterables.isEmpty(stack.get(DataComponents.CONTAINER).nonEmptyItems());
	}

	@Override
	public MapCodec<ContainsItems> type() {
		return ContainsItems.CODEC;
	}

}
