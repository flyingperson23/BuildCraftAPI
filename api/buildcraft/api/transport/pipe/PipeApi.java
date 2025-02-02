package buildcraft.api.transport.pipe;

import java.util.IdentityHashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraftforge.common.capabilities.Capability;

import buildcraft.api.core.CapabilitiesHelper;
import buildcraft.api.mj.MjAPI;
import buildcraft.api.transport.IInjectable;
import buildcraft.api.transport.IStripesRegistry;
import buildcraft.api.transport.pluggable.IPluggableRegistry;
import buildcraft.api.transport.pluggable.PipePluggable;

/** The central holding class for all pipe related registers and methods. */
public final class PipeApi {
    public static IPipeRegistry pipeRegistry;
    public static IPluggableRegistry pluggableRegistry;
    public static IStripesRegistry stripeRegistry;
    public static IPipeExtensionManager extensionManager;
    public static PipeFlowType flowStructure;
    public static PipeFlowType flowItems;
    public static PipeFlowType flowFluids;
    public static PipeFlowType flowPower;

    /** The default transfer information used if a pipe definition has not been registered. Note that this is replaced
     * by BuildCraft Transport to config-defined values. */
    public static FluidTransferInfo fluidInfoDefault = new FluidTransferInfo(20, 10);

    /** The default transfer information used if a pipe definition has not been registered. Note that this is replaced
     * by BuildCraft Transport to config-defined values. */
    public static PowerTransferInfo powerInfoDefault = PowerTransferInfo.create(80, false);

    public static final Map<PipeDefinition, FluidTransferInfo> fluidTransferData = new IdentityHashMap<>();
    public static final Map<PipeDefinition, PowerTransferInfo> powerTransferData = new IdentityHashMap<>();

    @Nonnull
    public static final Capability<IPipeHolder> CAP_PIPE_HOLDER;

    @Nonnull
    public static final Capability<IPipe> CAP_PIPE;

    @Nonnull
    public static final Capability<PipePluggable> CAP_PLUG;

    @Nonnull
    public static final Capability<IInjectable> CAP_INJECTABLE;

    public static FluidTransferInfo getFluidTransferInfo(PipeDefinition def) {
        FluidTransferInfo info = fluidTransferData.get(def);
        if (info == null) {
            return fluidInfoDefault;
        } else {
            return info;
        }
    }

    public static PowerTransferInfo getPowerTransferInfo(PipeDefinition def) {
        PowerTransferInfo info = powerTransferData.get(def);
        if (info == null) {
            return powerInfoDefault;
        } else {
            return info;
        }
    }

    public static class FluidTransferInfo {
        /** Controls the maximum amount of fluid that can be transferred around and out of a pipe per tick. Note that
         * this does not affect the flow rate coming into the pipe. */
        public final int transferPerTick;

        /** Controls how long the pipe should delay incoming fluids by. Minimum value is 1, because of the way that
         * fluids are handled internally. This value is multiplied by the fluids viscosity, and divided by 100 to give
         * the actual delay. */
        public final double transferDelayMultiplier;

        public FluidTransferInfo(int transferPerTick, int transferDelay) {
            this.transferPerTick = transferPerTick;
            if (transferDelay <= 0) {
                transferDelay = 1;
            }
            this.transferDelayMultiplier = transferDelay;
        }
    }

    public static class PowerTransferInfo {
        public final int transferPerTick;
        public final boolean isReceiver;

        public static PowerTransferInfo create(int transferPerTick, boolean isReceiver) {
            return new PowerTransferInfo(transferPerTick, isReceiver);
        }

        public PowerTransferInfo(int transferPerTick, boolean isReceiver) {
            if (transferPerTick < 10) {
                transferPerTick = 10;
            }
            this.transferPerTick = transferPerTick;
            this.isReceiver = isReceiver;
        }
    }

    // Internals

    static {
        CAP_PIPE = CapabilitiesHelper.registerCapability(IPipe.class);
        CAP_PLUG = CapabilitiesHelper.registerCapability(PipePluggable.class);
        CAP_PIPE_HOLDER = CapabilitiesHelper.registerCapability(IPipeHolder.class);
        CAP_INJECTABLE = CapabilitiesHelper.registerCapability(IInjectable.class);
    }
}
