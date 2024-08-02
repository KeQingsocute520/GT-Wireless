package keqing.gtwireless.api.capability.impl;

import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import keqing.gtwireless.api.capability.BigMath;

import java.math.BigInteger;
import java.util.UUID;

import static keqing.gtwireless.api.capability.WirelessEnergyNetworkManager.*;

public class WirelessEnergyContainerHandler extends EnergyContainerHandler {

    public UUID uuid = null;

    public WirelessEnergyContainerHandler(MetaTileEntity tileEntity,
                                          long maxCapacity,
                                          long maxInputVoltage,
                                          long maxInputAmperage,
                                          long maxOutputVoltage,
                                          long maxOutputAmperage) {
        super(tileEntity, maxCapacity, maxInputVoltage, maxInputAmperage, maxOutputVoltage, maxOutputAmperage);
    }

    public static WirelessEnergyContainerHandler emitterContainer(MetaTileEntity tileEntity, long maxCapacity, long maxOutputVoltage, long maxOutputAmperage) {
        return new WirelessEnergyContainerHandler(tileEntity, maxCapacity, 0L, 0L, maxOutputVoltage, maxOutputAmperage);
    }

    public static WirelessEnergyContainerHandler receiverContainer(MetaTileEntity tileEntity, long maxCapacity, long maxInputVoltage, long maxInputAmperage) {
        return new WirelessEnergyContainerHandler(tileEntity, maxCapacity, maxInputVoltage, maxInputAmperage, 0L, 0L);
    }

    @Override
    public void update() {
        if (!this.getMetaTileEntity().getWorld().isRemote) {
            if (this.metaTileEntity.getOffsetTimer() % 20L == 0L) {
                if (this.uuid != null) {
                    strongCheckOrAddUser(this.uuid);
                    if (this.getInputVoltage() == 0) {
                        if (this.getEnergyStored() > 0) {
                            addEUToWirelessEnergyNetwork(uuid, this.getEnergyStored());
                            this.removeEnergy(this.getEnergyStored());
                        }
                    } else {
                        long consumeEnergy = this.getEnergyCapacity() - this.getEnergyStored();
                        BigInteger pseudoUserEnergy = BigMath.min(getUserEU(this.uuid), BigInteger.valueOf(consumeEnergy));
                        long actuallyUserEnergy = pseudoUserEnergy.longValue();
                        this.addEnergy(actuallyUserEnergy);
                        addEUToWirelessEnergyNetwork(uuid, -actuallyUserEnergy);
                    }
                }
            }
        }
    }

    @Override
    public long getEnergyCanBeInserted() {
        return 0;
    }
}
