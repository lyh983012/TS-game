package THUgame.subevents;
import java.util.Random;

import javax.swing.JOptionPane;


import THUgame.datapack.DataPack;
import THUgame.event.EventBase;


/*
 * 体育部第二次活动——宣传线 * 
 * 
 *  ---- LOG ----
 *  update:20191203
 *  via：余冬杰
 *  
 * 
 * */

public class EventSUPE21 extends EventBase{

	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/
		
		/*		START OF YOUR CODE		*/	
		if (oldDataPack.count == 0){
			if (oldDataPack.time == 21) { // 准时到达
				oldDataPack.count = 2;
			}else {  					  // 迟到了一会 
				oldDataPack.count = 4;
			}
		}else if (oldDataPack.count == 3 || oldDataPack.count == 5) {
				oldDataPack.count = 6;
		}else if (oldDataPack.count == 11){
			oldDataPack.eventFinished=true;
			oldDataPack.time += 2;
		}else {
			oldDataPack.count = oldDataPack.count + 1;
		}	
		/*		END OF YOUR CODE		*/
		return;
	}
}
