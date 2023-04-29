package com.bogdan.qol.Sounds;

import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.bogdanqol;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Iterator;

public class SoundHandler {

    public static final ArrayList playing = new ArrayList();

    public static void playSound(Sound var0) {
        synchronized (playing) {
            playing.add(var0);
        }
    }

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        synchronized (playing) {
            Iterator var3 = playing.iterator();

            while (var3.hasNext()) {
                Sound var4 = (Sound) var3.next();
                bogdanqol.mc.getSoundHandler().playSound(var4.sound);
            }

            playing.clear();
        }
    }
}
