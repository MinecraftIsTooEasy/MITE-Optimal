package ink.huix.optimal.mixin.block;

import net.minecraft.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Block.class)
public class BlockMixin {

    private static final boolean[] is_normal_cube_lookup = new boolean[1024];

    @ModifyConstant(method = {
            "<clinit>", "getBlock(Ljava/lang/String;)Lnet/minecraft/Block;",}, constant = @Constant(intValue = 256))
    private static int injected(int value) {
        return 1024;
    }
}
