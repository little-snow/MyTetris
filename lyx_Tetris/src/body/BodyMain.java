package body;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import data.Action;
import data.Data;

public class BodyMain extends Body {
	Point[] location;
	boolean[][] ground;
	int iconId;
	Image[] icons;
	Image shadow;
	public BodyMain(Data data) {
		// 边框左上角
		super(52, 15);
		
		//获取小方块
		icons = new Image[9];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = new ImageIcon("images/icons/main" + i + ".png").getImage();
		}
		shadow = new ImageIcon("images/icons/shadow.png").getImage();
		// 接入data数据、获取方块位置、地上存在方块的位置(ground[y][x])
		Body.data = data;
		ground = Body.data.getGround();
	}

	@Override
	public void paint(Graphics g) {
		Action act = Body.data.getAction();
		if(act == null) 
			return;
		location = act.getLocation();
		iconId = Data.fail ? 8 : act.getIconId();
		// 将下落中的方块打印出来
		for (int i = 0; i < location.length; i++)
			g.drawImage(this.icons[iconId], CORNER_X + location[i].x * Body.ICON_SIZE, CORNER_Y + location[i].y * Body.ICON_SIZE, null);

		// 将落下的方块打印出来
		int gradeColor;
		if(!Data.fail){
			int grade = Body.data.getGrade();
			gradeColor = grade == 1 ?  7 : grade % 7;
		}
		else gradeColor = 8;
		for (int y = 0; y < ground.length; y++) {
			for (int x = 0; x < ground[y].length; x++) {
				if (ground[y][x]) {
					g.drawImage(this.icons[gradeColor], CORNER_X + x * Body.ICON_SIZE, CORNER_Y + y * Body.ICON_SIZE, null);
				}
			}
		}
		//打印阴影
		if(!Data.start && !Data.fail)
			g.drawImage(shadow, CORNER_X, CORNER_Y, 10*Body.ICON_SIZE,18*Body.ICON_SIZE, null);
	}
}