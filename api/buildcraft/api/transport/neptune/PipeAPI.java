package buildcraft.api.transport.neptune;

import java.util.HashMap;
import java.util.Map;

public class PipeAPI {
    public static IPipeRegistry pipeRegistry;
    public static IPluggableRegistry pluggableRegistry;
    public static PipeFlowType flowStructure;
    public static PipeFlowType flowItems;
    public static PipeFlowType flowFluids;
    public static PipeFlowType flowPower;

    public static final Map<PipeDefinition, Integer> fluidTransferValues = new HashMap<>();

    public static int getFluidPerTick(PipeDefinition def) {
        Integer from = fluidTransferValues.get(def);
        if (from != null) {
            return from.intValue();
        } else {
            return 20;
        }
    }
}
