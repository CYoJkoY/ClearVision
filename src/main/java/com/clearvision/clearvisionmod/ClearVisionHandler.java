// ClearVisionHandler.java

package com.clearvision.clearvisionmod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;

public class ClearVisionHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onFogDensity(EntityViewRenderEvent.FogDensity event) {
        // 取消水下和岩浆下的雾
        if (event.getState().getMaterial().isLiquid()) {
            event.setDensity(0.0F);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onFogColors(EntityViewRenderEvent.FogColors event) {
        // 设置雾颜色为正常视野
        if (event.getState().getMaterial().isLiquid()) {
            event.setRed(1.0F);
            event.setGreen(1.0F);
            event.setBlue(1.0F);
        }
    }

    @SubscribeEvent
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
        // 移除水下、岩浆和火焰的屏幕遮罩
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && mc.player != null) {
            mc.player.hurtTime = 0; // 受伤视角基础防护
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (mc.player != null) {
            mc.player.hurtTime = 0; // 受伤视角渲染前二次防护
        }
    }
}