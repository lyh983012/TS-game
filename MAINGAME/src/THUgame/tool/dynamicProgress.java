package THUgame.tool;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class dynamicProgress extends JPanel{
	/**
	 * wdnmd
	 */
	private static final long serialVersionUID = 1L;
	private ImagePanel progress;
	private JLabel value;
	private int x;
	private int y;
	private double wid;
	private int hi;
	
	
	public dynamicProgress(int x,int y,double wid,int hi){
		this.hi=hi;
		this.wid=wid;
		this.y=y;
		this.x=x;
		this.setBounds(x,y,(int) wid,hi);
	}
	
	
	public void setValue(double va){
		this.setOpaque(false);//注意要设成透明的
		value=new JLabel(String.valueOf((int)va));
		value.setBounds((int) (wid/2)-5, 0, 10, hi);
		this.add(value);
		
		progress=new ImagePanel("imgsrc//progress.png",0,0,(int)(va/100*wid),hi);
		progress.setBounds(0, 0, (int) wid, hi);
		System.out.println((int)(va/100*wid));
		this.add(progress);
		progress.setOpaque(false);//注意要设成透明的
		
		this.repaint();
	}

}
