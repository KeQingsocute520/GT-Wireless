package keqing.gtwireless;

import net.minecraftforge.common.config.Config;

import static keqing.gtwireless.GTWireless.MODID;

@Config(modid = MODID)
public class GTWConfig {
    @Config.Comment("开启配方注册")
    @Config.RequiresMcRestart
    @Config.Name("Enable Recipe resiger")
    public static boolean EnableReciperesiger = true;
}
