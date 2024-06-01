package co.aethre.pebblism_core.mixin;

import co.aethre.pebblism_core.PebblismCore;

import com.bawnorton.mixinsquared.TargetHandler;

import moriyashiine.enchancement.common.init.ModEnchantments;
import net.minecraft.client.renderer.LightTexture;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import net.minecraft.world.item.enchantment.EnchantmentHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = LightTexture.class, priority = 1100)
public class LightmapTextureManagerMixin {
	@TargetHandler(
			mixin = "moriyashiine.enchancement.mixin.perception.client.LightmapTextureManagerMixin",
			name = "enchancement$perception"
	)
	@Redirect(
			method = "@MixinSquared:Handler",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/entity/LivingEntity;)I"
			)
	)
	private int getEnchantmentLevel(Enchantment enchantment, LivingEntity livingEntity) {
		if (!enchantment.equals(ModEnchantments.PERCEPTION) || !(livingEntity instanceof Player player))
			return EnchantmentHelper.getEnchantmentLevel(enchantment, livingEntity);

		PebblismCore.LOGGER.info("successfully redirected");

		ItemStack helmet = ((NonNullList<ItemStack>) player.getArmorSlots()).get(EquipmentSlot.HEAD.getIndex());
		return EnchantmentHelper.getItemEnchantmentLevel(enchantment, helmet);
	}
}
