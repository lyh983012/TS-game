package THUgame.main;
import THUgame.datapack.DataPack;
import THUgame.event.EventBase;
import THUgame.event.EventChoice;
import THUgame.event.EventIndom;
import THUgame.event.EventMorningClass;
import THUgame.event.EventNoonClass;
import THUgame.event.EventTimeManager;
import THUgame.event.EventHome;
import THUgame.event.EventBackground;
import THUgame.event.EventWelcome;
public class EventManager extends Thread{
	
    private WindowManager GUI;
    private EventBase pushForward;
	static public DataPack dataPackage;
    public void set(WindowManager GUI){this.GUI=GUI;}
    
    public void run(){
    	while(true){
    		/*********************************		
    		 *     		 * 整体逻辑为：
    		 * 	开始——事件响应——数据包更新——判断是否是新事件——绘图——事件响应……
    		 * 
    		 * 在下面进行分支事件的选择，处理数据包
    		 * 并且重新绘制界面，界面根据数据包内容绘制
    		 * list:
    		 * 	-1.开始后进入的过路事件，什么都不做
    		 * 	0.inDom 在宿舍
    		 * 	1.MorningClass 早上上课事件
    		 *  2.NoonClass 下午上课事件
    		 *  30000.通过选择确定人物模板事件->30002
    		 *  30001.人物基本背景说明及选择提示->30000
    		 *  30002.欢迎界面->0
    		 * 	
    		 *********************************/
    		/*		START OF YOUR CODE		*/
    		switch(dataPackage.ID) {
    			case -1:
    				pushForward = new EventHome();
    				break;
				case 0:
					pushForward = new EventIndom();
					break;
				case 1:
					pushForward = new EventMorningClass();
					break;	
				case 2:
					pushForward = new EventNoonClass();
					break;	
				case 30000:
					pushForward = new EventChoice();
					break;
				case 30001:
					pushForward = new EventBackground();
					break;
				case 30002:
					pushForward = new EventWelcome();
					break;
    		}
    		/*		END OF YOUR CODE		*/
    		pushForward.actOn(dataPackage);
    		pushForward = new EventTimeManager();
    	    pushForward.actOn(dataPackage);
    		/*********************************		
    		 * 
    		 * 在数据包被处理完之后，判断是否发生分支事件转移
    		 * 	
    		 *********************************/
    		
    		if (dataPackage.eventFinished==true){
        		switch(dataPackage.ID) {
	    			case 0://dom界面后进入教室
	    				if(dataPackage.stateA.equals("上早上课")) {
	    					dataPackage.ID=1;
	    				}if(dataPackage.stateA.equals("上下午课")) {
	    					dataPackage.ID=2;
	    				}
	    				break;
					case 1://上午界面后进入dom
						if(dataPackage.choiceA.equals("back")){
	    					dataPackage.ID=0;
						}else if(dataPackage.choiceA.equals("continue")) {
	    					dataPackage.ID=2;
						}
	    				break;	
					case 2://下午界面后进入dom
						dataPackage.ID=0;
						break;	
					case -1://开始界面过后，进入选择界面
						if(dataPackage.stateA.equals("新游戏")) {
							System.out.println(111);
							dataPackage.ID=30001;}
	    				break;	
					case 30000://选择界面过后，进入游戏界面
						dataPackage.ID=30002;
						break;
					case 30001:
						dataPackage.ID=30000;
						break;
					case 30002:
						dataPackage.ID=0;
						break;
	        		}
	        		dataPackage.clearEventState();//复原状态，以免别人的分支出问题
    		}
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
