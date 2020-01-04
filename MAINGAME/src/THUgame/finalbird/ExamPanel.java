package THUgame.finalbird;
import javax.swing.*;


import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinExam;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class ExamPanel extends JPanel implements Runnable {
	
    private Game game;
    private JButton StartButton;
	static public EventManager mainGame;
	static public DataPack dataPackage;
    static boolean start = false;
    static JLabel dialogContent;
    
    public ExamPanel(EventManager mainGame,DataPack dataPackage) {

    	this.setLayout(null);
    	this.mainGame=mainGame;
    	this.dataPackage=dataPackage;
        game = new Game(this,mainGame,dataPackage);
       
        JPanel dialogPack = new JPanel();
		dialogPack.setBounds(66, 510, 689, 189);
		dialogPack.setOpaque(false);//注意要设成透明的
		dialogPack.setLayout(null);
		
			JPanel dialogPanel = new JPanel();//第1个subPanel，放控件
			dialogPanel.setBounds(0, 0, 689, 189);//(0, 0, 宽, 高);
			
			JPanel dialogBackgoundPanel = new ImagePanel("imgsrc//Dialog.png",0, 0, 689, 189);	//第2个subPanel，放图
																								//(0, 0, 宽, 高);
			dialogBackgoundPanel.setBounds(0, 0, 689, 189);//(0, 0, 宽, 高);
			dialogBackgoundPanel.setOpaque(false);//注意要设成透明的
			dialogPanel.setOpaque(false);		//注意要设成透明的
			dialogPanel.setLayout(null);
			
			JLabel dialogName = new JLabel();
			dialogName.setBounds(17, 6, 89, 40);
			dialogPanel.add(dialogName);
			dialogName.setText(dataPackage.name);
			
			dialogContent = new JLabel();
			dialogContent.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setBounds(15, 42, 677, 141);
			dialogPanel.add(dialogContent);
		
		dialogPack.add(dialogPanel);		//注意：先放的在上层，所以先放带控件的
		dialogPack.add(dialogBackgoundPanel);
		this.add(dialogPack);
		this.StartButton = new JButton("开始考试");
    	this.StartButton.setFont(new Font("TimesRoman", Font.BOLD, 24));
        this.StartButton.setBounds(900, 600, 120, 70);
        this.StartButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e) {
    				Init();
    				remove(StartButton);
    				 String notifi="<html>";
    			      	notifi+="再按一次空格开始考试！";
    			      	dialogContent.setText(notifi);
    					dialogContent.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
    			}
    		});
        String notifi="";
      	notifi+="<html>期末考试到了！大清的考试总是难上加难。<br>"
    			+ "一个学期的学习进度最终反映在了考试的难度上，希望我能正常发挥！"
    			+ "<br>规则：按space一次控制小鸟向上飞一段距离";
      	dialogContent.setText(notifi);
        this.add(StartButton);
        this.repaint();
        update();
        
    }

    public void update() {
        game.update();
        repaint();
    }

    protected void paintComponent(Graphics g) {
    	if(game.gameover&& Game.revive <=0) {
    		
    	}
    	
        super.paintComponent(g);
        String notifi="<html>";
        Graphics2D g2D = (Graphics2D) g;
        for (Render r : game.getRenders())
            if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);
        g2D.setColor(Color.BLACK);
        if (game.started) {
            notifi+="当前的已经取得的分数为 "+Game.score+"/100<br>";
			notifi+="由于学习认真，奖励的复活次数为"+Integer.toString(game.revive);
			dialogContent.setText(notifi);
        }

        if (game.gameover) {
        	if(game.revive>0)
        	{
        		notifi+="<br>按R再来一次";
        		dialogContent.setText(notifi);
        	}
        	else
        	{
        		if(Game.score<60) {
        			dialogContent.setText("你挂科了，等待你的将是审判");
        			dataPackage.choiceA="Fail";
        		}
        		else {
        			dialogContent.setText("你在在期末考试中获得了:【   "+Integer.toString(Game.score*2>100?100:Game.score*2)+" 】分<br>");
        		}
        		StartButton.setText("回宿舍");
        		StartButton.setFont(new Font("TimesRoman", Font.BOLD, 24));
        		this.add(StartButton);
        		StartButton.addActionListener(new EnderListener(Game.dataPackage,Game.mainGame));
        		//notifi+="<br>考试结束了";
        	}
        	}
       
    	
    }

    public void run() {
    	/*
    	 * 这里是设置游戏开始界面时的一些提示，需要加一个按钮进入游戏。
    	 * 进入游戏的方法就是把start设为true
    	 */
		
        try {
            while (start) {
                update();
                Thread.sleep(15);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

	private void Init() {
		start = true;
		this.requestFocus();
		new Thread(this).start();
	}
	
    class EnderListener implements ActionListener{
    	
    	public EventManager mainGame;
    	public DataPack dataPackage;
    	public EnderListener(DataPack dataPackage, EventManager mainGame){
    		this.mainGame=mainGame;
    		this.dataPackage=dataPackage;
    	}
    		@Override
    	public void actionPerformed(ActionEvent e) {
    			this.dataPackage.trigSubEvent=false;
    			this.dataPackage.stateA="";
    			this.dataPackage.notification = "<html>";
    			this.dataPackage.notification += "<br>";
	   			synchronized(mainGame) {
	   				this.mainGame.notify();
	   			}//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
   			}
    }
	
}
