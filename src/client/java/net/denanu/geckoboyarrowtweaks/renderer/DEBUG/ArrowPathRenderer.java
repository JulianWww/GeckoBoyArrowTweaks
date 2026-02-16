package net.denanu.geckoboyarrowtweaks.renderer.DEBUG;

import org.jetbrains.annotations.Nullable;

import com.ibm.icu.impl.Pair;

import net.denanu.geckoboyarrowtweaks.entity.player.PlayerModifications;
import net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.homing.HomingTarget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.util.ARGB;
import net.minecraft.util.debug.DebugValueAccess;
import net.minecraft.world.phys.Vec3;

@Environment(EnvType.CLIENT)
public class ArrowPathRenderer implements DebugRenderer.SimpleDebugRenderer  {
	private static final int COLOR = ARGB.color(255, 0, 0);


	public ArrowPathRenderer() {
	}

	@Override
	public void emitGizmos(double d, double e, double f, DebugValueAccess debugValueAccess, Frustum frustum, float g) {
		HomingTarget target = Minecraft.getInstance().player.getEntityData().get(PlayerModifications.HOMING_TARGET);
		if (!target.isPresent()) {
			return;
		}

		@Nullable
		Pair<Vec3, Vec3> aimData = target.getAimData(Minecraft.getInstance().level);
		if (aimData == null) {
			return;
		}
		Gizmos.line(aimData.first, aimData.first.add(0, 3, 0), COLOR);


	}

}
