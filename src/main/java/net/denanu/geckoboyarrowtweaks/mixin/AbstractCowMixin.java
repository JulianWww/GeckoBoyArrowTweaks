package net.denanu.geckoboyarrowtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.denanu.geckoboyarrowtweaks.entity.ai.goal.MoveTowardsHayArrowGoal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.cow.AbstractCow;

@Mixin(AbstractCow.class)
public class AbstractCowMixin extends MobMixin {
	@Inject(method="registerGoals()V", at=@At(value="TAIL"))
	void registerGoals(CallbackInfo ci) {
		PathfinderMob mob = (PathfinderMob)(Object)this;
		this.goalSelector.addGoal(4, new MoveTowardsHayArrowGoal(mob, 1, 32));
	}
}
