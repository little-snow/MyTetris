package body;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import data.Data;

public abstract class Body {
	public static Data data;
	protected Image[] icons = null;
	protected Image[] number = null;
	protected final int CORNER_X, CORNER_Y;
	public static final int ICON_SIZE = 25;
	public static final int NUM_SIZE = 24;

	protected Body(int x, int y) {
		// 定界坐标
		this.CORNER_X = x;
		this.CORNER_Y = y;

	}

	// 获取小方块
	protected void setIcon() {
		icons = new Image[9];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = new ImageIcon("images/icons/main" + i + ".png").getImage();
		}
	}

	// 获取数字图片
	protected void setNumber() {
		number = new Image[10];
		for (int i = 0; i < number.length; i++) {
			number[i] = new ImageIcon("images/string/" + i + ".png").getImage();
		}
	}

	// 将 索引 值转化为图片数字, loc 为最右侧数字右上角
	protected void drawNum(int num, Point loc, Graphics g) {
		int mod = 0, rest = num;
		if(num == 0) g.drawImage(number[0], loc.x - NUM_SIZE , loc.y, null);
		//注意  0 / 10 = 0 -> 判断条件为 rest > 0 
		for (int i = 1; rest > 0; i++) {
			mod = rest % 10;
			rest /= 10;
			g.drawImage(number[mod], loc.x - i * NUM_SIZE, loc.y,null);
		}
	}

	public abstract void paint(Graphics g);
}
