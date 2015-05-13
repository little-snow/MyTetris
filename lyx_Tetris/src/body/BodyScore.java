package body;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class BodyScore extends Body {
	// ��ȡ����ͼƬ
	private Image scoreImg = new ImageIcon("images/string/score.png").getImage();
	private int score;
	private Point loc;

	public BodyScore() {
		// �������½ǡ����
		super(571, 294);
		// !!! super ���ø��෽��
		// ��ȡ����ͼƬ
		setNumber();
		// ���Ҳ��������Ͻ�
		loc = new Point(CORNER_X - 10, CORNER_Y - 40);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(scoreImg, 380, 233, null);
		score = Body.data.getTotalScore();
		drawNum(score, loc, g);
	}
}
