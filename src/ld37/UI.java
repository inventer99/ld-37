package ld37;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI
{
	@SuppressWarnings("unused")
	private int width;
	private int height;
	
	private Font font36;
	
	private Color textColor = Color.WHITE;
	
	private String formattedTime()
	{
		long time = (long) Main.game.timeSurvived;
		
		long day = (time / (24 * 60));
		long hour = (time / 60) % 12 + 1;
		long minute = time % 60;
		long apm = ((time / 60) % 24) / 12;
		
		String Dday = "Day " + day;
		String Dhour = "" + hour;
		String Dminute = "" + ((minute <= 9) ? ("0" + minute) : (minute));
		String Dapm = "" + ((apm == 0) ? "am" : "pm");
		
		return Dday + ", " + Dhour + ":" + Dminute + " " + Dapm;
	}
	
	private String formattedCash()
	{		
		DecimalFormat formatter = new DecimalFormat("#,###");
		
		return "$" + formatter.format((long) Main.game.cash);
	}
	
	private void drawControls(Graphics2D g)
	{
		g.drawString("Controls", 5, height - 140);
		g.drawString("____________", 5, height - 140);
		g.drawString("Up", 5, height - 115);
		g.drawString("Down", 5, height - 95);
		g.drawString("Left", 5, height - 75);
		g.drawString("Right", 5, height - 55);
		g.drawString("Fix", 5, height - 35);
		
		g.drawString("       W", 5, height - 115);
		g.drawString("       S", 5, height - 95);
		g.drawString("       A", 5, height - 75);
		g.drawString("       D", 5, height - 55);
		g.drawString("       Space", 5, height - 35);
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(textColor);
		g.setFont(font36);
		g.drawString(formattedTime(), 5, 20);
		
		String cash = formattedCash();
		g.drawString(cash, width - g.getFontMetrics().stringWidth(cash) - 10, 20);
		
//		g.drawString(Main.game.player.localTranslation.x + "," + Main.game.player.localTranslation.y, 5, 60);
		
		drawControls(g);
	}
	
	public void init()
	{
		try
		{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/font/visitor2.ttf")));
		}
		catch (IOException | FontFormatException e)
		{
		     e.printStackTrace();
		}
		
		font36 = new Font("Visitor TT2 BRK", Font.TRUETYPE_FONT, 36);
		
		width = Main.engine.getConfig().getInt("render.width");
		height = Main.engine.getConfig().getInt("render.height");
	}
}
