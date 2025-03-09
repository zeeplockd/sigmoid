package me.zeeplockd.sigmoid.modules;

import me.zeeplockd.sigmoid.Sigmoid;
import meteordevelopment.meteorclient.events.game.ReceiveMessageEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SkibidiBot extends Module {
    private final SettingGroup sgGeneral = this.settings.getDefaultGroup();

    private final Setting<String> prefix = sgGeneral.add(new StringSetting.Builder()
        .name("prefix")
        .description("Prefix string.")
        .defaultValue(";")
        .build()
    );

    private final Setting<Boolean> fuckOffEnabled = sgGeneral.add(new BoolSetting.Builder()
        .name("fuckoff-enabled")
        .description("Whether the 'fuckoff' command should be active or not.")
        .defaultValue(true)
        .build()
    );

    public SkibidiBot() {
        super(Sigmoid.CATEGORY, "skibidi-bot", "Very funny chatbot.");
    }

    @EventHandler
    private void onReceiveMessage(ReceiveMessageEvent event) {
        String rawMessage = String.valueOf(event.getMessage().getString());  // Converts to plain text

        if (rawMessage.contains(prefix + "help")) {
            ChatUtils.sendPlayerMsg("Hello! This is SkibidiBot, the chatbot built into Sigmoid, a meteor client addon made by zeeplockd. Run 'commands' to get a list of commands.");
        } else if (rawMessage.contains(prefix + "commands")) {
            ChatUtils.sendPlayerMsg("Commands: commands, help, fuckoff, sex");
        } else if (rawMessage.contains(prefix + "fuckoff")) {
            if (fuckOffEnabled.get()) {
                ChatUtils.sendPlayerMsg("If you insist... Jeez.");
                if (mc.player != null) {
                    mc.player.networkHandler.onDisconnect(new DisconnectS2CPacket(Text.literal("%s[%sSkibidiBot%s] %sSomebody told you to fuck off.".formatted(Formatting.GRAY, Formatting.BLUE, Formatting.GRAY, Formatting.WHITE))));
                }
            } else {
                ChatUtils.sendPlayerMsg("Sorry! The fuckoff command is currently disabled. Beg me to enable it.");
            }
        } else if (rawMessage.contains(prefix + "sex")) {
            List<String> strings = Arrays.asList("Nya~~", "Ohh~~", "Harder daddy~~", "I'm go- gonna.. AHH~~", "I love this~~");
            Random random = new Random();
            String randomString = strings.get(random.nextInt(strings.size()));
            ChatUtils.sendPlayerMsg(randomString);
        }
    }
}
