package template.tool;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import template.datapack.DataPack;
import template.main.EventManager;

/* 	
 * 不要新建在这个文件夹下， 将这个类的public去掉，然后复制到要用这个类的java文件里，否则项目会很庞大
 * 
 * 下面是对鼠标动作的监听类的实现	
 * 在下面进行dataPack的处理
 * 选择你要的事件添加动作即可(修改数据包）
 * 如果希望立即重新绘图，应当
 * 
 * 		1.新建的时候就传入mainGame
 * 		
 * 		2.在每个动作结尾加入
 * 		synchronized(mainGame) {
 *			mainGame.notify();
 *		}唤醒后台线程
 * 
 **/
public class DemoMouseListener implements MouseListener{

	private EventManager mainGame;
	private JFrame frame;
	static public DataPack dataPackage;
	
	private int mode;
	public DemoMouseListener(int i){
		this.mode=i;
	}
	
	public void setFrame(JFrame frame) {
		this.frame=frame;
	}
	
	public void setGame(EventManager mainGame) {
		this.mainGame=mainGame;
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
		/*		START OF YOUR CODE		*/
		if(mode==0) {
			dataPackage.characterIQ=dataPackage.characterIQ+1;
			dataPackage.characterHappiness=dataPackage.characterHappiness-1;
    		EventManager.dataPackage=dataPackage;
		}else {
			dataPackage.characterIQ=dataPackage.characterIQ-1;
			dataPackage.characterHappiness=dataPackage.characterHappiness+1;
    		EventManager.dataPackage=dataPackage;
		}
		/*		END OF YOUR CODE		*/
		synchronized(mainGame) {
			mainGame.notify();
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