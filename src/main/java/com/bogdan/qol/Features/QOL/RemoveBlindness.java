package com.bogdan.qol.Features.QOL;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.utils.MinecraftUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RemoveBlindness {
    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (true) {
            if (Configs.RemoveBlindness) {
                EntityPlayerSP var2 = MinecraftUtils.getPlayer();
                if (var2 != null) {
                    var2.removePotionEffect(Potion.blindness.id);
                }

            }
        }
    }
}
