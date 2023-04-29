package com.bogdan.qol.utils;

import com.bogdan.qol.bogdanqol;
import net.minecraft.network.Packet;

public class NetUtils {
    public static final void sendPacket(Packet var0) {
        bogdanqol.mc.getNetHandler().getNetworkManager().sendPacket(var0);
    }
}
