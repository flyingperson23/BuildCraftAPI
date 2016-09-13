package buildcraft.api.recipes;

import com.google.common.collect.ImmutableSet;

import net.minecraft.item.ItemStack;

/** Provides an immutable assembly recipe */
public final class AssemblyRecipe {
    public final long requiredMicroJoules;
    public final ImmutableSet<ItemStack> requiredStacks;
    public final ItemStack output;

    public AssemblyRecipe(long requiredMicroJoules, ImmutableSet<ItemStack> requiredStacks, ItemStack output) {
        this.requiredMicroJoules = requiredMicroJoules;
        this.requiredStacks = requiredStacks;
        this.output = output;
    }
}
