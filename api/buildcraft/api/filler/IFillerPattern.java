/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.filler;

import javax.annotation.Nullable;

import buildcraft.api.core.IBox;
import buildcraft.api.statements.IStatement;
import buildcraft.api.statements.IStatementParameter;

/** A type of statement that is used for filler patterns. */
public interface IFillerPattern extends IStatement {
    /** @param box The box to create the pattern in.
     * @return The template to fill, or null if this shouldn't make a template with the given box. */
    @Nullable
    FilledTemplate createTemplate(IBox box, IStatementParameter[] params);
}
