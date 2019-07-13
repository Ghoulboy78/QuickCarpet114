package quickcarpet.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quickcarpet.annotation.Feature;
import quickcarpet.helper.EntityPlayerActionPack;
import quickcarpet.utils.IServerPlayerEntity;

@Feature("actionPack")
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements IServerPlayerEntity {
    private EntityPlayerActionPack actionPack;

    public EntityPlayerActionPack getActionPack() {
        return actionPack;
    }

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void init(MinecraftServer minecraftServer_1, ServerWorld serverWorld_1, GameProfile gameProfile_1, ServerPlayerInteractionManager serverPlayerInteractionManager_1, CallbackInfo ci) {
        this.actionPack = new EntityPlayerActionPack((ServerPlayerEntity) (Object) this);
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void onTick(CallbackInfo ci) {
        actionPack.onUpdate();
    }
}
