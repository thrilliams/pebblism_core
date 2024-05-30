package co.aethre.pebblism_core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.simibubi.create.AllEnchantments;

import moriyashiine.enchancement.common.init.ModEnchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

@Mixin(EnchantmentHelper.class)
abstract class EnchantmentHelperMixin {
	// i would rather this were a redirect for the backtank item, but there was some lambda nonsense going on, and my
	// understanding of mixins is remedial at best. if i can change this, i would like to.
	@Inject(
			at = @At("HEAD"),
			method = "getItemEnchantmentLevel",
			cancellable = true
	)
	private static void pebblism_core$getItemEnchantmentLevel(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Integer> ci) {
		Enchantment capacity = AllEnchantments.CAPACITY.get();
		// if the enchantment in question is capacity and the item in question has amphibious, treat the item as having max capacity
		if (enchantment.equals(capacity) && EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.AMPHIBIOUS, stack) > 0) {
			ci.setReturnValue(capacity.getMaxLevel());
		}
	}
}
