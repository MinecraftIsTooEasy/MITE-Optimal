package ink.huix.optimal.mixin.world;

import net.minecraft.Chunk;
import net.minecraft.ChunkSection;
import net.minecraft.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(World.class)
public class WorldMixin {

    @Inject(locals = LocalCapture.CAPTURE_FAILHARD, method = "getBlockId", at = @At(value = "FIELD", target = "Lnet/minecraft/Chunk;storageArrays:[Lnet/minecraft/ChunkSection;", shift = At.Shift.AFTER), cancellable = true)
    private void injectGetBlockId(int par1, int par2, int par3, CallbackInfoReturnable<Integer> cir, Chunk var4) {
        ChunkSection extended_block_storage = var4.storageArrays[par2 >> 4];
        if (extended_block_storage == null) {
            cir.setReturnValue(0);
            cir.cancel();
        } else {
            int par1_and_15 = par1 & 15;
            int par2_and_15 = par2 & 15;
            int par3_and_15 = par3 & 15;
            cir.setReturnValue(extended_block_storage.getExtBlockID(par1_and_15, par2_and_15, par3_and_15));
            cir.cancel();
        }
    }
}
