package THUgame.subevents;
import java.util.Random;

import javax.management.openmbean.OpenMBeanAttributeInfoSupport;
import javax.swing.JOptionPane;


import THUgame.datapack.DataPack;
import THUgame.event.EventBase;


/*
 * 第一次活动-2 后勤分支 * 
 * 
 *  ---- LOG ----
 *  update:20191214
 *  via：余冬杰
 *  对话流程
 *  	购买物资且准时：1-2-10-11-12-13-14-15-16-17-18
 *  	购买物资但迟到：3-4-5-6-7-8-9
 *      没买物资但准时：19-20-21-2-10-11-12-13-14-15-16-17-18
 * 		没买物资且迟到：19-20-22-4-5-6-7-8-9
 * */

public class EventSUPE22 extends EventBase{

	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/
		/*		START OF YOUR CODE		*/
		
		if (oldDataPack.count == 2){
			oldDataPack.count = 10;
		}else if (oldDataPack.count == 21) {
			oldDataPack.count = 2;
		}else if (oldDataPack.count == 22) {
			oldDataPack.count = 4;
		}else if (oldDataPack.count == 20){
			if (oldDataPack.time == 21) {
				oldDataPack.count = 21;
			}else {
				oldDataPack.count = 22;
			}
		}else if (oldDataPack.count == 18) {
			oldDataPack.eventFinished=true;
			oldDataPack.time += 2;
			oldDataPack.SUPEprocess=4;
			switch (oldDataPack.stateA) {
			case "1":
				oldDataPack.SUPEcontribution += 5;
				oldDataPack.characterHappiness += 2;
				break;
			case "3":
				oldDataPack.SUPEcontribution += 1;
				oldDataPack.characterHappiness += 2;
				break;
			}
			System.out.println(oldDataPack.SUPEcontribution);
		}else if (oldDataPack.count == 9) {
			oldDataPack.eventFinished=true;
			oldDataPack.time += 1;
			oldDataPack.SUPEprocess=4;
			switch (oldDataPack.stateA) {
			case "2":
				oldDataPack.SUPEcontribution += 4;
				oldDataPack.characterHappiness += 3;
				break;
			case "4":
				oldDataPack.SUPEcontribution += 1;
				oldDataPack.characterHappiness += 2;
				break;
			}
			System.out.println(oldDataPack.SUPEcontribution);
		}else {
			oldDataPack.count = oldDataPack.count + 1;
		}
		
		/*		END OF YOUR CODE		*/
		return;
	}
}
