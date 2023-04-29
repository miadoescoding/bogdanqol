package com.bogdan.qol.Features.Accentry;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.UpdateEvent;
import com.bogdan.qol.bogdanqol;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.MinecraftUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FastUse {
    @SubscribeEvent
    public void onRightClick(UpdateEvent var1) {
        if (Configs.FastUse) {
            EntityPlayerSP var2 = MinecraftUtils.getPlayer();
            if (var2 != null) {
                ItemStack var3 = var2.getItemInUse();
                if (var3 != null && var3.getItem() instanceof ItemFood) {
                    for (int var4 = 0; var4 < 35; ++var4) {
                        bogdanqol.mc.getNetHandler().addToSendQueue(new C03PacketPlayer(MinecraftUtils.getPlayer().onGround));
                    }

                    var2.stopUsingItem();
                    ChatLib.debug("Fast Use!");
                }

            }
        }
    }
}
