package keqing.gtwireless.api.capability;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.UUID;

public abstract class GlobalVariableStorage {

    public static HashMap<UUID, BigInteger> WirelessEnergy = new HashMap<>(100, 0.9f);
}
