package THUgame.event;
import java.util.Random;

import javax.swing.JOptionPane;

import THUgame.datapack.DataPack;

/*
 * 下午课程，暂时仿制上午课程
 * */

public class EventNoonClass extends EventBase{

	public void actOn(DataPack oldDataPack) {
		
		/*******************************************
		 * 事件结束
		 *******************************************/
		if (oldDataPack.choiceA.equals("back")) {
			oldDataPack.eventFinished=true;		//如果显示去上课，并且点击了这个按钮，那么宿舍事件结束
			return;								//直接返回，避免属性乱变
		}
		/*******************************************
		 * 在下面进行dataPack的处理
		 *******************************************/
		/*		START OF YOUR CODE		*/	
		Random r = new Random();
		oldDataPack.time+=1;					//当某个操作需要耗时，时间+1（原本的版本是计数器+1）
		int a = r.nextInt(8) + 1;
		switch(oldDataPack.choiceA) {
			case "answer":
				oldDataPack.characterIQ+=a-2;
				oldDataPack.characterEQ+=a;
				oldDataPack.characterHappiness+=1;
				oldDataPack.characterEnergy-=a-3;
				oldDataPack.notification="回答了一个问题，不管有没有答对，智商和社交力都产生了变化！有些开心，也有些疲惫。";
				break;
			case "ask":
				oldDataPack.characterIQ+=a;
				oldDataPack.characterEQ-=a;
				oldDataPack.characterEnergy-=3-a;
				oldDataPack.notification="有些困惑，问了个问题，智商和社交力都产生了变化！也有些疲惫。";
				break;
			case "next":
				oldDataPack.characterIQ+=a-4;
				oldDataPack.characterHappiness-=3;
				oldDataPack.characterEnergy-=1;
				oldDataPack.notification="老师开始讲下一题了，不知道刚才有没有听懂呢，真是难啊";
				break;
		}
		if (oldDataPack.time==18) {
			oldDataPack.trigSubEvent=true; 		//到达下课时间！下午课程仅仅允许回宿舍
		}
		if (oldDataPack.characterHealth<=0)
			JOptionPane.showMessageDialog(null, "你猝死了", "", JOptionPane.ERROR_MESSAGE);
		return;
	}
}
