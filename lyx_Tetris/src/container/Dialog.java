package container;

import javax.swing.JOptionPane;

public class Dialog {
	private String name;
	public static final byte RANK_BROKEN = 1;
	public static final byte WHETHER_EXIT = 2;

	public Dialog() {
	}

	// �˳���Ϸ�Ի���
	public void exit() {
		int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳���Ϸ��\n", "��ʾ", JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	//�������ݶԻ���
	public String updateRankList() {
		if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Congratulations!\n�����Ƽ�¼,�Ƿ񱣴�?", "��ʾ", JOptionPane.YES_NO_OPTION))
			this.name = JOptionPane.showInputDialog("���մ���:");
		return this.name;
	}
	
	public void showSucceed() {
		JOptionPane.showMessageDialog(null, "Succeed!");

	}
}