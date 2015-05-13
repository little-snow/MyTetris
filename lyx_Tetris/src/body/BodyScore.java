package body;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class BodyScore extends Body {
	// 获取分数图片
	private Image scoreImg = new ImageIcon("images/string/score.png").getImage();
	private int score;
	private Point loc;

	public BodyScore() {
		// 方框右下角、宽度
		super(571, 294);
		// !!! super 调用父类方法
		// 获取数字图片
		setNumber();
		// 最右侧数字右上角
		loc = new Point(CORNER_X - 10, CORNER_Y - 40);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(scoreImg, 380, 233, null);
		score = Body.data.getTotalScore();
		drawNum(score, loc, g);
	}
}
