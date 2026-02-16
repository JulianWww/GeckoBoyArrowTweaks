package net.denanu.geckoboyarrowtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.entity.projectile.arrow.AbstractArrow;

@Mixin(AbstractArrow.class)
public interface AbstractArrowAccessor {
	@Accessor("baseDamage")
	public double geckoboyarrowtweaks$getGetBaseDamage();

	@Invoker("setPierceLevel")
	public void geckoboyarrowtweaks$setPierceLevel(byte b);
}
