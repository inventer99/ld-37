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
	public Texture roof_right_down;
	public Texture roof_left_down;
	public Texture roof_right_up;
	public Texture roof_left_up;
	public Texture window;
	public Texture door;
	public Texture grass;
	public Texture grass_flowers;
	
	private String[] ground;
	private String[] plan1;
	private String[] plan2;
	
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
		roof_right_down = new Texture(Main.game.assetManager.loadImage("res/tiles/building/roof_right_down.png"));
		roof_left_down = new Texture(Main.game.assetManager.loadImage("res/tiles/building/roof_left_down.png"));
		roof_right_up = new Texture(Main.game.assetManager.loadImage("res/tiles/building/roof_right_up.png"));
		roof_left_up = new Texture(Main.game.assetManager.loadImage("res/tiles/building/roof_left_up.png"));
		window = new Texture(Main.game.assetManager.loadImage("res/tiles/building/window.png"));
		door = new Texture(Main.game.assetManager.loadImage("res/tiles/building/door.png"));
		grass = new Texture(Main.game.assetManager.loadImage("res/tiles/natural/grass.png"));
		grass_flowers = new Texture(Main.game.assetManager.loadImage("res/tiles/natural/grass_flowers.png"));
		
		ground = new String[] {
			"..........................................",
			"..........................................",
			".,......          ..,...          ........",
			"........   .  .   ......   .  .   ........",
			"........                          ...,,,,.",
			"....,...           .  .           ...,,...",
			"....,...                          ........",
			"........                          ........",
			"........                          ........",
			".,,.....                          ...,....",
			"........                          ........",
			"........                          .....,..",
			"...,,...          ......          ........",
			"........          ......          ........",
			".........,,...............................",
			"..........,,..............................",
			"...........,.............,,........,,.....",
			"..........................................",
		};
		
		plan1 = new String[] {
			"                                          ",
			"                                          ",
			"                                          ",
			"         LPWPPWPR        LPWPPWPR         ",
			"         LPPPPPPR        LPPPPPPR         ",
			"         ########LPWPPWPR########         ",
			"         ########LPPPPPPR########         ",
			"         ########################         ",
			"         ########################         ",
			"         ########################         ",
			"         ########################         ",
			"         ########   ##   ########         ",
			"           #  #            #  #           ",
			"                                          ",
			"                                          ",
			"                                          ",
			"                                          ",
			"                                          ",
		};
		
		plan2 = new String[] {
			"                                          ",
			"                                          ",
			"        <HHHHHHHH>      <HHHHHHHH>        ",
			"        V        V      V        V        ",
			"        V        (HHHHHH)        V        ",
			"        V                        V        ",
			"        V                        V        ",
			"        V                        V        ",
			"        V                        V        ",
			"        V        <HHHHHH>        V        ",
			"        V        VLPWWPRV        V        ",
			"        (HHHHHHHH)LPDDGR(HHHHHHHH)        ",
			"        LPPWPPWPPR      LPPWPPWPPR        ",
			"        LGGGPPGGPR      LPGGPPGGPR        ",
			"                                          ",
			"                                          ",
		};
		
		addPlanToLayer(ground, Main.game.layerBack);
		addPlanToLayer(plan1, Main.game.layer1);
		addPlanToLayer(plan2, Main.game.layer2);
	}
	
	private void addPlanToLayer(String[] plan, Node layer)
	{
		for(int y = 0;y < plan.length;y++)
		{
			for(int x = 0;x < plan[y].length();x++)
			{
				char tile = plan[y].charAt(x);
				
				switch(tile)
				{
					case '#': layer.addChild(new Tile(x, -y, wood_plank)); break;
					case 'P': layer.addChild(new Tile(x, -y, plaster)); break;
					case 'L': layer.addChild(new Tile(x, -y, plaster_board_left)); break;
					case 'R': layer.addChild(new Tile(x, -y, plaster_board_right)); break;
					case 'G': layer.addChild(new Tile(x, -y, plaster_grass)); break;
					case 'V': layer.addChild(new Tile(x, -y, roof_vertical)); break;
					case 'H': layer.addChild(new Tile(x, -y, roof_horizontal)); break;
					case '<': layer.addChild(new Tile(x, -y, roof_right_down)); break;
					case '>': layer.addChild(new Tile(x, -y, roof_left_down)); break;
					case '(': layer.addChild(new Tile(x, -y, roof_right_up)); break;
					case ')': layer.addChild(new Tile(x, -y, roof_left_up)); break;
					case 'W': layer.addChild(new Tile(x, -y, window)); break;
					case 'D': layer.addChild(new Tile(x, -y, door)); break;
					case '.': layer.addChild(new Tile(x, -y, grass)); break;
					case ',': layer.addChild(new Tile(x, -y, grass_flowers)); break;
				}
			}
		}
	}
}
