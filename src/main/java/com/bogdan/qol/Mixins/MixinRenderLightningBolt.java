package com.bogdan.qol.Mixins;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.client.renderer.entity.RenderLightningBolt;
import net.minecraft.entity.effect.EntityLightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderLightningBolt.class})
public class MixinRenderLightningBolt {
    @Inject(
            method = {"doRender"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void onRenderLightning(EntityLightningBolt var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
        if (SkyblockUtils.isInSkyblock()) {
            if (Configs.DisableLightnings) {
                var10.cancel();
            }
        }
    }
}
