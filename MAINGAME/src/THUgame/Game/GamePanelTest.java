package THUgame.Game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import THUgame.tool.ImagePanel;
import java.awt.Color;
import java.awt.EventQueue;


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
		
			JPanel EventPanel = new ShootGame(20);//将来可以用它来放临时小事件
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

<<<<<<< HEAD

class AnimatePanel extends JPanel implements MouseListener,MouseMotionListener{
	Timer timer;
    private JButton HeroNewButton;
    private JButton EnemyNewButton;
    int score=0;

    public AnimatePanel( int delay) {

        timer = new Timer(delay, new TimerListener());
        timer.start();
        addMouseListener(this);   
        addMouseMotionListener(this); 
        
        this.HeroNewButton = new JButton("Hero");
		this.HeroNewButton.setBounds(240, 266, 50, 50);
		this.add(HeroNewButton);
		
		this.EnemyNewButton = new JButton("Enemy");
		this.EnemyNewButton.setBounds(10, 10, 50, 50);
		this.add(EnemyNewButton);
		
		this.setVisible(true);

		
    }
    private boolean checkHit(int ex,int ey,int hx,int hy) {
    	if(Math.abs(ex-hx)<50 && Math.abs(ey-hy)<50)
    		return true;
    	else
    		return false;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
  
        int Ex=this.EnemyNewButton.getX();
        int Ey=this.EnemyNewButton.getY();
        int Hx=this.HeroNewButton.getX();
        int Hy=this.HeroNewButton.getY();
        Random r = new Random();
        if(checkHit(Ex,Ey,Hx,Hy)) {
        	score+=1;
        	while(true) {
        		Ex = r.nextInt(536) + 1;
        		Ey = r.nextInt(298) + 1;
        		if(!checkHit(Ex,Ey,Hx,Hy))
        			break;
        	}
        }
        this.EnemyNewButton.setBounds(Ex,Ey, 50, 50);
        g.drawString(String.valueOf(score),10,10);

    }

    class TimerListener implements ActionListener {
        /** Handle ActionEvent */
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		this.HeroNewButton.setBounds(e.getX()-25,e.getY()-50, 50, 50);
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

=======
>>>>>>> linyh_test
