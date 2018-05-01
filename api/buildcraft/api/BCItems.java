package buildcraft.api;

import net.minecraft.item.Item;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import buildcraft.api.items.FluidItemDrops;

public class BCItems {

    @ObjectHolder("buildcraftlib")
    public static class Lib {
        @ObjectHolder("guide")
        public static final Item GUIDE = null;
        @ObjectHolder("guide.note")
        public static final Item GUIDE_NOTE = null;
        @ObjectHolder("debugger")
        public static final Item DEBUGGER = null;
    }

    @ObjectHolder("buildcraftcore")
    public static class Core {
        @ObjectHolder("gear_wood")
        public static final Item GEAR_WOOD = null;
        @ObjectHolder("gear_stone")
        public static final Item GEAR_STONE = null;
        @ObjectHolder("gear_iron")
        public static final Item GEAR_IRON = null;
        @ObjectHolder("gear_gold")
        public static final Item GEAR_GOLD = null;
        @ObjectHolder("gear_diamond")
        public static final Item GEAR_DIAMOND = null;
        @ObjectHolder("wrench")
        public static final Item WRENCH = null;
        @ObjectHolder("paintbrush")
        public static final Item PAINTBRUSH = null;
        @ObjectHolder("list")
        public static final Item LIST = null;
        @ObjectHolder("map_location")
        public static final Item MAP_LOCATION = null;
        @ObjectHolder("marker_connector")
        public static final Item MARKER_CONNECTOR = null;
        @ObjectHolder("volume_box")
        public static final Item VOLUME_BOX = null;
        @ObjectHolder("goggles")
        public static final Item GOGGLES = null;

        /** It is recommended that you refer to {@link FluidItemDrops#item} when creating fluid drops rather than
         * this. */
        @ObjectHolder("fragile_fluid_shard")
        public static final Item FRAGILE_FLUID_SHARD = null;
    }

    @ObjectHolder("buildcraftbuilders")
    public static class Builders {

    }

    @ObjectHolder("buildcraftenergy")
    public static class Energy {
        @ObjectHolder("glob_of_oil")
        public static final Item GLOB_OF_OIL = null;
    }

    @ObjectHolder("buildcraftfactory")
    public static class Factory {
        @ObjectHolder("plastic_sheet")
        public static final Item PLASTIC_SHEET = null;
        @ObjectHolder("water_gel_spawn")
        public static final Item WATER_GEL = null;
        @ObjectHolder("gel")
        public static final Item GELLED_WATER = null;
    }

    @ObjectHolder("buildcrafttransport")
    public static class Transport {
        @ObjectHolder("plug_blocker")
        public static final Item PLUG_BLOCKER = null;

        @ObjectHolder("pipe_structure")
        public static final Item PIPE_STRUCTURE = null;

        @ObjectHolder("waterproof")
        public static final Item WATERPROOF = null;
    }

    @ObjectHolder("buildcraftsilicon")
    public static class Silicon {
        @ObjectHolder("redstone_chipset")
        public static final Item REDSTONE_CHIPSET = null;
    }

    @ObjectHolder("buildcraftrobotics")
    public static class Robotics {

    }
}
