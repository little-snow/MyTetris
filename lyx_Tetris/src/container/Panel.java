package container;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import body.Body;
import body.BodyGrade;
import body.BodyMain;
import body.BodyNext;
import body.BodyRanking;
import body.BodyScore;
import control.CenterControl;
import control.Logic;
import control.PlayerControl;
import data.Data;

public class Panel extends JPanel {
	private Image background;
	private Icon start;
	private Icon parse;
	private JButton start_parse;
	private JButton exit;
	Data data;
	Body[] bodys;
	CenterControl ctrl;
	PlayerControl player;
	
	public Panel() {
		// ����Ϊ���ɲ���
		this.setLayout(null);
		//��ʼ��������
		this.initial();
	}
	//��ʼ��������
	
	private void initial(){
		this.data = new Data();
		this.background = new ImageIcon("images/background/background.png").getImage();
		
		//��this��Ϊ�¼�Դ����ҿ�����Ϊ������ ��ֱ�ӿ���CenterControl��������ӿ���this CenterControl ����panel����Ϸ�߼���
		this.ctrl = new CenterControl(this, new Logic(data));
		this.player = new PlayerControl(ctrl);
		
		this.addKeyListener(player);
		bodys = new Body[] { new BodyMain(data), 
				new BodyNext(), 
				new BodyGrade(), 
				new BodyScore(), 
				new BodyRanking() 
		};
		
		/*
		 * ��ʼ����ť
		 */
		//���ð�ť����ͼ
		start = new ImageIcon("images/string/start.png");
		parse = new ImageIcon("images/string/parse.png");
		this.start_parse = new JButton(start);
		this.exit = new JButton(new ImageIcon("images/string/exit.png"));
		//���ð�ťλ��
		this.start_parse.setBounds(390,33,80,35);
		this.exit.setBounds(475, 33,80,35);
		//����͸������
		this.start_parse.setContentAreaFilled(false);
		this.exit.setContentAreaFilled(false);
		//�����ޱ߿�
		this.start_parse.setBorderPainted(false);
		this.exit.setBorderPainted(false);
		//��װ�¼�����
		this.start_parse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
					ctrl.startOrParse();
				if(Data.fail)
					ctrl.restart();
			}
		});
		
		this.exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.saveData(true);
			}
		});

		//��װ��ť
		this.add(start_parse);
		this.add(exit);
	}
	
	public void setStartButton(boolean state){
		if(Data.start)  {
			start_parse.setIcon(parse);
			return;
		} 
		else start_parse.setIcon(start);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// ���ڲ�ǰ�ڴ������
		super.paintComponent(g);
		// ˢ�»���
		g.drawImage(background, 0, 0, null);

		for (Body i : bodys) {
			i.paint(g);
		}
		// ��ȡ����
		this.requestFocus();
	}
}
