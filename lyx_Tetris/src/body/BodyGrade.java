package body;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class BodyGrade extends Body {
	// ��ȡ�ȼ�ͼƬ
	private Image gradeImg = new ImageIcon("images/string/grade.png").getImage();
	private int grade;
	private Point loc;
	public BodyGrade() {
		// �������½����ꡢ���
		super(574, 205);
		// ��ȡ����
		setNumber();
		loc = new Point(CORNER_X - 10, CORNER_Y - 50);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(gradeImg, 506, 110, null);
		grade = Body.data.getGrade();
		drawNum(grade, loc, g);
	}
}
