/***********
 * created by Jiabei&Xiaomi in 2019.10.30
 * version:1.0
 * Description: A maze game
 * other Info:
 ***********/


package THUgame.Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Stack;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

import THUgame.Game.RememberGame.TimerListener;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;

class Lattice{
	
	  private int x = -1;
	  private int y = -1;
	  private int Intree = 0;//Intree 标记为1，NotInTree 标记为0
	  private Lattice Father = null;
	  /*******************
	   * 构造函数传入xx yy初始化Lattice坐标
	   * @param xx 
	   * @param yy
	   */
	  public Lattice(int xx, int yy){
		  this.x = xx;
		  this.y = yy;
	  }
	  /*****
	   * 
	   * @return 返回X,Y坐标
	   */
	  public int GetX() {
		  return this.x;
	  }
	  public int GetY() {
		  return this.y;
	  }
	  /*****
	   * 
	   * @return 是否在树中
	   */
	public int Intree() {
		return this.Intree;
	}

	public Lattice getFather() {
		return this.Father;
	}

	public void setFather(Lattice f) {
		this.Father = f;
	}
	public void setIntree(int f) {
		this.Intree = f;
	}
	public int GetIntree() {
		return this.Intree;
	}
	
	public void SetWhite(Graphics g,int padding,int width) {
		int tx,ty;
		tx = padding + x*width;
		ty = padding + y*width - 100;
		g.drawLine(tx, ty, tx+width, ty);
		g.drawLine(tx, ty, tx, ty+width);
		g.drawLine(tx, ty, tx, ty+width);
		g.drawLine(tx+width, ty, tx+width, ty+width);
		g.drawLine(tx, ty+width, tx+width, ty+width);
	}
	
}

public class MazeGame extends JPanel implements KeyListener{
	static public EventManager mainGame;
	static public DataPack dataPackage;
	private JButton StartButton;
	private Timer timer;
	private int delay;
	private Lattice[][] maze;
	private int num, padding,width;
	static private boolean MazeReady;
	private int Ballx,Bally;
    private JLabel scoreTime;
    private int timeLeft=300;
    private int count=0;
    private boolean failed=false;
	MazePanel EventPanel;
	
	
	/**
	 * 构造函数
	 * @param x 
	 * @param y
	 */
	public MazeGame(int x, int y) {
		this.num = 10;
		this.padding = 150;
		this.width = (540-padding - padding) / num;
		this.maze = new Lattice[num][num];
		MazeGame.MazeReady = false;
		this.Ballx = 0;
		this.Bally = 0;
	    for (int i = 0; i <= num - 1; i++)
	        for (int j = 0; j <= num - 1; j++)
	          maze[i][j] = new Lattice(i, j);
	    
		this.delay = 50;
    	this.setBounds(x, y, 540, 350);
    	this.setLayout(null);
    	this.setOpaque(false);//注意要设成透明的
    	/*
    	 * 开始界面的按钮设置
    	 */
		EventPanel = new MazePanel();
		EventPanel.setOpaque(false);//注意要设成透明的
		EventPanel.setBounds(0, 0, 540, 350);
		EventPanel.setLayout(null);
		
        JTextPane txtpnshi = new JTextPane();
		txtpnshi.setBounds(170, 100, 220, 100);
		txtpnshi.setOpaque(false);
		txtpnshi.setEditable(false);
		txtpnshi.setText("这一次的作业稍微有些难度，需要花点心思来做。规则：尽快从迷宫的一头走向另一头");
		txtpnshi.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		EventPanel.add(txtpnshi);
		
        this.StartButton = new JButton("开始");
    	this.StartButton.setFont(new Font("Lucida Grande", Font.BOLD, 18));
        this.StartButton.setBounds(175, 266, 80, 50);
        this.StartButton.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			InitGame();
    			timer = new Timer(delay,new TimerListener());
    	    	timer.setInitialDelay(500);
    	    	timer.start();
    			}
    		});
        EventPanel.add(StartButton);
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
        
		JPanel EventBackgound = new ImagePanel("imgsrc//shootGame/eb.png",0, 0, 540, 350);	
		EventBackgound.setBounds(0, 0, 540, 350);
		EventBackgound.setOpaque(false);//注意要设成透明的
		EventBackgound.setLayout(null);
		
		this.add(EventPanel);
		this.add(EventBackgound);
	}
	
	class MazePanel extends JPanel{
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			//g.drawLine(0, 100, 100, 100);
			if(MazeReady==true) {
			g.setColor(Color.red);
		    for (int i = 0; i <= num; i++) {
		        g.drawLine(padding + i * width, padding-100, padding + i * width,
		            padding + num * width-100);
		      }
		    for (int j = 0; j <= num; j++) {
		        g.drawLine(padding, padding + j * width-100, padding + num * width,
		            padding + j * width-100);
		      }
		    g.setColor(Color.white);
		    for(int i = num-1;i>=0;i--)
		    	for(int j = num-1;j>=0;j--) {
		    		
		    		Lattice f = maze[i][j].getFather();
		    		if(f!=null) {
		    			int fx = f.GetX();
		    			int fy = f.GetY();
		    			clearFence(i,j,fx,fy,g);
		    		}
		    		if(dist(i,j,Ballx,Bally)>4)
		    			maze[i][j].SetWhite(g,padding,width);
		    	}
		    g.setColor(Color.white);
		    
		    g.drawLine(padding, padding + 1-100, padding, padding + width - 1-100);
		    int last = padding + num * width;
		    g.drawLine(last, last - 1-100, last, last - width + 1-100);
		    
			g.setColor(Color.red);
			g.fillOval(getcenterx(Ballx) - width / 3, getcentery(Bally) - width / 3,
			        width / 2, width / 2);
			g.setColor(Color.blue);
			g.fillOval(getcenterx(num-1) - width / 3, getcentery(num-1) - width / 3,
			        width / 2, width / 2);
			
			} 
		}
	}
	/**
	 * TimerListener，每次timer会调用repaint
	 */
    
	class TimerListener implements ActionListener {
        /** Handle ActionEvent */
        public void actionPerformed(ActionEvent e) {
        		repaint();	
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
    			this.dataPackage.time+=1;
    			this.dataPackage.characterIQ+=timeLeft/10;//在这里改属性
    			this.dataPackage.notification = "<html>我发现作业有些，沉迷其中，过去了4个小时";
    			this.dataPackage.notification += "<br>学习进度+1，心情值-1，体力消耗5点";
    			this.dataPackage.notification += "<br>沉浸在知识海洋里让我的智力值发生了"+String.valueOf((timeLeft-100)/50)+"点的变化</html>";
	   			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	   			synchronized(mainGame) {
	   				this.mainGame.notify();
	   			}//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
   			}
    }
	
	public void InitGame() {
		EventPanel.removeAll();
		scoreTime=new JLabel("剩余时间："+String.valueOf(timeLeft/10)+"."+String.valueOf(timeLeft%10)+"s");
        scoreTime.setBounds(40,30,100,20);
        EventPanel.add(scoreTime);
		initMaze();
		/***
		 * 为实现监听键盘的功能，addKeyListener，requestFocus非常重要
		 */
		
		this.addKeyListener(this);
		this.requestFocus();
		//上面两句一定要加
		return;
	}
	
	private double dist(int x, int y, int ballx, int bally) {
		double dist = Math.sqrt((x-ballx)*(x-ballx)+(y-bally)*(y-bally));
		return dist;
	}
	/****
	 * 
	 * @param p是当前需要获得邻域的点
	 * @return 返回邻域数组pn
	 */
	
	private Lattice[] getNeis(Lattice p) {
		final int[] constant = {-1,0,1,0,-1};//顺序为上右下左
		if(isOutOfBorder(p))
			return null;//如果当前处理的点越界，则返回空
		
		Lattice[] pn = new Lattice [4];//顺序为上右下左
		int xt;
		int yt;
		for(int i=0;i<=3;i++) {
			xt=p.GetX()+constant[i];
			yt=p.GetY()+constant[i+1];
			if(isOutOfBorder(xt,yt))
				continue;
			pn[i]=maze[xt][yt];
		}
		return pn;
	}
	
	private boolean isOutOfBorder(Lattice p) {
		if(p.GetX()<0||p.GetY()<0||p.GetX()>=this.num||p.GetY()>=this.num)
			return true;
		return false;
	}
	
	private boolean isOutOfBorder(int x,int y) {
		if(x<0||y<0||x>=num||y>=num)
			return true;
		return false;
	}
	
	public void initMaze() {
		Random r = new Random();
		int rx = Math.abs(r.nextInt()) % num;
		int ry = Math.abs(r.nextInt()) % num;
		Stack <Lattice> s = new Stack <Lattice>();
		Lattice p = maze[rx][ry];
		Lattice neis[]=null;
		s.push(p);
		while(!s.empty()) {
			p=s.pop();
			p.setIntree(1);//表明该点在树中
			neis=getNeis(p);//获得邻域
			int ran = Math.abs(r.nextInt())%4;
				for(int temp=0;temp<=3;temp++) {
					ran=(ran+1)%4;
					if(neis[ran]==null||neis[ran].GetIntree()==1) //如果越界（在此前做过判断，对应在数组中为null），或者节点已进入树中
						continue;						
					s.push(neis[ran]);
					neis[ran].setFather(p);
				}
		}
		this.MazeReady = true;
		
	}
	
	private void clearFence(int i, int j, int fx, int fy, Graphics g) {
		int sx = padding + ((j > fy ? j : fy) * width),
	        sy = padding-100 + ((i > fx ? i : fx) * width),
	        dx = (i == fx ? sx : sx + width),
	        dy = (i == fx ? sy + width : sy);
		if(sx!=dx) {
			sx++;
			dx--;
		}
		else {
			sy++;
			dy--;
		}
		g.drawLine(sx, sy, dx, dy);
	}
	
	public int getcenterx(int x) {
		return this.padding+ x*this.width+width/2; 
	}

	public int getcentery(int x) {
		return this.padding+ x*this.width+width/2-100; 
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			Thread.sleep(this.delay);
	    	count+=1;
	    	if(count==100/delay) {
	    		this.timeLeft-=1;  	
	    		count=0;
	    		}
	    	if (scoreTime!=null)
	    		scoreTime.setText("剩余时间："+String.valueOf(timeLeft/10)+"."+String.valueOf(timeLeft%10)+"s");
	    	if (this.timeLeft==0) {
	    		failed=true;
	    		endGame();
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
       
	}
	@Override
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}
	@Override

	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//if(MazeReady)
		int x = Ballx, y = Bally;
	 	int c =e.getKeyCode();
	    switch (c) {
	      case KeyEvent.VK_LEFT :
	        x--;
	        break;
	      case KeyEvent.VK_RIGHT :
	        x++;
	        break;
	      case KeyEvent.VK_UP :
	        y--;
	        break;
	      case KeyEvent.VK_DOWN :
	        y++;
	        break;
	      case KeyEvent.VK_SPACE :

	        break;
	      default:
	    }
	    if (!isOutOfBorder(x, y)
	            && (maze[y][x].getFather() == maze[Bally][Ballx]
	                || maze[Bally][Ballx].getFather() == maze[y][x])) {
	          Ballx = x;
	          Bally = y;
	    }
	    this.repaint();
	    this.checkWin();
		
	}
	
	public void checkWin() {
		if(Ballx == num-1&&Bally == num-1) {
			endGame();
		}
	}
   
	public void endGame() {
    	//flag=false;
		JPanel EventBackgound = new ImagePanel("imgsrc//shootGame/eb.png",0, 0, 540, 350);	
		EventBackgound.setBounds(0, 0, 540, 350);
		EventBackgound.setOpaque(false);//注意要设成透明的
		EventBackgound.setLayout(null);
		
		EventPanel = new MazePanel();
		EventPanel.setOpaque(false);//注意要设成透明的
		EventPanel.setBounds(0, 0, 540, 350);
		EventPanel.setLayout(null);
		
    	this.timer.stop();
   
    	JTextPane txtpnshi = new JTextPane();
		txtpnshi.setBounds(175, 100, 200, 200);
		txtpnshi.setOpaque(false);
		txtpnshi.setEditable(false);
		txtpnshi.setText("我在规定时间内完成了任务！");
		if(failed) {
			txtpnshi.setText("啊…这个作业实在是有点难，我放弃了……");
		}
		txtpnshi.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		this.StartButton = new JButton("结束");
		this.setFont(new Font("Lucida Grande", Font.BOLD, 18)); 
		this.StartButton.setBounds(230, 180, 70, 40);
		this.StartButton.addActionListener(new EnderListener(dataPackage,mainGame));
        EventPanel.add(this.StartButton);
		EventPanel.add(txtpnshi);
		
		this.removeAll();
		this.add(EventPanel);
		this.add(EventBackgound);
    }
    
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}