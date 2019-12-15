package THUgame.subevents;
import java.util.Random;

import javax.swing.JOptionPane;


import THUgame.datapack.DataPack;
import THUgame.event.EventBase;


/*
 * 第三次例会 
 * 
 *  ---- LOG ----
 *  update:20191215
 *  via：余冬杰
 *  对话流程:
 *      0-1-2-3-6-7-8-10-11-12-13-14
 * 		   \   /   \     /
 * 			4-5     9----
 */

public class EventSUPE4 extends EventBase{

	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/
		/*		START OF YOUR CODE		*/	
		if (oldDataPack.count == 1){
			if (oldDataPack.choiceB.equals("yes")){         // 选择竞选
				oldDataPack.count = 2; 
			}else if (oldDataPack.choiceB.equals("no")){    // 选择不竞选
				oldDataPack.count = 4;
			}
		}else if (oldDataPack.count == 3) {
			oldDataPack.count = 6;
		}else if (oldDataPack.count == 7) {
			if (oldDataPack.choiceB.equals("yes") && oldDataPack.SUPEcontribution >= 4) {
				oldDataPack.count = 8;
				oldDataPack.SUPEchair = 1;
			}else {
				oldDataPack.count = 9;
			}
		}else if (oldDataPack.count == 8) {
			oldDataPack.count = 10;
		}else if (oldDataPack.count == 9) {
			oldDataPack.count = 11;
		}else if (oldDataPack.count == 14){
			oldDataPack.eventFinished=true;
			oldDataPack.time += 2;
			oldDataPack.SUPEprocess=6;
		}else {
			oldDataPack.count = oldDataPack.count + 1;
		}	
		/*		END OF YOUR CODE		*/
		return;
	}
}
