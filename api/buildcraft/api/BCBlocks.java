package buildcraft.api;

import net.minecraft.block.Block;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class BCBlocks {

    @ObjectHolder("buildcraftcore")
    public static class Core {
        @GameRegistry.ObjectHolder("spring")
        public static final Block SPRING = null;
        @GameRegistry.ObjectHolder("decorated")
        public static final Block DECORATED = null;
        @GameRegistry.ObjectHolder("engine")
        public static final Block ENGINE = null;
        @GameRegistry.ObjectHolder("marker_volume")
        public static final Block MARKER_VOLUME = null;
        @GameRegistry.ObjectHolder("marker_path")
        public static final Block MARKER_PATH = null;
    }

    @ObjectHolder("buildcraftbuilders")
    public static class Builders {
        @GameRegistry.ObjectHolder("architect")
        public static final Block ARCHITECT = null;
        @GameRegistry.ObjectHolder("builder")
        public static final Block BUILDER = null;
        @GameRegistry.ObjectHolder("filler")
        public static final Block FILLER = null;
        @GameRegistry.ObjectHolder("library")
        public static final Block LIBRARY = null;
        @GameRegistry.ObjectHolder("replacer")
        public static final Block REPLACER = null;
        @GameRegistry.ObjectHolder("quarry")
        public static final Block QUARRY = null;
        @GameRegistry.ObjectHolder("frame")
        public static final Block FRAME = null;
    }

    @ObjectHolder("buildcraftenergy")
    public static class Energy {
        // Fluid blocks can be accessed ~somewhere else~
    }

    @ObjectHolder("buildcraftfactory")
    public static class Factory {
        @GameRegistry.ObjectHolder("autoworkbench_item")
        public static final Block AUTOWORKBENCH_ITEM = null;
        @GameRegistry.ObjectHolder("mining_well")
        public static final Block MINING_WELL = null;
        @GameRegistry.ObjectHolder("pump")
        public static final Block PUMP = null;
        @GameRegistry.ObjectHolder("tube")
        public static final Block TUBE = null;
        @GameRegistry.ObjectHolder("flood_gate")
        public static final Block FLOOD_GATE = null;
        @GameRegistry.ObjectHolder("tank")
        public static final Block TANK = null;
        @GameRegistry.ObjectHolder("chute")
        public static final Block CHUTE = null;
        @GameRegistry.ObjectHolder("water_gel")
        public static final Block WATER_GEL = null;
        @GameRegistry.ObjectHolder("distiller")
        public static final Block DISTILLER = null;
        @GameRegistry.ObjectHolder("heat_exchange")
        public static final Block HEAT_EXCHANGE = null;
    }

    @ObjectHolder("buildcrafttransport")
    public static class Transport {
        @GameRegistry.ObjectHolder("filtered_buffer")
        public static final Block FILTERED_BUFFER = null;
        @GameRegistry.ObjectHolder("pipe_holder")
        public static final Block PIPE_HOLDER = null;
    }

    @ObjectHolder("buildcraftsilicon")
    public static class Silicon {
        @GameRegistry.ObjectHolder("laser")
        public static final Block LASER = null;
        @GameRegistry.ObjectHolder("assembly_table")
        public static final Block ASSEMBLY_TABLE = null;
        @GameRegistry.ObjectHolder("advanced_crafting_table")
        public static final Block ADVANCED_CRAFTING_TABLE = null;
        @GameRegistry.ObjectHolder("integration_table")
        public static final Block INTEGRATION_TABLE = null;
        @GameRegistry.ObjectHolder("charging_table")
        public static final Block CHARGING_TABLE = null;
        @GameRegistry.ObjectHolder("programming_table")
        public static final Block PROGRAMMING_TABLE = null;
    }

    @ObjectHolder("buildcraftrobotics")
    public static class Robotics {

    }
}