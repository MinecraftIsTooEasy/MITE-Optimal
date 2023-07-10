package ink.huix.optimal;

import ink.huix.optimal.events.OptimalEvents;
import ink.huix.optimal.mixin.MinecraftMixin;
import net.xiaoyu233.fml.AbstractMod;
import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import net.xiaoyu233.fml.reload.event.MITEEvents;
import org.spongepowered.asm.mixin.MixinEnvironment;

import javax.annotation.Nonnull;

@Mod
public class Optimal extends AbstractMod {
    public static final String MITE_ITE_VERSION = "V2";
    @Nonnull
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("MITE-Optimal", MinecraftMixin.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }
    @Override
    public void postInit() {
        MITEEvents.MITE_EVENT_BUS.register(new OptimalEvents());
    }

    @Override
    public void preInit() {
    }

    @Override
    public String modId() {
        return "MITE-Optimal";
    }
    @Override
    public int modVerNum() {
        return 74;
    }
    @Override
    public String modVerStr() {
        return MITE_ITE_VERSION;
    }

}
