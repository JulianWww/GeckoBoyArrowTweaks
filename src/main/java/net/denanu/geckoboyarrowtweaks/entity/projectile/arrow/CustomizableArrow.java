package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableByte;
import org.jspecify.annotations.Nullable;

import net.denanu.geckoboyarrowtweaks.GeckoBoyArrowTweaks;
import net.denanu.geckoboyarrowtweaks.commands.DEBUG.SetTargetCommand;
import net.denanu.geckoboyarrowtweaks.entity.ModEntities;
import net.denanu.geckoboyarrowtweaks.entity.player.PlayerModifications;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.EmptyHomingTarget;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.EntityHomingTarget;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingController;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTarget;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTargetTypes;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.BouncyModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.DefaultArrowModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.EntityGlowModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.ExplosiveModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.HayModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.HeadlessModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.HomingArrowModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.LingeringPotionModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.NoGravityModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.PhantomMembraneModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.PiercingModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.SetOnFireModifer;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.SplashPotionModifier;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers.UnderwaterModifier;
import net.denanu.geckoboyarrowtweaks.mixin.AbstractArrowAccessor;
import net.denanu.geckoboyarrowtweaks.serializers.ModDataComponents;
import net.denanu.geckoboyarrowtweaks.serializers.ModEntityDataSerializers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class CustomizableArrow extends AbstractArrow {
	public static final ArrowModifierProvider FLETCHING_MODIFIERS = new ArrowModifierProvider()
			.register(Items.FEATHER, new DefaultArrowModifier())
			.register(Items.PHANTOM_MEMBRANE, new PhantomMembraneModifier())
			.register(Items.WHEAT, new HayModifier());

	public static final ArrowModifierProvider SHAFT_MODIFIERS = new ArrowModifierProvider()
			.register(Items.STICK, new DefaultArrowModifier())
			.register(Items.BREEZE_ROD, new NoGravityModifier())
			.register(Items.BLAZE_ROD, new SetOnFireModifer());

	public static final ArrowModifierProvider TIP_MODIFIERS = new ArrowModifierProvider()
			.register(Items.FLINT, new DefaultArrowModifier())
			.register(Items.PRISMARINE_SHARD, new UnderwaterModifier())
			.register(Items.ECHO_SHARD, new HomingArrowModifier())
			.register(Items.AMETHYST_SHARD, new PiercingModifier());

	public static final ArrowModifierProvider ADDITION_MODIFIERS = new ArrowModifierProvider()
			.register(Items.SLIME_BALL, new BouncyModifier())
			.register(Items.GLOWSTONE_DUST, new EntityGlowModifier())
			.register(Items.GUNPOWDER, new ExplosiveModifier())
			.register(Items.SPLASH_POTION, new SplashPotionModifier())
			.register(Items.LINGERING_POTION, new LingeringPotionModifier());

	private static HeadlessModifier HEADLESS_ARROW_MODIFIER = new HeadlessModifier();

	private static DefaultArrowModifier getFletchingMod(ItemStack stack) {
		return FLETCHING_MODIFIERS.get(stack.get(ModDataComponents.ARROW_FLETCHING), stack);
	}

	private static DefaultArrowModifier getShaftMod(ItemStack stack) {
		return SHAFT_MODIFIERS.get(stack.get(ModDataComponents.ARROW_SHAFT), stack);
	}

	private static DefaultArrowModifier getTipMod(ItemStack stack) {
		if (!stack.get(ModDataComponents.HAS_TIP)) {
			return HEADLESS_ARROW_MODIFIER;
		}
		return TIP_MODIFIERS.get(stack.get(ModDataComponents.ARROW_TIP), stack);
	}

	private static DefaultArrowModifier getAdditionMod(ItemStack stack) {
		return ADDITION_MODIFIERS.get(stack.get(ModDataComponents.ARROW_ADDITION), stack);
	}




	// ENTITY
	private static final EntityDataAccessor<Boolean> IS_UNDERWATER_ARROW = SynchedEntityData.defineId(CustomizableArrow.class, ModEntityDataSerializers.IS_UNDERWATER_ARROW);
	private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(CustomizableArrow.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> IS_HOMING = SynchedEntityData.defineId(CustomizableArrow.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<HomingTarget> HOMING_TARGET = SynchedEntityData.defineId(CustomizableArrow.class, ModEntityDataSerializers.HOMING_TARGET);
	private static final EntityDataAccessor<ArrowComposition> ARROW_COMPOSITION = SynchedEntityData.defineId(CustomizableArrow.class, ModEntityDataSerializers.ARROW_COMPOSITION);
	//private static final EntityDataAccessor<Boolean> IS_BOUNCY_ARROW = SynchedEntityData.defineId(CustomizableArrow.class, ModEntityDataSerializers.IS_BOUNCY_ARROW);



	private List<DefaultArrowModifier> modifiers;
	private boolean attractsAnimals = false;
	private boolean isFirstImpact = true;


	public CustomizableArrow(EntityType<? extends CustomizableArrow> entityType, Level level) {
		super(entityType, level);
		this.modifiers = List.of(new DefaultArrowModifier());
		this.updateColor();
	}

	public CustomizableArrow(Level level, double x, double y, double z, ItemStack stack, @Nullable ItemStack weapon) {
		super(ModEntities.CUSTOMIZABLE_ARROW, x, y, z, level, stack, weapon);
		this.modifiers = List.of(getFletchingMod(stack), getTipMod(stack), getShaftMod(stack), getAdditionMod(stack));
		this.applyConstrantModifiers();
		this.updateColor();
	}

	public CustomizableArrow(Level level, LivingEntity owner, ItemStack stack, @Nullable ItemStack weapon) {
		super(ModEntities.CUSTOMIZABLE_ARROW, owner, level, stack, weapon);
		this.modifiers = List.of(getFletchingMod(stack), getTipMod(stack), getShaftMod(stack), getAdditionMod(stack));
		this.applyConstrantModifiers();
		this.updateColor();

		if (owner instanceof Player player) {
			this.entityData.set(HOMING_TARGET, player.getEntityData().get(PlayerModifications.HOMING_TARGET));
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(IS_UNDERWATER_ARROW, false);
		builder.define(ID_EFFECT_COLOR, -1);
		builder.define(HOMING_TARGET, EmptyHomingTarget.EMPTY_TARGET);
		builder.define(IS_HOMING, false);
		builder.define(ARROW_COMPOSITION, ArrowComposition.getDefault());
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("isFirstImpact", this.isFirstImpact);
		nbt.store("target", HomingTargetTypes.CODEC, this.getTarget());
	}

	@Override
	protected void readAdditionalSaveData(ValueInput nbt) {
		super.readAdditionalSaveData(nbt);
		this.isFirstImpact = nbt.getBooleanOr("isFirstImpact", true);

		ItemStack stack = this.getPickupItemStackOrigin();
		this.modifiers = List.of(getFletchingMod(stack), getTipMod(stack), getShaftMod(stack), getAdditionMod(stack));
		this.applyConstrantModifiers();

		this.setHomingTarget(nbt.read("target", HomingTargetTypes.CODEC).orElse(EmptyHomingTarget.EMPTY_TARGET));
	}

	protected Stream<DefaultArrowModifier> getModifierStream() {
		return this.modifiers.stream();
	}

	protected void applyConstrantModifiers() {
		MutableBoolean isUnderWater = new MutableBoolean(false);
		MutableBoolean hasGravity = new MutableBoolean(true);
		MutableBoolean doHoming = new MutableBoolean(false);
		MutableByte piercing = new MutableByte(0);
		this.getModifierStream().forEach(mod -> {
			this.attractsAnimals = this.attractsAnimals || mod.attractsAnimals();
			isUnderWater.setValue(isUnderWater.get() || mod.isUnderwaterArrow());
			hasGravity.setValue(hasGravity.get() && mod.hasGravity());
			doHoming.setValue(doHoming.get() || mod.isHoming());
			piercing.setValue(piercing.byteValue() + mod.piercing());

			GeckoBoyArrowTweaks.LOGGER.info(mod.toString());
		});
		this.entityData.set(IS_UNDERWATER_ARROW, isUnderWater.get());
		this.setNoGravity(!hasGravity.get());
		this.entityData.set(IS_HOMING, doHoming.get());

		((AbstractArrowAccessor)this).geckoboyarrowtweaks$setPierceLevel((byte) (this.getPierceLevel() + piercing.byteValue()));

		if (SetTargetCommand.TARGET != null) {
			this.setHomingTarget(new EntityHomingTarget(SetTargetCommand.TARGET));
		}

		ItemStack origin = this.getPickupItemStackOrigin();
		this.entityData.set(ARROW_COMPOSITION, new ArrowComposition(
				origin.get(ModDataComponents.ARROW_FLETCHING),
				origin.get(ModDataComponents.ARROW_SHAFT),
				origin.get(ModDataComponents.ARROW_TIP),
				origin.get(ModDataComponents.ARROW_ADDITION),
				origin.get(ModDataComponents.HAS_TIP)
				));
	}

	@Override
	public void applyOnProjectileSpawned(ServerLevel serverLevel, ItemStack itemStack) {
		super.applyOnProjectileSpawned(serverLevel, itemStack);
		this.getModifierStream().forEach(mod -> {
			this.setDeltaMovement(this.getDeltaMovement().scale(mod.speedModifier()));
		});
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(Items.ARROW);
	}

	public void setHomingTarget(HomingTarget target) {
		if (this.doHoming()) {
			this.entityData.set(HOMING_TARGET, target);
		}
	}

	public HomingTarget getTarget() {
		return this.entityData.get(HOMING_TARGET);
	}

	public ArrowComposition getComposition() {
		return this.entityData.get(ARROW_COMPOSITION);
	}

	public boolean doHoming() {
		return this.entityData.get(IS_HOMING);
	}

	public boolean attractsAnimals() {
		return this.attractsAnimals;
	}

	public boolean getIsFirstImpact() {
		return this.isFirstImpact;
	}

	@Override
	protected float getWaterInertia() {
		if (this.entityData.get(IS_UNDERWATER_ARROW)) {
			return 0.99f;
		}
		return super.getWaterInertia();
	}


	@Override
	public void tick () {
		if(!this.isInGround()) {
			HomingController.tick(this);
		}
		float xrot = this.getXRot();
		float yrot = this.getYRot();
		super.tick();
		if (this.getDeltaMovement().lengthSqr() < 0.01) {
			this.setYRot(yrot);
			this.setXRot(xrot);
			this.tickDespawn();
		}

		if (this.level().isClientSide()) {
			if (this.isInGround()) {
				if (this.inGroundTime % 5 == 0) {
					this.makeParticle(1);
				}
			} else {
				this.makeParticle(2);
			}
		} else if (this.isInGround() && this.inGroundTime != 0 && !this.getPotionContents().equals(PotionContents.EMPTY) && this.inGroundTime >= 600) {
			this.level().broadcastEntityEvent(this, (byte)0);
			this.setPickupItemStack(new ItemStack(Items.ARROW));
		}
	}

	@Override
	protected void onHit(HitResult hitResult) {
		super.onHit(hitResult);
		this.isFirstImpact = false;
	}

	@Override
	protected void onHitBlock(BlockHitResult hit) {
		MutableBoolean cancelSuper = new MutableBoolean(false);
		this.getModifierStream().forEach(mod -> {
			cancelSuper.setValue(
					mod.onHitBlock(hit, this.level(), this) || cancelSuper.get()
					);
		});
		if (cancelSuper.isFalse()) {
			super.onHitBlock(hit);
		}
		else {
			Vec3 movement = this.getDeltaMovement();
			boolean critical = this.isCritArrow();
			byte pierching = this.getPierceLevel();
			super.onHitBlock(hit);
			this.setInGround(false);
			this.setDeltaMovement(movement);
			this.setCritArrow(critical);
			((AbstractArrowAccessor)this).geckoboyarrowtweaks$setPierceLevel(pierching);
		}
	}
	@Override
	protected void onHitEntity(EntityHitResult hit) {
		this.getModifierStream().forEach(mod -> {
			mod.onHitEntity(hit, this);
		});
		super.onHitEntity(hit);
	}

	public double getBaseDamage() {
		AbstractArrowAccessor mix = (AbstractArrowAccessor)this;
		return mix.geckoboyarrowtweaks$getGetBaseDamage();
	}

	// coppied from Arrow

	public PotionContents getPotionContents() {
		return this.getPickupItemStackOrigin().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
	}

	public float getPotionDurationScale() {
		return this.getPickupItemStackOrigin().getOrDefault(DataComponents.POTION_DURATION_SCALE, 1.0F);
	}

	private void setPotionContents(PotionContents potionContents) {
		this.getPickupItemStackOrigin().set(DataComponents.POTION_CONTENTS, potionContents);
		this.updateColor();
	}

	@Override
	protected void setPickupItemStack(ItemStack itemStack) {
		super.setPickupItemStack(itemStack);
		this.updateColor();
	}

	private void updateColor() {
		PotionContents potionContents = this.getPotionContents();
		this.entityData.set(ID_EFFECT_COLOR, potionContents.equals(PotionContents.EMPTY) ? -1 : potionContents.getColor());
	}

	public void addEffect(MobEffectInstance mobEffectInstance) {
		this.setPotionContents(this.getPotionContents().withEffectAdded(mobEffectInstance));
	}

	private void makeParticle(int amount) {
		int color = this.getColor();
		if (color != -1 && amount > 0) {
			for (int k = 0; k < amount; k++) {
				this.level()
				.addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, color), this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), 0.0, 0.0, 0.0);
			}
		}
	}

	public int getColor() {
		return this.entityData.get(ID_EFFECT_COLOR);
	}

	@Override
	protected void doPostHurtEffects(LivingEntity target) {
		super.doPostHurtEffects(target);
		Entity effectSource = this.getEffectSource();
		PotionContents potionContents = this.getPotionContents();
		float duration = this.getPotionDurationScale();
		potionContents.forEachEffect(effect -> target.addEffect(effect, effectSource), duration);
	}

	@Override
	public void handleEntityEvent(byte b) {
		if (b == 0) {
			int i = this.getColor();
			if (i != -1) {
				float f = (i >> 16 & 0xFF) / 255.0F;
				float g = (i >> 8 & 0xFF) / 255.0F;
				float h = (i >> 0 & 0xFF) / 255.0F;

				for (int j = 0; j < 20; j++) {
					this.level()
					.addParticle(
							ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, f, g, h), this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), 0.0, 0.0, 0.0
							);
				}
			}
		} else {
			super.handleEntityEvent(b);
		}
	}
}
