package buildcraft.api.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

/**
 * @deprecated This shouldn't work this way.
 */
@Deprecated
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
