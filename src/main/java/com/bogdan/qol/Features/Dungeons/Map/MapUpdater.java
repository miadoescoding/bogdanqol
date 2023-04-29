package com.bogdan.qol.Features.Dungeons.Map;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.TimeUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MapUpdater {

    private long lastMakeMap = 0L;

    private long lastUpdate = 0L;

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (TimeUtils.curTime() - this.lastMakeMap >= 500L && Configs.MapEnabled && Dungeon.isInDungeon && Dungeon.isFullyScanned) {
            this.lastMakeMap = TimeUtils.curTime();
            (new Thread(Dungeon::makeMap)).start();
        }

        if (TimeUtils.curTime() - this.lastUpdate >= 200L && Configs.MapEnabled && Dungeon.isInDungeon) {
            this.lastUpdate = TimeUtils.curTime();
            (new Thread(() -> {
                try {
                    Dungeon.updatePlayers();
                } catch (Exception var3) {
                    var3.printStackTrace();
                    ChatLib.chat("error updatePlayers");
                }

                try {
                    Dungeon.updateRooms();
                } catch (Exception var2) {
                    var2.printStackTrace();
                    ChatLib.chat("error updateRooms");
                }

                try {
                    Dungeon.updateDoors();
                } catch (Exception var1) {
                    var1.printStackTrace();
                    ChatLib.chat("error updateDoors");
                }

            })).start();
        }

    }
}
