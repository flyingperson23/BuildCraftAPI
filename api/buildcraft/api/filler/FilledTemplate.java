package buildcraft.api.filler;

import java.util.BitSet;

import net.minecraft.util.math.BlockPos;

/** A template, to be returned by an {@link IFillerPattern}
 * <p>
 * As this is backed by a {@link BitSet} ordered [x][y][z] it is much faster to fill in the Z axis than the X axis, so
 * many methods are provided that take advantage of this. */
public class FilledTemplate {
    public final BlockPos min;
    public final BlockPos max;
    public final BlockPos size;

    /** Data is organised as data[x][y][z] == data[x * size.y * size.z + y * size.z + z] */
    private final BitSet data;

    // Helper fields to make access a tiny bit quicker
    private final int sizeX, sizeY, sizeZ, sizeYZ, sizeXYZ;
    private final int maxX, maxY, maxZ;

    public FilledTemplate(BlockPos min, BlockPos max) {
        this.min = min;
        this.max = max;
        this.size = max.subtract(min);
        sizeX = size.getX();
        sizeY = size.getY();
        sizeZ = size.getZ();
        sizeYZ = sizeX * sizeY;
        sizeXYZ = sizeX * sizeYZ;
        this.data = new BitSet(sizeXYZ);
        maxX = sizeX - 1;
        maxY = sizeY - 1;
        maxZ = sizeZ - 1;
    }

    private int getIndexOf(int x, int y, int z) {
        return x * sizeYZ + y * sizeZ + z;
    }

    // Getters

    public boolean getAbsolute(BlockPos pos) {
        return getOffset(pos.subtract(min));
    }

    public boolean getOffset(BlockPos offset) {
        return get(offset.getX(), offset.getY(), offset.getZ());
    }

    public boolean get(int x, int y, int z) {
        return data.get(getIndexOf(x, y, z));
    }

    // Filling

    public void fill(BlockPos pos) {
        fill(pos.getX(), pos.getY(), pos.getZ());
    }

    public void fill(int x, int y, int z) {
        data.set(getIndexOf(x, y, z));
    }

    public void fillVolume(BlockPos from, BlockPos to) {
        fillVolume(from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ());
    }

    public void fillVolume(int fx, int fy, int fz, int tx, int ty, int tz) {
        for (int x = fx; x <= tx; x++) {
            fillAreaYZ(x, fy, fz, ty, tz);
        }
    }

    public void fillAreaYZ(int x, int fy, int fz, int ty, int tz) {
        for (int y = fy; y <= ty; y++) {
            fillLineZ(x, y, fz, tz);
        }
    }

    /** Completely fills up this template. */
    public void fill() {
        data.set(0, sizeXYZ);
    }

    /** Fills an entire plane of a particular X co-ord */
    public void fillPlaneYZ(int x) {
        validateCoord(x, 0, 0);
        data.set(getIndexOf(x, 0, 0), getIndexOf(x, maxY, maxZ));
    }

    /** Fills up the entire Z axis of a particular co-ord. */
    public void fillAxisZ(int x, int y) {
        fillLineZ(x, y, 0, maxZ);
    }

    /** Fills a line in the Z-axis. Likely to be faster than manually, as this delegates to the bitset impl, which can
     * set large lines at once. */
    public void fillLineZ(int x, int y, int fz, int tz) {
        validateCoord(x, y, fz);
        validateCoord(x, y, tz);
        data.set(getIndexOf(x, y, fz), 1 + getIndexOf(x, y, tz));
    }

    // Clearing (Basically a direct copy of filling, but with "clear" instead of "set"|"fill"

    public void clear(int x, int y, int z) {
        data.clear(getIndexOf(x, y, z));
    }

    // Internal (Validation)

    private void validateCoord(int x, int y, int z) {
        if (x < 0 || x >= sizeX) {
            throw new IllegalArgumentException("X value was not in the correct range! (x = " + x + ", sizeX = " + sizeX + ")");
        }
        if (y < 0 || y >= sizeY) {
            throw new IllegalArgumentException("Y value was not in the correct range! (y = " + y + ", sizeY = " + sizeY + ")");
        }
        if (z < 0 || z >= sizeZ) {
            throw new IllegalArgumentException("Z value was not in the correct range! (z = " + z + ", sizeZ = " + sizeZ + ")");
        }
    }
}
