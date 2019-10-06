package template.main;

import template.datapack.DataPack;
import template.event.EventTemplate;

public class EventManager extends Thread{
	
    private WindowManager GUI;
	static public DataPack dataPackage;
    public void set(WindowManager GUI){this.GUI=GUI;}
    
    public void run(){
    	while(true){
    		/*********************************		
    		 * 
    		 * 在下面进行分支事件的选择，处理数据包
    		 * 并且重新绘制界面，界面根据数据包内容绘制	
    		 * 	
    		 *********************************/
    		/*		START OF YOUR CODE		*/
    		switch(dataPackage.ID) {
				case 0:
					EventTemplate eventDemo = new EventTemplate();
					WindowManager.dataPackage=eventDemo.actOn(dataPackage);
					break;
				case 1:
					break;	
    		}
		    
		    GUI.repaint();//每次更新完数据包，用新的数据包重新绘制窗口界面
    		/*		END OF YOUR CODE		*/
		    synchronized(this){
		   		try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		   	}
    	}
	}

}
