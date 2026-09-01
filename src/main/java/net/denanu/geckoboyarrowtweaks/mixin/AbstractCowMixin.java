package net.denanu.geckoboyarrowtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.denanu.geckoboyarrowtweaks.entity.EatUtils;
import net.denanu.geckoboyarrowtweaks.entity.ai.goal.MoveTowardsAndEatHayArrowGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.cow.AbstractCow;
import net.minecraft.world.level.Level;

@Mixin(AbstractCow.class)
public abstract class AbstractCowMixin extends Animal {
	protected AbstractCowMixin(EntityType<? extends Animal> type, Level level) {
		super(type, level);
	}

	public EatUtils eatUtils;
	
	@Inject(method="registerGoals()V", at=@At(value="TAIL"))
	void registerGoals(CallbackInfo ci) {
		PathfinderMob mob = (PathfinderMob)(Object)this;
		MoveTowardsAndEatHayArrowGoal goal = new MoveTowardsAndEatHayArrowGoal(mob, 1, 32);
		this.goalSelector.addGoal(4, goal);
	}
}
