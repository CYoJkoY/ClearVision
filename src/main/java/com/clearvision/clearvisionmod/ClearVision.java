// ClearVision.java

package com.clearvision.clearvisionmod;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.potion.Potion;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

@Mod(modid = ClearVision.MODID, name = "ClearVision", version = "1.0")
public class ClearVision {
    public static final String MODID = "clearvision";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ClearVisionHandler()); // 注册事件处理器
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Pre event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player != null) {
            // 移除致盲和反胃效果的屏幕特效
            if (player.isPotionActive(Potion.getPotionFromResourceLocation("blindness"))) {
                player.removePotionEffect(Potion.getPotionFromResourceLocation("blindness"));
            }
            if (player.isPotionActive(Potion.getPotionFromResourceLocation("nausea"))) {
                player.removePotionEffect(Potion.getPotionFromResourceLocation("nausea"));
            }
        }
    }
}