package THUgame.subevents;
import java.util.Random;

import THUgame.datapack.DataPack;
import THUgame.event.EventBase;


/*
 * 体育部第三次活动 * 
 * 
 *  ---- LOG ----
 *  update:20191216
 *  via：余冬杰
 *  对话流程：
 *  	0-1-2-3-4-5-6-7-8-11-12-13-15
 * 						 \   / \   /
 * 					  	  10-   14-
 * */

public class EventSUPE7 extends EventBase{

	@Override
	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/
		
		/*		START OF YOUR CODE		*/	
		if (oldDataPack.count == 15){
			oldDataPack.eventFinished=true;
			oldDataPack.time = 19;
			oldDataPack.SUPEprocess=9;
			oldDataPack.SUPEstate = "TheEnd";
		}else if (oldDataPack.count == 8) {
			if(oldDataPack.stateA.equals("yes")) {             // 接受回答
				oldDataPack.count = 11;
			}else if (oldDataPack.stateA.equals("no")) {       // 拒绝回答
				oldDataPack.count = 10;
			}
		}else if (oldDataPack.count == 10) {
			oldDataPack.count = 12;
		}else if (oldDataPack.count == 12) {
			Random r = new Random();
			int winRandom = r.nextInt(10) + 1;   // 生成1-10的随机数
			if (oldDataPack.stateA.equals("no")){
				winRandom += 2;
			}else if (oldDataPack.stateA.equals("yes")) {
				switch (Integer.valueOf(oldDataPack.stateB)) {
				case 2:
					winRandom += 2;
					break;
				case 1:
					winRandom += 1;
					break;
				case 0:
					break;
				}
			}
			System.out.println(winRandom);
			if (winRandom <= 5) {
				oldDataPack.count = 13;
				oldDataPack.time = 16;
				oldDataPack.SUPEcontribution += 5;
				oldDataPack.characterHappiness -= 5;
			}else {
				oldDataPack.count = 14;
				oldDataPack.time = 16;
				oldDataPack.SUPEcontribution += 5;
				oldDataPack.characterHappiness += 3;
			}
		}else if (oldDataPack.count == 13) {
			oldDataPack.count = 15;
		}else{
			oldDataPack.count = oldDataPack.count + 1;
		}	
		/*		END OF YOUR CODE		*/
		return;
	}
}
