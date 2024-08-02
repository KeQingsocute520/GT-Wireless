package keqing.gtwireless;


import gregtech.common.ConfigHolder;
import keqing.gtwireless.common.CommonProxy;
import keqing.gtwireless.common.GTWMetaTileEnities;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = "gtwireless",
        name="GTWireless",
        acceptedMinecraftVersions = "[1.12.2,1.13)",
        version = "0.0.1-beta" ,
        dependencies = "required-after:gregtech@[2.8.0-beta,);"
)
public class GTWireless {
    public static final String PACK = "1.0.0";

    public static final String MODID = "gtwireless";
    public static final String NAME = "GTWireless";
    public static final String VERSION = "0.1";

    @Mod.Instance(GTWireless.MODID)
    public static GTWireless instance;
    @SidedProxy(modId = MODID,
            clientSide = "keqing.gtwireless.client.ClientProxy",
            serverSide = "keqing.gtwireless.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    public GTWireless() {}

    @Mod.EventHandler
    public void onConstruction(FMLConstructionEvent event) {
        ConfigHolder.machines.highTierContent = true;
        ConfigHolder.machines.enableHighTierSolars = true;
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        ConfigHolder.machines.highTierContent = true;
        GTWMetaTileEnities.init();
        proxy.preLoad();
    }



}