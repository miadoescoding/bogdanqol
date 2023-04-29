package com.bogdan.qol.Mixins;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityRenderer.class})
public class MixinEntityRenderer {
    @Inject(
            method = {"hurtCameraEffect"},
            at = {@At("HEAD")},
            cancellable = true
    )
    public void onHurtCam(CallbackInfo var1) {
        if (SkyblockUtils.isInSkyblock()) {
            if (Configs.RemoveHurtCam) {
                var1.cancel();
            }

        }
    }
}
