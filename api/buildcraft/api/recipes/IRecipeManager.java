/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.recipes;

import java.util.Collection;

public interface IRecipeManager<T> {

    /** Add a recipe.
     *
     * @param input Object... containing either an ItemStack, or a paired string and integer(ex: "dyeBlue", 1)
     * @param powerCost MJ cost to produce
     * @param output resulting ItemStack */
    void addRecipe(String id, long powerCost, T output, Object... input);

    void addRecipe(String id, long powerCost, int craftingDelay, T output, Object... input);

    void addRecipe(IFlexibleRecipe<T> recipe);

    void removeRecipe(String id);

    void removeRecipe(IFlexibleRecipe<T> recipe);

    Collection<IFlexibleRecipe<T>> getRecipes();

    IFlexibleRecipe<T> getRecipe(String id);
}
