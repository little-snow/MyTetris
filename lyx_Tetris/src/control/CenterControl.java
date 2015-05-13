package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import container.Panel;
import data.Data;
import data.RankList;

public class CenterControl implements ActionListener{
	/**
	 * 控制panel（panel.data可调用data数据） 控制logic（logic.data调用data数据）
	 */
	private Panel panel;
	private Logic logic;
	// 访问本地排行榜数据
	private RankList rankList;
	//控制自动下落
	public static Timer timer;
	
	public CenterControl(Panel panel, Logic logic) {
		this.panel = panel;
		this.logic = logic;
		// 新建游戏记录 存储区
		this.rankList = new RankList();
		// 从本地读取排行榜到游戏
		this.logic.setRankList(rankList.readRankList());
		timer = new Timer(Data.getDelay(),this);
	}

	// 键值为 ↑
	public void keyUp() {
		this.logic.keyUp();
		this.panel.repaint();
	}

	// 键值为 ↓
	public boolean keyDown() {
		boolean downable = this.logic.keyDown();
		this.panel.repaint();
		if (Data.fail == true) {
			timer.stop();
			this.panel.setStartButton(false);
			JOptionPane.showMessageDialog(null, "Game Over!");
			saveData(false);
		}
		return downable;
	}

	// 键值为 ←
	public void keyLeft() {
		this.logic.keyLeft();
		this.panel.repaint();
	}

	// 键值为 →
	public void keyRight() {
		this.logic.keyRight();
		this.panel.repaint();

	}

	// 继续/暂停
	public void startOrParse() {
		if (!Data.fail) {
			this.panel.setStartButton(this.logic.startOrParse());
			this.panel.repaint();
		}
		else
			this.restart();
			return;

	}

	// 游戏结束存储数据
	public void saveData(boolean choice) {
		this.logic.saveData(choice);
		this.panel.repaint();

	}

	public void quickFall() {
		while (this.keyDown())
			;
	}

	public void restart() {
		Data.initData();
		timer.setDelay(Data.getDelay());
		this.panel.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(Data.start)
			this.keyDown();
	}
	
}
