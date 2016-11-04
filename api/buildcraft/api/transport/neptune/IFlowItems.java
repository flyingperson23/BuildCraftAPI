package buildcraft.api.transport.neptune;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import buildcraft.api.core.IStackFilter;

public interface IFlowItems {
    /** @param count
     * @param from
     * @param filter The filter to determine what can be extracted.
     * @return The number of items extracted. */
    int tryExtractItems(int count, EnumFacing from, IStackFilter filter);

    /** @param stack
     * @param colour
     * @param speed
     * @param from
     * @return The leftover stack. May be null. */
    ItemStack tryInsertItems(ItemStack stack, EnumDyeColor colour, double speed, EnumFacing from);
}
