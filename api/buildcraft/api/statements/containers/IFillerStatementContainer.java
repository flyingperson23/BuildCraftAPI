package buildcraft.api.statements.containers;

import buildcraft.api.filler.IFillerPattern;
import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;

public interface IFillerStatementContainer extends IStatementContainer {
    void setPattern(IFillerPattern pattern, IStatementParameter[] params);
}
