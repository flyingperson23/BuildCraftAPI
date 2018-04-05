/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.statements;

import buildcraft.api.core.render.ISprite;
import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class StatementParameterItemStack implements IStatementParameter {
    @Nullable
    protected final ItemStack stack;

    public StatementParameterItemStack() {
        stack = null;
    }

    public StatementParameterItemStack(@Nonnull ItemStack stack) {
        this.stack = stack;
    }

    public StatementParameterItemStack(NBTTagCompound nbt) {
        stack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("stack"));
    }

    @Override
    public void writeToNbt(NBTTagCompound compound) {
        if (stack != null) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            stack.writeToNBT(tagCompound);
            compound.setTag("stack", tagCompound);
        }
    }

    @Override
    public ISprite getSprite() {
        return null;
    }

    @Override
    @Nullable
    public ItemStack getItemStack() {
        return stack;
    }

    @Override
    public StatementParameterItemStack onClick(IStatementContainer source, IStatement stmt, ItemStack stack,
        StatementMouseClick mouse) {
        if (stack == null) {
            return new StatementParameterItemStack();
        } else {
            ItemStack newStack = stack.copy();
            newStack.stackSize = 1;
            return new StatementParameterItemStack(newStack);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof StatementParameterItemStack) {
            StatementParameterItemStack param = (StatementParameterItemStack) object;

            return ItemStack.areItemStacksEqual(stack, param.stack)
                && ItemStack.areItemStackTagsEqual(stack, param.stack);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getDescription() {
        throw new UnsupportedOperationException("Don't call getDescription directly!");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public List<String> getTooltip() {
        if (stack == null) {
            return ImmutableList.of();
        }
        List<String> tooltip = stack.getTooltip(null, false);
        if (!tooltip.isEmpty()) {
            tooltip.set(0, stack.getRarity().rarityColor + tooltip.get(0));
            for (int i = 1; i < tooltip.size(); i++) {
                tooltip.set(i, TextFormatting.GRAY + tooltip.get(i));
            }
        }
        return tooltip;
    }

    @Override
    public String getUniqueTag() {
        return "buildcraft:stack";
    }

    @Override
    public IStatementParameter rotateLeft() {
        return this;
    }

    @Override
    public IStatementParameter[] getPossible(IStatementContainer source) {
        return null;
    }
}
