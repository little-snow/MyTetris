package body;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import data.PerRank;

public class BodyRanking extends Body {
	private final int ROW_H = 22;
	private final int ROW_NUM = 5;
	// �ӱ����ļ��ж�ȡ rankList
	private List<PerRank> rankList;
	// ��ȡ���а�ͼ
	private Image ranking = new ImageIcon("images/string/ranking.png").getImage();
	// ��ʾTOP n
	String[] tops;
	Color[] colors;

	public BodyRanking() {
		// CORNER_X/Y Ϊ���������Ͻ� + ˮƽƫ��/��ֱƫ��
		super(379 + 7, 337 + 22);
		tops = new String[ROW_NUM];
		for (int i = 0; i < ROW_NUM;) {
			tops[i] = "TOP " + ++i;
		}
		this.colors = new Color[6];
		this.setColors(colors);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(ranking, 368, 322, null);
		// ˢ������
		rankList = Body.data.getRankList();
		// �������а�
		for (int i = 0; i < ROW_NUM; i++) {
			PerRank p = rankList.get(i);
			this.drawRankList(i, CORNER_X, CORNER_Y + i * ROW_H, tops[i], p, g);
		}
		this.setColors(this.colors);
	}
	private void setColors(Color[] colors){
		colors[0] = Color.red;
		colors[1] = Color.magenta;
		colors[2] = Color.yellow;
		colors[3] = Color.green;
		colors[4] = Color.pink;
		colors[5] = Color.DARK_GRAY;
	}
	// �������֣�name = ����N������score = ���� + ����
	private void drawRankList(int i, int x, int y, String rank, PerRank p, Graphics g) {
		int pp = p.getScore();
		if (pp == 0) {
			g.setColor(colors[5]);
			g.drawString("NO DATA", x + 50, y);
			return;
		}
		g.setColor(colors[i]);
		g.drawString(rank + "       " + p.getName(), x + 10, y);
		String score = Integer.toString(pp);
		g.drawString(score, x + 165 - 7*score.length() , y);
	}
}
