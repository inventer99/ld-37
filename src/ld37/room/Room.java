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
	public Texture plaster_grass;
	public Texture roof_vertical;
	public Texture roof_horizontal;
	public Texture window;
	public Texture grass;
	public Texture grass_flowers;
	
	private int[][] ground;
	private int[][] plan1;
	private int[][] plan2;
	
	@Override
	public void onInit()
	{
		wood_plank = new Texture(Main.game.assetManager.loadImage("res/tiles/building/wood_plank.png"));
		plaster = new Texture(Main.game.assetManager.loadImage("res/tiles/building/plaster.png"));
		plaster_board_left = new Texture(Main.game.assetManager.loadImage("res/tiles/building/plaster_board_left.png"));
		plaster_board_right = new Texture(Main.game.assetManager.loadImage("res/tiles/building/plaster_board_right.png"));
		plaster_grass = new Texture(Main.game.assetManager.loadImage("res/tiles/building/plaster_grass.png"));
		roof_vertical = new Texture(Main.game.assetManager.loadImage("res/tiles/building/roof_vertical.png"));
		roof_horizontal = new Texture(Main.game.assetManager.loadImage("res/tiles/building/roof_horizontal.png"));
		window = new Texture(Main.game.assetManager.loadImage("res/tiles/building/window.png"));
		grass = new Texture(Main.game.assetManager.loadImage("res/tiles/natural/grass.png"));
		grass_flowers = new Texture(Main.game.assetManager.loadImage("res/tiles/natural/grass_flowers.png"));
		
		ground = new int[][] {
			{21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 22, 22, 21, 21, 22, 22, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0, 22, 22, 21, 21, 21, 21, 21, 21,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0, 21, 21, 21, 21, 21, 21, 21, 21,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
			{21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
		};
		
		plan1 = new int[][] {
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  3, 13,  3,  3, 13,  3,  5,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  3,  3,  3,  3,  3,  3,  5,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		};
		
		plan2 = new int[][] {
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  8,  8,  8,  8,  8,  8,  8,  8,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  8,  8,  8,  8,  8,  8,  8,  8,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  4,  3,  3, 13,  3,  3, 13,  3,  3,  5,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  4,  6,  6,  3,  3,  6,  6,  6,  3,  5,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		};
		
		addPlanToLayer(ground, Main.game.layerBack);
		addPlanToLayer(plan1, Main.game.layerBack);
		addPlanToLayer(plan2, Main.game.layerFront);
	}
	
	private void addPlanToLayer(int[][] plan, Node layer)
	{
		for(int y = 0;y < plan.length;y++)
		{
			for(int x = 0;x < plan[y].length;x++)
			{
				int tile = plan[y][x];
				
				switch(tile)
				{
					case 2: layer.addChild(new Tile(x, -y, wood_plank)); break;
					case 3: layer.addChild(new Tile(x, -y, plaster)); break;
					case 4: layer.addChild(new Tile(x, -y, plaster_board_left)); break;
					case 5: layer.addChild(new Tile(x, -y, plaster_board_right)); break;
					case 6: layer.addChild(new Tile(x, -y, plaster_grass)); break;
					case 7: layer.addChild(new Tile(x, -y, roof_vertical)); break;
					case 8: layer.addChild(new Tile(x, -y, roof_horizontal)); break;
					case 13: layer.addChild(new Tile(x, -y, window)); break;
					case 21: layer.addChild(new Tile(x, -y, grass)); break;
					case 22: layer.addChild(new Tile(x, -y, grass_flowers)); break;
				}
			}
		}
	}
}
