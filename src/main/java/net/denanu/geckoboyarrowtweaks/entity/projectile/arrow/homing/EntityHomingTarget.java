package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing;

import org.jetbrains.annotations.Nullable;

import com.ibm.icu.impl.Pair;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityHomingTarget implements HomingTarget {
	public static final MapCodec<EntityHomingTarget> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			EntityReference.<Entity>codec().fieldOf("entity").forGetter(val -> val.target)
			).apply(instance, EntityHomingTarget::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, EntityHomingTarget> STREAM_CODEC = StreamCodec.composite(
			EntityReference.<Entity>streamCodec(), target -> target.target, EntityHomingTarget::new
			);

	private EntityReference<Entity> target;

	public EntityHomingTarget(EntityReference<Entity> targetId) {
		this.target = targetId;
	}

	@Override
	public boolean isPresent() {
		return true;
	}

	@Override
	@Nullable
	public Pair<Vec3, Vec3> getAimData(Level level) {
		Entity t = EntityReference.getEntity(target, level);
		if (t == null) {
			return null;
		}
		return Pair.of(t.position().add(0, t.getEyeHeight(), 0), t.getDeltaMovement());
	}

	@Override
	public HomingTargetType<EntityHomingTarget> getType() {
		return HomingTargetTypes.ENTITY_TARGET;
	}

	@Override
	public void encode(RegistryFriendlyByteBuf buff) {
		EntityReference.<Entity>streamCodec().encode(buff, this.target);
	}
}
