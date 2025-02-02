package buildcraft.api;

import net.minecraft.item.Item;

import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class BCItems {

    @ObjectHolder("buildcraftlib")
    public static class Lib {
        public static final Item GUIDE = null;
        public static final Item GUIDE_NOTE = null;
        public static final Item DEBUGGER = null;
    }

    @ObjectHolder("buildcraftcore")
    public static class Core {
        public static final Item GEAR_WOOD = null;
        public static final Item GEAR_STONE = null;
        public static final Item GEAR_IRON = null;
        public static final Item GEAR_GOLD = null;
        public static final Item GEAR_DIAMOND = null;
        public static final Item WRENCH = null;
        public static final Item PAINTBRUSH = null;
        public static final Item LIST = null;
        public static final Item MAP_LOCATION = null;
        public static final Item MARKER_CONNECTOR = null;
        public static final Item VOLUME_BOX = null;
        public static final Item GOGGLES = null;
    }

    @ObjectHolder("buildcraftbuilders")
    public static class Builders {

    }

    @ObjectHolder("buildcraftenergy")
    public static class Energy {
        public static final Item GLOB_OF_OIL = null;
    }

    @ObjectHolder("buildcraftfactory")
    public static class Factory {

    }

    @ObjectHolder("buildcrafttransport")
    public static class Transport {
        public static final Item PLUG_BLOCKER = null;
        public static final Item PLUG_POWER_ADAPTOR = null;

        public static final Item PIPE_STRUCTURE = null;
        public static final Item PIPE_WOOD_ITEM = null;
        public static final Item PIPE_EMZULI_ITEM = null;
        public static final Item PIPE_EMERALD_ITEM = null;
        public static final Item PIPE_WOOD_FLUID = null;
        public static final Item PIPE_EMERALD_FLUID = null;
    }

    @ObjectHolder("buildcraftsilicon")
    public static class Silicon {
        public static final Item REDSTONE_CHIPSET = null;

        public static final Item PLUG_PULSAR = null;
    }

    @ObjectHolder("buildcraftrobotics")
    public static class Robotics {

    }
}
