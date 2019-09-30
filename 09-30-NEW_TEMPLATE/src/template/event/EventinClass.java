package template.event;
import java.util.Random;

import javax.swing.JOptionPane;

import template.datapack.DataPack;


/*
 * template version 1.2
 * 分支事件后台模板
 * update:20190930 30
 * 更新：
 * 	 添加了“是否要让小事件显示”的属性的判断
 * */

public class EventinClass extends EventBase{

	public DataPack actOn(DataPack oldDataPack) {
		/* 		在下面进行dataPack的处理	*/
		/*		START OF YOUR CODE		*/
		if (oldDataPack.choiceA.equals("back")) {
			oldDataPack.ID=0;
			oldDataPack.clearEventState();
		}
		Random r = new Random();
		oldDataPack.count+=1;
		if (oldDataPack.count==4) {
			oldDataPack.stateA="回宿舍";
		}
		int a = r.nextInt(8) + 1;
		oldDataPack.count+=1;
		switch(oldDataPack.choiceA) {
			case "answer":
				oldDataPack.characterIQ+=a-2;
				oldDataPack.characterEQ+=a;
				oldDataPack.characterHappiness+=1;
				oldDataPack.characterHealth-=a-3;
				break;
			case "ask":
				oldDataPack.characterIQ+=a;
				oldDataPack.characterEQ-=a;
				oldDataPack.characterHealth-=3-a;
				break;
			case "next":
				oldDataPack.characterIQ+=a-4;
				oldDataPack.characterHappiness-=3;
				oldDataPack.characterHealth-=1;
				break;
		}
		if (oldDataPack.characterHealth<=0)
			JOptionPane.showMessageDialog(null, "你猝死了", "", JOptionPane.ERROR_MESSAGE);
		nextDataPack=oldDataPack;
		/*******************************************
		 * 事件结束，ID为下一个事件，务必要将数据包 事件状态复原
		 *******************************************/
		/*		END OF YOUR CODE		*/
		if (oldDataPack.count>=5) {
			oldDataPack.trigSubEvent=true;
		}
		return nextDataPack;
	}
}
