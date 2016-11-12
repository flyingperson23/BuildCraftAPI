package buildcraft.api.statements;

import net.minecraft.util.EnumFacing;

public interface ITriggerInternalSided extends IStatement {
    boolean isTriggerActive(EnumFacing side, IStatementContainer source, IStatementParameter[] parameters);
}
