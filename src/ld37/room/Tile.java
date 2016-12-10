package ld37.room;

import pixgen.comp.Texture;
import pixgen.math.Vector;
import pixgen.scene.Entity;

public class Tile extends Entity
{
	public Tile(int x, int y, Texture texture)
	{
		this.addComponent(texture);
		
		this.localTranslation = new Vector(x, y);
	}
}
