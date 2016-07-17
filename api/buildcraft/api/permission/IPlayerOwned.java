package buildcraft.api.permission;

import javax.annotation.Nullable;

/** Defines an entity or tile that is owned by a specific player. */
public interface IPlayerOwned {
    IOwner getOwner();

    /** Gets the current status of this owned object. Null means that you should resolve this with the
     * {@link PermissionAPI#OWNERSHIP_MANAGER} to get the actual protection status. */
    @Nullable
    EnumProtectionStatus getStatus();
}
