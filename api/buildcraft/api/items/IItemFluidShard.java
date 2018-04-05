package buildcraft.api.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

public interface IItemFluidShard {
    void addFluidDrops(List<ItemStack> toDrop, @Nullable FluidStack fluid);
}
