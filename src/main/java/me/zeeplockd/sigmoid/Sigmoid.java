package me.zeeplockd.sigmoid;

import me.zeeplockd.sigmoid.modules.SkibidiBot;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Sigmoid extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Sigmoid");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Sigmoid (zeeplockd was here :D)");

        // Modules
        Modules.get().add(new SkibidiBot());
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
