//created by Jiabei 2019.10.18
//modified by lyh 2019.10.28
/* 
 * update:20191030
 * via：林逸晗
 * 更新：加入safeGuardCount For button
 */

package THUgame.Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;


public class RememberGame extends JPanel{
	
	/*用法同Win里面的,为方便操作，在这里就直接修改dataPackage属性*/
	static public EventManager mainGame;
	static public DataPack dataPackage;
	/*游戏用的*/
    private JButton HeroNewButton;
    private int delay;
    private int score;
    private int buttonsize;
    static private ArrayList<Button> Buttons;
	static private ArrayList<Integer> Answer;
	static private ArrayList<Integer> RightAnswer;
	static private boolean GameVictory;
	static private boolean GameLose;
	static private boolean flag;
	static private boolean waitforplayerdelay;
	static private boolean waitforplayer;
	
	private JLabel scoreShow;
    private JLabel scoreTime;
    private JLabel ready;
    private JPanel mask;
	private Timer timer;
	private int length;
	private boolean RememStart;
	private int turn;
	private int Maxturn=3;

	JPanel EventPanel;

    class TimerListener implements ActionListener {
        /** Handle ActionEvent */
        @Override
		public void actionPerformed(ActionEvent e) {
        		repaint();
        	}
        }
    
	class Button extends JButton implements MouseListener{
		private int ID;
		
		public Button(int id)
		{
			this.ID = id;
			
		}
		
		private void changeColor(Color color)
		{
			this.setForeground(color);
			
			return;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if(RememStart ==true) {
				Answer.add(this.ID);
				//this.setForeground(Color.red);
				if(Answer.size()<RightAnswer.size()) {
					for(int i = 0;i<Answer.size();i++) {
						if(!Objects.equals(Answer.get(i), RightAnswer.get(i))) 
						{
							GameLose = true;
							return;
						}
					}
				}
				else if(Answer.size() == RightAnswer.size()) {
					for(int i = 0;i<Answer.size();i++) {
						if(Answer.get(i)!=RightAnswer.get(i)) {
							GameLose = true;
							return;
						}
					}
					GameVictory = true;
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
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
    			this.dataPackage.choiceA="";
    			this.dataPackage.stateA="";
    			this.dataPackage.characterIQ+=score*2/10;//在这里改属性
    			this.dataPackage.time+=3;
    			this.dataPackage.studyProgress+=2;
    			this.dataPackage.notification = "<html>我发现作业非常困难，沉迷其中，过去了6个小时";
    			this.dataPackage.notification += "<br>学习进度+1，心情值-1，体力消耗5点";
    			this.dataPackage.notification += "<br>漫长的五小时后，我可能错过了一些事情，但是<br>沉浸在知识海洋里让我的智力值发生了"+String.valueOf(score*2/10)+"点的变化</html>";
	   			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	   			synchronized(mainGame) {
	   				this.mainGame.notify();
	   			}//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
   			}
   		}
  
    public RememberGame(int x,int y) {
    	this.setBounds(x, y, 540, 350);
    	this.setLayout(null);
    	this.setOpaque(false);//注意要设成透明的
    	this.delay=80;
    	turn=Maxturn;
    	RememberGame.RightAnswer = new ArrayList<Integer>();
    	RememberGame.Answer =new ArrayList<Integer>();
    	RememberGame.Buttons=new ArrayList<Button>();
    	
		EventPanel = new JPanel();
		EventPanel.setOpaque(false);//注意要设成透明的
		EventPanel.setBounds(0, 0, 540, 350);
		EventPanel.setLayout(null);
		
    	this.HeroNewButton = new JButton("开始");
    	this.HeroNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 18));
        this.HeroNewButton.setBounds(175, 266, 80, 50);
        this.HeroNewButton.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			flag = true;
    			InitGame();
    			timer = new Timer(delay,new TimerListener());
    	    	timer.setInitialDelay(500);
    	    	timer.start();
    			}
    		});
        EventPanel.add(HeroNewButton);
        
        JButton backbutton = new JButton("放弃");
        backbutton.setFont(new Font("Lucida Grande", Font.BOLD, 18));
        backbutton.setBounds(285, 266, 80, 50);
        backbutton.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			dataPackage.trigSubEvent=false;
    			dataPackage.choiceA="";
    			dataPackage.notification = "我发现作业非常困难，于是我不写作业了";
	   			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	   			synchronized(mainGame) {
	   				mainGame.notify();
	   			}//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
    		}
    	});
        EventPanel.add(backbutton);
        
        JTextPane txtpnshi = new JTextPane();
		txtpnshi.setBounds(150, 100, 260, 140);
		txtpnshi.setOpaque(false);
		txtpnshi.setEditable(false);
		txtpnshi.setText("这一次作业十分困难，需要完成这个任务！规则：记忆点击顺序，按顺序点击对应的方块，一共有"+Maxturn+"轮。但是相应地有更多的奖励");
		txtpnshi.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		EventPanel.add(txtpnshi);
		
		JPanel EventBackgound = new ImagePanel("imgsrc//shootGame/eb.png",0, 0, 540, 350);	
		EventBackgound.setBounds(0, 0, 540, 350);
		EventBackgound.setOpaque(false);//注意要设成透明的
		EventBackgound.setLayout(null);
		
		this.add(EventPanel);
		this.add(EventBackgound);
    }
    
    public void InitGame() {	
    	if(turn==Maxturn) {//第一次初始化
    		scoreShow=new JLabel("游戏剩余"+turn+"轮");
    	    scoreShow.setBounds(50,40,100,20);
    	    scoreTime=new JLabel("当前得分：0");
    	    scoreTime.setBounds(50,60,100,20);
    	    ready=new JLabel("准备好记忆方块的顺序！");
    	    ready.setBounds(190,80,200,20);
	    	buttonsize = 60;
	    	EventPanel.removeAll();
	    	EventPanel.add(scoreShow);
	        EventPanel.add(scoreTime);
	        EventPanel.add(ready);
	    	for(int i = 0;i<3;i++)
	    	{
	    		for(int j = 0;j<3;j++)
	    		{
	    			Button B = new Button(i*3+j+1);
	    			RememberGame.Buttons.add(B);
	        		EventPanel.add(B);
	        		B.setText(String.valueOf(i*3+j+1));
	        		B.setFont(new Font("Lucida Grande", Font.BOLD, 18));
	        		B.setBounds(175+j*buttonsize,130+i*buttonsize,buttonsize,buttonsize);
	        		B.setForeground(Color.black);
	        		B.addMouseListener(B);
	    		}
	
	    	}
    	}
    	//每轮的初始化。生成随机序列
 
    	this.RememStart = false;
    	RememberGame.GameVictory = false;
    	RememberGame.GameLose = false;
    	Random r = new Random();
    	this.length = r.nextInt(2)+5;
    	Buttons.get(0).getForeground();
		EventPanel.setVisible(true);
    	EventPanel.repaint();
    	RememberGame.RightAnswer = new ArrayList<Integer>();
    	RememberGame.Answer =new ArrayList<Integer>();
    	waitforplayer=true;
    	waitforplayerdelay=false;
    }
    
    @Override
	public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	/*给人反应的延时，逻辑比较繁琐。。*/
    	if(waitforplayer==true) {
			if(waitforplayerdelay)
	    		try {
	    			Thread.sleep(1500);
	    			waitforplayerdelay=false;
	    			waitforplayer=false;
	    	    	EventPanel.remove(ready);
	    	    	return;
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    			return;
	    		}
			EventPanel.add(ready);
			waitforplayerdelay=true;
			return;
    	}
    	/*  显示  */
    	if(flag == true){
	    	Random r = new Random();
	    	if(this.RememStart  == false) {
	    		try {
					Thread.sleep(400);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        	if(RememberGame.RightAnswer.size()<=this.length)
	        	{	
	        		int tempbutton;
	        		if(RememberGame.RightAnswer.size()==0){
	        			tempbutton = r.nextInt(9);
	        			RememberGame.Buttons.get(tempbutton).changeColor(Color.red);
	        			tempbutton++;
	        			RememberGame.RightAnswer.add(tempbutton);
	        		}
	        		else {
	        			tempbutton = r.nextInt(9);
	        			if(tempbutton == RightAnswer.get(RightAnswer.size()-1)-1) {
	        				tempbutton = (tempbutton+4)%9;
	        			}
	        			RememberGame.Buttons.get(RightAnswer.get(RightAnswer.size()-1)-1).changeColor(Color.black);
	        			RememberGame.Buttons.get(tempbutton).changeColor(Color.red);
	        			tempbutton++;
	        			RememberGame.RightAnswer.add(tempbutton);
	        		}
	        	}
	        	else{
	        		RememberGame.Buttons.get(RightAnswer.get(RightAnswer.size()-1)-1).changeColor(Color.black);
	        		this.RememStart = true;
	        	}
	    	}
	    	
	    	/*点击刷新*/
	    	if(this.turn>0 && (GameVictory||GameLose)) {
	    		this.turn-=1;
		    	if(GameLose)
		    	{
		    		this.score=this.score-4;
		    		scoreShow.setText("第"+(Maxturn-turn)+"轮你输了");
		            scoreTime.setText("当前得分为:"+this.score);

		    	}
		    	else if(GameVictory) {
		    		this.score=this.score+7;
		    		scoreShow.setText("第"+(Maxturn-turn)+"轮你赢了");
		            scoreTime.setText("当前得分为:"+this.score);
		    	}
	    		this.InitGame();
	    	}
	    	else if(this.turn<=0) {
	            this.endGame();
	           	this.timer.stop();
	    	}
    	}
    	
    }
	
    public void endGame() {
    	flag=false;
    	this.timer.stop();
    	EventPanel.removeAll();
    	JTextPane txtpnshi = new JTextPane();
		txtpnshi.setBounds(175, 100, 200, 200);
		txtpnshi.setOpaque(false);
		txtpnshi.setEditable(false);
		txtpnshi.setText("自习结束，你的得分："+String.valueOf(score)+"在这个珍贵的机会之中，你的智力值发生了"+String.valueOf(score/10)+"点的变化");
		txtpnshi.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		HeroNewButton = new JButton("结束");
        HeroNewButton.setBounds(245, 266, 100, 20);
        HeroNewButton.addActionListener(new EnderListener(dataPackage,mainGame));
        EventPanel.add(HeroNewButton);
		EventPanel.add(txtpnshi);
    }

    
}