package co.aethre.pebblism_core.mixin;

import static net.Pandarix.betterarcheology.item.ModItems.TORRENT_TOTEM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TridentItem;

@Mixin(TridentItem.class)
abstract class TridentItemMixin {
	@Redirect(
			method = { "use", "releaseUsing" },
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/entity/player/Player;isInWaterOrRain()Z"
			)
	)
	private boolean pebblism_core$isInWaterOrRain(Player player) {
		return TrinketsApi
				// get the player's trinkets
				.getTrinketComponent(player)
				// if they have any, treat them as being in water or rain if they have a torrent totem
				.map(trinketComponent ->
						!trinketComponent.getEquipped(TORRENT_TOTEM).isEmpty()
								|| player.isInWaterOrRain()
				)
				// otherwise revert to default behavior
				.orElseGet(player::isInWaterOrRain);
	}
}
