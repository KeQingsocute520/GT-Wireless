package keqing.gtwireless.client;

import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregtech.client.utils.BloomEffectUtil.getEffectiveBloomLayer;

@SideOnly(Side.CLIENT)
public class BloomEffectUtils {

    public static  BlockRenderLayer getRealBloomLayer() {
        return getEffectiveBloomLayer();
    }
}
