package buildcraft.api.permission;

import buildcraft.api.APIHelper;

public class PermissionAPI {
    public static final IOwnershipManager OWNERSHIP_MANAGER;

    static {
        OWNERSHIP_MANAGER = APIHelper.getInstance("buildcraft.lib.permission.PlayerOwnership", IOwnershipManager.class);
    }
}
