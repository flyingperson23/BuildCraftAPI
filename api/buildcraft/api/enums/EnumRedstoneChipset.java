package buildcraft.api.enums;

import buildcraft.api.BCItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nullable;
import java.util.Locale;

public enum EnumRedstoneChipset implements IStringSerializable {
    RED,
    IRON,
    GOLD,
    QUARTZ,
    DIAMOND;

    private final String name = name().toLowerCase(Locale.ROOT);

    @Nullable
    public ItemStack getStack(int stackSize) {
        Item chipset = BCItems.Silicon.REDSTONE_CHIPSET;
        if (chipset == null)
            throw null;

        return new ItemStack(chipset, stackSize, ordinal());
    }

    public ItemStack getStack() {
        return getStack(1);
    }

    public static EnumRedstoneChipset fromStack(ItemStack stack) {
        if (stack == null) {
            return RED;
        }
        return fromOrdinal(stack.getMetadata());
    }

    public static EnumRedstoneChipset fromOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            return RED;
        }
        return values()[ordinal];
    }

    @Override
    public String getName() {
        return name;
    }
}
