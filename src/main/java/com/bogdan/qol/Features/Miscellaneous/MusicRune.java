package com.bogdan.qol.Features.Miscellaneous;

import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.utils.MinecraftUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MusicRune {
    public static void play() {
        (new Thread(() -> {
            int var0 = (int) (Math.random() * 10.0);

            try {
                MinecraftUtils.getPlayer().playSound("note.harp", 300.0F, (float) var0 / 12.0F);
                Thread.sleep(80L);
                MinecraftUtils.getPlayer().playSound("note.harp", 300.0F, (float) (var0 + 3) / 12.0F);
                Thread.sleep(80L);
                MinecraftUtils.getPlayer().playSound("note.harp", 300.0F, (float) (var0 + 6) / 12.0F);
                Thread.sleep(80L);
                MinecraftUtils.getPlayer().playSound("note.harp", 300.0F, (float) (var0 + 9) / 12.0F);
            } catch (Exception var2) {
                var2.printStackTrace();
            }

        })).start();
    }

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (true) {
        }
    }
}
