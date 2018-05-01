package buildcraft.api.items;

import java.util.Objects;

import net.minecraft.item.ItemStack;

public class BCStackHelper {

    /**
     * This is just a copy of the same check used in 1.11+ that doesn't sadly exist in 1.10
     * @param stack The Itemstack to check
     * @return Returns true if the stack is null or empty
     */
    public static boolean isEmpty(ItemStack stack) {
        return Objects.isNull(stack) || stack.stackSize <= 0 || stack.isItemStackDamageable() && (stack.getItemDamage() < -32768 || stack.getItemDamage() > 65535);
    }
}