package co.aethre.create_enchancement.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.simibubi.create.AllEnchantments;
import com.simibubi.create.content.equipment.armor.BacktankUtil;

import moriyashiine.enchancement.common.init.ModEnchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

@Mixin(BacktankUtil.class)
public class BacktankUtilMixin {
	@Inject(
			at = @At("HEAD"),
			method = "maxAir(Lnet/minecraft/item/ItemStack;)I",
			cancellable = true,
			remap = false
	)
	private static void create_enchancement$maxAir(ItemStack item, CallbackInfoReturnable<Integer> info) {
		// if the item in question has amphibious, treat it as also having max capacity
		if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.AMPHIBIOUS, item) > 0) {
			info.setReturnValue(BacktankUtil.maxAir(AllEnchantments.CAPACITY.get().getMaxLevel()));
		}
	}
}
