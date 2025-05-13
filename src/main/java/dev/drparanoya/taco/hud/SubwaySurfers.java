package dev.drparanoya.taco.hud;

import dev.drparanoya.taco.Taco;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.hud.HudElement;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
import meteordevelopment.meteorclient.systems.hud.HudRenderer;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import net.minecraft.util.Identifier;

import java.lang.Math;

public class SubwaySurfers extends HudElement {
    public static final HudElementInfo<SubwaySurfers> INFO = new HudElementInfo<>(Taco.HUD_GROUP, "subway-surfers", "Helps you focus", SubwaySurfers::new);

    private final SettingGroup sgGeneral = settings.getDefaultGroup();
	
	private final Identifier[] FRAMES = new Identifier[50];

    private final Setting<Double> scale = sgGeneral.add(new DoubleSetting.Builder()
        .name("Scale")
        .description("Scale of the video")
        .defaultValue(1.0d)
        .min(0.001d)
        .sliderRange(0.0d, 5.0d)
        .build()
    );

    private final Setting<SettingColor> color = sgGeneral.add(new ColorSetting.Builder()
        .name("Color")
        .description("Color of the video")
        .defaultValue(new SettingColor(255, 255, 255, 255))
        .build()
    );

    public SubwaySurfers() {
        super(INFO);
		
		for (int i = 0; i < 50; ++i)
			FRAMES[i] = Identifier.of("taco", Integer.toString(i + 1) + ".png");
    }
	
    private int frame = 0;
	private int tick = 0;
	
	private double w = 220;
	private double h = 391;
	

    @Override
    public void tick(HudRenderer renderer) {
        if (frame < 99)
			++frame;
        else
			frame = 0;
    }

    @Override
    public void render(HudRenderer renderer) {
		double x = this.x, y = this.y;
		setSize(w * scale.get(), h * scale.get());
		tick = frame % 2 == 0 ? frame / 2 : (frame - 1) / 2;
        renderer.texture(FRAMES[tick], x, y, w * scale.get(), h * scale.get(), color.get());
    }
}
