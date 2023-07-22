package buildcraft.api.recipes;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import net.minecraftforge.fluids.FluidStack;

public interface IRefineryRecipeManager {
    IDistillationRecipe createDistillationRecipe(FluidStack in, FluidStack outLiquid, long powerRequired);

    default IDistillationRecipe addDistillationRecipe(FluidStack in, FluidStack outLiquid, long powerRequired) {
        return getDistillationRegistry().addRecipe(createDistillationRecipe(in, outLiquid, powerRequired));
    }

    default IDistillationRecipe addDistillationRecipe(FluidStack in, FluidStack outLiquid, FluidStack outLiquid2, long powerRequired) {
        return getDistillationRegistry().addRecipe(createDistillationRecipe(in, new FluidStack(outLiquid.getFluid(), outLiquid.amount+outLiquid2.amount), powerRequired));
    }


    IRefineryRegistry<IDistillationRecipe> getDistillationRegistry();

    interface IRefineryRegistry<R extends IRefineryRecipe> {
        /** @return an unmodifiable collection containing all of the distillation recipes that satisfy the given
         *         predicate. All of the recipe objects are guaranteed to never be null. */
        Stream<R> getRecipes(Predicate<R> toReturn);

        /** @return an unmodifiable set containing all of the distillation recipes. */
        Collection<R> getAllRecipes();

        @Nullable
        R getRecipeForInput(@Nullable FluidStack fluid);

        Collection<R> removeRecipes(Predicate<R> toRemove);

        /** Adds the given recipe to the registry. Note that this will remove any existing recipes for the passed
         * recipe's {@link IRefineryRecipe#in()}
         * 
         * @param recipe The recipe to add.
         * @return The input recipe. */
        R addRecipe(R recipe);
    }

    interface IRefineryRecipe {
        FluidStack in();
    }

    interface IDistillationRecipe extends IRefineryRecipe {
        long powerRequired();
        FluidStack outLiquid();
    }
}
