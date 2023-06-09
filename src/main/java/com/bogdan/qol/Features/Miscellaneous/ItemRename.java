package com.bogdan.qol.Features.Miscellaneous;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.ControlUtils;
import com.bogdan.qol.utils.NBTUtils;
import joptsimple.internal.Strings;

import java.io.BufferedReader;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ItemRename {

    public static String fileName = "config/bogdanqolItemRename.cfg";

    public static HashMap renameMap = new HashMap();

    public static void process(String[] var0) {
        if (var0.length < 2) {
            ChatLib.chat(getUsage());
        } else {
            switch (var0[1]) {
                case "clearall":
                    renameMap.clear();
                    save();
                    ChatLib.chat("&aSuccessfully removed all custom names!");
                    break;
                case "clear":
                    renameMap.remove(NBTUtils.getUUID(ControlUtils.getHeldItemStack()));
                    save();
                    ChatLib.chat("&aSuccessfully removed custom name of held item!");
                    break;
                case "set":
                    String var4 = Strings.join(Arrays.copyOfRange(var0, 2, var0.length), " ");
                    String var5 = NBTUtils.getUUID(ControlUtils.getHeldItemStack());
                    if (var5.equals("")) {
                        ChatLib.chat("&cNo uuid for this item, can't rename!");
                    } else {
                        renameMap.put(var5, var4);
                        save();
                        ChatLib.chat("&aSuccessfully set custom name for held item!");
                    }
                    break;
                default:
                    ChatLib.chat(getUsage());
            }

        }
    }

    public static void load() {
        try {
            File var0 = new File(fileName);
            if (var0.exists()) {
                BufferedReader var1 = Files.newBufferedReader(Paths.get(fileName));
                Type var2 = (new TypeToken() {
                }).getType();
                HashMap var3 = (new Gson()).fromJson(var1, var2);
                Iterator var4 = var3.entrySet().iterator();

                while (var4.hasNext()) {
                    Map.Entry var5 = (Map.Entry) var4.next();
                    renameMap.put(var5.getKey(), var5.getValue());
                }
            }
        } catch (Exception var6) {
            System.out.println("Error while loading item rename config file");
            var6.printStackTrace();
        }

    }

    public static void save() {
        try {
            String var0 = (new Gson()).toJson(renameMap);
            Files.write(Paths.get(fileName), var0.getBytes(StandardCharsets.UTF_8));
        } catch (Exception var1) {
            System.out.println("Error while saving item rename config file");
            var1.printStackTrace();
        }

    }

    public static String getUsage() {
        return "&c/bn rename clear&b to clear custom name of held item.\n&c/bn rename clearall&b to clear custom names of all items.\n&c/bn rename set name&b to set custom name of held item.\n";
    }
}
