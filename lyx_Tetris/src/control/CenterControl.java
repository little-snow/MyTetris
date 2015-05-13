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
	 * ����panel��panel.data�ɵ���data���ݣ� ����logic��logic.data����data���ݣ�
	 */
	private Panel panel;
	private Logic logic;
	// ���ʱ������а�����
	private RankList rankList;
	//�����Զ�����
	public static Timer timer;
	
	public CenterControl(Panel panel, Logic logic) {
		this.panel = panel;
		this.logic = logic;
		// �½���Ϸ��¼ �洢��
		this.rankList = new RankList();
		// �ӱ��ض�ȡ���а���Ϸ
		this.logic.setRankList(rankList.readRankList());
		timer = new Timer(Data.getDelay(),this);
	}

	// ��ֵΪ ��
	public void keyUp() {
		this.logic.keyUp();
		this.panel.repaint();
	}

	// ��ֵΪ ��
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

	// ��ֵΪ ��
	public void keyLeft() {
		this.logic.keyLeft();
		this.panel.repaint();
	}

	// ��ֵΪ ��
	public void keyRight() {
		this.logic.keyRight();
		this.panel.repaint();

	}

	// ����/��ͣ
	public void startOrParse() {
		if (!Data.fail) {
			this.panel.setStartButton(this.logic.startOrParse());
			this.panel.repaint();
		}
		else
			this.restart();
			return;

	}

	// ��Ϸ�����洢����
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
