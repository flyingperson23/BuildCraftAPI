package buildcraft.api.mj;

import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

public class RfWrapperReceiver implements IMjReceiver {

    private IEnergyStorage storage;

    public RfWrapperReceiver(IEnergyStorage storage) {
        this.storage = storage;
    }
    @Override
    public boolean canConnect(@Nonnull IMjConnector other) {
        return storage.canReceive();
    }

    @Override
    public long getPowerRequested() {
        int rfRequested = storage.getMaxEnergyStored() - storage.getEnergyStored();
        return (rfRequested * MjAPI.MJ) / MjAPI.rfPerMj;
    }

    @Override
    public long receivePower(long microJoules, boolean simulate) {
        int rf = (int) (microJoules * MjAPI.rfPerMj / MjAPI.MJ);
        int rfAccepted = storage.receiveEnergy(rf, simulate);
        int excessRf = rf - rfAccepted;
        return excessRf * MjAPI.MJ / MjAPI.rfPerMj;
    }
}
