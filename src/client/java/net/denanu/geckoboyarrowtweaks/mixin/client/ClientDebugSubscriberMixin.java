package net.denanu.geckoboyarrowtweaks.mixin.client;

import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.multiplayer.ClientDebugSubscriber;
import net.minecraft.util.debug.DebugSubscription;

@Mixin(ClientDebugSubscriber.class)
public class ClientDebugSubscriberMixin {
	@Inject(method="requestedSubscriptions()Ljava/util/Set", at=@At("TAIL"))
	public void requestedSubscriptions(CallbackInfoReturnable<Set<DebugSubscription<?>>> ci, @Local Set<DebugSubscription<?>> set) {
	}
}
