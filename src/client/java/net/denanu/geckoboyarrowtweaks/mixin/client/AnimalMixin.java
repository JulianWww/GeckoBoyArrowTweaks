package net.denanu.geckoboyarrowtweaks.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import net.denanu.geckoboyarrowtweaks.entity.EatUtils;
import net.denanu.geckoboyarrowtweaks.mixin_ducts.EatUtilsProvider;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

@Mixin(Animal.class)
public abstract class AnimalMixin extends AgeableMob implements EatUtilsProvider {

	EatUtils eatUtils = new EatUtils();

	protected AnimalMixin(EntityType<? extends AgeableMob> type, Level level) {
		super(type, level);
	}
	
	@Inject(method="aiStep()V", at=@At(value="HEAD"))
	void aiStepMixin(CallbackInfo ci) {		
		this.eatUtils.aiStep(this.level());
	}
	
	@Inject(method="handleEntityEvent(B)V", at=@At(value="HEAD"))
	void handleEntityEventMixin(CallbackInfo ci, @Local byte id) {
		this.eatUtils.handleEntityEvent(id);
	}
	
	@Override
	public EatUtils getEatUtils() {
		return this.eatUtils;
	}

}
