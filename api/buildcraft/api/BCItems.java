package buildcraft.api;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BCItems {

    @GameRegistry.ObjectHolder("buildcraftbuilders")
    public static class Builders {
        @GameRegistry.ObjectHolder("snapshot")
        public static final Item SNAPSHOT = null;
        @GameRegistry.ObjectHolder("schematic_single")
        public static final Item SCHEMATIC_SINGLE = null;
        @GameRegistry.ObjectHolder("filler_planner")
        public static final Item ADDON_FILLER_PLANNER = null;
    }

    @GameRegistry.ObjectHolder("buildcraftcore")
    public static class Core {
        @GameRegistry.ObjectHolder("gear_wood")
        public static final Item GEAR_WOOD = null;
        @GameRegistry.ObjectHolder("gear_stone")
        public static final Item GEAR_STONE = null;
        @GameRegistry.ObjectHolder("gear_iron")
        public static final Item GEAR_IRON = null;
        @GameRegistry.ObjectHolder("gear_gold")
        public static final Item GEAR_GOLD = null;
        @GameRegistry.ObjectHolder("gear_diamond")
        public static final Item GEAR_DIAMOND = null;
        @GameRegistry.ObjectHolder("wrench")
        public static final Item WRENCH = null;
        @GameRegistry.ObjectHolder("paintbrush")
        public static final Item PAINTBRUSH = null;
        @GameRegistry.ObjectHolder("list")
        public static final Item LIST = null;
        @GameRegistry.ObjectHolder("map_location")
        public static final Item MAP_LOCATION = null;
        @GameRegistry.ObjectHolder("marker_connector")
        public static final Item MARKER_CONNECTOR = null;
        @GameRegistry.ObjectHolder("volume_box")
        public static final Item VOLUME_BOX = null;
        @GameRegistry.ObjectHolder("goggles")
        public static final Item GOGGLES = null;

        /** It is recommended that you refer to {@link buildcraft.api.items.FluidItemDrops#item} when creating fluid drops rather than
         * this. */
        @GameRegistry.ObjectHolder("fragile_fluid_shard")
        public static final Item FRAGILE_FLUID_SHARD = null;
    }

    @GameRegistry.ObjectHolder("buildcraftenergy")
    public static class Energy {
        @GameRegistry.ObjectHolder("glob_of_oil")
        public static final Item GLOB_OF_OIL = null;
    }

    @GameRegistry.ObjectHolder("buildcraftfactory")
    public static class Factory {
        @GameRegistry.ObjectHolder("plastic_sheet")
        public static final Item PLASTIC_SHEET = null;
        @GameRegistry.ObjectHolder("water_gel_spawn")
        public static final Item WATER_GEL = null;
        @GameRegistry.ObjectHolder("gel")
        public static final Item GELLED_WATER = null;
    }

    @GameRegistry.ObjectHolder("buildcraftlib")
    public static class Lib {
        @GameRegistry.ObjectHolder("guide")
        public static final Item GUIDE = null;
        @GameRegistry.ObjectHolder("guide.note")
        public static final Item GUIDE_NOTE = null;
        @GameRegistry.ObjectHolder("debugger")
        public static final Item DEBUGGER = null;
    }

    @GameRegistry.ObjectHolder("buildcraftrobotics")
    public static class Robotics {

    }

    @GameRegistry.ObjectHolder("buildcraftsilicon")
    public static class Silicon {
        @GameRegistry.ObjectHolder("redstone_chipset")
        public static final Item REDSTONE_CHIPSET = null;
    }

    @GameRegistry.ObjectHolder("buildcrafttransport")
    public static class Transport {
        @GameRegistry.ObjectHolder("waterproof")
        public static final Item WATERPROOF = null;
        @GameRegistry.ObjectHolder("plug_blocker")
        public static final Item PLUG_BLOCKER = null;
        @GameRegistry.ObjectHolder("plug_power_adaptor")
        public static final Item PLUG_POWER_ADAPTOR = null;
        @GameRegistry.ObjectHolder("plug_gate")
        public static final Item PLUG_GATE = null;
        @GameRegistry.ObjectHolder("plug_lens")
        public static final Item PLUG_LENS = null;
        @GameRegistry.ObjectHolder("plug_pulsar")
        public static final Item PLUG_PULSAR = null;
        @GameRegistry.ObjectHolder("plug_light_sensor")
        public static final Item PLUG_LIGHT_SENSOR = null;
        @GameRegistry.ObjectHolder("plug_facade")
        public static final Item PLUG_FACADE = null;
        @GameRegistry.ObjectHolder("wire")
        public static final Item WIRE = null;

    }


}