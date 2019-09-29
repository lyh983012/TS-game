package template.event;
import java.util.Random;

import template.datapack.DataPack;


/*
 * template version 1.0
 * 分支事件后台模板
 * update:20190927 18:39
 * */

public class EventTemplate extends EventBase{

	public DataPack actOn(DataPack oldDataPack) {
		/* 		在下面进行dataPack的处理	*/
		/*		START OF YOUR CODE		*/
		Random r = new Random();
		oldDataPack.count+=1;
		if (oldDataPack.count==4) {
			oldDataPack.stateA="去上课";
		}else {
			oldDataPack.stateA="自习";
		}
		int a = r.nextInt(5) + 1;
		switch(oldDataPack.choiceA) {
			case "sleep":
				oldDataPack.characterIQ-=a;
				oldDataPack.characterHealth+=2;
				oldDataPack.characterHappiness+=4;
				oldDataPack.characterStrength+=1+a;
				break;
			case "selfstudy":
				oldDataPack.characterIQ+=a;
				oldDataPack.characterHealth-=1;
				oldDataPack.characterHappiness-=2;
				oldDataPack.characterStrength-=a-2;
				break;
		}
		nextDataPack=oldDataPack;
		/*******************************************
		 * 事件结束，ID为下一个事件，务必要将数据包 事件状态复原
		 *******************************************/
		if (oldDataPack.count==5) {
			oldDataPack.ID=1;
			oldDataPack.clearEventState();
		}
		/*		END OF YOUR CODE		*/
		return nextDataPack;
	}
}
