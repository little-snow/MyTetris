package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import data.Data;

public class PlayerControl extends KeyAdapter {
	// ʹ��ҿ��� �ܲ��� centercontrol
	private CenterControl centerControl;

	public PlayerControl(CenterControl centerControl) {
		this.centerControl = centerControl;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		// ��ֵΪ ESC ʱ �˳�
		if(id == KeyEvent.VK_ESCAPE)
			this.centerControl.saveData(true);
		else if(id == KeyEvent.VK_ENTER){
			this.centerControl.startOrParse();
		}
		else if(Data.start)
			switch (id) {
	
			// ����ֵ�ֱ�Ϊ �� �� �� �� ʱ����Ӧ
				case KeyEvent.VK_UP:
					this.centerControl.keyUp();
					break;
				case KeyEvent.VK_DOWN:
					this.centerControl.keyDown();
					break;
				case KeyEvent.VK_LEFT:
					this.centerControl.keyLeft();
					break;
				case KeyEvent.VK_RIGHT:
					this.centerControl.keyRight();
					break;
	
				// ��ֵΪ sp ʱ ��������
				case KeyEvent.VK_SPACE:
					this.centerControl.quickFall();
					break;

				default:
					break;
				}
	}

}
