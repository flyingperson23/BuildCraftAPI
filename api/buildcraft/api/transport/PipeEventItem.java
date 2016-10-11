package buildcraft.api.transport;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import buildcraft.transport.api_move.IFlowItems;
import buildcraft.transport.api_move.IPipeHolder;

public abstract class PipeEventItem extends PipeEvent {

    public final IFlowItems flow;

    protected PipeEventItem(IPipeHolder holder, IFlowItems flow) {
        super(holder);
        this.flow = flow;
    }

    protected PipeEventItem(boolean canBeCancelled, IPipeHolder holder, IFlowItems flow) {
        super(canBeCancelled, holder);
        this.flow = flow;
    }

    // Insertion has the following events:

    // TryInsert: See if (and how much) of a given stack can be accepted

    // SideCheck: Remove invalid sides from a set of all connected sides
    // Also can apply ordering to make items prefer some sides over some others

    /* TryBounce: Fired if SideCheck removes ALL sides, to see if the item is allowed to bounce back to where it came
     * from */

    /* Split: Split up the items into different stacks to be sent to the destinations (only the highest priority list of
     * SideCheck will be included in the output) */

    // FindDest: Finds a destination for each of the split items

    // ModifySpeed: Changes the speed of the item

    public static class TryInsert extends PipeEventItem {
        public final EnumDyeColor colour;
        public final EnumFacing from;
        /** The itemstack that is attempting to be inserted. NEVER CHANGE THIS! */
        public final ItemStack attempting;
        /** The count of items that are being accepted into this pipe. Starts off at the stack count of
         * {@link #attempting} */
        public int accepted;

        public TryInsert(IPipeHolder holder, IFlowItems flow, EnumDyeColor colour, EnumFacing from, ItemStack attempting) {
            super(true, holder, flow);
            this.colour = colour;
            this.from = from;
            this.attempting = attempting;
            this.accepted = attempting.stackSize;
        }
    }

    public static class SideCheck extends PipeEventItem {
        public final List<EnumSet<EnumFacing>> possible = new ArrayList<>();
        public final EnumDyeColor colour;
        public final EnumFacing from;
        public final ItemStack stack;

        public SideCheck(IPipeHolder holder, IFlowItems flow, EnumDyeColor colour, EnumFacing from, ItemStack stack) {
            super(holder, flow);
            this.colour = colour;
            this.from = from;
            this.stack = stack;
        }
    }

    public static class TryBounce extends PipeEventItem {
        public final EnumDyeColor colour;
        public final EnumFacing from;
        public final ItemStack stack;
        public boolean canBounce = true;// TEMP!

        public TryBounce(IPipeHolder holder, IFlowItems flow, EnumDyeColor colour, EnumFacing from, ItemStack stack) {
            super(holder, flow);
            this.colour = colour;
            this.from = from;
            this.stack = stack;
        }
    }

    public static class Split extends PipeEventItem {
        public final ImmutableList<EnumFacing> possibleDestinations;
        public final List<ItemEntry> items = new ArrayList<>();

        public Split(IPipeHolder holder, IFlowItems flow, ImmutableList<EnumFacing> possibleDestinations, ItemEntry toSplit) {
            super(holder, flow);
            this.possibleDestinations = possibleDestinations;
            items.add(toSplit);
        }
    }

    public static class FindDest extends PipeEventItem {
        public final ImmutableList<EnumFacing> possibleDestinations;
        public final ImmutableList<ItemEntry> items;

        public FindDest(IPipeHolder holder, IFlowItems flow, ImmutableList<EnumFacing> possibleDestinations, ImmutableList<ItemEntry> items) {
            super(holder, flow);
            this.possibleDestinations = possibleDestinations;
            this.items = items;
        }
    }

    /** Fired whenever an items speed needs to be adjusted - most likely when the item is inserted into a pipe. */
    public static class ModifySpeed extends PipeEventItem {
        public final ItemEntry item;
        public final double initSpeed;
        public double speed;

        public ModifySpeed(IPipeHolder holder, IFlowItems flow, ItemEntry item, double initSpeed) {
            super(holder, flow);
            this.item = item;
            this.initSpeed = initSpeed;
            this.speed = initSpeed;
        }

        public void modifyTo(double target, double maxDelta) {
            if (speed < target) {
                speed += maxDelta;
                if (speed > target) {
                    speed = target;
                }
            } else if (speed > target) {
                speed -= maxDelta;
                if (speed < target) {
                    speed = target;
                }
            }
        }
    }

    public static class ItemEntry {
        public final EnumDyeColor colour;
        public final ItemStack stack;
        public final EnumFacing from;
        public EnumFacing to;

        public ItemEntry(EnumDyeColor colour, ItemStack stack, EnumFacing from) {
            this.colour = colour;
            this.stack = stack;
            this.from = from;
        }
    }
}
