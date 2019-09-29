package template.main;

import template.datapack.DataPack;
import template.event.EventBase;
import template.event.EventTemplate;
import template.event.EventinClass;

public class EventManager extends Thread{
	
    private WindowManager GUI;
    private EventBase pushForward;
	static public DataPack dataPackage;
    public void set(WindowManager GUI){this.GUI=GUI;}
    
    public void run(){
    	while(true){
    		/*********************************		
    		 * 
    		 * 在下面进行分支事件的选择，处理数据包
    		 * 并且重新绘制界面，界面根据数据包内容绘制
    		 * list:
    		 * 	0.demo 模板事件
    		 * 	1.InClass 上课事件	
    		 * 	
    		 *********************************/
    		/*		START OF YOUR CODE		*/

    		
    		switch(dataPackage.ID) {
				case 0:
					pushForward = new EventTemplate();
					break;
				case 1:
					pushForward = new EventinClass();
					break;	
    		}

			
    		/*		END OF YOUR CODE		*/
    		
    		WindowManager.dataPackage=pushForward.actOn(dataPackage);
		    GUI.repaint();//每次更新完数据包，用新的数据包重新绘制窗口界面
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
