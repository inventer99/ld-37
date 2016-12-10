package ld37.room;

import java.awt.Color;
import java.awt.Graphics2D;

import ld37.Main;
import pixgen.scene.Node;

public class Sky extends Node
{
	private Color drawColor;
	
	@Override
	public void onUpdate(float delta)
	{
		localTranslation = Main.game.player.localTranslation;
		
		double time = Main.game.timeSurvived;
		
		int alpha = (int) ((-Math.cos(Math.toRadians(time)) * 0.5 + 0.5) * 255);
		drawColor = new Color(0, 0, 128, alpha);
	}
	
	@Override
	public void onRender(Graphics2D g)
	{
		g.setColor(drawColor);
		g.fillRect(
				-Main.engine.getConfig().getInt("render.width") / 2,
				-Main.engine.getConfig().getInt("render.height") / 2,
				Main.engine.getConfig().getInt("render.width"),
				Main.engine.getConfig().getInt("render.height"));
	}
}
