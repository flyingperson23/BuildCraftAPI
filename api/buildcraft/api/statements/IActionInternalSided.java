package buildcraft.api.statements;

import net.minecraft.util.EnumFacing;

public interface IActionInternalSided extends IStatement {
    void actionActivate(EnumFacing side, IStatementContainer source, IStatementParameter[] parameters);
}
