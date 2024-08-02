package keqing.gtwireless.common;
import gregtech.api.metatileentity.MetaTileEntity;
import keqing.gtwireless.common.part.MetaTileEntityWirelessEnergyHatch;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.GTValues.VN;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static keqing.gtwireless.GTWireless.MODID;

public class GTWMetaTileEnities {
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_INPUT_ENERGY_HATCH = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_OUTPUT_ENERGY_HATCH = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_INPUT_ENERGY_HATCH_4A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_OUTPUT_ENERGY_HATCH_4A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_INPUT_ENERGY_HATCH_16A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_OUTPUT_ENERGY_HATCH_16A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_INPUT_ENERGY_HATCH_64A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_OUTPUT_ENERGY_HATCH_64A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_INPUT_ENERGY_HATCH_256A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_OUTPUT_ENERGY_HATCH_256A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_INPUT_ENERGY_HATCH_1024A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_OUTPUT_ENERGY_HATCH_1024A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_INPUT_ENERGY_HATCH_4096A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_OUTPUT_ENERGY_HATCH_4096A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_INPUT_ENERGY_HATCH_16384A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_OUTPUT_ENERGY_HATCH_16384A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_INPUT_ENERGY_HATCH_65536A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_OUTPUT_ENERGY_HATCH_65536A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_INPUT_ENERGY_HATCH_262144A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_OUTPUT_ENERGY_HATCH_262144A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_INPUT_ENERGY_HATCH_1048576A = new MetaTileEntityWirelessEnergyHatch[15];
    public static final MetaTileEntityWirelessEnergyHatch[] WIRELESS_OUTPUT_ENERGY_HATCH_1048576A = new MetaTileEntityWirelessEnergyHatch[15];

    private static <F extends MetaTileEntity> F registerPartMetaTileEntity(int id, F mte) {
        if (id > 1000)
            return null;
        return registerMetaTileEntity(id + 13999, mte);
    }
    
    public static ResourceLocation gtwId( String path) {
        return new ResourceLocation(MODID, path);
    }
    public static void init() {
        //  ULV-MAX Wireless Energy/Dynamo Hatch (consist of high-amp version)
        for (int i = 0; i < 15; i++) {
            String tier = VN[i].toLowerCase();
            WIRELESS_INPUT_ENERGY_HATCH[i]          = registerPartMetaTileEntity( i,       new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.input."          + tier), i, 2,       false));
            WIRELESS_INPUT_ENERGY_HATCH_4A[i]       = registerPartMetaTileEntity( 15 + i,  new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.input_4a."       + tier), i, 4,       false));
            WIRELESS_INPUT_ENERGY_HATCH_16A[i]      = registerPartMetaTileEntity( 30 + i,  new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.input_16a."      + tier), i, 16,      false));
            WIRELESS_INPUT_ENERGY_HATCH_64A[i]      = registerPartMetaTileEntity( 45 + i,  new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.input_64a."      + tier), i, 64,      false));
            WIRELESS_INPUT_ENERGY_HATCH_256A[i]     = registerPartMetaTileEntity( 60 + i,  new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.input_256a."     + tier), i, 256,     false));
            WIRELESS_INPUT_ENERGY_HATCH_1024A[i]    = registerPartMetaTileEntity( 75 + i,  new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.input_1024a."    + tier), i, 1024,    false));
            WIRELESS_INPUT_ENERGY_HATCH_4096A[i]    = registerPartMetaTileEntity( 90 + i,  new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.input_4096a."    + tier), i, 4096,    false));
            WIRELESS_INPUT_ENERGY_HATCH_16384A[i]   = registerPartMetaTileEntity( 105 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.input_16384a."   + tier), i, 16384,   false));
            WIRELESS_INPUT_ENERGY_HATCH_65536A[i]   = registerPartMetaTileEntity( 120 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.input_65536a."   + tier), i, 65536,   false));
            WIRELESS_INPUT_ENERGY_HATCH_262144A[i]  = registerPartMetaTileEntity( 135 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.input_262144a."  + tier), i, 262144,  false));
            WIRELESS_INPUT_ENERGY_HATCH_1048576A[i] = registerPartMetaTileEntity( 150 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.input_1048576a." + tier), i, 1048576, false));

            WIRELESS_OUTPUT_ENERGY_HATCH[i]          = registerPartMetaTileEntity( 165 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.output."          + tier), i, 2,       true));
            WIRELESS_OUTPUT_ENERGY_HATCH_4A[i]       = registerPartMetaTileEntity( 180 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.output_4a."       + tier), i, 4,       true));
            WIRELESS_OUTPUT_ENERGY_HATCH_16A[i]      = registerPartMetaTileEntity( 195 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.output_16a."      + tier), i, 16,      true));
            WIRELESS_OUTPUT_ENERGY_HATCH_64A[i]      = registerPartMetaTileEntity( 210 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.output_64a."      + tier), i, 64,      true));
            WIRELESS_OUTPUT_ENERGY_HATCH_256A[i]     = registerPartMetaTileEntity( 225 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.output_256a."     + tier), i, 256,     true));
            WIRELESS_OUTPUT_ENERGY_HATCH_1024A[i]    = registerPartMetaTileEntity( 240 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.output_1024a."    + tier), i, 1024,    true));
            WIRELESS_OUTPUT_ENERGY_HATCH_4096A[i]    = registerPartMetaTileEntity( 255 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.output_4096a."    + tier), i, 4096,    true));
            WIRELESS_OUTPUT_ENERGY_HATCH_16384A[i]   = registerPartMetaTileEntity( 270 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.output_16384a."   + tier), i, 16384,   true));
            WIRELESS_OUTPUT_ENERGY_HATCH_65536A[i]   = registerPartMetaTileEntity( 285 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.output_65536a."   + tier), i, 65536,   true));
            WIRELESS_OUTPUT_ENERGY_HATCH_262144A[i]  = registerPartMetaTileEntity( 300 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.output_262144a."  + tier), i, 262144,  true));
            WIRELESS_OUTPUT_ENERGY_HATCH_1048576A[i] = registerPartMetaTileEntity( 315 + i, new MetaTileEntityWirelessEnergyHatch(gtwId("wireless_energy_hatch.output_1048576a." + tier), i, 1048576, true));
        }
    }
}
