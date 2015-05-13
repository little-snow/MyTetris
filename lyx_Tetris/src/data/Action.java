package data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Action {
	// 方块数组
	private Point[] location;
	// 临时存储变换后的坐标, loc[0] for x loc[1] for y
	private int[][] loc = new int[2][4];
	private static List<Point[]> locations;
	//方块编号
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
	
	//获取图形编号
	public int getIconId() {
		return iconId;
	}
	
	//初始化方块
	public void initialIcons(int actionCode) {
		this.iconId = actionCode;
		Point[] temp = locations.get(actionCode);
		location = new Point[temp.length];
		int i = 0;
		for(; i < temp.length; i++){
			location[i] = new Point(temp[i].x, temp[i].y);
		}
	}

	// 判断能否旋转,若不能则返回 true
	private boolean isOverZone(int x, int y, boolean[][] ground) {
		return (x < 0 || x > 9 || y < 0 || y > 17 || ground[y][x]);
	}

	// 当方块可移动时修改坐标，返回是否成功修改
	public boolean move(int x, int y, boolean[][] ground) {
		for (int i = 0; i < location.length; i++) {
			loc[0][i] = location[i].x + x;
			loc[1][i] = location[i].y + y;
			if (this.isOverZone(loc[0][i], loc[1][i], ground)) {
				return false;
			}

		}
		// 修改图形的坐标
		for (int i = 0; i < location.length; i++) {
			location[i].x = loc[0][i];
			location[i].y = loc[1][i];
		}
		return true;
	}

	/**
	 * 图形旋转控制，
	 * 逆时针
	 *		A.x = O.x - O.y + B.y
	 *		A.y = O.x + O.y - B.x
	 *顺时针
	 *		A.x = O.y + O.x - B.y
	 *		A.y = O.y - O.x + B.x
	 */
	public void roll(boolean[][] ground) {
		if(iconId == 3) return;
		for (int i = 1; i < location.length; i++) {
			
			/*逆时针
			 * loc[0][i] = location[0].x - location[0].y + location[i].y;
				loc[1][i] = location[0].x + location[0].y - location[i].x;
*/
			//顺时针
			loc[0][i] = location[0].y + location[0].x - location[i].y;
			loc[1][i] = location[0].y - location[0].x + location[i].x;
			// 判断是否能旋转,会超出边界时直接return
			if (this.isOverZone(loc[0][i], loc[1][i], ground))
				return;
		}
		// 旋转图形
		for (int i = 1; i < location.length; i++) {
			location[i].x = loc[0][i];
			location[i].y = loc[1][i];
		}
	}

	public Point[] getLocation() {
		return this.location;
	}
}
