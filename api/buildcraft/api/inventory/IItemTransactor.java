package buildcraft.api.inventory;

import javax.annotation.Nullable;
import java.util.List;

import com.google.common.collect.Lists;

import buildcraft.api.core.IStackFilter;
import buildcraft.api.items.BCStackHelper;
import net.minecraft.item.ItemStack;


/** A simple way to define something that deals with item insertion and extraction, without caring about slots. */
public interface IItemTransactor {
    /** @param stack The stack to insert. Must not be null!
     * @param allOrNone If true then either the entire stack will be used or none of it.
     * @param simulate If true then the in-world state of this will not be changed.
     * @return The overflow stack. Will be null if all of it was accepted. */
    @Nullable
    ItemStack insert(@Nullable ItemStack stack, boolean allOrNone, boolean simulate);

    /** Similar to {@link #insert(ItemStack, boolean, boolean)} but probably be more efficient at inserting lots of
     * items.
     *
     * @param stacks The stacks to insert. Must not be null!
     * @param simulate If true then the in-world state of this will not be changed.
     * @return The overflow stacks. Will be an empty list if all of it was accepted. */
    default List<ItemStack> insert(List<ItemStack> stacks, boolean simulate) {
        List<ItemStack> leftOver = Lists.newArrayList();
        for (ItemStack stack : stacks) {
            ItemStack leftOverStack = insert(stack, false, simulate);
            if (!BCStackHelper.isEmpty(leftOverStack)) {
                leftOver.add(leftOverStack);
            }
        }
        return leftOver;
    }

    /** Extracts a number of items that match the given filter
     *
     * @param filter The filter that MUST be met by the extracted stack. Null means no filter - it can be any item.
     * @param min The minimum number of items to extract, or 0 if not enough items can be extracted
     * @param max The maximum number of items to extract.
     * @param simulate If true then the in-world state of this will not be changed.
     * @return The stack that was extracted, or null if it could not be. */
    @Nullable
    ItemStack extract(@Nullable IStackFilter filter, int min, int max, boolean simulate);

    default boolean canFullyAccept(@Nullable ItemStack stack) {
        return BCStackHelper.isEmpty(insert(stack, true, true));
    }

    default boolean canPartiallyAccept(@Nullable ItemStack stack) {
        return BCStackHelper.isEmpty(stack) || insert(stack, false, true).stackSize < stack.stackSize;
    }

    @FunctionalInterface
    interface IItemInsertable extends IItemTransactor {
        @Nullable
        @Override
        default ItemStack extract(IStackFilter filter, int min, int max, boolean simulate) {
            return null;
        }
    }

    @FunctionalInterface
    interface IItemExtractable extends IItemTransactor {
        @Nullable
        @Override
        default ItemStack insert(@Nullable ItemStack stack, boolean allOrNone, boolean simulate) {
            return stack;
        }
    }
}
