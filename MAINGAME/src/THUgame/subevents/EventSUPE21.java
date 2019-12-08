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
				oldDataPack.characterEQ -= 2;
			}
		}else if (oldDataPack.count == 3 || oldDataPack.count == 5) {
				oldDataPack.count = 6;
		}else if (oldDataPack.count == 8) {
			switch (oldDataPack.stateB) {
			case "Success":
				System.out.println("奥利给！");
				int timePasses = 1 + Integer.valueOf(oldDataPack.stateA) / 4;
				if (timePasses == 1) {
					oldDataPack.count = 9;
				}else if (timePasses == 2) {
					oldDataPack.count = 10;
				}else if (timePasses == 3) {
					oldDataPack.count = 11;
				}
				oldDataPack.time += timePasses;
				break;
			case "justStop":
				System.out.println("给力嗷！");
				oldDataPack.count = 13;
				break;
			}
		}else if (oldDataPack.count >=9 && oldDataPack.count <= 11){
			oldDataPack.count = 12;
		}else if (oldDataPack.count == 12){
			oldDataPack.eventFinished=true;
			// SUPE贡献值+3，心情+5，体力-时间*2
			oldDataPack.SUPEcontribution += 3;
			oldDataPack.characterHappiness += 5;
			oldDataPack.characterEnergy -= (1 + Integer.valueOf(oldDataPack.stateA) / 4) * 2;
			oldDataPack.SUPEprocess=4;
			
		}else if (oldDataPack.count == 14){
			oldDataPack.eventFinished=true;
			oldDataPack.SUPEcontribution += 2;
			oldDataPack.characterHappiness += 1;
			oldDataPack.characterEnergy -= 8;
			oldDataPack.time += 4;
			oldDataPack.SUPEprocess=4;
		}else {
			oldDataPack.count = oldDataPack.count + 1;
		}	
		/*		END OF YOUR CODE		*/
		return;
	}
}
