package ink.huix.optimal.events;

import com.google.common.eventbus.Subscribe;
import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.PlayerLoggedInEvent;

public class OptimalEvents {

    @Subscribe
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        EntityPlayer par1EntityPlayerMP = event.getPlayer();
        par1EntityPlayerMP.sendChatToPlayer(ChatMessage.createFromText("[MITE-Optimal]: ")
                .addText("MITE-Optimal").addText("Wrtie by Wensc; Build by Huix").setColor(EnumChatFormat.WHITE));
        }
}
