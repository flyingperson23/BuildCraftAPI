package buildcraft.api.filler;

import java.util.BitSet;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import buildcraft.api.core.IBox;

/** A template, to be returned by an {@link IFillerPattern}
 * <p>
 * As this is backed by a {@link BitSet} ordered [x][y][z] it is much faster to fill in the X axis than the Y or Z axis,
 * so many methods are provided that take advantage of this. */
public class FilledTemplate {

    public enum TemplateState {
        FILL('X'),
        CLEAR(' '),
        IGNORE('?');

        public final char toStringChar;

        private TemplateState(char toStringChar) {
            this.toStringChar = toStringChar;
        }
    }

    public final BlockPos min;
    public final BlockPos max;
    public final BlockPos size;

    /** Data is organised as data[x][y][z] == data[z * size.y * size.x + y * size.x + x] */
    private final BitSet data;

    /** Data is organised in the same order as in {@link #data}. If a bit is set in this set it means that that block
     * will be ignored, no matter the value in data. <br>
     * If this is null then nothing will be ignored. */
    @Nullable
    private BitSet ignoreSet;

    // Helper fields to make access a tiny bit quicker
    public final int sizeX, sizeY, sizeZ, sizeXY, sizeXYZ;
    public final int maxX, maxY, maxZ;

    public FilledTemplate(BlockPos min, BlockPos max) {
        this.min = min;
        this.max = max;
        this.size = max.subtract(min).add(1, 1, 1);
        sizeX = size.getX();
        sizeY = size.getY();
        sizeZ = size.getZ();
        sizeXY = sizeX * sizeY;
        sizeXYZ = sizeXY * sizeZ;
        this.data = new BitSet(sizeXYZ);
        maxX = sizeX - 1;
        maxY = sizeY - 1;
        maxZ = sizeZ - 1;
    }

    public FilledTemplate(IBox box) {
        this(box.min(), box.max());
    }

    public FilledTemplate(FilledTemplate from) {
        this.min = from.min;
        this.max = from.max;
        this.size = from.size;
        this.sizeX = from.sizeX;
        this.sizeY = from.sizeY;
        this.sizeZ = from.sizeZ;
        this.sizeXY = from.sizeXY;
        this.sizeXYZ = from.sizeXYZ;
        this.maxX = from.maxX;
        this.maxY = from.maxY;
        this.maxZ = from.maxZ;
        this.data = (BitSet) from.data.clone();
        if (from.ignoreSet != null) {
            this.ignoreSet = (BitSet) from.ignoreSet.clone();
        }
    }

    // NBT

    public FilledTemplate(NBTTagCompound nbt) {
        min = new BlockPos(nbt.getInteger("minX"), nbt.getInteger("minY"), nbt.getInteger("minZ"));
        max = new BlockPos(nbt.getInteger("maxX"), nbt.getInteger("maxY"), nbt.getInteger("maxZ"));
        this.size = max.subtract(min).add(1, 1, 1);
        sizeX = size.getX();
        sizeY = size.getY();
        sizeZ = size.getZ();
        sizeXY = sizeX * sizeY;
        sizeXYZ = sizeXY * sizeZ;
        maxX = sizeX - 1;
        maxY = sizeY - 1;
        maxZ = sizeZ - 1;
        this.data = BitSet.valueOf(nbt.getByteArray("data"));
        if (nbt.hasKey("shouldLeave")) {
            ignoreSet = BitSet.valueOf(nbt.getByteArray("shouldLeave"));
        }
    }

    public NBTTagCompound writeToNbt() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("minX", min.getX());
        nbt.setInteger("minY", min.getY());
        nbt.setInteger("minZ", min.getZ());
        nbt.setInteger("maxX", max.getX());
        nbt.setInteger("maxY", max.getY());
        nbt.setInteger("maxZ", max.getZ());
        nbt.setByteArray("data", data.toByteArray());
        if (ignoreSet != null) {
            nbt.setByteArray("shouldLeave", ignoreSet.toByteArray());
        }
        return nbt;
    }

    // Internal

    private int getIndexOf(int x, int y, int z) {
        return z * sizeXY + y * sizeX + x;
    }

    private void fillBoundedProper(int fi, int ti) {
        data.set(fi, ti + 1);
        if (ignoreSet != null) {
            ignoreSet.clear(fi, ti + 1);
        }
    }

    private void clearBoundedProper(int fi, int ti) {
        data.clear(fi, ti + 1);
        if (ignoreSet != null) {
            ignoreSet.clear(fi, ti + 1);
        }
    }

    // Getters

    public TemplateState getAbsolute(BlockPos pos) {
        return getOffset(pos.subtract(min));
    }

    public TemplateState getOffset(BlockPos offset) {
        return get(offset.getX(), offset.getY(), offset.getZ());
    }

    public TemplateState get(int x, int y, int z) {
        int index = getIndexOf(x, y, z);
        if (ignoreSet != null && ignoreSet.get(index)) {
            return TemplateState.IGNORE;
        }
        return data.get(getIndexOf(x, y, z)) ? TemplateState.FILL : TemplateState.CLEAR;
    }

    /** @return true if this should fill the given position. NOTE: This IGNORES if the given position should be ignored.
     *         Provided for patterns which never ignore anything. */
    public boolean shouldFill(int x, int y, int z) {
        return data.get(getIndexOf(x, y, z));
    }

    public boolean shouldIgnore(int x, int y, int z) {
        if (ignoreSet != null) {
            return ignoreSet.get(getIndexOf(x, y, z));
        }
        return false;
    }

    // Filling

    public void fill(BlockPos pos) {
        fill(pos.getX(), pos.getY(), pos.getZ());
    }

    public void fill(int x, int y, int z) {
        int index = getIndexOf(x, y, z);
        fillBoundedProper(index, index);
    }

    public void fillVolume(BlockPos from, BlockPos to) {
        fillVolume(from.getX(), to.getX(), from.getY(), to.getY(), from.getZ(), to.getZ());
    }

    public void fillVolume(int fx, int tx, int fy, int ty, int fz, int tz) {
        for (int z = fz; z <= tz; z++) {
            fillAreaXY(fx, tx, fy, ty, z);
        }
    }

    public void fillAreaYZ(int x, int fy, int ty, int fz, int tz) {
        for (int z = fz; z <= tz; z++) {
            fillLineY(x, fy, ty, z);
        }
    }

    public void fillAreaXZ(int fx, int tx, int y, int fz, int tz) {
        for (int z = fz; z <= tz; z++) {
            fillLineX(fx, tx, y, z);
        }
    }

    public void fillAreaXY(int fx, int tx, int fy, int ty, int z) {
        for (int y = fy; y <= ty; y++) {
            fillLineX(fx, tx, y, z);
        }
    }

    /** Completely fills up this template. */
    public void fill() {
        fillBoundedProper(0, sizeXYZ - 1);
    }

    /** Fills an entire plane of a particular X co-ord */
    public void fillPlaneYZ(int x) {
        fillAreaYZ(x, 0, maxY, 0, maxZ);
    }

    public void fillPlaneXZ(int y) {
        fillAreaXZ(0, maxX, y, 0, maxZ);
    }

    public void fillPlaneXY(int z) {
        validateCoord(0, 0, z);
        fillBoundedProper(getIndexOf(0, 0, z), getIndexOf(maxX, maxY, z));
    }

    public void fillAxisX(int y, int z) {
        fillLineX(0, maxX, y, z);
    }

    public void fillAxisY(int x, int z) {
        fillLineY(x, 0, maxY, z);
    }

    /** Fills up the entire Z axis of a particular co-ord. */
    public void fillAxisZ(int x, int y) {
        fillLineZ(x, y, 0, maxZ);
    }

    /** Fills a line in the X-axis. Likely to be faster than manually, as this delegates to the bitset impl, which can
     * set large lines at once. */
    public void fillLineX(int fx, int tx, int y, int z) {
        validateCoord(fx, y, z);
        validateCoord(tx, y, z);
        fillBoundedProper(getIndexOf(fx, y, z), getIndexOf(tx, y, z));
    }

    public void fillLineY(int x, int fy, int ty, int z) {
        validateCoord(x, fy, z);
        validateCoord(x, ty, z);
        for (int y = fy; y <= ty; y++) {
            fill(x, y, z);
        }
    }

    public void fillLineZ(int x, int y, int fz, int tz) {
        validateCoord(x, y, fz);
        validateCoord(x, y, tz);
        for (int z = fz; z <= tz; z++) {
            fill(x, y, z);
        }
    }

    // Clearing (Basically a direct copy of filling, but with "clear" instead of "set"|"fill"

    public void clear(BlockPos pos) {
        clear(pos.getX(), pos.getY(), pos.getZ());
    }

    public void clear(int x, int y, int z) {
        int index = getIndexOf(x, y, z);
        clearBoundedProper(index, index);
    }

    public void clearVolume(BlockPos from, BlockPos to) {
        clearVolume(from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ());
    }

    public void clearVolume(int fx, int fy, int fz, int tx, int ty, int tz) {
        for (int x = fx; x <= tx; x++) {
            clearAreaYZ(x, fy, fz, ty, tz);
        }
    }

    public void clearAreaYZ(int x, int fy, int fz, int ty, int tz) {
        for (int y = fy; y <= ty; y++) {
            clearLineZ(x, y, fz, tz);
        }
    }

    /** Completely fills up this template. */
    public void clear() {
        clearBoundedProper(0, sizeXYZ - 1);
    }

    /** Fills an entire plane of a particular X co-ord */
    public void clearPlaneYZ(int x) {
        validateCoord(x, 0, 0);
        clearBoundedProper(getIndexOf(x, 0, 0), getIndexOf(x, maxY, maxZ));
    }

    /** Fills up the entire Z axis of a particular co-ord. */
    public void clearAxisZ(int x, int y) {
        clearLineZ(x, y, 0, maxZ);
    }

    /** Fills a line in the Z-axis. Likely to be faster than manually, as this delegates to the bitset impl, which can
     * set large lines at once. */
    public void clearLineZ(int x, int y, int fz, int tz) {
        validateCoord(x, y, fz);
        validateCoord(x, y, tz);
        clearBoundedProper(getIndexOf(x, y, fz), getIndexOf(x, y, tz));
    }

    // Ignoring

    public void ignore() {
        getIgnoreSet().set(0, getIndexOf(maxX, maxY, maxZ));
    }

    public void ignore(int x, int y, int z) {
        validateCoord(x, y, z);
        getIgnoreSet().set(getIndexOf(x, y, z));
    }

    // Setters

    public void set(BlockPos pos, boolean fill) {
        set(pos.getX(), pos.getY(), pos.getZ(), fill);
    }

    public void set(int x, int y, int z, boolean fill) {
        if (fill) {
            fill(x, y, z);
        } else {
            clear(x, y, z);
        }
    }

    public void set(int x, int y, int z, TemplateState state) {
        switch (state) {
            case FILL:
                fill(x, y, z);
                return;
            case CLEAR:
                clear(x, y, z);
                return;
            case IGNORE:
                ignore(x, y, z);
                return;
            default:
                throw new IllegalStateException("Unknown TemplateState " + state);
        }
    }

    /** Swaps every clear with fill, and fill with clear. */
    public void invert() {
        data.flip(0, sizeXYZ);
    }

    // Internal (Validation)

    private void validateCoord(int x, int y, int z) {
        if (x < 0 || x >= sizeX) {
            throw new IllegalArgumentException(
                "X value was not in the correct range! (x = " + x + ", sizeX = " + sizeX + ")");
        }
        if (y < 0 || y >= sizeY) {
            throw new IllegalArgumentException(
                "Y value was not in the correct range! (y = " + y + ", sizeY = " + sizeY + ")");
        }
        if (z < 0 || z >= sizeZ) {
            throw new IllegalArgumentException(
                "Z value was not in the correct range! (z = " + z + ", sizeZ = " + sizeZ + ")");
        }
    }

    private BitSet getIgnoreSet() {
        if (ignoreSet == null) {
            ignoreSet = new BitSet(sizeXYZ);
        }
        return ignoreSet;
    }

    /** @return A massivly long (potentially) string, which is sliced section of this template. Better for debugging. */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int y = 0; y < sizeY; y++) {
            str.append("====\n");
            for (int x = 0; x < sizeX; x++) {
                for (int z = 0; z < sizeZ; z++) {
                    TemplateState state = get(x, y, z);
                    str.append(state.toStringChar);
                }
                str.append("\n");
            }
        }
        str.append("====\n");
        return str.toString();
    }
}
