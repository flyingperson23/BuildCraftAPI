package buildcraft.api.transport.neptune;

import net.minecraft.item.EnumDyeColor;

public interface IWireManager {

    IPipeHolder getHolder();

    EnumDyeColor getColorOfPart(EnumWirePart part);

    EnumDyeColor removePart(EnumWirePart part);

    boolean addPart(EnumWirePart part, EnumDyeColor colour);

    boolean hasPartOfColor(EnumDyeColor color);
}
