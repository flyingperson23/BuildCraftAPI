package buildcraft.api.permission;

import javax.annotation.Nonnull;

public enum EnumProtectionStatus {
    ANYONE(2),
    OWNER_ONLY(1),
    NO_AUTOMATION(0);

    public static final EnumProtectionStatus[] VALUES = values();

    public final byte permissionLevel;

    EnumProtectionStatus(int permissionLevel) {
        this.permissionLevel = (byte) permissionLevel;
    }

    @Nonnull
    public static EnumProtectionStatus get(int permissionLevel) {
        if (permissionLevel >= ANYONE.permissionLevel) {
            return ANYONE;
        } else if (permissionLevel == OWNER_ONLY.permissionLevel) {
            return OWNER_ONLY;
        } else {
            return NO_AUTOMATION;
        }
    }

    public EnumProtectionStatus next() {
        if (this == ANYONE) {
            return OWNER_ONLY;
        } else if (this == OWNER_ONLY) {
            return NO_AUTOMATION;
        } else {
            return ANYONE;
        }
    }

    public EnumProtectionStatus last() {
        if (this == ANYONE) {
            return NO_AUTOMATION;
        } else if (this == OWNER_ONLY) {
            return ANYONE;
        } else {
            return OWNER_ONLY;
        }
    }
}
