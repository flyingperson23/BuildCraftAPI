package buildcraft.api.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

import buildcraft.api.properties.BuildCraftProperties;

/** This shouldn't be used for anything other than {@link BuildCraftProperties#SNAPSHOT_TYPE}. */
public enum EnumOptionalSnapshotType implements IStringSerializable {
    NONE(null),
    TEMPLATE(EnumSnapshotType.TEMPLATE),
    BLUEPRINT(EnumSnapshotType.BLUEPRINT);

    public final EnumSnapshotType type;

    EnumOptionalSnapshotType(EnumSnapshotType type) {
        this.type = type;
    }

    public static EnumOptionalSnapshotType fromNullable(EnumSnapshotType type) {
        if (type == null) {
            return NONE;
        }
        switch (type) {
            case TEMPLATE:
                return TEMPLATE;
            case BLUEPRINT:
                return BLUEPRINT;
            default:
                return NONE;
        }
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
