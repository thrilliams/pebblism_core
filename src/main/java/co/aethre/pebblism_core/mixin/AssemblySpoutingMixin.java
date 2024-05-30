package co.aethre.pebblism_core.mixin;

import static com.simibubi.create.compat.rei.category.CreateRecipeCategory.basicSlot;
import static com.simibubi.create.compat.rei.category.CreateRecipeCategory.setFluidTooltip;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.simibubi.create.compat.rei.category.CreateRecipeCategory;
import com.simibubi.create.compat.rei.category.sequencedAssembly.ReiSequencedAssemblySubCategory;
import com.simibubi.create.content.processing.sequenced.SequencedRecipe;
import com.simibubi.create.foundation.fluid.FluidIngredient;

import me.shedaniel.math.Point;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.common.util.EntryIngredients;

@Mixin(ReiSequencedAssemblySubCategory.AssemblySpouting.class)
abstract class AssemblySpoutingMixin {
	/**
	 * @author june
	 * @reason fixes sequenced assemblies with multiple filling steps from accessing outside of a list (will be fixed in a later version of create)
	 */
	@Overwrite(remap = false)
	public int addFluidIngredients(SequencedRecipe<?> recipe, List<Widget> widgets, int x, int index, Point origin) {
		FluidIngredient fluidIngredient = recipe.getRecipe()
				.getFluidIngredients()
				.get(0);
		Slot fluidSlot = basicSlot(x + 4, 15, origin).markInput().entries(EntryIngredients.of(CreateRecipeCategory.convertToREIFluid(fluidIngredient.getMatchingFluidStacks().get(0))));
		CreateRecipeCategory.setFluidRenderRatio(fluidSlot);
		setFluidTooltip(fluidSlot);
		widgets.add(fluidSlot);
		return 1;
	}
}
