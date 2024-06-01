package co.aethre.pebblism_core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.hidoni.transmog.TransmogUtils;

import co.aethre.pebblism_core.PebblismCoreConfig;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

@Mixin(ItemRenderer.class)
abstract class ItemRendererMixin {
	@ModifyVariable(
			method = {
					"render",
					"getModel",
					"renderStatic(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/level/Level;III)V"
			},
			at = @At("HEAD"),
			argsOnly = true
	)
	private ItemStack replaceItemStack(ItemStack stack) {
		if (PebblismCoreConfig.onlyApplyToArmor) return stack;
		return TransmogUtils.getAppearanceStackOrOriginal(stack);
	}
}
