package com.bogdan.qol.Mixins;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Features.QOL.GhostQOL;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.client.renderer.entity.layers.LayerCreeperCharge;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin({LayerCreeperCharge.class})
public abstract class MixinLayerCreeperCharge implements LayerRenderer {

    ResourceLocation VISIBLE_CREEPER_ARMOR = new ResourceLocation("bogdanqol", "creeper_armor.png");

    @ModifyArg(
            method = {"doRenderLayer"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/RenderCreeper;bindTexture(Lnet/minecraft/util/ResourceLocation;)V"
            )
    )
    private ResourceLocation modifyChargedCreeperLayer(ResourceLocation var1) {
        return SkyblockUtils.isInMist() && Configs.VisibleGhost == GhostQOL.FILLED_OUTLINE_BOX ? this.VISIBLE_CREEPER_ARMOR : var1;
    }
}
