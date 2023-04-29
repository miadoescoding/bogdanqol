package com.bogdan.qol.Features.Miscellaneous;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.bogdan.qol.bogdanqol;
import com.bogdan.qol.utils.ChatLib;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

import java.io.BufferedReader;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommandKeybind {
    public static void loadKeyBinds() {
        try {
            File var0 = new File("config/bogdanqolKeybinds.cfg");
            if (var0.exists()) {
                BufferedReader var1 = Files.newBufferedReader(Paths.get("config/bogdanqolKeybinds.cfg"));
                Type var2 = (new TypeToken() {
                }).getType();
                HashMap var3 = (new Gson()).fromJson(var1, var2);
                Iterator var4 = var3.entrySet().iterator();

                while (var4.hasNext()) {
                    Map.Entry var5 = (Map.Entry) var4.next();
                    bogdanKeyBind.keyBinds.put(var5.getKey(), new bogdanKeyBind((String) var5.getKey(), (Integer) var5.getValue()));
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        KeyBinding var7 = new KeyBinding("Use /bn keybind to add keybinds!", 0, "Addons - bogdanqol KeyBind");
        ClientRegistry.registerKeyBinding(var7);
    }

    public static void saveKeyBinds() {
        try {
            HashMap var0 = new HashMap();
            Iterator var1 = bogdanKeyBind.keyBinds.values().iterator();

            while (var1.hasNext()) {
                bogdanKeyBind var2 = (bogdanKeyBind) var1.next();
                var0.put(var2.getCommand(), var2.getBind().getKeyCode());
            }

            String var4 = (new Gson()).toJson(var0);
            Path var5 = Paths.get("config/bogdanqolKeybinds.cfg");
            Files.write(var5, var4.getBytes(StandardCharsets.UTF_8));
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static void list() {
        if (bogdanKeyBind.keyBinds.size() == 0) {
            ChatLib.chat("You have no keybind.");
        } else {
            Iterator var0 = bogdanKeyBind.keyBinds.values().iterator();

            while (var0.hasNext()) {
                bogdanKeyBind var1 = (bogdanKeyBind) var0.next();
                ChatComponentText var2 = new ChatComponentText(ChatLib.addColor("&9[bogdanaddon] > &e" + var1.getCommand() + " &b(" + Keyboard.getKeyName(var1.getBind().getKeyCode()) + ") &r&c&l[REMOVE]"));
                ChatStyle var3 = new ChatStyle();
                var3.setChatClickEvent(new ClickEvent(Action.RUN_COMMAND, "/bn keybind removeWithKey " + var1.getBind().getKeyCode() + " " + var1.getCommand()));
                var3.setChatHoverEvent(new HoverEvent(net.minecraft.event.HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to remove this keybind")));
                var2.setChatStyle(var3);
                bogdanqol.mc.thePlayer.addChatMessage(var2);
            }

        }
    }

    public static void remove(String var0, String var1) {
        int var2;
        try {
            var2 = Integer.parseInt(var1);
        } catch (Exception var4) {
            ChatLib.chat("Not a number!");
            return;
        }

        bogdanKeyBind var3 = bogdanKeyBind.getKeybind(var0, var2);
        if (var3 == null) {
            ChatLib.chat("No such keybind! Are the cases and keycode matching correctly?");
        } else {
            bogdanqol.mc.gameSettings.keyBindings = (KeyBinding[]) ArrayUtils.removeElement((Object[]) bogdanqol.mc.gameSettings.keyBindings, var3.getBind());
            bogdanKeyBind.keyBinds.remove(var0);
            saveKeyBinds();
            ChatLib.chat("&cRemoved&b keybind \"&e" + var0 + "&b\" (" + Keyboard.getKeyName(var3.getBind().getKeyCode()) + ") !");
        }
    }

    public static void add(String var0) {
        if (bogdanKeyBind.keyBinds.containsKey(var0)) {
            ChatLib.chat("Keybind already exists!");
        } else {
            bogdanKeyBind var1 = new bogdanKeyBind(var0, 0);
            bogdanKeyBind.keyBinds.put(var0, var1);
            saveKeyBinds();
            ChatLib.chat("&aAdded&b keybind \"&e" + var0 + "&b\"!");
        }
    }

    public static void remove(String var0) {
        bogdanKeyBind var1 = bogdanKeyBind.getKeybind(var0, -1);
        if (var1 == null) {
            ChatLib.chat("No such keybind! Are the cases matching correctly?");
        } else {
            bogdanqol.mc.gameSettings.keyBindings = (KeyBinding[]) ArrayUtils.removeElement((Object[]) bogdanqol.mc.gameSettings.keyBindings, var1.getBind());
            bogdanKeyBind.keyBinds.remove(var0);
            saveKeyBinds();
            ChatLib.chat("&cRemoved&b keybind \"&e" + var0 + "&b\"!");
        }
    }

    public static String getUsage() {
        return "&c/bn keybind add &ecommand&b to add keybind.\n&c/bn keybind remove &ecommand&b to remove keybind.\n&c/bn keybinds &bto list all keybinds.\n&eKeybinds are set in Options -> Controls.";
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent var1) {
        if (true) {
            Iterator var2 = bogdanKeyBind.keyBinds.values().iterator();

            while (true) {
                bogdanKeyBind var3;
                do {
                    do {
                        if (!var2.hasNext()) {
                            return;
                        }

                        var3 = (bogdanKeyBind) var2.next();
                    } while (!var3.getBind().isPressed());
                } while (var3.getCommand().startsWith("/") && ClientCommandHandler.instance.executeCommand(bogdanqol.mc.thePlayer, var3.getCommand()) != 0);

                bogdanqol.mc.thePlayer.sendChatMessage(var3.getCommand());
            }
        }
    }
}
