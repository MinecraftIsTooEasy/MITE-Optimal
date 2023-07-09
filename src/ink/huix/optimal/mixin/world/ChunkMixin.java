package ink.huix.optimal.mixin.world;


import net.minecraft.Chunk;
import net.minecraft.ChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Chunk.class)
public class ChunkMixin {
    @Shadow
    private final boolean is_empty;
    @Shadow
    public ChunkSection[] storageArrays;

    public ChunkMixin(boolean isEmpty) {
        is_empty = isEmpty;
    }

    @Overwrite
    public final int getBlockID(int par1, int par2, int par3) {
        if (this.is_empty) {
            return 0;
        } else {
            int par2_shifted = par2 >> 4;
            if (par2_shifted < this.storageArrays.length) {
                ChunkSection extended_block_storage = this.storageArrays[par2_shifted];
                if (extended_block_storage != null) {
                    int par2_and_15 = par2 & 15;
                    int var7 = extended_block_storage.getExtBlockID(par1 & 15, par2_and_15, par3 & 15);
                    return var7;
                }
            }
            return 0;
        }
    }

}
