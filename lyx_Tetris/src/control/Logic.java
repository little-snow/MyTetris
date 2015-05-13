package control;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import container.Dialog;
import data.Action;
import data.Data;
import data.PerRank;
import data.RankList;

public class Logic {
	private Data data;
	private Action action;
	private Random random = new Random();
	private final int MAP_X = 10;
	private final int MAP_Y = 18;
	
	private boolean[][] ground = new boolean[MAP_Y][MAP_X];

	public Logic(Data data) {
		this.data = data;
		this.data.setGround(ground);
	}

	// 键值为 ←
	public void keyLeft() {
		this.action.move(-1, 0, data.getGround());
	}

	// 键值为 →
	public void keyRight() {
		this.action.move(1, 0, data.getGround());
	}

	

	// 键值为 ↑
	public void keyUp() {
		this.action.roll(data.getGround());
	}

	// 键值为 ↓
	public boolean keyDown() {

		if (this.action.move(0, 1, data.getGround())) {
			return true;
		}
		// 当某方法中是一个 if 写到底时最好在 if 语句中写 return

		// 把落点坐标写入map
		for (Point e : this.action.getLocation())
			this.ground[e.y][e.x] = true;
		// 判断是否能消行并处理
		this.removeLine();
		
		// 将之前新建的方块编号转换成相应方块
		this.action.initialIcons(this.data.getNext());
		
		// 新建下一组方块的编号
		this.data.setNext(random.nextInt(7));
		
		// 检查游戏是否失败
			this.checkFail();
			return false;
	}

	public void setRankList(List<PerRank> rankList) {
		this.data.setRankList(rankList);
	}

	// 保存数据
	public void saveData(boolean choice) {
		if (choice) {
			// 用户主动退出
			new Dialog().exit();
			return;
		}
		// 保存数据
		this.saveData(this.data.getTotalScore(), this.data.getRankList());
	}

	// 暂停 继续,游戏运行中 返回true
	public boolean startOrParse() {
		if(!Data.start){
			this.action = action == null? new Action() : action;
			this.data.setStart(true);
			this.data.setAction(action);
			CenterControl.timer.start();
			return true;
		}
		else{
			this.data.setStart(false);
			CenterControl.timer.stop();
			return false;
		}
	}

	// 消行操作
	private void removeLine() {
		// 获取地图数组
		boolean[][] map = this.data.getGround();
		int flag = 0;
		for (int y = MAP_Y - 1; y > -1; y--) {
			int x = 0;
			for (; x < 10 && map[y][x]; x++)
				;
			if (x == MAP_X) {
				flag++;
				// 重设地图
				this.setNewGround(map, y);
				y++;
			}
		}
		if (flag != 0)
			// 加分
			this.levelUp(flag);

	}

	// 刷新 map 真值
	private void setNewGround(boolean[][] map, int y) {
		for (; y > 0; y--)
			for (int x = 0; x < MAP_X; x++)
				map[y][x] = map[y - 1][x];
		for (int x = 0; x < MAP_X; x++)
			map[y][x] = false;
	}

	// 设置升级
	private void levelUp(int removeLineAdd) {
		/**
		 * 修改分数: removeLineAdd * baseScore
		 */
		this.data.setTotalScore(5 * removeLineAdd * (removeLineAdd + 1));
		if (this.data.getNowScore() >= this.data.getPerScore()) {
			this.data.setGrade(1);
			CenterControl.timer.setDelay(Data.getDelay());
		}
	}

	/**
	 * @param newScore
	 *            本次游戏分数
	 * @param preList
	 *            本地数据
	 */
	private void saveData(int newScore, List<PerRank> preList) {
		int i = 0;
		for (; i < 5; i++) {
			// 搜索 若破纪录则终止循环
			if (newScore > preList.get(i).getScore()) {
				break;
			}

		}
		if (i < 5) {
			// 显示 是否保存数据对话框
			String name = new Dialog().updateRankList();			
			if (name == null) {
				return;
			}
			preList.add(new PerRank(name, newScore));
			this.data.setRankList(preList);
			preList.remove(5);
			// 往本地写文件,显示是否成功
			if (new RankList().writeRankList(this.data.getRankList())) {
				new Dialog().showSucceed();
			}
		}
	}

	// gameover 返回 true
	private void checkFail() {
		// 获得当前停止的方块
		for (Point location : this.action.getLocation()) {
			if (ground[location.y][location.x]){
				// 确定游戏失败后
				this.startOrParse();
				Data.fail = true;
				this.data.setStart(false);
				return;
			}
			

		}

	}

}
