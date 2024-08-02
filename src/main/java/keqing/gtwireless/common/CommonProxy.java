package keqing.gtwireless.common;
import keqing.gtwireless.GTWConfig;
import keqing.gtwireless.loader.WirelessEnergyHatches;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "gtw"
)
public class CommonProxy {
    public void init() {
        if(GTWConfig.EnableReciperesiger)WirelessEnergyHatches.init();
    }
    public void preLoad() {

    }

}