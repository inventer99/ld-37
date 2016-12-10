package ld37.room;

import ld37.Main;
import pixgen.comp.Texture;
import pixgen.scene.Node;

public class Room extends Node
{
	public Texture wood_plank;
	public Texture plaster;
	public Texture plaster_board_left;
	public Texture plaster_board_right;
	public Texture roof_vertical;
	public Texture roof_horizontal;
	public Texture grass;
	public Texture grass_flowers;
	
	private int[][] plan1;
	
	@Override
	public void onInit()
	{
		wood_plank = new Texture(Main.game.assetManager.loadImage("res/tiles/building/wood_plank.png"));
		plaster = new Texture(Main.game.assetManager.loadImage("res/tiles/building/plaster.png"));
		plaster_board_left = new Texture(Main.game.assetManager.loadImage("res/tiles/building/plaster_board_left.png"));
		plaster_board_right = new Texture(Main.game.assetManager.loadImage("res/tiles/building/plaster_board_right.png"));
		roof_vertical = new Texture(Main.game.assetManager.loadImage("res/tiles/building/roof_vertical.png"));
		roof_horizontal = new Texture(Main.game.assetManager.loadImage("res/tiles/building/roof_horizontal.png"));
		grass = new Texture(Main.game.assetManager.loadImage("res/tiles/natural/grass.png"));
		grass_flowers = new Texture(Main.game.assetManager.loadImage("res/tiles/natural/grass_flowers.png"));
		
		plan1 = new int[][] {
			{21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 22, 22, 21, 21, 22, 22, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  8,  8,  8,  8,  8,  8,  8,  8,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  7,  4,  3,  3,  3,  3,  3,  3,  5,  7, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  7,  4,  3,  3,  3,  3,  3,  3,  5,  7, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  7,  2,  2,  2,  2,  2,  2,  2,  2,  7, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  7,  2,  2,  2,  2,  2,  2,  2,  2,  7, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  7,  2,  2,  2,  2,  2,  2,  2,  2,  7, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  7,  2,  2,  2,  2,  2,  2,  2,  2,  7, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  7,  2,  2,  2,  2,  2,  2,  2,  2,  7, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  7,  2,  2,  2,  2,  2,  2,  2,  2,  7, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  7,  2,  2,  2,  2,  2,  2,  2,  2,  7, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  7,  2,  2,  2,  2,  2,  2,  2,  2,  7, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  8,  8,  8,  8,  8,  8,  8,  8,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
		};
	
		for(int y = 0;y < plan1.length;y++)
		{
			for(int x = 0;x < plan1[y].length;x++)
			{
				int tile = plan1[y][x];
				
				switch(tile)
				{
					case 2: addChild(new Tile(x, -y, wood_plank)); break;
					case 3: addChild(new Tile(x, -y, plaster)); break;
					case 4: addChild(new Tile(x, -y, plaster_board_left)); break;
					case 5: addChild(new Tile(x, -y, plaster_board_right)); break;
					case 7: addChild(new Tile(x, -y, roof_vertical)); break;
					case 8: addChild(new Tile(x, -y, roof_horizontal)); break;
					case 21: addChild(new Tile(x, -y, grass)); break;
					case 22: addChild(new Tile(x, -y, grass_flowers)); break;
				}
			}
		}
		
		localTranslation.x = plan1[0].length / -2;
		localTranslation.y = plan1.length / 2;
	}
}
