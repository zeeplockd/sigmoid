package me.zeeplockd.sigmoid;

import me.zeeplockd.sigmoid.commands.CommandExample;
import me.zeeplockd.sigmoid.hud.HudExample;
import me.zeeplockd.sigmoid.modules.ModuleExample;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Sigmoid extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Sigmoid");
    public static final HudGroup HUD_GROUP = new HudGroup("Sigmoid");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Sigmoid (zeeplockd was here :D)");

        // Modules
        Modules.get().add(new ModuleExample());

        // Commands
        Commands.add(new CommandExample());

        // HUD
        Hud.get().register(HudExample.INFO);
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "me.zeeplockd.sigmoid";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("fenziie", "sigmoid");
    }
}
