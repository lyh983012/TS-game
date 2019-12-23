package THUgame.tool;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;

/* 
 * 【游戏panel模板】
 * 
 * --DIALOG--
 * version 1.0
 * via 林逸晗
 * update:20191016 00:59
 * 更新：实现了它
 * 
 **/

public class GamePanel extends JPanel implements MouseListener,MouseMotionListener{
    /*
     * 重要！这里需要通过传进来的
     * 	static public EventManager mainGame;
		static public DataPack dataPackage;
		实现下一步选择和底层唤醒。
     */
	static public EventManager mainGame;
	static public DataPack dataPackage;
	
	/*游戏用的，随便加，不要减*/
    private JButton startButton;
    private int score=0;
    private int timeLeft=300;
    private int count;
    private int delay;
    private boolean started=false;
    private Timer timer;
    
    class TimerListener implements ActionListener {
        /** Handle ActionEvent */
        @Override
		public void actionPerformed(ActionEvent e) {
        	if (timeLeft>=0 && started) {//以开始游戏和游戏还有剩余时间为指令反复刷新界面
        		repaint();
        	}
        }
    }

    /*游戏内部的对象生成器，在例程里为Bullet*/
    class GameObject extends JButton{
		public GameObject(){
		}
	}

    /*停止游戏的监听器，很重要，和游戏主窗口里的鼠标监听器一个作用*/
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
    
    /*初始界面构建，delay是刷新两帧之间的ms数，但是不准*/
    public GamePanel(int delay) {

    	this.delay=delay;
        this.startButton = new JButton("开始小游戏");
        this.startButton.setBounds(245, 266, 100, 20);
        this.startButton.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			InitGame();
    			}
    		});
        this.add(startButton);

    }

    /*初始化*/
    public void InitGame() {
    	/*重要的三行，添加一些监听器，接口在底下实现*/
    	this.removeAll();
        addMouseListener(this);   
        addMouseMotionListener(this); 
    	timeLeft=200;
    	/*启动游戏*/
		this.setVisible(true);
	    this.timer = new Timer(delay,new TimerListener());
	    this.timer.setInitialDelay(1000);//给玩家反应时间
	    this.timer.start();
	    this.repaint();
    	this.started=true;
    }
    
    /*结束界面*/
    public void endGame() {
		this.removeAll();
    	JTextPane txtpnshi = new JTextPane();
		txtpnshi.setBounds(175, 100, 200, 50);
		txtpnshi.setOpaque(false);
		txtpnshi.setEditable(false);
		this.add(txtpnshi);
		txtpnshi.setText("游戏结束，你的得分："+String.valueOf(score));

    }
    	  
    /*repaint会调用这个接口，利用它进行界面逻辑的设置*/
    @Override
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
	        g.drawString("你的得分："+String.valueOf(score),50,40);
	        g.drawString("剩余时间："+String.valueOf(this.timeLeft/10)+"."+String.valueOf(this.timeLeft%10)+"s",50,60);
        }
    }
    
    /*矩形碰撞检测*/
    /*
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
    */
    
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
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


