package buildcraft.api.facades;

import net.minecraft.item.EnumDyeColor;

import javax.annotation.Nullable;

public interface IFacadePhasedState {
    IFacadeState getState();

    @Nullable
    EnumDyeColor getActiveColor();
}