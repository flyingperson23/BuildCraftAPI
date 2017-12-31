package buildcraft.api;

import java.util.Locale;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;

public enum EnumBuildCraftModule implements IBuildCraftMod {
    LIB,
    // Base module for all BC.
    CORE,
    // Potentially optional modules for adding more BC functionality
    BUILDERS,
    ENERGY,
    FACTORY,
    ROBOTICS,
    SILICON,
    TRANSPORT,
    // Optional module for compatibility with other mods
    COMPAT;

    public static final EnumBuildCraftModule[] VALUES = values();

    public final String name = name().toLowerCase(Locale.ROOT);
    public final String modId = "buildcraft" + name;
    public final boolean loaded;

    private EnumBuildCraftModule() {
        loaded = Loader.isModLoaded(modId);
    }

    static {
        if (!Loader.instance().hasReachedState(LoaderState.PREINITIALIZATION)) {
            throw new RuntimeException("You can only use EnumBuidCraftModule from pre-init onwards!");
        }
    }

    public static boolean isBuildCraftMod(String testModId) {
        for (EnumBuildCraftModule mod : VALUES) {
            if (mod.modId.equals(testModId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getNetworkName() {
        return modId;
    }
}
