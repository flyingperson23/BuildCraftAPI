/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.statements;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

import buildcraft.api.statements.StatementManager.IParamReaderBuf;
import buildcraft.api.statements.StatementManager.IParameterReader;

public interface IStatementParameter extends IGuiSlot {

    /** @return An {@link ItemStack} to render for this parameter, or {@link ItemStack#EMPTY} if this should not render
     *         an {@link ItemStack}. */
    @Nonnull
    ItemStack getItemStack();

    /** Return a non-null value to be set as the statement parameter if you handled the mouse click and do not want all
     * possible values to be shown, or null if you did nothing and wish to show all possible values.
     * 
     * @see #getPossible(IStatementContainer) */
    IStatementParameter onClick(IStatementContainer source, IStatement stmt, ItemStack stack,
        StatementMouseClick mouse);

    void writeToNbt(NBTTagCompound compound);

    /** Writes this parameter to the given {@link PacketBuffer}. The default implementation writes out the value of
     * {@link #writeToNbt(NBTTagCompound)}, and that will be passed back into
     * {@link IParameterReader#readFromNbt(NBTTagCompound)}.
     * <p>
     * It is likely that implementors can write a more compact form of themselves, so they are encouraged to override
     * this and also register an {@link IParamReaderBuf} in
     * {@link StatementManager#registerParameter(String, IParamReaderBuf)} or
     * {@link StatementManager#registerParameter(IParameterReader, IParamReaderBuf)} */
    default void writeToBuf(PacketBuffer buffer) {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNbt(nbt);
        buffer.writeCompoundTag(nbt);
    }

    /** This returns the parameter after a left rotation. Used in particular in blueprints orientation. */
    IStatementParameter rotateLeft();

    IStatementParameter[] getPossible(IStatementContainer source);

    /** Checks to see if this parameter is a parameter for the given statement, in the given index. This is called when
     * the main statement is changed (for example when the player selects a new statement via a GUI).
     * 
     * @param statment
     * @param index
     * @return A parameter suitable for the statement, or null if this could not be changed. NOTE: it is EXTEREMLY
     *         important that the statement agrees with the returned statement, as otherwise it will crash! It is much
     *         safer to return null than to blindly return a value. */
    default IStatementParameter convertForNewStatement(IStatement statment, int index) {
        return null;
    }
}
