package keqing.gtwireless.client;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.gui.resources.ResourceHelper;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.cclop.LightMapOperation;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;

import static keqing.gtwireless.GTWireless.MODID;
import static keqing.gtwireless.client.BloomEffectUtils.getRealBloomLayer;

public class GTWOverlayRenderer implements ICubeRenderer {

    /**
     * Path of your texture (in resource location).
     */
    private final String path;

    /**
     * Basic texture.
     *
     * <p>
     *     Used {@link TextureAtlasSprite} to load texture.
     * </p>
     */
    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite sprite;

    /**
     * Emissive texture (extended texture).
     *
     * <p>
     *     Also used {@link TextureAtlasSprite},
     *     if you add '_emissive' prefix on files, then add an extended sprite.
     * </p>
     */

    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite spriteEmissive;

    public GTWOverlayRenderer(String path) {
        this.path = path;
        Textures.CUBE_RENDERER_REGISTRY.put(path, this);
        Textures.iconRegisters.add(this);
    }


    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap textureMap) {
        String modID = MODID;
        String path = this.path;
        String[] split = this.path.split(":");
        if (split.length == 2) {
            modID = split[0];
            path = split[1];
        }
        this.sprite = textureMap.registerSprite(new ResourceLocation(modID, "blocks/" + path));
        String emissive = "blocks/" + path + "_emissive";
        if (ResourceHelper.doResourcepacksHaveTexture(modID, emissive, true)) {
            this.spriteEmissive = textureMap.registerSprite(new ResourceLocation(modID, emissive));
        }
    }

    /**
     * Register Extended textures.
     *
     * <p>
     *     If texture has extended emissive, and settings are load,
     *     i.e. correspond setting in {@link ConfigHolder} is true.
     *     Then load a extended emissive texture.
     * </p>
     *
     * @param renderState       Special Renderer State.
     * @param translation       Translation Matrix.
     * @param pipeline          Vertex Operation.
     * @param bounds            Boundaries.
     * @param frontFacing       Front Face.
     * @param isActive          Used to check if machine is active.
     * @param isWorkingEnabled  Used to check if machine is working enabled.
     */
    @SideOnly(Side.CLIENT)
    public void renderOrientedState(CCRenderState renderState,
                                    Matrix4 translation,
                                    IVertexOperation[] pipeline,
                                    Cuboid6 bounds,
                                    EnumFacing frontFacing,
                                    boolean isActive,
                                    boolean isWorkingEnabled) {
        Textures.renderFace(renderState, translation, pipeline, frontFacing, bounds, this.sprite, BlockRenderLayer.CUTOUT_MIPPED);
        if (this.spriteEmissive != null) {
            if (ConfigHolder.client.machinesEmissiveTextures) {
                IVertexOperation[] lightPipeline = ArrayUtils.add(pipeline, new LightMapOperation(240, 240));
                Textures.renderFace(renderState, translation, lightPipeline, frontFacing, bounds, this.spriteEmissive, getRealBloomLayer());
            } else {
                Textures.renderFace(renderState, translation, pipeline, frontFacing, bounds, this.spriteEmissive, BlockRenderLayer.CUTOUT_MIPPED);
            }
        }
    }

    /**
     * Particle Sprite Getter
     *
     * @return  Texture Atlas Sprite.
     */
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getParticleSprite() {
        return this.sprite;
    }
}