package co.aethre.pebblism_core.mixin;

import dev.mayaqq.estrogen.client.Dash;
import dev.mayaqq.estrogen.client.registry.EstrogenClientEvents;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// extremely temporary fix for a crash that has been merged into the estrogen repo but not yet released in a build
@Mixin(EstrogenClientEvents.class)
abstract class EstrogenClientEventsMixin {
	@Inject(
			method = "onDisconnect",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private static void pebblism_core$onDisconnect(CallbackInfo ci) {
		Dash.uwufy = false;
		ci.cancel();
	}
}
