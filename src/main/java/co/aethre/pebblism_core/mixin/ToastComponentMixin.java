package co.aethre.pebblism_core.mixin;

import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;

import net.minecraft.client.gui.components.toasts.TutorialToast;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToastComponent.class)
abstract class ToastComponentMixin {
	@Inject(
			method = "addToast",
			at = @At("HEAD"),
			cancellable = true
	)
	private void pebblism_core$addToast(Toast toast, CallbackInfo ci) {
		// refuse to add tutorial toasts
		if (toast instanceof TutorialToast) ci.cancel();
	}
}
