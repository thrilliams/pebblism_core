package co.aethre.pebblism_core.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import co.aethre.pebblism_core.PebblismCore;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;

@Mixin(Level.class)
abstract class LevelMixin {
	@Shadow public abstract GameRules getGameRules();
	@Shadow public abstract Explosion explode(@Nullable Entity source, @Nullable DamageSource damageSource, @Nullable ExplosionDamageCalculator damageCalculator, double x, double y, double z, float radius, boolean fire, ExplosionInteraction explosionInteraction, boolean spawnParticles);

	@Inject(
			method = "explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;Z)Lnet/minecraft/world/level/Explosion;",
			at = @At("HEAD"),
			cancellable = true
	)
	private void pebblism_core$explode(@Nullable Entity source, @Nullable DamageSource damageSource, @Nullable ExplosionDamageCalculator damageCalculator, double x, double y, double z, float radius, boolean fire, ExplosionInteraction explosionInteraction, boolean spawnParticles, CallbackInfoReturnable<Explosion> ci) {
		if (explosionInteraction.equals(ExplosionInteraction.MOB) && !this.getGameRules().getBoolean(PebblismCore.RULE_DO_MOB_EXPLOSION_BLOCK_DAMAGE)) {
			ci.setReturnValue(this.explode(source, damageSource, damageCalculator, x, y, z, radius, fire, ExplosionInteraction.NONE, spawnParticles));
		}
	}
}
