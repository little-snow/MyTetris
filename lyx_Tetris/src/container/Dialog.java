package container;

import javax.swing.JOptionPane;

public class Dialog {
	private String name;
	public static final byte RANK_BROKEN = 1;
	public static final byte WHETHER_EXIT = 2;

	public Dialog() {
	}

	// 退出游戏对话框
	public void exit() {
		int i = JOptionPane.showConfirmDialog(null, "确定要退出游戏？\n", "提示", JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	//更新数据对话框
	public String updateRankList() {
		if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Congratulations!\n您已破记录,是否保存?", "提示", JOptionPane.YES_NO_OPTION))
			this.name = JOptionPane.showInputDialog("尊姓大名:");
		return this.name;
	}
	
	public void showSucceed() {
		JOptionPane.showMessageDialog(null, "Succeed!");

	}
}