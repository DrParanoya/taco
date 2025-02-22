package dev.drparanoya.taco;

import com.mojang.logging.LogUtils;
import dev.drparanoya.taco.hud.DancingTaco;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import org.slf4j.Logger;

public class Taco extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final HudGroup HUD_GROUP = new HudGroup("Taco");


    @Override
    public void onInitialize() {
        LOG.info(".taco");
        Hud.get().register(DancingTaco.INFO);
    }

    @Override
    public String getPackage() {
        return "dev.drparanoya.taco";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("DrParanoya", "DrParanoya");
    }
}
