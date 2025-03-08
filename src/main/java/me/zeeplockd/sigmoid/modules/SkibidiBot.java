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

public class SkibidiBot extends Module {
    private final SettingGroup sgGeneral = this.settings.getDefaultGroup();

    private final Setting<String> prefix = sgGeneral.add(new StringSetting.Builder()
        .name("prefix")
        .description("Prefix string.")
        .defaultValue(";")
        .build()
    );

    public SkibidiBot() {
        super(Sigmoid.CATEGORY, "skibidi-bot", "Very funny chatbot.");
    }

    @EventHandler
    private void onReceiveMessage(ReceiveMessageEvent event) {
        // Strip formatting from the incoming message
        String rawMessage = String.valueOf(event.getMessage().getString());  // Converts to plain text

        // Now process the message without formatting
        if (rawMessage.contains(prefix + "help")) {
            ChatUtils.sendPlayerMsg("Hello! This is SkibidiBot, the chatbot built into Sigmoid, a meteor client addon made by zeeplockd. Run 'commands' to get a list of commands.");
        } else if (rawMessage.contains(prefix + "commands")) {
            ChatUtils.sendPlayerMsg("Commands: commands, help, fuckoff");
        } else if (rawMessage.contains(prefix + "fuckoff")) {
            ChatUtils.sendPlayerMsg("If you insist... Jeez.");
            mc.player.networkHandler.onDisconnect(new DisconnectS2CPacket(Text.literal("%s[%sSkibidiBot%s] %sSomebody told you to fuck off.".formatted(Formatting.GRAY, Formatting.BLUE, Formatting.GRAY, Formatting.WHITE))));
        }
    }
}
