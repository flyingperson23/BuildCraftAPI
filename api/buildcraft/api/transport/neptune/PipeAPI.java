package buildcraft.api.transport.neptune;

import java.util.HashMap;
import java.util.Map;

public class PipeAPI {
    public static IPipeRegistry pipeRegistry;
    public static IPluggableRegistry pluggableRegistry;
    public static PipeFlowType flowStructure;
    public static PipeFlowType flowItems;
    public static PipeFlowType flowFluids;
    public static PipeFlowType flowPower;

    /** The default transfer information used if a pipe definition has not been registered. Note that this is replaced
     * by BC|Transport to config-defined values. */
    public static FluidTransferInfo transferInfoDefault = new FluidTransferInfo(20, 10);

    public static final Map<PipeDefinition, FluidTransferInfo> fluidTransferData = new HashMap<>();

    public static FluidTransferInfo getFluidTransferInfo(PipeDefinition def) {
        FluidTransferInfo info = fluidTransferData.get(def);
        if (info == null) {
            return transferInfoDefault;
        } else {
            return info;
        }
    }

    public static class FluidTransferInfo {
        /** Controls the maximum amount of fluid that can be transfered around and out of a pipe per tick. Note that
         * this does not affect the flow rate coming into the pipe. */
        public final int transferPerTick;

        
        // Delay is per fluid???
        /** Controls how long the pipe should delay incoming fluids by. Minimum value is 1, because of the way that
         * fluids are handled internally. */
        public final int transferDelay;

        public FluidTransferInfo(int transferPerTick, int transferDelay) {
            this.transferPerTick = transferPerTick;
            if (transferDelay <= 0) {
                transferDelay = 1;
            }
            this.transferDelay = transferDelay;
        }
    }
}
