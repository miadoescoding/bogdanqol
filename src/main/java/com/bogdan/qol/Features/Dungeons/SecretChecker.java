package com.bogdan.qol.Features.Dungeons;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Features.Dungeons.Map.Dungeon;
import com.bogdan.qol.Features.Remote.API.ApiException;
import com.bogdan.qol.Features.Remote.API.HypixelPlayerData;
import com.bogdan.qol.Features.Remote.API.PhraseSecrets;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.CommandsUtils;
import com.bogdan.qol.utils.MinecraftUtils;
import com.bogdan.qol.utils.TabUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecretChecker {

    private static final String leadPattern = "^Party Leader: (?:\\[VIP\\+?] |\\[MVP\\+*] |\\[YOUTUBE] |\\[GM] |\\[ADMIN] |)(?<name>[a-zA-Z0-9_]+) ●";
    private static final String rankNamePattern = "^(?:\\[VIP\\+?] |\\[MVP\\+*] |\\[YOUTUBE] |\\[GM] |\\[ADMIN] |)(?<name>[a-zA-Z0-9_]+)";
    private static final String namePattern = "[a-zA-Z0-9_]+";
    private static final String infoPattern = "^Party Members \\((?<name>[0-9]+)\\)";
    private static final String rankPattern = "(?:\\[VIP\\+?] |\\[MVP\\+*] |\\[YOUTUBE] |\\[GM] |\\[ADMIN] |)";
    boolean end = false;
    ArrayList secrets;
    boolean start = false;
    int playerNum;
    ArrayList players;

    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent var1) {
        if (true) {
            if (Configs.SecretChecker) {
                if (var1.type == 0) {
                    String var2 = ChatLib.removeFormatting(var1.message.getUnformattedText());
                    if (var2 != null) {
                        if (var2.equals("Dungeon starts in 4 seconds.")) {
                            this.start = true;
                            this.playerNum = -1;
                            this.players = new ArrayList();
                            this.secrets = new ArrayList();
                            CommandsUtils.addCommand("/pl");
                        } else if (this.start && this.players != null && this.players.size() == this.playerNum) {
                            this.start = false;
                            this.fetch();
                        } else if (this.start && var2.startsWith("Woah slow down, you're doing that too fast!")) {
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException var11) {
                            }

                            CommandsUtils.addCommand("/pl");
                        } else if (this.start && var2.startsWith("You are not currently in a party.")) {
                            this.start = false;
                            ChatLib.chat("Join a party to use Secret Checker!");
                        } else {
                            Pattern var12;
                            Matcher var14;
                            if (this.start && var2.startsWith("Party Members ")) {
                                var12 = Pattern.compile("^Party Members \\((?<name>[0-9]+)\\)");
                                var14 = var12.matcher(var2);
                                if (var14.find()) {
                                    this.playerNum = Integer.parseInt(var14.group("name"));
                                }
                            } else if (this.start && var2.startsWith("Party Leader:")) {
                                var12 = Pattern.compile("^Party Leader: (?:\\[VIP\\+?] |\\[MVP\\+*] |\\[YOUTUBE] |\\[GM] |\\[ADMIN] |)(?<name>[a-zA-Z0-9_]+) ●");
                                var14 = var12.matcher(var2);
                                if (var14.find()) {
                                    this.players.add(var14.group("name"));
                                }
                            } else {
                                String var3;
                                String[] var4;
                                String[] var5;
                                int var6;
                                int var7;
                                String var8;
                                Pattern var9;
                                Matcher var10;
                                if (this.start && var2.startsWith("Party Moderators:")) {
                                    var3 = (var2 + " ").replace("Party Moderators: ", "");
                                    var4 = var3.split(" ● ");
                                    var5 = var4;
                                    var6 = var4.length;

                                    for (var7 = 0; var7 < var6; ++var7) {
                                        var8 = var5[var7];
                                        var9 = Pattern.compile("^(?:\\[VIP\\+?] |\\[MVP\\+*] |\\[YOUTUBE] |\\[GM] |\\[ADMIN] |)(?<name>[a-zA-Z0-9_]+)");
                                        var10 = var9.matcher(var8);
                                        if (var10.find()) {
                                            this.players.add(var10.group("name"));
                                        }
                                    }
                                } else if (this.start && var2.startsWith("Party Members:")) {
                                    var3 = (var2 + " ").replace("Party Members: ", "");
                                    var4 = var3.split(" ● ");
                                    var5 = var4;
                                    var6 = var4.length;

                                    for (var7 = 0; var7 < var6; ++var7) {
                                        var8 = var5[var7];
                                        var9 = Pattern.compile("^(?:\\[VIP\\+?] |\\[MVP\\+*] |\\[YOUTUBE] |\\[GM] |\\[ADMIN] |)(?<name>[a-zA-Z0-9_]+)");
                                        var10 = var9.matcher(var8);
                                        if (var10.find()) {
                                            this.players.add(var10.group("name"));
                                        }
                                    }
                                }
                            }
                        }

                        if (var2.equals("                             > EXTRA STATS <")) {
                            if (this.players == null) {
                                return;
                            }

                            if (this.secrets == null) {
                                return;
                            }

                            this.fetch();
                        }

                        if (this.end) {
                            this.end = false;
                            Matcher var13 = Pattern.compile("Secrets Found: (\\d+)").matcher((CharSequence) TabUtils.getNames().get(31));
                            if (var13.find()) {
                                if (Configs.MapEnabled) {
                                    ChatLib.chat("&fSecrets Found: (&b" + var13.group(1) + "&f/&b" + Dungeon.totalSecrets + "&f)");
                                } else {
                                    ChatLib.chat("&fSecrets Found: &b" + var13.group(1));
                                }
                            } else {
                                ChatLib.chat("&fSecrets Found:");
                            }

                            for (int var15 = 0; var15 < this.playerNum; ++var15) {
                                ChatLib.chat("  &f" + this.players.get(var15) + ": &b" + ((Integer) this.secrets.get(var15 + this.playerNum) - (Integer) this.secrets.get(var15)));
                            }
                        }

                    }
                }
            }
        }
    }

    public void fetch() {
        (new Thread(() -> {
            Iterator var1 = this.players.iterator();

            while (var1.hasNext()) {
                String var2 = (String) var1.next();

                try {
                    String var3 = MinecraftUtils.getUUIDFromName(var2);
                    HypixelPlayerData var4 = new HypixelPlayerData(var3, Configs.APIKey);
                    PhraseSecrets var5 = new PhraseSecrets(var4.getPlayerData());
                    this.secrets.add(var5.getSecrets());
                } catch (ApiException var6) {
                    ChatLib.chat(var6.getMessage());
                }
            }

            if (this.secrets.size() == this.playerNum * 2) {
                this.end = true;
            }

        })).start();
    }
}
