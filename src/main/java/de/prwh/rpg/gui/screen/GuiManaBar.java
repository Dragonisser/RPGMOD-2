package de.prwh.rpg.gui.screen;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.mana.IMana;
import de.prwh.rpg.capabilities.mana.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiManaBar extends Gui {

	private Minecraft mc;

	private static final ResourceLocation texture = new ResourceLocation(RPGMain.MODID, "textures/gui/mana_bar.png");

	public GuiManaBar(Minecraft mc) {
		super();
		this.mc = mc;
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {
		if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {
			return;
		}

		IMana info = mc.player.getCapability(ManaProvider.MANA_CAP, null);
		if (info == null)
			return;

		if (this.mc.player.capabilities.isCreativeMode) {
			return;
		}

		int xPos = event.getResolution().getScaledWidth() / 2 - 161;
		int yPos = event.getResolution().getScaledHeight() - 20;

		GlStateManager.pushAttrib();
		GlStateManager.disableLighting();
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.getTextureManager().bindTexture(texture);

		int textureFrameWidth = 66;
		int textureFrameHeight = 11;
		int textureFrameInner = 60;

		float mana = (float)(int)info.getMana();
		float max_mana = (float)(int)info.getMaxMana();
		
		drawTexturedModalRect(xPos, yPos, 0, 0, textureFrameWidth, textureFrameHeight);
		int manabarwidth = (int) ((mana / max_mana) * textureFrameInner);
		drawTexturedModalRect(xPos + 3, yPos + 3, 0, 11, manabarwidth, 5);

		DecimalFormat df = new DecimalFormat("#");
		df.setRoundingMode(RoundingMode.DOWN);
		
		String s = df.format(mana) + " / " + df.format(max_mana);
		int length = mc.fontRenderer.getStringWidth(s) / 2;

		this.mc.fontRenderer.drawString(s, xPos + textureFrameWidth / 2 - length, yPos - 10, -1);

		GlStateManager.popAttrib();
	}
}
