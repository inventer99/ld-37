package ld37;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayDeque;

import pixgen.util.Updateable;

public class UI implements Updateable
{
	private int width;
	private int height;
	
	private Font font36;
	private Font font52;
	
	public Color textColor = new Color(160, 46, 46);
	public Color fixColor = new Color(224, 151, 26);
	public Color hitColor = new Color(73, 224, 26);
	public Color healthColor = new Color(224, 26, 26);
	
	public boolean doDrawControls;
	public boolean doDrawGameOver;
	
	private String formattedTime()
	{
		long time = (long) Main.game.timeSurvived + 60 * 11;
		
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
	
	private String formattedSurvivedTime()
	{
		long time = (long) Main.game.timeSurvived;
		
		long day = (time / (24 * 60));
		long hour = (time / (60)) % 24;
		long minute = time % 60;
		
		String Dday = day + ((day == 1) ? " day" : " days");
		String Dhour = hour + ((hour == 1) ? " hour" : " hours");
		String Dminute = minute + ((minute == 1) ? " minute" : " minutes");
		
		return Dday + ", " + Dhour + ", " + Dminute;
	}
	
	private String formattedCash(long value)
	{		
		DecimalFormat formatter = new DecimalFormat("#,###");
		
		return (value >= 0 ? "+" : "-") + "$" + formatter.format(Math.abs(value));
	}
	
	private void drawControls(Graphics2D g)
	{
		g.drawString("Controls (H)", 5, height - 160);
		g.drawString("____________", 5, height - 160);
		g.drawString("Up", 5, height - 135);
		g.drawString("Down", 5, height - 115);
		g.drawString("Left", 5, height - 95);
		g.drawString("Right", 5, height - 75);
		g.drawString("Attack", 5, height - 55);
		g.drawString("Fix", 5, height - 35);
		
		g.drawString("       W", 5, height - 135);
		g.drawString("       S", 5, height - 115);
		g.drawString("       A", 5, height - 95);
		g.drawString("       D", 5, height - 75);
		g.drawString("       LMB", 5, height - 55);
		g.drawString("       RMB", 5, height - 35);
	}
	
	private float time;
	private ArrayDeque<String> cashChanges;
	
	public void changeCash(long value)
	{
		cashChanges.addLast(formattedCash(value));
	}
	
	@Override
	public void update(float delta)
	{
		time += delta;
		
		if(time >= 1.25)
		{
			if(!cashChanges.isEmpty())
				cashChanges.pop();
			time = 0;
		}
	}
	
	public void draw(Graphics2D g)
	{
		if(!doDrawGameOver)
		{
			g.setColor(textColor);
			g.setFont(font36);
			g.drawString(formattedTime(), 5, 20);
			
			String cash = formattedCash(Main.game.cash);
			g.drawString(cash, width - g.getFontMetrics().stringWidth(cash) - 10, 20);
			
			int i = 0;
			for(String s : cashChanges)
			{
				g.drawString(s, width - g.getFontMetrics().stringWidth(s) - 10, 40 + (20 * i));
				i++;
			}
			
	//		g.drawString(Main.game.player.localTranslation.x + "," + Main.game.player.localTranslation.y, 5, 60);
		
			if(doDrawControls)
				drawControls(g);
		}
		else
		{
			g.setColor(textColor);
			g.setFont(font52);
			
			String go = "Game Over";
			String time = "You survived for " + formattedSurvivedTime();
			
			int cx = (width / 2);
			int cy = (height / 2);
			
			g.drawString(go, cx - (g.getFontMetrics().stringWidth(go) / 2), cy - 30);
			
			g.setFont(font36);
			g.drawString(time, cx - (g.getFontMetrics().stringWidth(time) / 2), cy + 15);
		}
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
		font52 = new Font("Visitor TT2 BRK", Font.TRUETYPE_FONT, 52);
		
		width = Main.engine.getConfig().getInt("render.width");
		height = Main.engine.getConfig().getInt("render.height");
		
		doDrawControls = true;
		doDrawGameOver = false;
		
		time= 0;
		cashChanges = new ArrayDeque<String>();
	}
}
