package buildcraft.api.lists;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.item.ItemStack;

public abstract class ListMatchHandler {
    public enum Type {
        TYPE,
        MATERIAL,
        CLASS
    }

    public abstract boolean matches(Type type, @Nonnull ItemStack stack, @Nonnull ItemStack target, boolean precise);

    public abstract boolean isValidSource(Type type, @Nonnull ItemStack stack);

    /** Get custom client examples.
     * 
     * @param type
     * @param stack
     * @return A List (even empty!) if the examples satisfy this handler, null if iteration and .matches should be used
     *         instead. */
    @Nullable
    public List<ItemStack> getClientExamples(Type type, @Nonnull ItemStack stack) {
        return null;
    }
}
