package net.denanu.geckoboyarrowtweaks.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;

@Mixin(QuadrupedModel.class)
public interface QuadrupedModelAccessor {
	@Accessor("head")
	public ModelPart geckoboyarrowtewaks$getHead();
}
