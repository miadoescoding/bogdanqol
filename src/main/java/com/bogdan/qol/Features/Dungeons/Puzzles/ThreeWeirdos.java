package com.bogdan.qol.Features.Dungeons.Puzzles;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.Features.Dungeons.Map.Dungeon;
import com.bogdan.qol.Features.Remote.RemoteUtils;
import com.bogdan.qol.bogdanqol;
import com.bogdan.qol.utils.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreeWeirdos {

    private static final String url = "https://cdn.jsdelivr.net/gh/Skytils/SkytilsMod-Data@main/solvers/threeweirdos.json";
    private static final HashSet solutions = new HashSet();
    private static String npc = null;
    private static long lastFetch = 0L;

    public static void fetch() {
        (new Thread(() -> {
            try {
                String var0 = RemoteUtils.get("https://cdn.jsdelivr.net/gh/Skytils/SkytilsMod-Data@main/solvers/threeweirdos.json", new ArrayList(), false);
                JsonParser var1 = new JsonParser();
                JsonArray var2 = var1.parse(String.format("{\"res\": %s}", var0)).getAsJsonObject().get("res").getAsJsonArray();
                synchronized (solutions) {
                    Iterator var4 = var2.iterator();

                    while (var4.hasNext()) {
                        JsonElement var5 = (JsonElement) var4.next();
                        solutions.add(var5.getAsString());
                    }
                }
            } catch (Exception var8) {
                var8.printStackTrace();
            }

        })).start();
    }

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (true) {
            if (TimeUtils.curTime() - lastFetch > 1200000L) {
                lastFetch = TimeUtils.curTime();
                fetch();
            }

            if (Configs.ThreeWeirdosSolver) {
                if (Dungeon.currentRoom.equals("Three Weirdos")) {
                    if (npc != null) {
                        BlockPos var2 = null;
                        Iterator var3 = EntityUtils.getEntities().iterator();

                        while (var3.hasNext()) {
                            Entity var4 = (Entity) var3.next();
                            if (var4 instanceof EntityArmorStand && var4.hasCustomName() && ChatLib.removeFormatting(var4.getCustomNameTag()).equals(npc)) {
                                var2 = new BlockPos(var4);
                                break;
                            }
                        }

                        if (var2 != null) {
                            for (int var6 = -5; var6 <= 5; ++var6) {
                                for (int var7 = -5; var7 <= 5; ++var7) {
                                    BlockPos var5 = var2.add(var6, 0, var7);
                                    if (Math.abs(var6) + Math.abs(var7) != 1 && BlockUtils.getBlockAt(var5) == Blocks.chest) {
                                        bogdanqol.mc.theWorld.setBlockToAir(var5);
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onReceive(ClientChatReceivedEvent var1) {
        if (true) {
            if (var1.type == 0) {
                if (Configs.ThreeWeirdosSolver) {
                    if (SkyblockUtils.isInDungeon()) {
                        if (Dungeon.currentRoom.equals("Three Weirdos")) {
                            String var2 = ChatLib.removeFormatting(var1.message.getUnformattedText()).trim();
                            if (var2.matches("PUZZLE SOLVED! [a-zA-Z0-9_]+ wasn't fooled by.*") || var2.matches("PUZZLE FAIL! [a-zA-Z0-9_]+ was fooled by \\w+! Yikes!")) {
                                npc = null;
                            }

                            if (var2.startsWith("[NPC]")) {
                                Pattern var3 = Pattern.compile("\\[NPC] (\\w+): [a-zA-Z!'., ]+");
                                Matcher var4 = var3.matcher(var2);
                                if (!var4.find()) {
                                    return;
                                }

                                String var5 = var4.group(1);
                                synchronized (solutions) {
                                    Iterator var7 = solutions.iterator();

                                    while (var7.hasNext()) {
                                        String var8 = (String) var7.next();
                                        if (var2.contains(var8)) {
                                            npc = var5;
                                            break;
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load var1) {
        npc = null;
    }
}
