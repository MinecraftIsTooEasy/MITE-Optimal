package ink.huix.optimal.mixin.item;


import net.minecraft.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Item.class)
public class ItemMixin {

    @ModifyConstant(method = "<init>(ILjava/lang/String;I)V", constant = @Constant(intValue = 256))
    private static int injected(int value) {
        return 1024;
    }
}
