package THUgame.event;
import java.util.Random;

import javax.swing.JOptionPane;

import THUgame.datapack.DataPack;

/*
 * 分支事件后台模板
 *  
 * template version 1.2
 * update:20190930 30
 * 更新：
 * 	 添加了“是否要让小事件显示”的属性的判断
 * 
 * template version 1.3
 * update:20191006 
 * 更新：
 * 	 把事件切换移除，统一在主线程里管理
 *	 计数器更换为时间，更合理
 *   完善注释
 *   增加了下午课程的分支
 * */

public class EventMorningClass extends EventBase{

	public void actOn(DataPack oldDataPack) {
		
		/*******************************************
		 * 事件结束
		 *******************************************/
		if (oldDataPack.choiceA.equals("back") || oldDataPack.choiceA.equals("continue")) {
			oldDataPack.eventFinished=true;		//如果选择回宿舍或者去上下午的课，终止
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
				if(oldDataPack.characterEnergy<5) {
					oldDataPack.notification="我没有力气站起来提问。";
					break;
				}else if(oldDataPack.characterIQ<0){
					oldDataPack.notification="我的学力似乎不支持我听懂任何人说话。";
					break;
				}else {
					oldDataPack.characterIQ+=a;
					oldDataPack.characterEQ-=a;
					oldDataPack.characterEnergy-=3-a;
					oldDataPack.notification="有些困惑，问了个问题，智商和社交力都产生了变化！也有些疲惫。";
					break;
				}
			case "next":
				oldDataPack.characterIQ+=a-4;
				oldDataPack.characterHappiness-=3;
				oldDataPack.characterEnergy-=1;
				oldDataPack.notification="老师开始讲下一题了，不知道刚才有没有听懂呢，真是难啊";
				break;
		}
		if(oldDataPack.characterEnergy<30) {
			oldDataPack.characterHealth-=1;
		}
		if (oldDataPack.time==12)
			oldDataPack.trigSubEvent=true; 		//到达下课时间！
		if (oldDataPack.characterHealth<=0)
			JOptionPane.showMessageDialog(null, "你猝死了", "", JOptionPane.ERROR_MESSAGE);
		return;
	}
}
