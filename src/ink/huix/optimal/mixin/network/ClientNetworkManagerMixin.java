package ink.huix.optimal.mixin.network;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

@Mixin(NetClientHandler.class)
public class ClientNetworkManagerMixin {

    @Overwrite
    public void handleMultiBlockChange(Packet52MultiBlockChange par1Packet52MultiBlockChange) {
        int var2 = par1Packet52MultiBlockChange.xPosition * 16;
        int var3 = par1Packet52MultiBlockChange.zPosition * 16;
        if (par1Packet52MultiBlockChange.metadataArray != null) {
            DataInputStream var4 = new DataInputStream(new ByteArrayInputStream(par1Packet52MultiBlockChange.metadataArray));

            try {
                long before = System.nanoTime();

                int delay;
                for(delay = 0; delay < par1Packet52MultiBlockChange.size; ++delay) {
                    short var8 = var4.readShort();
                    short var9 = var4.readShort();
                    int var10 = var9 >> 8;
                    int var11 = var9 & 15;
                    int var12 = var8 >> 12 & 15;
                    int var13 = var8 >> 8 & 15;
                    int var14 = var8 & 255;
                    this.i.g(var12 + var2, var14, var13 + var3, var10, var11);
                }

                delay = (int)(System.nanoTime() - before) / 10000000;
                if (delay > 0) {
                    Minecraft.MITE_log.logInfo("Long time processing handleMultiBlockChange (delay=" + delay + ") #Blocks=" + par1Packet52MultiBlockChange.size);
                }
            } catch (IOException var15) {
                System.out.println("Exception occured, packet52");
            }
        }

    }

    @Overwrite
    public void handleMultiBlockChange(Packet97MultiBlockChange packet) {
        byte[] bytes = packet.getBytes();
        int base_x = packet.chunk_x * 16;
        int base_z = packet.chunk_z * 16;
        long before = System.nanoTime();

        int delay;
        for(delay = 0; delay < packet.num_blocks; ++delay) {
            int offset = delay * 6;
            int x = base_x + bytes[offset];
            int y = bytes[offset + 1] & 255;
            int z = base_z + bytes[offset + 2];
            int block_id = bytes[offset + 3];
            int id_extra = bytes[offset + 4];
            if(id_extra < 0) {
                id_extra = 256 + id_extra;
            }
            byte metadata = bytes[offset + 5];
            this.i.g(x, y, z, block_id * 256 + id_extra, metadata);
            if (this.i.hasSkylight()) {
                this.i.getChunkFromBlockCoords(x, z).addPendingSkylightUpdate(x, y, z);
            }
        }

        delay = (int)(System.nanoTime() - before) / 10000000;
        if (delay > 0) {
            Minecraft.MITE_log.logInfo("Long time processing handleMultiBlockChange97 (delay=" + delay + ") #Blocks=" + packet.num_blocks);
        }
    }

    @Shadow
    private bdd i;
}
