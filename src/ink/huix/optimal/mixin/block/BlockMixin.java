package ink.huix.optimal.mixin.block;

import net.minecraft.Block;
import net.minecraft.StepSound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;

@Mixin(Block.class)
public class BlockMixin {

    @ModifyConstant(method = {
            "<clinit>", "getBlock(Ljava/lang/String;)Lnet/minecraft/Block;",}, constant = @Constant(intValue = 256))
    private static int injected(int value) {
        return 1024;
    }

    public Block setHardnessForMITE(float resistance) {
        return this.setHardness(resistance);
    }

    @Shadow
    protected Block setHardness(float v) {
        return null;
    }

    public Block setResistanceForMITE(float v) {
        return this.setResistance(v);
    }

    @Shadow
    protected Block setResistance(float par1) {
        return null;
    }

    public Block setLightLevelForMITE(float v) {
        return this.setLightValue(v);
    }

    @Shadow
    protected Block setLightValue(float exp) {
        return null;
    }

    public Block setTextureNameForMITE(String location) {
        return this.setTextureName(location);
    }
    @Shadow
    protected Block setTextureName(String par1Str) {
        return null;
    }

    @Shadow
    protected Block setStepSound(StepSound par1StepSound) {
        return null;
    }

    public Block setStepSoundForMITE(StepSound stepSound) {
        return this.setStepSound(stepSound);
    }

}
