package buildcraft.api.transport.neptune;

import net.minecraft.util.EnumFacing;

import buildcraft.api.core.IFluidFilter;

public interface IFlowFluid {
    /** @param millibuckets
     * @param from
     * @param filter The filter to determine what can be extracted.
     * @return The fluidstack extracted and inserted into the pipe. */
    int tryExtractFluid(int millibuckets, EnumFacing from, IFluidFilter filter);
}
