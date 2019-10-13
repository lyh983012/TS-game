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
 *   增加了主要循环的事件分支，完善了 上午课-下午课-睡觉的主要循环
 * 
 * update:20191010
 * 更新：
 * 	 加入对话逻辑，使用notification存储对话的信息 
 * */

public class EventIndom extends EventBase{

	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/
		if (oldDataPack.choiceA.equals("gotoclass")) {
			oldDataPack.eventFinished=true;			//并且点击了去上课这个按钮，那么宿舍事件结束
			return;									//直接返回，避免属性乱变
		}
		
		/*******************************************
		 * 在下面进行dataPack的处理
		 *******************************************/
		/*		START OF YOUR CODE		*/	
		if(oldDataPack.characterHealth<0) {
			oldDataPack.notification="我死了。";
		}
		Random r = new Random();
		int randomValue = r.nextInt(8) + 1;
		int randomTime = r.nextInt(2) + 1;
		switch(oldDataPack.choiceA) {
			case "sleep":
				if(oldDataPack.time<24 && oldDataPack.time>6){ // 24点之前睡
					oldDataPack.time+=1;		//小憩一会儿，时间+1（原本的版本是计数器+1）
					if(oldDataPack.time>=24) {	//看是否变成下一天，记得查bug
						oldDataPack.time%=24;
						oldDataPack.date+=1;
					}
				}else if(oldDataPack.date == 2 && oldDataPack.time == 0) { // 学期第二天0点睡觉，触发被呼噜打醒
					oldDataPack.time=3;	//3点被打醒
					oldDataPack.notification="";
					oldDataPack.characterHappiness -= 2;
					oldDataPack.characterEnergy -= 2;
					
					oldDataPack.trigSubEvent = true;
					break;
				}else { // 正常24点睡觉，然后睡醒
					oldDataPack.trigSubEvent = false;  // 吵醒之后睡着，取消子事件
					oldDataPack.time=7+randomTime;	//睡大觉，有机率睡爆，时间等于7点+0～2（原本的版本是计数器+1） 
				}
				oldDataPack.notification="睡醒了呢！头脑有些不清醒，但是健康和体力都增加了，人的心情也变好了。";
				oldDataPack.characterIQ-=randomValue;
				oldDataPack.characterHealth+=2;
				oldDataPack.characterHappiness+=5;
				oldDataPack.characterEnergy+=5;
				
				break;
			case "selfstudy":
				oldDataPack.trigSubEvent = false;  // 吵醒之后自习，取消子事件
				if(oldDataPack.characterEnergy<5) {
					oldDataPack.notification="我没有力气读书了。";
					break;
				}else if(oldDataPack.characterIQ<0){
					oldDataPack.notification="我的学力似乎不支持我看懂书上的字。";
					break;
				}else {
					oldDataPack.time+=1;		//自习需要耗时，时间+1（原本的版本是计数器+1）
					if(oldDataPack.time>=24) {  //看是否变成下一天
						oldDataPack.time%=24;
						oldDataPack.date+=1;
					}
					oldDataPack.notification="再写会儿作业，身体变得有些疲劳，微微有些不适";
					oldDataPack.characterIQ+=randomValue;
					if(oldDataPack.characterEnergy>10)
						oldDataPack.characterEnergy-=8;
					else
						oldDataPack.characterHealth-=5;
					oldDataPack.characterEnergy-=5;
					oldDataPack.characterHappiness-=2;
					break;
				}
			case "wakehimup":
				oldDataPack.trigSubEvent = false;  // 吵醒之后叫醒，取消子事件
				oldDataPack.time=7+randomTime;	//睡大觉，有机率睡爆，时间等于7点+0～2（原本的版本是计数器+1） 
				oldDataPack.notification="虽然感觉安静了些，但还是觉得有些尴尬，社交力和心情稍稍下降了";
				oldDataPack.characterHappiness -= 1;
				oldDataPack.characterEQ -= 1;
				
				oldDataPack.characterIQ -= randomValue;
				oldDataPack.characterHealth += 2;
				oldDataPack.characterHappiness += 3;
				oldDataPack.characterEnergy += 3;
				break;
				
			case "stayup":
				oldDataPack.trigSubEvent = false;  // 吵醒之后待着，取消子事件
				oldDataPack.time=7+randomTime;	//睡大觉，有机率睡爆，时间等于7点+0～2（原本的版本是计数器+1） 
				oldDataPack.notification = "后面睡得都不太好，白天告诉了舍友，舍友非常抱歉，社交力上升了";
				oldDataPack.characterEQ += 1;
				
				oldDataPack.characterIQ -= randomValue;
				oldDataPack.characterHealth += 1;
				oldDataPack.characterHappiness += 4;
				oldDataPack.characterEnergy += 2;	
				break;
		}
		
		// 发生动作后的状态转移判定
		if (oldDataPack.time==8 || oldDataPack.time==10) { //只在特定时间可以去上课
			oldDataPack.stateA="上早上课"; 				   //判断上午还是下午
			oldDataPack.stateB="classtime";				   //用于判断是否显示按钮
		}else if(oldDataPack.time==13 || oldDataPack.time==15){
			oldDataPack.stateA="上下午课"; 				   //同理
			oldDataPack.stateB="classtime";
		}
		else if(oldDataPack.trigSubEvent){				   //如果触发被吵醒子事件
			oldDataPack.stateA="被吵醒";
			oldDataPack.stateB="othertime";
		}else {
			oldDataPack.stateA="自习";
			oldDataPack.stateB="othertime";
		}
		
		if (oldDataPack.characterHealth<=0)
			JOptionPane.showMessageDialog(null, "你猝死了", "", JOptionPane.ERROR_MESSAGE);//弹出猝死界面
		/*		END OF YOUR CODE		*/
		return;
	}
}
