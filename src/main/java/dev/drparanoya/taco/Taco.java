package dev.drparanoya.taco;

import com.mojang.logging.LogUtils;
import dev.drparanoya.taco.hud.*;
import dev.drparanoya.taco.modules.*;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.minecraft.item.Items;
import org.slf4j.Logger;

public class Taco extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final HudGroup HUD_GROUP = new HudGroup("Taco");
    public static final Category TACO = new Category("Taco", Items.COOKIE.getDefaultStack());


    @Override
    public void onInitialize() {
        LOG.info(".taco");

        Modules.get().add(new AntiStrip());

        Hud.get().register(DancingTaco.INFO);
        Hud.get().register(SubwaySurfers.INFO);
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(TACO);
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
