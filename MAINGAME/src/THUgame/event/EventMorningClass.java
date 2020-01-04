package THUgame.event;
import java.util.Random;

import javax.swing.JOptionPane;

import THUgame.datapack.DataPack;

/*
 * 早课事件
 * 
 * ---DIALOG---
 * update:20191018
 * via：林逸晗
 * 更新：加入游戏
 *   
 * update:20191006 
 * via：林逸晗
 * 更新：把事件切换移除，统一在主线程里管理
 *		计数器更换为时间，更合理
 * 		完善注释
 * 		增加了下午课程的分支
 *   
 * update:20190930 30
 * via：林逸晗
 * 更新：添加了“是否要让小事件显示”的属性的判断
 * 

 * */

public class EventMorningClass extends EventBase{

	@Override
	public void actOn(DataPack oldDataPack) {
		
		/*******************************************
		 * 事件结束
		 *******************************************/
		if (oldDataPack.choiceA.equals("back") || oldDataPack.choiceA.equals("continue")) {
			oldDataPack.eventFinished=true;		//如果选择回宿舍或者去上下午的课，终止
			if(oldDataPack.choiceA.equals("continue"))
				oldDataPack.time+=1;	
			return;								//直接返回，避免属性乱变
		}
		/*******************************************
		 * 在下面进行dataPack的处理
		 *******************************************/
		/*		START OF YOUR CODE		*/	
		Random r = new Random();
		int a = r.nextInt(10);
		switch(oldDataPack.choiceA) {
			case "answer":
				if(oldDataPack.characterEnergy<5) {
					oldDataPack.notification="我没有力气站起来回答。";
					break;
				}else {
					if(a<=3)
						oldDataPack.trigSubEvent=true;
					else {
						oldDataPack.time+=1;					//当某个操作需要耗时，时间+1（原本的版本是计数器+1）
						oldDataPack.characterHappiness+=3;
						oldDataPack.characterEnergy-=a;
						oldDataPack.studyProgress+=1;
						oldDataPack.notification="<html>回答了一个问题，不管有没有答对，学到的东西能用上了。有些开心";
						oldDataPack.notification += "<br>时间过去了1小时，学习进度+1，心情值+3，体力减少了一些</html>";
					}
				}
				break;
			case "ask":
				if(oldDataPack.characterEnergy<5) {
					oldDataPack.notification="我没有力气站起来提问。";
					break;
				}else if(oldDataPack.characterIQ<10){
					oldDataPack.notification="我的学力似乎不支持我听懂任何人说话。";
					break;
				}else {
					oldDataPack.time+=1;					//当某个操作需要耗时，时间+1（原本的版本是计数器+1）
					oldDataPack.characterHappiness+=1;
					oldDataPack.characterEnergy-=a;
					oldDataPack.studyProgress+=2;
					oldDataPack.notification="<html>提了一个问题，解决了一些学习上的困惑";
					oldDataPack.notification += "<br>时间过去了1小时，学习进度+2，心情值+1，体力减少了一些</html>";
					break;
				}
			case "next":
				oldDataPack.time+=1;
				oldDataPack.characterEnergy-=1;
				oldDataPack.characterHappiness-=2;
				oldDataPack.notification="<html>老师开始讲下一题了，不知道刚才有没有听懂呢，真是难啊";
				oldDataPack.notification +="<br>时间过去了1小时，学习进度没变化，心情值-2，体力消耗1点</html>";
				break;
		}
		if(oldDataPack.characterEnergy<30) {
			oldDataPack.characterHealth-=1;
			oldDataPack.notification +="<br>由于体力过低，强行活动导致健康减少了</html>";
		}
		if (oldDataPack.time==12)
			oldDataPack.trigSubEvent=true; 		//到达下课时间！
		if (oldDataPack.characterHealth<=0)
			JOptionPane.showMessageDialog(null, "你猝死了", "", JOptionPane.ERROR_MESSAGE);
		return;
	}
}
