package com.bogdan.qol.Features.Accentry;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.PacketReceivedEvent;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.CommandsUtils;
import com.bogdan.qol.utils.PacketUtils;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoBack {
    @SubscribeEvent
    public void onTitlePacketReceived(PacketReceivedEvent var1) {
        if (Configs.AutoBack) {
            if (var1.packet instanceof S45PacketTitle) {
                S45PacketTitle var2 = (S45PacketTitle) var1.packet;
                IChatComponent var3 = PacketUtils.getMessage(var2);
                if (var3 == null) {
                    return;
                }

                String var4 = ChatLib.removeFormatting(var3.getUnformattedText());
                if (var4.contains("你失败了")) {
                    CommandsUtils.addCommand("/back");
                }
            }

        }
    }
}
