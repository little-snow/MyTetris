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
		// 设置为自由布局
		this.setLayout(null);
		//初始化面板对象
		this.initial();
	}
	//初始化面板对象
	
	private void initial(){
		this.data = new Data();
		this.background = new ImageIcon("images/background/background.png").getImage();
		
		//将this作为事件源，玩家控制作为监听器 并直接控制CenterControl，进而间接控制this CenterControl 控制panel和游戏逻辑块
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
		 * 初始化按钮
		 */
		//设置按钮背景图
		start = new ImageIcon("images/string/start.png");
		parse = new ImageIcon("images/string/parse.png");
		this.start_parse = new JButton(start);
		this.exit = new JButton(new ImageIcon("images/string/exit.png"));
		//设置按钮位置
		this.start_parse.setBounds(390,33,80,35);
		this.exit.setBounds(475, 33,80,35);
		//设置透明背景
		this.start_parse.setContentAreaFilled(false);
		this.exit.setContentAreaFilled(false);
		//设置无边框
		this.start_parse.setBorderPainted(false);
		this.exit.setBorderPainted(false);
		//安装事件监听
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

		//安装按钮
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
		// 将内部前期处理加上
		super.paintComponent(g);
		// 刷新画面
		g.drawImage(background, 0, 0, null);

		for (Body i : bodys) {
			i.paint(g);
		}
		// 获取焦点
		this.requestFocus();
	}
}
