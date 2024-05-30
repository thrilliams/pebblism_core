package co.aethre.pebblism_core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kyanite.deeperdarker.util.DDArmorMaterials;
import com.simibubi.create.content.equipment.armor.NetheriteDivingHandler;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;

@Mixin(NetheriteDivingHandler.class)
abstract class NetheriteDivingHandlerMixin {
	@Inject(
			at = @At("HEAD"),
			method = "isNetheriteArmor",
			cancellable = true
	)
	private static void pebblism_core$isNetheriteArmor(ItemStack stack, CallbackInfoReturnable<Boolean> ci) {
		ci.setReturnValue(
				stack.getItem() instanceof ArmorItem armorItem &&
				(armorItem.getMaterial() == ArmorMaterials.NETHERITE || armorItem.getMaterial() == DDArmorMaterials.WARDEN)
		);
	}
}
