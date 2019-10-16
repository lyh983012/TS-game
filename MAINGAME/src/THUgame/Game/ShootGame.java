package THUgame.Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;

public class ShootGame extends JPanel implements MouseListener,MouseMotionListener{
	 
	/*用法同Win里面的,为方便操作，在这里就直接修改dataPackage属性*/
	static public EventManager mainGame;
	static public DataPack dataPackage;
	
	/*游戏用的*/
    private JButton HeroNewButton;
    private int score=0;
    private int timeLeft=300;
    private int count;
    private int delay;
    private int Ex,Ey;
    private int Hx,Hy;
    private int Ew,Eh;
    private int Hw,Hh;
    private boolean started=false;
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
    			this.dataPackage.characterIQ+=score*10;//在这里改属性
	    		System.out.println("end");
	   			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	   			synchronized(mainGame) {
	   				this.mainGame.notify();
	   			}//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
   			}
   		}
    /*初始界面*/
    public ShootGame(int delay) {

    	this.delay=delay;
 
        this.HeroNewButton = new JButton("开始课堂小游戏");
        this.HeroNewButton.setBounds(245, 266, 100, 20);
        this.HeroNewButton.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			InitGame();
    			}
    		});
        this.add(HeroNewButton);
        
        JTextPane txtpnshi = new JTextPane();
		txtpnshi.setBounds(175, 100, 220, 70);
		txtpnshi.setOpaque(false);
		txtpnshi.setEditable(false);
		this.add(txtpnshi);
		txtpnshi.setText("课堂游戏规则：控制你的人物接受正确的表达式，正确得1分，错误减2分，总得分影响智力增加");
        
	    
    }

    /*初始化*/
    public void InitGame() {
    	this.removeAll();
    	
    	
    	this.Bullets=new ArrayList<Bullet>();
        addMouseListener(this);   
        addMouseMotionListener(this); 
        
    	timeLeft=200;
    	Ew=100;
        Eh=20;
		Hw=40;
		Hh=40;
        
        this.HeroNewButton = new JButton("你");
		this.HeroNewButton.setBounds(268-Hw/2, 266, Hw, Hh);
		
		JButton HeroNewButton2 = new JButton("老师");
		HeroNewButton2.setBounds(268-Hw/2, 10, Hw, Hh);
		this.add(HeroNewButton2);
		this.add(HeroNewButton);
		
		this.setVisible(true);
	    this.timer = new Timer(delay,new TimerListener());
	    this.timer.setInitialDelay(1000);
	    this.timer.start();

	    this.repaint();
    	this.started=true;
    }
    
    /*
     * 重要！这里需要通过传进来的
     * 	static public EventManager mainGame;
		static public DataPack dataPackage;
		实现下一步选择。
     */
    public void endGame() {
		this.removeAll();
    	JTextPane txtpnshi = new JTextPane();
		txtpnshi.setBounds(175, 100, 200, 50);
		txtpnshi.setOpaque(false);
		txtpnshi.setEditable(false);
		this.add(txtpnshi);
		txtpnshi.setText("游戏结束，你的得分："+String.valueOf(score));

		this.HeroNewButton = new JButton("结束");
        this.HeroNewButton.setBounds(245, 266, 100, 20);
        this.HeroNewButton.addActionListener(new EnderListener(dataPackage,mainGame));
        this.add(HeroNewButton);
    }
    	  
    
    /*
     * repaint会调用这个接口，利用它进行界面逻辑的设置
     */
    public void paintComponent(Graphics g) {
    	/************************************
    	 * 我发现在这里写延时比较靠谱
    	 ***********************************/
    	
    	try {
			Thread.sleep(this.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	/************************************
    	 * 计数器和计时器
    	 ***********************************/
    	count+=1;
    	if(count==100/delay) {
    		this.timeLeft-=1;
    		count=0;
    	}
    	
    	/************************************
    	 * 时间耗尽就停止绘图更新，否则按逻辑更新
    	 ***********************************/
    	if(this.timeLeft==0) {
        	this.endGame();
        	this.timer.stop();
        	this.started=false;
        	super.paintComponent(g);
        	
        }else if(this.started){
        	super.paintComponent(g);
			this.HeroNewButton.setBounds(Hx,Hy,Hw,Hh);
			/************************************
	    	 * 判断是否加入新的子弹
	    	 ***********************************/
			Random r = new Random();
			int randomValue = r.nextInt(100);
	        if(Bullets.size()<8 && randomValue<20) {
	        	Bullet newBullet=new Bullet();
	        	Bullets.add(newBullet);
	        	this.add(newBullet);
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
	        		this.remove(word);
	        		iter.remove();
	            }else {
	            	word.setBounds(Ex+word.dx,Ey+word.dy,Ew,Eh);
	            	if(Ex>536 || Ex<0 || Ey>398) {
	            		this.remove(word);
	            		iter.remove();
	            	}
	            }
	        }
	        g.drawString("你的得分："+String.valueOf(score),50,40);
	        g.drawString("剩余时间："+String.valueOf(this.timeLeft/10)+"."+String.valueOf(this.timeLeft%10)+"s",50,60);
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


