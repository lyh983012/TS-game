package THUgame.subevents;
import THUgame.datapack.DataPack;
import THUgame.event.EventBase;


/*
/*
 * 第二次例会 
 * 
 *  ---- LOG ----
 *  update:20191214
 *  via：余冬杰
 *  对话流程:
 *      0-1-2-3-6-7-8-9-10-11
 * 			 \
 * 			  4-5-7-8-9-10-11
 */

public class EventSUPE3 extends EventBase{

	@Override
	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/

		/*		START OF YOUR CODE		*/	
		if (oldDataPack.count == 2) {
			if (oldDataPack.SUPEmentor==1) { // 宣传
				oldDataPack.count = 3;
			}else {  						 // 后勤
				oldDataPack.count = 4;
			}
		}else if (oldDataPack.count == 3) {
			oldDataPack.characterHappiness += 5;
			oldDataPack.count = 6;
		}else if (oldDataPack.count == 4) {
			oldDataPack.count = 5;
		}else if (oldDataPack.count == 5) {
			oldDataPack.characterHappiness -= 3;
			oldDataPack.count = 7;
		}else if (oldDataPack.count == 6) {
			oldDataPack.characterHappiness -= 2;
			oldDataPack.count = 7;
		}else if (oldDataPack.count == 7) {
			oldDataPack.characterHappiness += 1;
			oldDataPack.count = 8;
		}else if (oldDataPack.count == 11){
			oldDataPack.eventFinished=true;
			oldDataPack.SUPEstate = "1110";
			oldDataPack.time += 2;
			oldDataPack.SUPEprocess=5;
		}else {
			oldDataPack.count = oldDataPack.count + 1;
		}	
		/*		END OF YOUR CODE		*/
		return;
	}
}
