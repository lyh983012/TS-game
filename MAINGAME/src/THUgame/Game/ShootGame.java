package THUgame.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;

/* 
 * 【弹幕游戏的panel】
 * 
 * --DIALOG--
 * version 1.1
 * via 林逸晗
 * update:20191018 00:59
 * 更新：修正了一个bug
 * 
 *  * --DIALOG--
 * version 1.0
 * via 林逸晗
 * update:20191015 00:59
 * 更新：实现了他
 * 
 **/

public class ShootGame extends JPanel {
	 
	/*用法同Win里面的,为方便操作，在这里就直接修改dataPackage属性*/
	static public EventManager mainGame;
	static public DataPack dataPackage;
	
	/*游戏用的*/
    private int score=0;
    private int timeLeft=300;
    private int count;
    private int delay;
    private int Ex,Ey;
    private int Hx,Hy;
    private int Ew,Eh;
    private int Hw,Hh;
    private Timer timer;
    private ArrayList<Bullet> Bullets;


    class TimerListener implements ActionListener {
        /** Handle ActionEvent */
        public void actionPerformed(ActionEvent e) {
        	if (timeLeft>=0 && started) {
        		repaint();
        	}
        }
    }

	
    /*敌人生成器*/
    class Bullet extends JButton{
		
		boolean correct;
		int dx,dy;
		
	
		public Bullet(){
			Random r = new Random();
			dx = r.nextInt(8) - 4;
			dy = r.nextInt(3) + 1;
			
			int a = r.nextInt(10) - 4;
			int b = r.nextInt(10) + 1;
			int answer;
			int tub=r.nextInt(10) - 10;
			if(r.nextInt(2)==1)
				this.correct=true;
			else
				this.correct=false;
			switch(r.nextInt(4)) {
				case 0:
					answer=a+b;
					if(!this.correct) 
						answer+=tub;
					this.setText(String.valueOf(a)+"+"+String.valueOf(b)+"="+String.valueOf(answer));
					break;
				case 1:
					answer=a-b;
					if(!this.correct) 
						answer+=tub;
					this.setText(String.valueOf(a)+"-"+String.valueOf(b)+"="+String.valueOf(answer));
					break;
				case 2:
					answer=a*b;
					if(!this.correct) 
						answer+=tub;
					this.setText(String.valueOf(a)+"*"+String.valueOf(b)+"="+String.valueOf(answer));
					break;
				case 3:
					double answer2=a/b;
					if(!this.correct) 
						answer2+=tub;
					this.setText(String.valueOf(a)+"/"+String.valueOf(b)+"="+String.valueOf(answer2));
					break;
			}
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
    			this.dataPackage.characterIQ+=score/10;//在这里改属性
    			this.dataPackage.notification="<html>老师随机点你完成了一个非常负责的任务，你硬着头皮完成了这个任务";
    			this.dataPackage.notification += "<br>在这个珍贵的机会之下，你的智力值发生了"+String.valueOf(score/10)+"的变化</html>";
	    		//System.out.println("end");
	   			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	   			synchronized(mainGame) {
	   				this.mainGame.notify();
	   			}//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
   			}
   		}

    /*初始界面*/
    public ShootGame(int x,int y) {
    	
    	this.setBounds(x, y, 540, 350);
    	this.setLayout(null);
    	this.setOpaque(false);//注意要设成透明的
    	this.delay=20;
        this.HeroNewButton = new JButton("开始课堂小游戏");
        this.HeroNewButton.setBounds(245, 266, 100, 20);
        this.HeroNewButton.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			InitGame();
    			}
    		});
        JTextPane txtpnshi = new JTextPane();
		txtpnshi.setBounds(175, 100, 220, 200);
		txtpnshi.setOpaque(false);
		txtpnshi.setEditable(false);
		
		EventPanel = new JPanel();
		EventPanel.setOpaque(false);//注意要设成透明的
		EventPanel.setBounds(0, 0, 540, 350);
		EventPanel.setLayout(null);
        EventPanel.add(HeroNewButton);
		EventPanel.add(txtpnshi);
		
		JPanel EventBackgound = new ImagePanel("imgsrc//shootGame/eb.png",0, 0, 540, 350);	
		EventBackgound.setBounds(0, 0, 540, 350);
		EventBackgound.setOpaque(false);//注意要设成透明的
		EventBackgound.setLayout(null);
		
		this.add(EventPanel);
		this.add(EventBackgound);
		txtpnshi.setText("你触发了课堂小测游戏！游戏规则：控制你的人物接受正确的表达式，正确得1分，错误减2分，总得分影响智力增加");
		txtpnshi.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	    
    }
    
    /*初始化*/
    public void InitGame() {
    	
    	EventPanel.removeAll();
    	Bullets=new ArrayList<Bullet>();
        
    	timeLeft=200;
    	Ew=100;
        Eh=20;
		Hw=40;
		Hh=40;
        HeroNewButton = new JButton("你");
		HeroNewButton.setBounds(260, 266, Hw, Hh);
		
		Teacher = new JButton();
		Teacher.setBounds(260, 10, Hw, Hh);
		Teacher.setBorderPainted(false);
		Teacher.setContentAreaFilled(false);
		setIcon("/imgsrc/shootGame/teacher1.png",Teacher);
		setSelectedIcon("/imgsrc/shootGame/teacher1.png",Teacher);

        scoreShow=new JLabel("你的得分："+String.valueOf(score));
        scoreShow.setBounds(50,40,100,20);
        scoreTime=new JLabel("剩余时间："+String.valueOf(timeLeft/10)+"."+String.valueOf(timeLeft%10)+"s");
        scoreTime.setBounds(50,60,100,20);
        EventPanel.add(new listenerPanel());
		EventPanel.add(Teacher);
		EventPanel.add(HeroNewButton);
        EventPanel.add(scoreShow);
        EventPanel.add(scoreTime);
		EventPanel.setVisible(true);
        
	    timer = new Timer(1,new TimerListener());
	    timer.setInitialDelay(500);
	    timer.start();
	    repaint();
    	started=true;
    }
    
    public void endGame() {

    	EventPanel.removeAll();
    	JTextPane txtpnshi = new JTextPane();
		txtpnshi.setBounds(175, 100, 200, 200);
		txtpnshi.setOpaque(false);
		txtpnshi.setEditable(false);
		txtpnshi.setText("小测结束，你的得分："+String.valueOf(score)+"在这个珍贵的机会之中，你的智力值发生了"+String.valueOf(score/10)+"点的变化");
		txtpnshi.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		HeroNewButton = new JButton("结束");
        HeroNewButton.setBounds(245, 266, 100, 20);
        HeroNewButton.addActionListener(new EnderListener(dataPackage,mainGame));
        EventPanel.add(HeroNewButton);
		EventPanel.add(txtpnshi);
    }
    	  
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	boolean refreshTeacher=false;
    	/************************************
    	 * 我发现在这里写延时比较靠谱
    	 ***********************************/
    	try {
			Thread.sleep(this.delay);
	    	count+=1;
	    	if(count==100/delay) {
	    		this.timeLeft-=1;
	    		refreshTeacher=true;
	    		count=0;    	
	    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	/************************************
    	 * 时间耗尽就停止绘图更新，否则按逻辑更新
    	 ***********************************/
    	if(this.timeLeft==0) {
        	this.endGame();
        	this.timer.stop();
        	this.started=false;
        }else if(this.started){
        	if(refreshTeacher) {
        		Random r = new Random();
        		int randomSnore = r.nextInt(3) + 1;  // 生成被吵醒的随机数
        		setIcon("/imgsrc/shootGame/teacher"+String.valueOf(randomSnore)+".png",Teacher);
        		setSelectedIcon("/imgsrc/shootGame/teacher"+String.valueOf(randomSnore)+".png",Teacher);
        	}
			this.HeroNewButton.setBounds(Hx,Hy,Hw,Hh);
			/************************************
	    	 * 判断是否加入新的子弹
	    	 ***********************************/
			Random r = new Random();
			int randomValue = r.nextInt(100);
	        if(Bullets.size()<8 && randomValue<20) {
	        	Bullet newBullet=new Bullet();
	        	Bullets.add(newBullet);
	        	EventPanel.add(newBullet);
	        	newBullet.setBounds(250,10+Hh,Ew,Eh);
	        }
	        
	        /************************************
	    	 * 便利子弹判断是否撞击／出界
	    	 ***********************************/
	        Iterator<Bullet> iter = Bullets.iterator();
	        while (iter.hasNext()) {
	        	Bullet word = iter.next();
	        	Ex=word.getX();
	        	Ey=word.getY();
	        	if(checkHit(Ex,Ey,Hx,Hy)) {
	        		if(word.correct==true) {
	        			score+=1;
	        		}else {
	        			score-=2;
	        		}
	        		EventPanel.remove(word);
	        		iter.remove();
	            }else {
	            	word.setBounds(Ex+word.dx,Ey+word.dy,Ew,Eh);
	            	if(Ex>540 || Ex<-80 || Ey>340) {
	            		EventPanel.remove(word);
	            		iter.remove();
	            	}
	            }
	        	scoreShow.setText("你的得分："+String.valueOf(score));
	            scoreTime.setText("剩余时间："+String.valueOf(timeLeft/10)+"."+String.valueOf(timeLeft%10)+"s");
	        }
        }
    }
    
    private boolean checkHit(int Ex,int Ey,int Hx,int Hy) {
    	if( Ex<Hx && Ex+Ew<Hx )
    		return false;
    	if( Hx<Ex && Hx+Hw<Ex)
    		return false;
    	if( Ey<Hy && Ey+Eh<Hy )
    		return false;
    	if( Hy<Ey && Hy+Hh<Ey)
    		return false;
    	
    	return true;
    		
    }

    private class listenerPanel extends JPanel implements MouseListener,MouseMotionListener{
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		listenerPanel(){
    		this.setOpaque(false);//注意要设成透明的
    		this.setBounds(0, 0, 540, 350);
    		this.setLayout(null);
            addMouseListener(this);   
            addMouseMotionListener(this); 
    	}
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			Hx=e.getX()-Hw/2;
	        Hy=e.getY()-Hh/2;
			
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
  
    private class Bullet extends JTextField{
		private static final long serialVersionUID = 1L;
		boolean correct;
		int dx,dy;
		public Bullet(){
			this.setBackground(new Color(240, 240, 240));
			this.setBorder(new LineBorder(new Color(0, 0, 0), 1));
			this.setHorizontalAlignment(SwingConstants.CENTER);
			Random r = new Random();
			dx = r.nextInt(8) - 4;
			dy = r.nextInt(3) + 1;
			
			int a = r.nextInt(10) +1;
			int b = r.nextInt(10) +1;
			int answer;
			int tub=r.nextInt(10) - 10;
			if(r.nextInt(2)==1)
				this.correct=true;
			else
				this.correct=false;
			switch(r.nextInt(4)) {
				case 0:
					answer=a+b;
					if(!this.correct) 
						answer+=tub;
					this.setText(String.valueOf(a)+"+"+String.valueOf(b)+"="+String.valueOf(answer));
					break;
				case 1:
					answer=a-b;
					if(!this.correct) 
						answer+=tub;
					this.setText(String.valueOf(a)+"-"+String.valueOf(b)+"="+String.valueOf(answer));
					break;
				case 2:
					answer=a*b;
					if(!this.correct) 
						answer+=tub;
					this.setText(String.valueOf(a)+"*"+String.valueOf(b)+"="+String.valueOf(answer));
					break;
				case 3:
					answer=a*b;
					if(!this.correct) 
						b+=(double)tub;
					this.setText(String.valueOf(answer)+"/"+String.valueOf(a)+"="+String.valueOf(b));
					break;
			}
		}
	}

    private class EnderListener implements ActionListener{
    	
    	public EventManager mainGame;
    	public DataPack dataPackage;
    	
    	public EnderListener(DataPack dataPackage, EventManager mainGame){
    		this.mainGame=mainGame;
    		this.dataPackage=dataPackage;
    	}
    		@Override
    	public void actionPerformed(ActionEvent e) {
    			this.dataPackage.trigSubEvent=false;
    			this.dataPackage.characterIQ+=score/10;//在这里改属性
    			this.dataPackage.notification="<html>老师随机点你完成了一个非常负责的任务，你硬着头皮完成了这个任务";
    			this.dataPackage.notification += "<br>在这个珍贵的机会之中，你的智力值发生了"+String.valueOf(score/10)+"点的变化</html>";
	   			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	   			synchronized(mainGame) {
	   				this.mainGame.notify();
	   			}//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
   			}
   		}
    
    private class TimerListener implements ActionListener {
        /** Handle ActionEvent */
        public void actionPerformed(ActionEvent e) {
        	if (timeLeft>=0 && started) {
        		repaint();
        	}
        }
    }
	
	private void setIcon(String file,JButton com){ 
        ImageIcon ico=new ImageIcon(getClass().getResource(file)); 
        ico.getImage();
		Image temp=ico.getImage().getScaledInstance(com.getWidth(),com.getHeight(),Image.SCALE_DEFAULT);
        ico=new ImageIcon(temp); 
        com.setIcon(ico); 
    } 
	
	private void setSelectedIcon(String file,JButton com){ 
        ImageIcon ico=new ImageIcon(getClass().getResource(file)); 
        ico.getImage();
		Image temp=ico.getImage().getScaledInstance(com.getWidth(),com.getHeight(),Image.SCALE_DEFAULT);
        ico=new ImageIcon(temp); 
        com.setPressedIcon(ico); 
    } 
}


