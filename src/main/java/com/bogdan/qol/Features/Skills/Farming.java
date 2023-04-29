package com.bogdan.qol.Features.Skills;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.Objects.KeyBind;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.ControlUtils;
import com.bogdan.qol.utils.MathUtils;
import com.bogdan.qol.utils.MinecraftUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Farming {

    private static final KeyBind keyBind = new KeyBind("Auto Farming", 0);
    private static boolean should = false;
    private static boolean autoFarmingThreadLock = false;

    private static boolean enabled() {
        return should;
    }

    private static void moveAccordingToRight(boolean var0) {
        if (var0) {
            ControlUtils.holdRight();
            ControlUtils.releaseLeft();
        } else {
            ControlUtils.holdLeft();
            ControlUtils.releaseRight();
        }

    }

    private static void stop() {
        if (should) {
            if (MinecraftUtils.getPlayer() != null) {
                MinecraftUtils.getPlayer().playSound("random.successful_hit", 1000.0F, 1.0F);
            }

            should = false;
            ChatLib.chat("Auto Farming &cdeactivated");
        }
    }

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (keyBind.isPressed()) {
            should = !should;
            ChatLib.chat("Auto Farming" + (should ? " &aactivated" : " &cdeactivated"));
        }

        if (Configs.AutoFarm) {
            if (should) {
                if (!autoFarmingThreadLock) {
                    if (MinecraftUtils.getPlayer() != null) {
                        autoFarmingThreadLock = true;
                        (new Thread(() -> {
                            try {
                                boolean var0 = MathUtils.getYaw() < 0.0F;
                                ControlUtils.changeDirection(180.0F, 10.0F);
                                ControlUtils.stopMoving();
                                ControlUtils.holdLeftClick();
                                moveAccordingToRight(var0);

                                while (enabled()) {
                                    float var1 = MathUtils.getY(MinecraftUtils.getPlayer());

                                    while (MathUtils.getY(MinecraftUtils.getPlayer()) >= var1 && enabled()) {
                                        moveAccordingToRight(var0);
                                        Thread.sleep(20L);
                                    }

                                    if (!enabled()) {
                                        break;
                                    }

                                    while (MathUtils.getY(MinecraftUtils.getPlayer()) != var1 - 3.0F && MathUtils.getY(MinecraftUtils.getPlayer()) < var1 + 200.0F && enabled()) {
                                        Thread.sleep(20L);
                                    }

                                    if (MathUtils.getY(MinecraftUtils.getPlayer()) > var1 + 200.0F) {
                                        Thread.sleep(1000L);
                                        var0 = MathUtils.getX(MinecraftUtils.getPlayer()) < 0.0F;
                                    } else {
                                        var0 = !var0;
                                    }
                                }

                                ControlUtils.releaseLeft();
                                ControlUtils.releaseRight();
                                ControlUtils.releaseLeftClick();
                                stop();
                            } catch (Exception var5) {
                                var5.printStackTrace();
                                stop();
                            } finally {
                                ControlUtils.releaseForward();
                                autoFarmingThreadLock = false;
                            }

                        })).start();
                    }
                }
            }
        }
    }
}
