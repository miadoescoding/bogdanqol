package com.bogdan.qol.Features.QOL;

import com.bogdan.qol.utils.CommandsUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class BatchCommands {

    private static final String fileName = "config/bogdanqolCommands.cfg";

    public static void execute() {
        ArrayList var0 = new ArrayList();

        try {
            File var1 = new File("config/bogdanqolCommands.cfg");
            BufferedReader var2 = null;
            if (var1.exists()) {
                var2 = new BufferedReader(new FileReader("config/bogdanqolCommands.cfg"));

                String var3;
                while ((var3 = var2.readLine()) != null) {
                    if (var3.length() != 0 && var3.charAt(0) == '/') {
                        var0.add(var3);
                    }
                }

                var2.close();
            }

            Iterator var6 = var0.iterator();

            while (var6.hasNext()) {
                String var4 = (String) var6.next();
                CommandsUtils.addCommand(var4);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }
}
