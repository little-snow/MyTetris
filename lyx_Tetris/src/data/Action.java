package data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Action {
	// ��������
	private Point[] location;
	// ��ʱ�洢�任�������, loc[0] for x loc[1] for y
	private int[][] loc = new int[2][4];
	private static List<Point[]> locations;
	//������
	private int iconId;
	public Action() {
		locations = new ArrayList<>();
		locations.add(new Point[] { new Point(4, 0), new Point(3, 0), new Point(5, 0),new Point(6, 0) });
		locations.add(new Point[] { new Point(4, 0), new Point(3, 0), new Point(5, 0),new Point(5, 1) });
		locations.add(new Point[] { new Point(4, 0), new Point(3, 0), new Point(5, 0),new Point(3, 1) });
		locations.add(new Point[] { new Point(4, 0), new Point(5, 0), new Point(4, 1),new Point(5, 1) });
		locations.add(new Point[] { new Point(4, 0), new Point(5, 0), new Point(3, 1),new Point(4, 1) });
		locations.add(new Point[] { new Point(4, 0), new Point(3, 0), new Point(4, 1),new Point(5, 1) });
		locations.add(new Point[] { new Point(4, 0), new Point(3, 0), new Point(5, 0),new Point(4, 1) });
		
		this.initialIcons(new Random().nextInt(7));
	}
	
	//��ȡͼ�α��
	public int getIconId() {
		return iconId;
	}
	
	//��ʼ������
	public void initialIcons(int actionCode) {
		this.iconId = actionCode;
		Point[] temp = locations.get(actionCode);
		location = new Point[temp.length];
		int i = 0;
		for(; i < temp.length; i++){
			location[i] = new Point(temp[i].x, temp[i].y);
		}
	}

	// �ж��ܷ���ת,�������򷵻� true
	private boolean isOverZone(int x, int y, boolean[][] ground) {
		return (x < 0 || x > 9 || y < 0 || y > 17 || ground[y][x]);
	}

	// ��������ƶ�ʱ�޸����꣬�����Ƿ�ɹ��޸�
	public boolean move(int x, int y, boolean[][] ground) {
		for (int i = 0; i < location.length; i++) {
			loc[0][i] = location[i].x + x;
			loc[1][i] = location[i].y + y;
			if (this.isOverZone(loc[0][i], loc[1][i], ground)) {
				return false;
			}

		}
		// �޸�ͼ�ε�����
		for (int i = 0; i < location.length; i++) {
			location[i].x = loc[0][i];
			location[i].y = loc[1][i];
		}
		return true;
	}

	/**
	 * ͼ����ת���ƣ�
	 * ��ʱ��
	 *		A.x = O.x - O.y + B.y
	 *		A.y = O.x + O.y - B.x
	 *˳ʱ��
	 *		A.x = O.y + O.x - B.y
	 *		A.y = O.y - O.x + B.x
	 */
	public void roll(boolean[][] ground) {
		if(iconId == 3) return;
		for (int i = 1; i < location.length; i++) {
			
			/*��ʱ��
			 * loc[0][i] = location[0].x - location[0].y + location[i].y;
				loc[1][i] = location[0].x + location[0].y - location[i].x;
*/
			//˳ʱ��
			loc[0][i] = location[0].y + location[0].x - location[i].y;
			loc[1][i] = location[0].y - location[0].x + location[i].x;
			// �ж��Ƿ�����ת,�ᳬ���߽�ʱֱ��return
			if (this.isOverZone(loc[0][i], loc[1][i], ground))
				return;
		}
		// ��תͼ��
		for (int i = 1; i < location.length; i++) {
			location[i].x = loc[0][i];
			location[i].y = loc[1][i];
		}
	}

	public Point[] getLocation() {
		return this.location;
	}
}
