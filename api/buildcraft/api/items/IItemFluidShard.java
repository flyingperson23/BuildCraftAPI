package buildcraft.api.items;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public interface IItemFluidShard {
    void addFluidDrops(List<ItemStack> toDrop, @Nullable FluidStack fluid);
}
