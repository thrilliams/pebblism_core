package co.aethre.create_enchancement.mixin;

import com.simibubi.create.AllEnchantments;

import moriyashiine.enchancement.common.init.ModEnchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
abstract class EnchantmentHelperMixin {
	@Inject(
			at = @At("HEAD"),
			method = "getItemEnchantmentLevel",
			cancellable = true
	)
	private static void create_enchancement$getItemEnchantmentLevel(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Integer> callback) {
		Enchantment capacity = AllEnchantments.CAPACITY.get();
		// if the enchantment in question is capacity and the item in question has amphibious, treat the item as having max capacity
		if (enchantment.equals(capacity) && EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.AMPHIBIOUS, stack) > 0) {
			callback.setReturnValue(capacity.getMaxLevel());
		}
	}
}
