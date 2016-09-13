package buildcraft.api.recipes;

import com.google.common.collect.ImmutableSet;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

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

    public AssemblyRecipe(NBTTagCompound nbt) {
        requiredMicroJoules = nbt.getLong("required_micro_joules");
        NBTTagList requiredStacksTag = nbt.getTagList("required_stacks", Constants.NBT.TAG_COMPOUND);
        ItemStack[] requiredStacksArray = new ItemStack[requiredStacksTag.tagCount()];
        for(int i = 0; i < requiredStacksArray.length; i++) {
            requiredStacksArray[i] = ItemStack.loadItemStackFromNBT(requiredStacksTag.getCompoundTagAt(i));
        }
        requiredStacks = ImmutableSet.copyOf(requiredStacksArray);
        output = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("output"));
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setLong("required_micro_joules", requiredMicroJoules);
        NBTTagList requiredStacksTag = new NBTTagList();
        for(ItemStack requiredStack : requiredStacks) {
            requiredStacksTag.appendTag(requiredStack.writeToNBT(new NBTTagCompound()));
        }
        nbt.setTag("required_stacks", requiredStacksTag);
        nbt.setTag("output", output.writeToNBT(new NBTTagCompound()));
        return nbt;
    }
}
