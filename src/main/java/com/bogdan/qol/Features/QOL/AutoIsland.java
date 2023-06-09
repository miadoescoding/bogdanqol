package com.bogdan.qol.Features.QOL;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.CommandsUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoIsland {

    private boolean evacuate = false;

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent var1) {
        if (true) {
            if (Configs.AutoIsland) {
                String var2 = var1.message.getUnformattedText();
                if (var2.equals("Evacuating to Hub...")) {
                    this.evacuate = true;
                }

            }
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (this.evacuate) {
            this.evacuate = false;
            (new Thread(() -> {
                try {
                    Thread.sleep(2000L);
                    ChatLib.chat("Auto Island");
                    CommandsUtils.addCommand("/is");
                } catch (Exception var1) {
                    var1.printStackTrace();
                }

            })).start();
        }

    }
}
