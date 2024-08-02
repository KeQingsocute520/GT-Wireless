package keqing.gtwireless.loader;


import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.common.blocks.BlockComputerCasing;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.MetaBlocks;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.Lubricant;
import static gregtech.api.unification.material.Materials.SolderingAlloy;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;
import static keqing.gtwireless.common.GTWMetaTileEnities.*;


public class WirelessEnergyHatches {
    public static final Material[] tierList = { MarkerMaterials.Tier.ULV, MarkerMaterials.Tier.LV, MarkerMaterials.Tier.MV, MarkerMaterials.Tier.HV, MarkerMaterials.Tier.EV, MarkerMaterials.Tier.IV, MarkerMaterials.Tier.LuV, MarkerMaterials.Tier.ZPM, MarkerMaterials.Tier.UV, MarkerMaterials.Tier.UHV, MarkerMaterials.Tier.UEV, MarkerMaterials.Tier.UIV, MarkerMaterials.Tier.UXV, MarkerMaterials.Tier.OpV, MarkerMaterials.Tier.MAX };
    public static final int[] CWT = new int[]{8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072};
    public static void init() {
        //  TODO Add MAX stage recipes, should rebalanced it now,
        //       because when add MAX stage recipe, then we can make MAX Magneto Resonatic Circuit.

        for (int i = ULV; i < UHV; i++) {
            int finalI = i;
            if(i<IV) {
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                        .circuitMeta(1)
                        .input(circuit, tierList[i])
                        .input(ENERGY_INPUT_HATCH[i])
                        .output(WIRELESS_INPUT_ENERGY_HATCH[i])
                        .EUt(VA[finalI])
                        .duration(200)
                        .buildAndRegister();

                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                        .circuitMeta(1)
                        .input(circuit, tierList[i])
                        .input(ENERGY_OUTPUT_HATCH[i])
                        .output(WIRELESS_OUTPUT_ENERGY_HATCH[i])
                        .EUt(VA[finalI])
                        .duration(200)
                        .buildAndRegister();

            }
            else if(i<UV)
            {
                RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                        .input(circuit, tierList[i])
                        .input(ENERGY_INPUT_HATCH[i])
                        .output(WIRELESS_INPUT_ENERGY_HATCH[i])
                        .scannerResearch(b -> b.researchStack(WIRELESS_INPUT_ENERGY_HATCH[finalI -1].getStackForm()).duration(200).EUt(VA[finalI]))
                        .EUt(VA[finalI])
                        .duration(200)
                        .buildAndRegister();



                RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                        .input(circuit, tierList[i])
                        .input(ENERGY_OUTPUT_HATCH[i])
                        .output(WIRELESS_OUTPUT_ENERGY_HATCH[i])
                        .scannerResearch(b -> b.researchStack(WIRELESS_OUTPUT_ENERGY_HATCH[finalI -1].getStackForm()).duration(200).EUt(VA[finalI]))
                        .EUt(VA[finalI])
                        .duration(200)
                        .buildAndRegister();

            }
            else
            {
                RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                        .input(circuit, tierList[i])
                        .input(ENERGY_INPUT_HATCH[i])
                        .output(WIRELESS_INPUT_ENERGY_HATCH[i])
                        .stationResearch(b -> b.researchStack(WIRELESS_INPUT_ENERGY_HATCH[finalI -1].getStackForm()).CWUt(CWT[finalI]).EUt(VA[finalI]))
                        .EUt(VA[finalI])
                        .duration(200)
                        .buildAndRegister();



                RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                        .input(circuit, tierList[i])
                        .input(ENERGY_OUTPUT_HATCH[i])
                        .output(WIRELESS_OUTPUT_ENERGY_HATCH[i])
                        .stationResearch(b -> b.researchStack(WIRELESS_OUTPUT_ENERGY_HATCH[finalI -1].getStackForm()).CWUt(CWT[finalI]).EUt(VA[finalI]))
                        .EUt(VA[finalI])
                        .duration(200)
                        .buildAndRegister();

            }


        }
    }
}
