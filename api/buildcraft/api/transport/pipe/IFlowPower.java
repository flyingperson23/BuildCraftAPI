package buildcraft.api.transport.pipe;

import net.minecraft.util.EnumFacing;

import buildcraft.api.mj.IMjPassiveProvider;

public interface IFlowPower {
    /** Makes this pipe reconfigure itself, possibly due to the addition of new modules. */
    void reconfigure();

}
