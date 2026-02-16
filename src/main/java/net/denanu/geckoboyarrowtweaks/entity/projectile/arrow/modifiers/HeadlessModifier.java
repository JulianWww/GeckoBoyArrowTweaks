package net.denanu.geckoboyarrowtweaks.entity.projectile.arrow.modifiers;

public class HeadlessModifier extends ArrowDamageModifier {

	@Override
	protected double getDamageModifier() {
		return 0.1;
	}
}
