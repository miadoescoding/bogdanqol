package com.bogdan.qol.Mixins;

import com.bogdan.qol.Events.UpdateEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityPlayer.class})
public abstract class MixinEntityPlayer {
    @Inject(
            method = {"onLivingUpdate"},
            at = {@At("HEAD")}
    )
    private void livingUpdate(CallbackInfo var1) {
        MinecraftForge.EVENT_BUS.post(new UpdateEvent());
    }
}
