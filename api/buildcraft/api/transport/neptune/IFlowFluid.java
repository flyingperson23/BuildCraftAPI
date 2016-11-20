package buildcraft.api.transport.neptune;

import net.minecraft.util.EnumFacing;

import net.minecraftforge.fluids.FluidStack;

public interface IFlowFluid {
    /** @param millibuckets
     * @param from
     * @param filter The fluidstack that the extracted fluid must match, or null for any fluid.
     * @return The fluidstack extracted and inserted into the pipe. */
    int tryExtractFluid(int millibuckets, EnumFacing from, FluidStack filter);
}
