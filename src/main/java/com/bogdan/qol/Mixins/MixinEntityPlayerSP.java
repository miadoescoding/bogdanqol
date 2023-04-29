package com.bogdan.qol.Mixins;

import com.bogdan.qol.Events.ItemDropEvent;
import com.bogdan.qol.utils.ControlUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityPlayerSP.class})
public class MixinEntityPlayerSP {
    @Inject(
            method = {"dropOneItem"},
            at = {@At("HEAD")},
            cancellable = true
    )
    public void onDropItem(boolean var1, CallbackInfoReturnable var2) {
        if (MinecraftForge.EVENT_BUS.post(new ItemDropEvent(var1, ControlUtils.getHeldItemStack()))) {
            var2.setReturnValue(null);
        }

    }
}
