package template.event;
import java.util.Random;

import template.datapack.DataPack;


/*
 * template version 1.0
 * 分支事件后台模板
 * update:20190927 18:39
 * */

public class EventinClass extends EventBase{

	public DataPack actOn(DataPack oldDataPack) {
		/* 		在下面进行dataPack的处理	*/
		/*		START OF YOUR CODE		*/
		
		Random r = new Random();
		oldDataPack.count+=1;
		if (oldDataPack.count==4) {
			oldDataPack.stateA="回宿舍";
		}
		int a = r.nextInt(5) + 1;
		switch(oldDataPack.choiceA) {
			case "answer":
				oldDataPack.characterIQ+=a-2;
				oldDataPack.characterEQ+=a;
				oldDataPack.characterHappiness+=1;
				break;
			case "ask":
				oldDataPack.characterIQ+=a;
				oldDataPack.characterEQ-=a;
				oldDataPack.characterHealth-=2-a*2;
				break;
			case "next":
				oldDataPack.characterIQ+=a-4;
				oldDataPack.characterHappiness-=3;
				break;
		}
		nextDataPack=oldDataPack;
		/*******************************************
		 * 事件结束，ID为下一个事件，务必要将数据包 事件状态复原
		 *******************************************/
		/*		END OF YOUR CODE		*/
		if (oldDataPack.count==5) {
			oldDataPack.ID=0;
			oldDataPack.clearEventState();
		}
		return nextDataPack;
	}
}
