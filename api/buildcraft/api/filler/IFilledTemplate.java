package buildcraft.api.filler;

import net.minecraft.util.math.BlockPos;

/**
 * Use methods of the interface as much as possible, implementation can do optimizations
 */
public interface IFilledTemplate {
    BlockPos getSize();

    default BlockPos getMax() {
        return getSize().subtract(new BlockPos(1, 1, 1));
    }

    boolean get(int x, int y, int z);

    void set(int x, int y, int z, boolean value);

    default void setAreaXY(int fromX, int toX, int fromY, int toY, int z, boolean value) {
        for (int y = fromY; y <= toY; y++) {
            for (int x = fromX; x <= toX; x++) {
                set(x, y, z, value);
            }
        }
    }

    default void setAreaYZ(int x, int fromY, int toY, int fromZ, int toZ, boolean value) {
        for (int y = fromY; y <= toY; y++) {
            for (int z = fromZ; z <= toZ; z++) {
                set(x, y, z, value);
            }
        }
    }

    default void setAreaXZ(int fromX, int toX, int y, int fromZ, int toZ, boolean value) {
        for (int x = fromX; x <= toX; x++) {
            for (int z = fromZ; z <= toZ; z++) {
                set(x, y, z, value);
            }
        }
    }

    default void setLineX(int fromX, int toX, int y, int z, boolean value) {
        for (int x = fromX; x <= toX; x++) {
            set(x, y, z, value);
        }
    }

    default void setLineY(int x, int fromY, int toY, int z, boolean value) {
        for (int y = fromY; y <= toY; y++) {
            set(x, y, z, value);
        }
    }

    default void setLineZ(int x, int y, int fromZ, int toZ, boolean value) {
        for (int z = fromZ; z <= toZ; z++) {
            set(x, y, z, value);
        }
    }

    default void setAll(boolean value) {
        for (int z = 0; z < getSize().getZ(); z++) {
            for (int y = 0; y < getSize().getY(); y++) {
                for (int x = 0; x < getSize().getX(); x++) {
                    set(x, y, z, value);
                }
            }
        }
    }
}
