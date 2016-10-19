package buildcraft.api.transport.neptune;

import net.minecraft.item.EnumDyeColor;

public interface IWireManager {

    IPipeHolder getHolder();

    EnumWirePart getWireByColour(EnumDyeColor colour);

    EnumDyeColor getWireByPart(EnumWirePart part);

    EnumWirePart removeWireByColour(EnumDyeColor colour);

    EnumDyeColor removeWireByPart(EnumWirePart part);

    boolean addWire(EnumWirePart part, EnumDyeColor colour);

}
