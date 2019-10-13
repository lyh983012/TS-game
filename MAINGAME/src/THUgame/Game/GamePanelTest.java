package THUgame.Game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import THUgame.Game.AnimatePanel.Bullet;
import THUgame.tool.ImagePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JTextPane;



public class GamePanelTest{
	
	JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GamePanelTest window = new GamePanelTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*************************************************************	
	 * 【构造函数】
	 * 不要新建JFrame窗口对象，而是把上层传进来的窗口对象里面的东西扔了，重新添加
	 *************************************************************/
	public GamePanelTest() {
		
		JFrame frame=new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);		
		/*************************************************************	
		 * 背景镶板，所有的组件都在里面
		 * 两个按钮直接用插件拖进去的
		 * 这一部分按照流程做的话就会自然消失的，推荐直接在可视化界面编辑属性
		 * 最后放一下背景
		 *************************************************************/
		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBackground(new Color(0, 0, 0));
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
		
		
		JPanel EventPack = new JPanel();//将来可以用它来放临时小事件
		EventPack.setBounds(254, 134, 536, 398);
		EventPack.setLayout(null);
		EventPack.setOpaque(false);//注意要设成透明的
		
			JPanel EventPanel = new AnimatePanel(20);//将来可以用它来放临时小事件
			EventPanel.setBounds(0, 0, 536, 398);
			EventPanel.setOpaque(false);//注意要设成透明的
			EventPanel.setLayout(null);
			
			JPanel EventBackgound = new ImagePanel("imgsrc//eb.png",0, 0, 536, 398);	
			EventBackgound.setBounds(0, 0, 536, 398);
			EventBackgound.setOpaque(false);//注意要设成透明的
			EventBackgound.setLayout(null);
			
		EventPack.add(EventPanel);
		EventPack.add(EventBackgound);
		backgroundPanel.add(EventPack);


		frame.getContentPane().removeAll();
		frame.getContentPane().add(backgroundPanel);
		frame.getContentPane().repaint();
		frame.setVisible(true);
	}
}


class AnimatePanel extends JPanel implements MouseListener,MouseMotionListener{
	
	
    private JButton HeroNewButton;
    public int score=0;
    public int timeLeft=300;
    public int count;
    public int delay;
    public int Ex,Ey;
    public int Hx,Hy;
    
    public int Ew,Eh;
    public int Hw,Hh;
    public boolean started=false;
    
    public Timer timer;
    public ArrayList<Bullet> Bullets;

    class TimerListener implements ActionListener {
        /** Handle ActionEvent */
        public void actionPerformed(ActionEvent e) {
        	if (timeLeft>=0 && started) {
        		repaint();
        	}
        }
    }

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

    public AnimatePanel(int delay) {
    	this.delay=delay;
    	this.Bullets=new ArrayList<Bullet>();
        addMouseListener(this);   
        addMouseMotionListener(this); 
        Ew=100;Eh=20;
		Hw=40;Hh=40;
        
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

    public void InitGame() {
    	this.removeAll();
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
    
    public void endGame() {
		this.removeAll();
    	JTextPane txtpnshi = new JTextPane();
		txtpnshi.setBounds(175, 100, 200, 50);
		txtpnshi.setOpaque(false);
		txtpnshi.setEditable(false);
		this.add(txtpnshi);
		txtpnshi.setText("游戏结束，你的得分："+String.valueOf(score));
		JPanel hh=this;

		this.HeroNewButton = new JButton("结束");
        this.HeroNewButton.setBounds(245, 266, 100, 20);
        this.HeroNewButton.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			hh.setVisible(false);
    			}
    		});
        this.add(HeroNewButton);
    }
    	
    
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


