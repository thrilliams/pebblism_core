package co.aethre.pebblism_core.mixin;

import com.simibubi.create.compat.rei.category.CreateRecipeCategory;
import com.simibubi.create.compat.rei.category.sequencedAssembly.ReiSequencedAssemblySubCategory;

import com.simibubi.create.content.processing.sequenced.SequencedRecipe;

import com.simibubi.create.foundation.fluid.FluidIngredient;

import me.shedaniel.math.Point;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.Widget;

import me.shedaniel.rei.api.common.util.EntryIngredients;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static com.simibubi.create.compat.rei.category.CreateRecipeCategory.basicSlot;
import static com.simibubi.create.compat.rei.category.CreateRecipeCategory.setFluidTooltip;

@Mixin(ReiSequencedAssemblySubCategory.AssemblySpouting.class)
abstract class AssemblySpoutingMixin {
	@Inject(
			method = "addFluidIngredients",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private void pebblism_core$addFluidIngredients(SequencedRecipe<?> recipe, List<Widget> widgets, int x, int index, Point origin, CallbackInfoReturnable<Integer> ci) {
		FluidIngredient fluidIngredient = recipe.getRecipe()
				.getFluidIngredients()
				.get(0);
		Slot fluidSlot = basicSlot(x + 4, 15, origin).markInput().entries(EntryIngredients.of(CreateRecipeCategory.convertToREIFluid(fluidIngredient.getMatchingFluidStacks().get(0))));
		CreateRecipeCategory.setFluidRenderRatio(fluidSlot);
		setFluidTooltip(fluidSlot);
		widgets.add(fluidSlot);
		ci.setReturnValue(1);
	}
}
