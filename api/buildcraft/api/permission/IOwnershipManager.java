package buildcraft.api.permission;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;

/** Provides a way of checking for ownership. */
public interface IOwnershipManager {
    @Nonnull
    EnumProtectionStatus resolveStatus(IPlayerOwned owned);

    /** Checks to see if the given player is allowed to use the owned object.
     * 
     * @param attempting
     * @param owned
     * @return */
    boolean canUse(EntityPlayer attempting, IPlayerOwned owned);

    /** Checks to see if the given automata is allowed to use the owned object.
     * 
     * @param attempting
     * @param owned
     * @return */
    boolean canUse(IOwner attempting, IPlayerOwned owned);
}
