package net.denanu.geckoboyarrowtweaks.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.model.animal.llama.LlamaModel;
import net.minecraft.client.model.geom.ModelPart;

@Mixin(LlamaModel.class)
public interface LlamaModelAccessor {
	@Accessor("head")
	public ModelPart getHead();
}
