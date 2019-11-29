package THUgame.subwindows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.Timer;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinBase;


public class WinEndAnimate extends WinBase{
	Timer timer = null;
	public WinEndAnimate(EventManager mainGame,JFrame frame) {
		
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.mainGame=mainGame;
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥

		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBackground(Color.WHITE);
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);

		JPanel Background=new ImagePanel("imgsrc//WinEndAnimate//3.jpg",0, 0, 1080, 720);
		Background.setBounds(0, 0, 1080, 720);
		backgroundPanel.add(Background);
		Background.setLayout(null);
		
		timer=new Timer(200,new ActionListener()
    	{
			int count=3;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(count<=26) {
					frame.getContentPane().removeAll();
					JPanel bc = new ImagePanel("imgsrc//WinEndAnimate//"+(count)+".jpg",0, 0, 1080, 720);	
					bc.setBounds(0, 0, 1080, 720);
					frame.getContentPane().add(bc);
					frame.getContentPane().repaint();
				}
				if(count==26) {
					timer.stop();
					dataPackage.eventFinished=true;
					synchronized(mainGame) {
						mainGame.notify();
					}
				}
				count+=1;
			}
    	});
		timer.start();

		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.getContentPane().removeAll();
		frame.getContentPane().add(backgroundPanel);
		frame.getContentPane().repaint();
    	frame.setVisible(true);
    	//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	}
}