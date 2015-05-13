package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import data.Data;

public class PlayerControl extends KeyAdapter {
	// 使玩家控制 能操作 centercontrol
	private CenterControl centerControl;

	public PlayerControl(CenterControl centerControl) {
		this.centerControl = centerControl;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		// 键值为 ESC 时 退出
		if(id == KeyEvent.VK_ESCAPE)
			this.centerControl.saveData(true);
		else if(id == KeyEvent.VK_ENTER){
			this.centerControl.startOrParse();
		}
		else if(Data.start)
			switch (id) {
	
			// 当键值分别为 ↑ ↓ ← → 时的响应
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
	
				// 键值为 sp 时 快速下落
				case KeyEvent.VK_SPACE:
					this.centerControl.quickFall();
					break;

				default:
					break;
				}
	}

}
