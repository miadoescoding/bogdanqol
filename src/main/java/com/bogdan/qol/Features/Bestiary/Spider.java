package com.bogdan.qol.Features.Bestiary;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.Features.RenderEntityESP;
import com.bogdan.qol.Objects.EntityInfo;
import com.bogdan.qol.utils.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.Tuple3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Spider extends RenderEntityESP {

    private static final String ARACHNEKEEPER_STRING = "Arachne's Keeper";

    private static final int[][] shadowFuryPointsRange = new int[][]{{-237, 68, -320, -243, 63, -326}, {-225, 60, -319, -232, 62, -324}, {-212, 59, -314, -215, 63, -312}, {-218, 59, -319, -221, 64, -315}};
    private static final String BROODMOTHER_SHOW = "&5&lBrood Mother";
    private static final String BROODMOTHER_STRING = "Brood Mother";
    private static final String ARACHNEKEEPER_SHOW = "&c&lArachne's Keeper";
    private final HashMap lastWarnTime = new HashMap();
    private final HashMap lastDeathWarnTime = new HashMap();
    private boolean shadowFuryWarnedInThisLobby = false;
    private ArrayList bestShadowFuryPoints = new ArrayList();

    public void dealWithEntityInfo(EntityInfo var1) {
        long var2 = TimeUtils.curTime();
        String var4 = var1.getKind();
        String var5 = var1.getEntity().getName();
        String var6 = SkyblockUtils.getCurrentServer();
        if (var4.contains("Keeper")) {
            long var7 = (Long) this.lastDeathWarnTime.getOrDefault(var6, 0L);
            long var9 = (Long) this.lastWarnTime.getOrDefault(var6, 0L);
            if (var5.contains("§e0§f/§a")) {
                if (var2 > var7 + 60000L && Configs.ArachneKeeperDeathWarn) {
                    this.lastDeathWarnTime.put(var6, var2);
                    ChatLib.chat("&4&lKeeper died!");
                }
            } else if (var2 > var9 + 60000L && Configs.ArachneKeeperWarnMode == 1) {
                this.lastWarnTime.put(var6, var2);
                ChatLib.chat("&c&lArachne's Keeper is in this lobby!");
            }
        }

        if (Configs.ArachneKeeperWarnMode == 2 && var4.contains("Arachne's Keeper")) {
            GuiUtils.showTitle("&c&lArachne's Keeper", "", 0, 5, 0);
        }

        if (Configs.BroodMotherWarn && var4.contains("Brood Mother")) {
            GuiUtils.showTitle("&5&lBrood Mother", "", 0, 5, 0);
        }

    }

    @SubscribeEvent
    public void onRenderWorldShadowFury(RenderWorldLastEvent event) {
        if (Configs.SpiderDenShadowfuryPoint) {
            this.bestShadowFuryPoints.forEach((var0_) -> {
                Tuple3 var0 = (Tuple3) var0_;
                int var1 = (Integer) var0._1();
                int var2 = (Integer) var0._2();
                int var3 = (Integer) var0._3();
                GuiUtils.drawBoxAtBlock(var1, var2, var3, 0, 255, 0, 100, 1, 1, 0.01F);
            });
        }

    }

    public EntityInfo getEntityInfo(Entity var1) {
        if (!(var1 instanceof EntityArmorStand)) {
            return null;
        } else {
            HashMap var2 = new HashMap();
            String var3 = ChatLib.removeFormatting(var1.getName());
            var2.put("entity", var1);
            var2.put("yOffset", 1.0F);
            var2.put("drawString", EntityInfo.EnumDraw.DRAW_KIND);
            var2.put("width", 1.0F);
            var2.put("fontColor", 3407667);
            var2.put("isFilled", true);
            if (var3.contains("Arachne's Keeper")) {
                var2.put("kind", "Arachne's Keeper");
                return new EntityInfo(var2);
            } else if (var3.contains("Brood Mother")) {
                var2.put("kind", "Brood Mother");
                return new EntityInfo(var2);
            } else {
                return null;
            }
        }
    }

    public boolean enabled() {
        return SkyblockUtils.isInSpiderDen();
    }

    public boolean shouldDrawString(EntityInfo var1) {
        if (SkyblockUtils.isInSpiderDen()) {
            String var2 = var1.getKind();
            return Configs.ArachneKeeperDisplayName && var2.contains("Arachne's Keeper") || Configs.BroodMotherDisplayName && var2.contains("Brood Mother");
        } else {
            return false;
        }
    }

    @SubscribeEvent
    public void onTickShadowFury(TickEndEvent var1) {
        if (true) {
            if (SkyblockUtils.isInSpiderDen() && Configs.SpiderDenShadowfuryPoint) {
                ArrayList var2 = new ArrayList();
                List var3 = EntityUtils.getEntities();
                Iterator var4 = var3.iterator();

                while (var4.hasNext()) {
                    Entity var5 = (Entity) var4.next();
                    if (var5 instanceof EntityArmorStand) {
                        double var6 = MathUtils.getX(var5);
                        double var8 = MathUtils.getY(var5);
                        double var10 = MathUtils.getZ(var5);
                        if (!(var6 < -260.0) && var6 <= -204.0 && !(var8 < 59.0) && var8 <= 77.0 && !(var10 < -350.0) && var10 <= -300.0 && !var5.getName().contains("Rick") && !var5.getName().contains("CLICK")) {
                            var2.add(var5);
                        }
                    }
                }

                boolean var28 = false;
                int var29 = 100000;
                this.bestShadowFuryPoints = new ArrayList();
                int[][] var30 = shadowFuryPointsRange;
                int var7 = var30.length;

                for (int var31 = 0; var31 < var7; ++var31) {
                    int[] var9 = var30[var31];
                    int var32 = Math.min(var9[0], var9[3]);
                    int var11 = Math.min(var9[1], var9[4]);
                    int var12 = Math.min(var9[2], var9[5]);
                    int var13 = Math.max(var9[0], var9[3]);
                    int var14 = Math.max(var9[1], var9[4]);
                    int var15 = Math.max(var9[2], var9[5]);

                    for (int var16 = var32; var16 <= var13; ++var16) {
                        for (int var17 = var11; var17 <= var14; ++var17) {
                            for (int var18 = var12; var18 <= var15; ++var18) {
                                int var19 = 0;
                                Iterator var20 = var2.iterator();

                                while (true) {
                                    while (var20.hasNext()) {
                                        Entity var21 = (Entity) var20.next();
                                        double var22 = MathUtils.getX(var21);
                                        double var24 = MathUtils.getY(var21);
                                        double var26 = MathUtils.getZ(var21);
                                        if (var26 > -335.0 && var26 < -320.0 && var24 > 59.0 && var24 < 77.0 && var22 > -248.0 && var22 < -216.0) {
                                            var28 = true;
                                        } else if (MathUtils.distanceSquaredFromPoints((double) var16 + 0.5, var17, (double) var18 + 0.5, var22, var24 - 1.2, var26) < 144.0) {
                                            ++var19;
                                        }
                                    }

                                    if (var19 > 0) {
                                        if (var19 < var29) {
                                            var29 = var19;
                                            this.bestShadowFuryPoints = new ArrayList();
                                        }

                                        if (var19 <= var29) {
                                            this.bestShadowFuryPoints.add(new Tuple3(var16, var17, var18));
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }

                if (var28 && !this.shadowFuryWarnedInThisLobby) {
                    this.shadowFuryWarnedInThisLobby = true;
                    ChatLib.chat("&5Be aware: Kill the outside mobs before shadow fury!");
                }

            }
        }
    }

    public boolean shouldRenderESP(EntityInfo var1) {
        if (SkyblockUtils.isInSpiderDen()) {
            String var2 = var1.getKind();
            return Configs.ArachneKeeperDisplayBox && var2.contains("Arachne's Keeper") || Configs.BroodMotherDisplayBox && var2.contains("Brood Mother");
        } else {
            return false;
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load var1) {
        if (true) {
            this.shadowFuryWarnedInThisLobby = false;
        }
    }
}
