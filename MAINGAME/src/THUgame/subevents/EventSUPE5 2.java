package THUgame.subevents;
import java.util.Random;

import javax.swing.JOptionPane;


import THUgame.datapack.DataPack;
import THUgame.event.EventBase;


/*
 * 体育部第二次活动 * 
 * 
 *  ---- LOG ----
 *  update:20191215
 *  via：余冬杰
 *  
 * 
 * */

public class EventSUPE5 extends EventBase{

	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/
		
		/*		START OF YOUR CODE		*/	
		if (oldDataPack.count == 10){
			oldDataPack.eventFinished=true;
			oldDataPack.time += 2;
			oldDataPack.SUPEprocess=7;
		}else if (oldDataPack.count == 6) {
			if(Integer.valueOf(oldDataPack.stateA) == 0) {             // 对0个
				oldDataPack.count = 7;
			}else if (Integer.valueOf(oldDataPack.stateA) == 1) {      // 对1个
				oldDataPack.count = 8;
			}else if (Integer.valueOf(oldDataPack.stateA) == 2) {      // 对2个
				oldDataPack.count = 9;
			}
		}else if (oldDataPack.count == 7) {
			oldDataPack.count = 10;
		}else if (oldDataPack.count == 8) {
			oldDataPack.count = 10;
		}else{
			oldDataPack.count = oldDataPack.count + 1;
		}	
		/*		END OF YOUR CODE		*/
		return;
	}
}
