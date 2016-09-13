package buildcraft.api.recipes;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

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

    public IntegrationRecipe(NBTTagCompound nbt) {
        requiredMicroJoules = nbt.getLong("required_micro_joules");
        target = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("target"));
        NBTTagList toIntegrateTag = nbt.getTagList("to_integrate", Constants.NBT.TAG_COMPOUND);
        ItemStack[] toIntegrateArray = new ItemStack[toIntegrateTag.tagCount()];
        for(int i = 0; i < toIntegrateArray.length; i++) {
            toIntegrateArray[i] = ItemStack.loadItemStackFromNBT(toIntegrateTag.getCompoundTagAt(i));
        }
        toIntegrate = ImmutableList.copyOf(toIntegrateArray);
        output = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("output"));
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setLong("required_micro_joules", requiredMicroJoules);
        nbt.setTag("target", target.writeToNBT(new NBTTagCompound()));
        NBTTagList toIntegrateTag = new NBTTagList();
        for(ItemStack toIntegrateElement : toIntegrate) {
            toIntegrateTag.appendTag(toIntegrateElement.writeToNBT(new NBTTagCompound()));
        }
        nbt.setTag("to_integrate", toIntegrateTag);
        nbt.setTag("output", output.writeToNBT(new NBTTagCompound()));
        return nbt;
    }
}
