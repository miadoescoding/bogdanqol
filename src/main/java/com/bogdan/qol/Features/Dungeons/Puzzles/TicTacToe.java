package com.bogdan.qol.Features.Dungeons.Puzzles;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Features.Dungeons.Map.Room;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TicTacToe {

    private static Room room;

    public static void setRoom(Room var0) {
        room = var0;
    }

    @SubscribeEvent
    public void onRightClickButton(PlayerInteractEvent var1) {
        if (true) {
            if (Configs.TicTacToeSolver) {
            }
        }
    }
}
