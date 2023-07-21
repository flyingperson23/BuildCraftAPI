package buildcraft.api.transport.pipe;

import net.minecraft.util.EnumFacing;

import buildcraft.api.mj.MjAPI;

public abstract class PipeEventPower extends PipeEvent {
    public final IFlowPower flow;

    protected PipeEventPower(IPipeHolder holder, IFlowPower flow) {
        super(holder);
        this.flow = flow;
    }

    protected PipeEventPower(boolean canBeCancelled, IPipeHolder holder, IFlowPower flow) {
        super(canBeCancelled, holder);
        this.flow = flow;
    }

    public static class Configure extends PipeEventPower {
        private int maxPower = 100;
        private boolean receiver = false;

        public Configure(IPipeHolder holder, IFlowPower flow) {
            super(holder, flow);
        }

        public int getMaxPower() {
            return this.maxPower;
        }

        public void setMaxPower(int maxPower) {
            this.maxPower = maxPower;
        }
        public boolean isReceiver() {
            return this.receiver;
        }

        /** Sets this pipe to be one that receives power from external sources. */
        public void setReceiver(boolean receiver) {
            this.receiver = receiver;
        }
    }

    public static class PrimaryDirection extends PipeEventPower {
        private EnumFacing facing;

        public PrimaryDirection(IPipeHolder holder, IFlowPower flow, EnumFacing facing) {
            super(holder, flow);
            this.facing = facing;
        }

        public EnumFacing getFacing() {
            return facing;
        }

        public void setFacing(EnumFacing facing) {
            this.facing = facing;
        }
    }
}
