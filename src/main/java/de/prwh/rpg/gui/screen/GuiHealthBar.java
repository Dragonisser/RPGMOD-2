package de.prwh.rpg.gui.screen;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.health.HealthProvider;
import de.prwh.rpg.capabilities.health.IHealth;
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
public class GuiHealthBar extends Gui {

	private Minecraft mc;

	private static final ResourceLocation texture = new ResourceLocation(RPGMain.MODID, "textures/gui/health_bar.png");

	public GuiHealthBar(Minecraft mc) {
		super();
		this.mc = mc;
	}

	@SubscribeEvent
	public void render(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == ElementType.HEALTH) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {
		if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {
			return;
		}

		IHealth info = mc.player.getCapability(HealthProvider.HEALTH_CAP, null);
		if (info == null) {
			return;
		}
		
		if (this.mc.player.capabilities.isCreativeMode) {
			return;
		}

		int xPos = event.getResolution().getScaledWidth() / 2 - 91;
		int yPos = 0;
		if(mc.player.getTotalArmorValue()> 0) {
			yPos = event.getResolution().getScaledHeight() - 51;
		} else {
			yPos = event.getResolution().getScaledHeight() - 41;
		}
		

		GlStateManager.pushAttrib();
		GlStateManager.disableLighting();
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		mc.getTextureManager().bindTexture(texture);

		/*
		 * Whole texture width = 90 Whole texture high = 11
		 *
		 * manabarwidth the * number is the length of the inner part
		 *
		 * drawTexturedModalRect + 3, + 3 are where the inner part should start
		 */

		int textureFrameWidth = 81;
		int textureFrameHeight = 11;
		int textureFrameInner = 75;

		drawTexturedModalRect(xPos, yPos, 0, 0, textureFrameWidth, textureFrameHeight);
		int manabarwidth = (int) ((info.getHealth() / info.getMaxHealth()) * textureFrameInner);
		drawTexturedModalRect(xPos + 3, yPos + 3, 0, 11, manabarwidth, 5);
		
		DecimalFormat df = new DecimalFormat("#");
		df.setRoundingMode(RoundingMode.DOWN);
		
		
		String s = df.format(info.getHealth()) + " / " + df.format(info.getMaxHealth());
		int length = mc.fontRenderer.getStringWidth(s) / 2;

		mc.fontRenderer.drawString(s, xPos + textureFrameWidth / 2 - length, yPos - 10, -1);

		GlStateManager.popAttrib();
	}
}
