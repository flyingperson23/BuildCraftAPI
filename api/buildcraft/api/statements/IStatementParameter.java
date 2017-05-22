/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.statements;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IStatementParameter extends IGuiSlot {

    /** @return An itemstack to render for this parameter, or {@link ItemStack#EMPTY} if this should not render an
     *         itemstack. */
    @Nonnull
    ItemStack getItemStack();

    /** Return a non-null value to be set as the statement parameter if you handled the mouse click and do not want all
     * possible values to be shown, or null if you did nothing and wish to show all possible values.
     * 
     * @see #getPossible(IStatementContainer, IStatement) */
    IStatementParameter onClick(IStatementContainer source, IStatement stmt, ItemStack stack, StatementMouseClick mouse);

    void writeToNbt(NBTTagCompound compound);

    /** This returns the parameter after a left rotation. Used in particular in blueprints orientation. */
    IStatementParameter rotateLeft();

    IStatementParameter[] getPossible(IStatementContainer source, IStatement stmt);
}
