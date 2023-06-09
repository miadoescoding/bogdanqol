package com.bogdan.qol.Features.Miscellaneous;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.bogdanqol;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.TimeUtils;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasyTrigger {

    private static final ArrayDeque queue = new ArrayDeque();

    public static void doCommand(String var0, int var1) {
        if (Configs.EasyTriggerDebugMode) {
            ChatLib.chat("Easy Trigger - " + var1 + " triggered", false);
        }

        if (queue.size() >= 5) {
            if (Configs.EasyTriggerDebugMode) {
                ChatLib.chat("Easy Trigger - but queue length exceeded.", false);
            }

        } else {
            queue.offerLast(TimeUtils.curTime());
            if (!var0.startsWith("/") || ClientCommandHandler.instance.executeCommand(bogdanqol.mc.thePlayer, var0) == 0) {
                bogdanqol.mc.thePlayer.sendChatMessage(var0);
            }

        }
    }

    @SubscribeEvent
    public void onReceive(ClientChatReceivedEvent var1) {
        if (true) {
            if (Configs.EasyTrigger) {
                String var2 = ChatLib.removeFormatting(var1.message.getUnformattedText());
                Pattern var3;
                Matcher var4;
                if (Configs.EasyTriggerPair1Enabled) {
                    var3 = Pattern.compile(Configs.EasyTriggerPair1OnReceive);
                    var4 = var3.matcher(var2);
                    if (var4.find()) {
                        doCommand(Configs.EasyTriggerPair1DoCommand, 1);
                    }
                }

                if (Configs.EasyTriggerPair2Enabled) {
                    var3 = Pattern.compile(Configs.EasyTriggerPair2OnReceive);
                    var4 = var3.matcher(var2);
                    if (var4.find()) {
                        doCommand(Configs.EasyTriggerPair2DoCommand, 2);
                    }
                }

                if (Configs.EasyTriggerPair3Enabled) {
                    var3 = Pattern.compile(Configs.EasyTriggerPair3OnReceive);
                    var4 = var3.matcher(var2);
                    if (var4.find()) {
                        doCommand(Configs.EasyTriggerPair3DoCommand, 3);
                    }
                }

                if (Configs.EasyTriggerPair4Enabled) {
                    var3 = Pattern.compile(Configs.EasyTriggerPair4OnReceive);
                    var4 = var3.matcher(var2);
                    if (var4.find()) {
                        doCommand(Configs.EasyTriggerPair4DoCommand, 4);
                    }
                }

                if (Configs.EasyTriggerPair5Enabled) {
                    var3 = Pattern.compile(Configs.EasyTriggerPair5OnReceive);
                    var4 = var3.matcher(var2);
                    if (var4.find()) {
                        doCommand(Configs.EasyTriggerPair5DoCommand, 5);
                    }
                }

            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        long var2 = TimeUtils.curTime();

        while (!queue.isEmpty() && var2 - (Long) queue.getFirst() > 1000L) {
            queue.pollFirst();
        }

    }
}
