package quickcarpet.mixin;

import net.minecraft.world.gen.PillagerSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import quickcarpet.settings.Settings;

@Mixin(PillagerSpawner.class)
public class PillagerSpawnerMixin {
    @ModifyConstant(method = "spawn", constant = @Constant(intValue = 12000))
    private int minDelay(int minDelay) {
        return (int)(minDelay / Settings.patrolMultiplier);
    }
    @ModifyConstant(method = "spawn", constant = @Constant(intValue = 1200))
    private int range(int range) {
        return (int)(range / Settings.patrolMultiplier);
    }
}
