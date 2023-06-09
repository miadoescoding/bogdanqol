package com.bogdan.qol.Commands;

import com.bogdan.qol.utils.ChatLib;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class FarmingType extends CommandBase {
    public String getCommandName() {
        return "farmingtype";
    }

    public String getCommandUsage(ICommandSender var1) {
        return this.getUsage();
    }

    public void processCommand(ICommandSender var1, String[] var2) throws CommandException {
        if (var2[0].equalsIgnoreCase("VERTICAL")) {
            FarmingPoint.setType(EnumFarmingType.VERTICAL);
        } else if (var2[0].equalsIgnoreCase("PUMPKIN")) {
            FarmingPoint.setType(EnumFarmingType.PUMPKIN);
        } else {
            ChatLib.chat(this.getUsage());
        }

    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public String getUsage() {
        return "&c/farmingtype vertical&b for vertical farm.\n&c/farmingtype pumpkin&b for pumpkin farm.";
    }
}
