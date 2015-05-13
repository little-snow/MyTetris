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

	// ��ֵΪ ��
	public void keyLeft() {
		this.action.move(-1, 0, data.getGround());
	}

	// ��ֵΪ ��
	public void keyRight() {
		this.action.move(1, 0, data.getGround());
	}

	

	// ��ֵΪ ��
	public void keyUp() {
		this.action.roll(data.getGround());
	}

	// ��ֵΪ ��
	public boolean keyDown() {

		if (this.action.move(0, 1, data.getGround())) {
			return true;
		}
		// ��ĳ��������һ�� if д����ʱ����� if �����д return

		// ���������д��map
		for (Point e : this.action.getLocation())
			this.ground[e.y][e.x] = true;
		// �ж��Ƿ������в�����
		this.removeLine();
		
		// ��֮ǰ�½��ķ�����ת������Ӧ����
		this.action.initialIcons(this.data.getNext());
		
		// �½���һ�鷽��ı��
		this.data.setNext(random.nextInt(7));
		
		// �����Ϸ�Ƿ�ʧ��
			this.checkFail();
			return false;
	}

	public void setRankList(List<PerRank> rankList) {
		this.data.setRankList(rankList);
	}

	// ��������
	public void saveData(boolean choice) {
		if (choice) {
			// �û������˳�
			new Dialog().exit();
			return;
		}
		// ��������
		this.saveData(this.data.getTotalScore(), this.data.getRankList());
	}

	// ��ͣ ����,��Ϸ������ ����true
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

	// ���в���
	private void removeLine() {
		// ��ȡ��ͼ����
		boolean[][] map = this.data.getGround();
		int flag = 0;
		for (int y = MAP_Y - 1; y > -1; y--) {
			int x = 0;
			for (; x < 10 && map[y][x]; x++)
				;
			if (x == MAP_X) {
				flag++;
				// �����ͼ
				this.setNewGround(map, y);
				y++;
			}
		}
		if (flag != 0)
			// �ӷ�
			this.levelUp(flag);

	}

	// ˢ�� map ��ֵ
	private void setNewGround(boolean[][] map, int y) {
		for (; y > 0; y--)
			for (int x = 0; x < MAP_X; x++)
				map[y][x] = map[y - 1][x];
		for (int x = 0; x < MAP_X; x++)
			map[y][x] = false;
	}

	// ��������
	private void levelUp(int removeLineAdd) {
		/**
		 * �޸ķ���: removeLineAdd * baseScore
		 */
		this.data.setTotalScore(5 * removeLineAdd * (removeLineAdd + 1));
		if (this.data.getNowScore() >= this.data.getPerScore()) {
			this.data.setGrade(1);
			CenterControl.timer.setDelay(Data.getDelay());
		}
	}

	/**
	 * @param newScore
	 *            ������Ϸ����
	 * @param preList
	 *            ��������
	 */
	private void saveData(int newScore, List<PerRank> preList) {
		int i = 0;
		for (; i < 5; i++) {
			// ���� ���Ƽ�¼����ֹѭ��
			if (newScore > preList.get(i).getScore()) {
				break;
			}

		}
		if (i < 5) {
			// ��ʾ �Ƿ񱣴����ݶԻ���
			String name = new Dialog().updateRankList();			
			if (name == null) {
				return;
			}
			preList.add(new PerRank(name, newScore));
			this.data.setRankList(preList);
			preList.remove(5);
			// ������д�ļ�,��ʾ�Ƿ�ɹ�
			if (new RankList().writeRankList(this.data.getRankList())) {
				new Dialog().showSucceed();
			}
		}
	}

	// gameover ���� true
	private void checkFail() {
		// ��õ�ǰֹͣ�ķ���
		for (Point location : this.action.getLocation()) {
			if (ground[location.y][location.x]){
				// ȷ����Ϸʧ�ܺ�
				this.startOrParse();
				Data.fail = true;
				this.data.setStart(false);
				return;
			}
			

		}

	}

}
