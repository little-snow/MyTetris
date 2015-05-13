package container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame() {
		
		this.setTitle("����˹����");
		//Frame��������ռ��Լ6px,����ռ��Լ29px
		this.setSize(646, 509);		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//��Ϸ���ھ��з���
		this.setLocation((screenSize.width - 640) / 2,
				(screenSize.height - 480) / 2 - 50);
		Panel panel = new Panel();
		this.add(panel);
		this.addWindowListener(new WindowAdapter() {
		        @Override
				public void windowClosing(WindowEvent e) {
		        	panel.ctrl.saveData(true);
		        }     
		   });
		this.setVisible(true);
	}
}
