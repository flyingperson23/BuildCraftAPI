package buildcraft.api.items;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IItemFluidShard {
    void addFluidDrops(List<ItemStack> toDrop, @Nullable FluidStack fluid);
}
