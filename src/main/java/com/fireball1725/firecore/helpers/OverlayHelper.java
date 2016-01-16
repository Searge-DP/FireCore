package com.fireball1725.firecore.helpers;

import com.fireball1725.firecore.util.ColorString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.ArrayList;

public class OverlayHelper extends Gui {
    protected Minecraft mc = Minecraft.getMinecraft();
    protected TextureManager textureManager = mc.renderEngine;
    protected FontRenderer fontRenderer = mc.fontRenderer;

    public OverlayHelper() {
    }

    public void drawWindow(int x, int y, int w, int h, int bgColor) {
        drawRect(x - 3, y - 4, x + w + 3, y - 3, bgColor);
        drawRect(x - 3, y + h + 3, x + w + 3, y + h + 4, bgColor);
        drawRect(x - 3, y - 3, x + w + 3, y + h + 3, bgColor);
        drawRect(x - 4, y - 3, x - 3, y + h + 3, bgColor);
        drawRect(x + w + 3, y - 3, x + w + 4, y + h + 3, bgColor);
    }

    public void drawWindowWithBorder(int x, int y, int w, int h, int bgColor, int frameColor) {
        drawWindow(x, y, w, h, bgColor);
        int frameFade;
        frameFade = (frameColor & 0xFEFEFE) >> 1 | frameColor & 0xFF000000;

        drawGradientRect(x - 3, y - 3 + 1, x - 3 + 1, y + h + 3 - 1, frameColor, frameFade);
        drawGradientRect(x + w + 2, y - 3 + 1, x + w + 3, y + h + 3 - 1, frameColor, frameFade);
        drawGradientRect(x - 3, y - 3, x + w + 3, y - 3 + 1, frameColor, frameFade);
        drawGradientRect(x - 3, y + h + 2, x + w + 3, y + h + 3, frameColor, frameFade);
    }

    public void drawVertProgressBar(int x, int y, int w, int h, int p, int bgColor, int frameColor, int progressColor) {
        drawProgressBar(x, y, w, h, p, bgColor, frameColor, progressColor, 1);
    }

    public void drawHorzProgressBar(int x, int y, int w, int h, int p, int bgColor, int frameColor, int progressColor) {
        drawProgressBar(x, y, w, h, p, bgColor, frameColor, progressColor, 0);
    }

    protected void drawProgressBar(int x, int y, int w, int h, int p, int bgColor, int frameColor, int progressColor, int hv) {
        drawWindowWithBorder(x, y, w, h, bgColor, frameColor);

        if (hv == 0) {
            float pWf = ((float) w / 100) * p;
            int pW = Math.round(pWf);
            drawWindow(x + 2, y + 2, pW - 4, h - 4, progressColor);
        } else {
            float pHf = ((float) h / 100) * p;
            int pH = Math.round(pHf);
            drawWindow(x + 2, y + h - pH + 2, w - 4, pH - 4, progressColor);
        }

    }

    public void drawItemStack(ItemStack itemStack, int x, int y) {
        int[][] savedGLState = OpenGLHelper.saveGLState(new int[]{GL11.GL_ALPHA_TEST, GL11.GL_LIGHTING});
        RenderItem render = new RenderItem();

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        render.renderItemIntoGUI(this.fontRenderer, this.textureManager, itemStack, x, y);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();

        OpenGLHelper.restoreGLState(savedGLState);
    }

    public void drawCenteredStrings(int x, int y, int w, int offset, ArrayList<ColorString> messages) {
        for (int i = 0; i < messages.size(); i++) {
            ColorString message = messages.get(i);
            int messageWidth = fontRenderer.getStringWidth(message.getMessage());
            int messageX = x + ((w >> 1) - (messageWidth >> 1));
            int messageY = y + (1 + offset * i);

            fontRenderer.drawStringWithShadow(message.getMessage(), messageX, messageY, message.getColor());
        }
    }

    public void drawCenteredString(int x, int y, int w, String message, int color) {
        int messageWidth = fontRenderer.getStringWidth(message);
        int messageX = x + ((w >> 1) - (messageWidth >> 1));

        fontRenderer.drawStringWithShadow(message, messageX, y, color);
    }

    public void drawPlayerHead(int x, int y) {
        ResourceLocation playerSkin = ((AbstractClientPlayer) mc.getMinecraft().thePlayer).getLocationSkin();
        mc.getTextureManager().bindTexture(playerSkin);

        int[][] savedGLState = OpenGLHelper.saveGLState(new int[]{GL11.GL_ALPHA_TEST, GL11.GL_LIGHTING});
        GL11.glPushMatrix();
        GL11.glScalef(1.0F, 0.5F, 1.0F);

        this.drawTexturedModalRect(x, y, 32, 64, 32, 64);
        this.drawTexturedModalRect(x, y, 160, 64, 32, 64);

        GL11.glPopMatrix();
        OpenGLHelper.restoreGLState(savedGLState);
    }

    private void drawPixels(int x, int y, int u, int v, int width, int height, int times) {
        for (int cu = u; cu < u + width; cu++) {
            for (int cv = v; cv < v + height; cv++) {
                for (int factor = 0; factor < times; factor++) {
                    drawTexturedModalRect(x + (cu * 3) + factor, y + (cv * 3) + factor, cu + u, cv + v, 1, 1);
                }
            }
        }
    }

    public void drawResource(ResourceLocation resource, int x, int y, int x1, int y1, int w, int h) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
        drawTexturedModalRect(0, 0, 0, 0, 128, 128);
    }
}
