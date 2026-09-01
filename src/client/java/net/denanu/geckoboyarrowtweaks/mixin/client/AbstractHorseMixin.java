package net.denanu.geckoboyarrowtweaks.mixin.client;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.denanu.geckoboyarrowtweaks.entity.EatUtils;
import net.denanu.geckoboyarrowtweaks.mixin_ducts.EatUtilsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.level.Level;

@Mixin(AbstractHorse.class)
public abstract class AbstractHorseMixin extends Animal {
	protected AbstractHorseMixin(EntityType<? extends Animal> type, Level level) {
		super(type, level);
	}

	@Inject(method="<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V", at=@At(value="TAIL"))
	void initMixin(CallbackInfo ci) {
		EatUtils eatUtils = ((EatUtilsProvider)this).getEatUtils();
		AbstractHorse horse = (AbstractHorse)(Object)this;
		
		eatUtils.startEatingCallback = Optional.of(() -> horse.setEating(true));
		eatUtils.stopEatingCallback = Optional.of(() -> horse.setEating(false));
	}
}
