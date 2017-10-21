/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.filler;

import buildcraft.api.core.render.ISprite;
import buildcraft.api.statements.IStatement;
import buildcraft.api.statements.IStatementParameter;

/** A type of statement that is used for filler patterns. */
public interface IFillerPattern extends IStatement {
    /**
     * @param filledTemplate empty template
     * @return {@code true} if the template filled, or {@code false} if this shouldn't make a template for the given params.
     */
    boolean fillTemplate(IFilledTemplate filledTemplate, IStatementParameter[] params);

    @Override
    IFillerPattern[] getPossible();

    /** Note that this sprite *must* be stitched to the texture atlas, as it is drawn on the side of the filler
     * block. */
    @Override
    ISprite getSprite();
}
