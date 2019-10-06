package template.event;
import java.util.Random;

import javax.swing.JOptionPane;

import template.datapack.DataPack;


/*
 * template version 1.0
 * 分支事件后台模板
 * update:20190927 18:39
 * */

public class EventIndom extends EventBase{

	public DataPack actOn(DataPack oldDataPack) {
		/* 		在下面进行dataPack的处理	*/
		/*		START OF YOUR CODE		*/
		Random r = new Random();
		if (oldDataPack.count==3) {
			oldDataPack.stateA="去上课";
		}else {
			oldDataPack.stateA="自习";
		}
		int a = r.nextInt(8) + 1;
		switch(oldDataPack.choiceA) {
			case "sleep":
				oldDataPack.characterIQ-=a;
				oldDataPack.characterHealth+=2;
				oldDataPack.characterHappiness+=4;
				oldDataPack.characterStrength+=1+a;
				break;
			case "selfstudy":
				oldDataPack.count+=1;
				oldDataPack.characterIQ+=a;
				oldDataPack.characterHealth-=a-1;
				oldDataPack.characterHappiness-=2;
				oldDataPack.characterStrength-=a-2;
				break;
		}
		if (oldDataPack.characterHealth<=0)
			JOptionPane.showMessageDialog(null, "你猝死了", "", JOptionPane.ERROR_MESSAGE);
		nextDataPack=oldDataPack;
		/*******************************************
		 * 事件结束，ID为下一个事件，
		 * 
		 * 		{{{{{{务必要将数据包 事件状态复原}}}}
		 * 
		 *******************************************/
		if (oldDataPack.count==5) {
			oldDataPack.ID=1;
			oldDataPack.clearEventState();//复原状态，以免别人的分支出问题
		}
		/*		END OF YOUR CODE		*/
		return nextDataPack;
	}
}
