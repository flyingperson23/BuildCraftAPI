package buildcraft.api.statements.containers;

import buildcraft.api.core.IBox;
import buildcraft.api.filler.IFillerPattern;
import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;

public interface IFillerStatementContainer extends IStatementContainer {

    /** @return True if this filler has a non-zero sized box. */
    boolean hasBox();

    /** @return The box that the filler will (default) to building in.
     * 
     * @throws IllegalStateException if {@link #hasBox()} returns false. */
    IBox getBox() throws IllegalStateException;

    void setPattern(IFillerPattern pattern, IStatementParameter[] params);
}
