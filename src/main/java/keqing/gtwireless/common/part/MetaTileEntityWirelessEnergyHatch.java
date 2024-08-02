package keqing.gtwireless.common.part;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtech.client.utils.PipelineUtil;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import keqing.gtwireless.api.capability.GTWDataCode;
import keqing.gtwireless.api.capability.WirelessEnergyNetworkWorldSavedData;
import keqing.gtwireless.api.capability.impl.WirelessEnergyContainerHandler;
import keqing.gtwireless.client.GTWTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;
import java.util.UUID;

public class MetaTileEntityWirelessEnergyHatch extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IEnergyContainer> {

    private final int amperage;
    private final boolean isExport;
    private final WirelessEnergyContainerHandler energyContainer;
    private UUID uuid = null;
    private final String NBT_TAG = "WirelessEnergyNetworkUUID";

    public MetaTileEntityWirelessEnergyHatch(ResourceLocation metaTileEntityId,
                                             int tier,
                                             int amperage,
                                             boolean isExport) {
        super(metaTileEntityId, tier);
        this.amperage = amperage;
        this.isExport = isExport;
        if (isExport) {
            this.energyContainer = WirelessEnergyContainerHandler.emitterContainer(this, GTValues.V[tier] * 64L * (long) amperage, GTValues.V[tier], amperage);
        } else {
            this.energyContainer = WirelessEnergyContainerHandler.receiverContainer(this, GTValues.V[tier] * 16L * (long) amperage, GTValues.V[tier], amperage);
        }
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityWirelessEnergyHatch(metaTileEntityId, this.getTier(), amperage, isExport);
    }

    @Override
    public MultiblockAbility<IEnergyContainer> getAbility() {
        return isExport ? MultiblockAbility.OUTPUT_ENERGY : MultiblockAbility.INPUT_ENERGY;
    }

    @Override
    public void registerAbilities(List<IEnergyContainer> list) {
        list.add(energyContainer);
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GTWDataCode.ChannelWirelessEnergyHatchUUID) {
            this.uuid = buf.readUniqueId();
            this.energyContainer.uuid = buf.readUniqueId();
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        if (this.uuid != null)
            data.setUniqueId(NBT_TAG, uuid);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        if (data.hasKey(NBT_TAG + "Most")) {
            this.uuid = data.getUniqueId(NBT_TAG);
            this.energyContainer.uuid = data.getUniqueId(NBT_TAG);
        }
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    protected boolean openGUIOnRightClick() {
        return false;
    }

    @Override
    public final boolean onScrewdriverClick(EntityPlayer player,
                                            EnumHand hand,
                                            EnumFacing facing,
                                            CuboidRayTraceResult hitResult) {
        if (player.isSneaking()) {
            setUUID(player.getUniqueID());
            if (player.getEntityWorld().isRemote) {
                player.sendMessage( new TextComponentTranslation("gtwireless.machine.wireless_energy_hatch.connect"));
            }
        }
        return true;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
        this.energyContainer.uuid = uuid;
        this.writeCustomData(GTWDataCode.ChannelWirelessEnergyHatchUUID, (b) -> b.writeUniqueId(this.uuid));
    }

    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (this.shouldRenderOverlay()) {
            getOverlay().renderSided(getFrontFacing(),renderState,translation,pipeline);
        }

    }
    private  SimpleOverlayRenderer getOverlay() {
        if (amperage == 2) {
            return GTWTexture.MULTIPART_WIRELESS_ENERGY;
        } else if (amperage == 4) {
            return GTWTexture.MULTIPART_WIRELESS_ENERGY_4x;
        } else if (amperage == 16) {
            return GTWTexture.MULTIPART_WIRELESS_ENERGY_16x;
        } else if (amperage == 64) {
            return GTWTexture.MULTIPART_WIRELESS_ENERGY_64x;
        } else if (amperage == 256) {
            return GTWTexture.MULTIPART_WIRELESS_ENERGY_256x;
        } else if (amperage == 1024) {
            return GTWTexture.MULTIPART_WIRELESS_ENERGY_1024x;
        } else if (amperage == 4096) {
            return GTWTexture.MULTIPART_WIRELESS_ENERGY_4096x;
        } else if (amperage == 16384) {
            return GTWTexture.MULTIPART_WIRELESS_ENERGY_16384x;
        } else if (amperage == 65536) {
            return GTWTexture.MULTIPART_WIRELESS_ENERGY_65536x;
        } else if (amperage == 262144) {
            return GTWTexture.MULTIPART_WIRELESS_ENERGY_262144x;
        } else if (amperage == 1048576) {
            return GTWTexture.MULTIPART_WIRELESS_ENERGY_1048576x;
        }
        else return GTWTexture.MULTIPART_WIRELESS_ENERGY;

    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, boolean advanced) {
        String tierName = GTValues.VNF[this.getTier()];
        tooltip.add(I18n.format("gtwireless.machine.wireless_energy_hatch.tooltip.1"));
        tooltip.add(I18n.format("gtwireless.machine.wireless_energy_hatch.tooltip.2"));

        if (this.isExport) {
            tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_out", this.energyContainer.getOutputVoltage(), tierName));
            tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_out_till", this.energyContainer.getOutputAmperage()));
        } else {
            tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_in", this.energyContainer.getInputVoltage(), tierName));
            tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_in_till", this.energyContainer.getInputAmperage()));
        }

        tooltip.add(I18n.format("gregtech.universal.tooltip.energy_storage_capacity", this.energyContainer.getEnergyCapacity()));
        tooltip.add(I18n.format("gregtech.universal.enabled"));

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            tooltip.add(I18n.format("gtwireless.machine.wireless_energy_hatch.tooltip.shift"));
        } else {
            tooltip.add(I18n.format("gregtech.tooltip.hold_shift"));
        }
    }

    @Override
    public void addToolUsages(ItemStack stack,
                              World world,
                              List<String> tooltip,
                              boolean advanced) {
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"));
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"));
        super.addToolUsages(stack, world, tooltip, advanced);
    }
}
