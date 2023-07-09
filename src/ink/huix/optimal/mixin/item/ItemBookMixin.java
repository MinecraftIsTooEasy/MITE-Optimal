package ink.huix.optimal.mixin.item;

import net.minecraft.ItemBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemBlock.class)
public class ItemBookMixin {

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 256))
    private static int injected(int value) {
        return 1024;
    }
}
