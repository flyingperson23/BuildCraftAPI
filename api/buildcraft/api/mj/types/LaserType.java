package buildcraft.api.mj.types;

import javax.annotation.Nonnull;

import buildcraft.api.mj.IMjConnectorType;
import buildcraft.api.mj.MjSimpleType;

public enum LaserType implements IMjConnectorType {
    LASER_EMITTER_IN(MjSimpleType.KN_TRANSPORTER_CONVERT),
    LASER_EMITTER_OUT(MjSimpleType.LA_TRANSPORTER_CONVERT),
    TABLE_ASSEMBLY,
    TABLE_CRAFTING,
    TABLE_INTEGRATION,
    TABLE_CHARGING,
    TABLE_PROGRAMMING;

    @Nonnull
    private final MjSimpleType simple;

    private LaserType() {
        this(MjSimpleType.LASER_CONSUMER);
    }

    private LaserType(@Nonnull MjSimpleType simple) {
        this.simple = simple;
    }

    @Override
    public boolean is(IMjConnectorType other) {
        return this == other || simple.is(other);
    }

    @Override
    public MjSimpleType getSimpleType() {
        return simple;
    }
}
