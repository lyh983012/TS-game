package THUgame.subevents;
import java.util.Random;

import javax.swing.JOptionPane;


import THUgame.datapack.DataPack;
import THUgame.event.EventBase;


/*
 * 招新事件 * 
 * 
 *  ---- LOG ----
 *  update:20191123
 *  via：余冬杰
 *  更新：招新的大致过程明确了
 *  TODO:
 *      1.
 * 
 * */

public class EventSUPE6 extends EventBase{

	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/ 
		
		/*		START OF YOUR CODE		*/	
		if (oldDataPack.count == 11){
			oldDataPack.eventFinished=true;
			oldDataPack.time += 2;
			oldDataPack.SUPEprocess=8;
		}else {
			oldDataPack.count = oldDataPack.count + 1;
		}	
		/*		END OF YOUR CODE		*/
		return;
	}
}
