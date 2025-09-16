package dev.drparanoya.taco.modules;

import dev.drparanoya.taco.Taco;
import meteordevelopment.meteorclient.events.entity.player.InteractBlockEvent;
import meteordevelopment.meteorclient.settings.BlockListSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;

public class AntiStrip extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Block[] LOGS = {
        Blocks.ACACIA_LOG,
        Blocks.BIRCH_LOG,
        Blocks.CHERRY_LOG,
        Blocks.CRIMSON_STEM,
        Blocks.DARK_OAK_LOG,
        Blocks.JUNGLE_LOG,
        Blocks.MANGROVE_LOG,
        Blocks.OAK_LOG,
        Blocks.PALE_OAK_LOG,
        Blocks.SPRUCE_LOG,
        Blocks.WARPED_STEM
    };

    private final Setting<List<Block>> logTypes = sgGeneral.add(new BlockListSetting.Builder()
        .name("log-types")
        .description("What log types to protect")
        .defaultValue(LOGS)
        .filter((Block b) -> List.of(LOGS).contains(b))
        .build()
    );

    @EventHandler
    private void onInteractBlock(InteractBlockEvent event) {
        int slot = mc.player.getInventory().getSelectedSlot();
        if (mc.player.getInventory().getStack(slot).isIn(ItemTags.AXES) &&
            logTypes.get().contains(mc.world.getBlockState(event.result.getBlockPos()).getBlock()))
            event.cancel();
    }

    public AntiStrip() {
        super(Taco.TACO, "anti-strip", "Protects you from accidentally stripping logs");
    }
}
