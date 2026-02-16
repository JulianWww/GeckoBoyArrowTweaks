package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

public class NoGravityModifier extends DefaultArrowModifier {
	@Override
	public boolean hasGravity() {
		return false;
	}
}
