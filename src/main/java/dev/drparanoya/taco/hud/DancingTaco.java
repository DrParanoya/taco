package dev.drparanoya.taco.hud;

import dev.drparanoya.taco.Taco;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.hud.HudElement;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
import meteordevelopment.meteorclient.systems.hud.HudRenderer;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import net.minecraft.util.Identifier;

public class DancingTaco extends HudElement {
    public static final HudElementInfo<DancingTaco> INFO = new HudElementInfo<>(Taco.HUD_GROUP, "taco", ".taco", DancingTaco::new);

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Integer> delay = sgGeneral.add(new IntSetting.Builder()
        .name("Delay")
        .description("Delay in ticks")
        .defaultValue(5)
        .min(0)
        .sliderRange(0, 20)
        .build()
    );

    private final Setting<Double> scale = sgGeneral.add(new DoubleSetting.Builder()
        .name("Scale")
        .description("Scale of the taco")
        .defaultValue(1.0d)
        .min(0.001d)
        .sliderRange(0.0d, 5.0d)
        .build()
    );

    private final Setting<SettingColor> color = sgGeneral.add(new ColorSetting.Builder()
        .name("Color")
        .description("The color of the taco")
        .defaultValue(new SettingColor(255, 255, 255, 255))
        .build()
    );

    public DancingTaco() {
        super(INFO);
    }

    private final Identifier[] TACOS = {
        Identifier.of("taco", "dancingtaco1.png"),
        Identifier.of("taco", "dancingtaco2.png"),
        Identifier.of("taco", "dancingtaco3.png"),
        Identifier.of("taco", "dancingtaco4.png")
    };
    private int ticks = 0;
    private int elem = 0;

    @Override
    public void tick(HudRenderer renderer) {
        double x = this.x, y = this.y;
        if (ticks < delay.get()) ++ticks;
        else {
            elem = elem < 3 ? ++elem : 0;
            ticks = 0;
        }
    }

    @Override
    public void render(HudRenderer renderer) {
		double x = this.x, y = this.y;
		setSize(192 * scale.get(), 133 * scale.get());
        renderer.texture(TACOS[elem], x, y, 192 * scale.get(), 133 * scale.get(), color.get());
    }
}
