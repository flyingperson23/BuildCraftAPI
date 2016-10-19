package buildcraft.api.transport;

import java.util.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import buildcraft.api.transport.neptune.IFlowItems;
import buildcraft.api.transport.neptune.IPipeHolder;

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
        public final EnumDyeColor colour;
        public final EnumFacing from;
        public final ItemStack stack;

        private final int[] precedence = new int[6];
        private final EnumSet<EnumFacing> allowed = EnumSet.allOf(EnumFacing.class);

        public SideCheck(IPipeHolder holder, IFlowItems flow, EnumDyeColor colour, EnumFacing from, ItemStack stack) {
            super(holder, flow);
            this.colour = colour;
            this.from = from;
            this.stack = stack;
        }

        public void disallow(EnumFacing... sides) {
            for (EnumFacing side : sides) {
                allowed.remove(side);
            }
        }

        public void disallowAllExcept(EnumFacing... sides) {
            allowed.retainAll(Lists.newArrayList(sides));
        }

        public void disallowAll() {
            allowed.clear();
        }

        public void increasePrecedence(EnumFacing side) {
            increasePrecedence(side, 1);
        }

        public void increasePrecedence(EnumFacing side, int by) {
            precedence[side.ordinal()] += by;
        }

        public void decreasePrecedence(EnumFacing side) {
            decreasePrecedence(side, 1);
        }

        public void decreasePrecedence(EnumFacing side, int by) {
            precedence[side.ordinal()] -= by;
        }

        public List<EnumSet<EnumFacing>> getOrder() {
            int[] ordered = Arrays.copyOf(precedence, 6);
            Arrays.sort(ordered);
            int last = 0;
            List<EnumSet<EnumFacing>> list = Lists.newArrayList();
            for (int i = 0; i < 6; i++) {
                int current = ordered[i];
                if (i != 0 && current == last) {
                    continue;
                }
                last = current;
                EnumSet<EnumFacing> set = EnumSet.noneOf(EnumFacing.class);
                for (EnumFacing face : EnumFacing.VALUES) {
                    if (allowed.contains(face)) {
                        if (precedence[face.ordinal()] == current) {
                            set.add(face);
                        }
                    }
                }
                if (set.size() > 0) {
                    list.add(set);
                }
            }
            return list;
        }
    }

    public static class TryBounce extends PipeEventItem {
        public final EnumDyeColor colour;
        public final EnumFacing from;
        public final ItemStack stack;
        public boolean canBounce = false;

        public TryBounce(IPipeHolder holder, IFlowItems flow, EnumDyeColor colour, EnumFacing from, ItemStack stack) {
            super(holder, flow);
            this.colour = colour;
            this.from = from;
            this.stack = stack;
        }
    }

    public static abstract class OrderedEvent extends PipeEventItem {
        public final List<EnumSet<EnumFacing>> orderedDestinations;

        public OrderedEvent(IPipeHolder holder, IFlowItems flow, List<EnumSet<EnumFacing>> orderedDestinations) {
            super(holder, flow);
            this.orderedDestinations = orderedDestinations;
        }

        public ImmutableList<EnumFacing> generateRandomOrder() {
            ImmutableList.Builder<EnumFacing> builder = ImmutableList.builder();
            for (EnumSet<EnumFacing> set : orderedDestinations) {
                List<EnumFacing> faces = new ArrayList<>(set);
                Collections.shuffle(faces);
                builder.addAll(faces);
            }
            return builder.build();
        }
    }

    public static class Split extends OrderedEvent {
        public final List<ItemEntry> items = new ArrayList<>();

        public Split(IPipeHolder holder, IFlowItems flow, List<EnumSet<EnumFacing>> order, ItemEntry toSplit) {
            super(holder, flow, order);
            items.add(toSplit);
        }
    }

    public static class FindDest extends OrderedEvent {
        public final ImmutableList<ItemEntry> items;

        public FindDest(IPipeHolder holder, IFlowItems flow, List<EnumSet<EnumFacing>> orderedDestinations, ImmutableList<ItemEntry> items) {
            super(holder, flow, orderedDestinations);
            this.items = items;
        }
    }

    /** Fired whenever an items speed needs to be adjusted - most likely when the item is inserted into a pipe. */
    public static class ModifySpeed extends PipeEventItem {
        public final ItemEntry item;
        public final double currentSpeed;
        public double targetSpeed = 0;
        public double maxSpeedChange = 0;

        public ModifySpeed(IPipeHolder holder, IFlowItems flow, ItemEntry item, double initSpeed) {
            super(holder, flow);
            this.item = item;
            currentSpeed = initSpeed;
        }

        public void modifyTo(double target, double maxDelta) {
            targetSpeed = target;
            maxSpeedChange = maxDelta;
        }
    }

    public static class ItemEntry {
        public final EnumDyeColor colour;
        public final ItemStack stack;
        public final EnumFacing from;
        /** An list of the destinations to try, in order. */
        public List<EnumFacing> to;

        public ItemEntry(EnumDyeColor colour, ItemStack stack, EnumFacing from) {
            this.colour = colour;
            this.stack = stack;
            this.from = from;
        }
    }
}
