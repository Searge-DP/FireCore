package com.fireball1725.firecore.event;

import com.fireball1725.firecore.helpers.OpenGLHelper;
import com.fireball1725.firecore.reference.ModInfo;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class EventRenderPlayerEvent {
    @SubscribeEvent
    public void renderSword(RenderPlayerEvent.Specials.Post event) {
        ResourceLocation textureFireBallSword = new ResourceLocation(ModInfo.MOD_ID, "textures/Benihime.png");
        IModelCustom modelFireBallSword = AdvancedModelLoader.loadModel(new ResourceLocation(ModInfo.MOD_ID, "models/Benihime.obj"));

        event.renderer.modelBipedMain.bipedBody.postRender(0.0625F);
        Minecraft.getMinecraft().renderEngine.bindTexture(textureFireBallSword);

        int[][] savedGLState = OpenGLHelper.saveGLState(new int[]{GL11.GL_ALPHA_TEST, GL11.GL_LIGHTING});
        GL11.glPushMatrix();

        OpenGLHelper.rotateXYZ(90, -40, 0);
        GL11.glTranslatef(-0.2F, 0.17F, 0.0F);
        modelFireBallSword.renderOnly("Benihime_Handle", "Benihime_Guard", "Benihime_Bow", "Benihime_Blade");

        OpenGLHelper.rotateXYZ(0, -50, 0);
        GL11.glTranslatef(0.23F, 0, -0.05F);
        modelFireBallSword.renderOnly("Benihime_Tassle");

        GL11.glPopMatrix();
        OpenGLHelper.restoreGLState(savedGLState);
    }
}
