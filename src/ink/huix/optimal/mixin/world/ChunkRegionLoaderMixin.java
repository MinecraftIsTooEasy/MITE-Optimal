package ink.huix.optimal.mixin.world;

import net.minecraft.ChunkRegionLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ChunkRegionLoader.class)
public class ChunkRegionLoaderMixin {


    @ModifyConstant(method = "handleSectionChecksumFailure", constant = @Constant(intValue = 256))
    private static int injected(int value) {
        return 1024;
    }

    @ModifyVariable(method = "getInvalidSectionBlockConversionIdsOrMetadata", at = @At("STORE"), ordinal = 0)
    private static int[][] injected(int[][] source) {
        return new int[1024][];
    }
}
