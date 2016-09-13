package buildcraft.api.recipes;

import com.google.common.collect.ImmutableList;

import net.minecraft.item.ItemStack;

public final class IntegrationRecipe {
    public final long requiredMicroJoules;
    public final ItemStack target;
    public final ImmutableList<ItemStack> toIntegrate;
    public final ItemStack output;

    public IntegrationRecipe(long requiredMicroJoules, ItemStack target, ImmutableList<ItemStack> toIntegrate, ItemStack output) {
        this.requiredMicroJoules = requiredMicroJoules;
        this.target = target;
        this.toIntegrate = toIntegrate;
        this.output = output;
    }
}
