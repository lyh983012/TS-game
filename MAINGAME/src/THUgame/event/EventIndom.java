package THUgame.event;
import java.util.Random;

import javax.swing.JOptionPane;

import THUgame.datapack.DataPack;


/*
 * 宿舍事件 * 
 * 
 * --DIALOG--
 *  update:20191018
 * via：余冬杰
 * 更新：加入打呼噜
 * 
 * update:20191014
 * via：林逸晗
 * 更新：加入游戏逻辑
 * 
 * update:20191010
 * via：林逸晗
 * 更新：加入对话逻辑，使用notification存储对话的信息 
 * 
 * update:20191006 
 * via：林逸晗
 * 更新：把事件切换移除，统一在主线程里管理
 *	    计数器更换为时间，更合理
 *      完善注释
 *      增加了主要循环的事件分支，完善了 上午课-下午课-睡觉的主要循环
 *  
 * update:20190930 30
 * via：林逸晗
 * 更新：添加了“是否要让小事件显示”的属性的判断
 * 
 * */

public class EventInDom extends EventBase{

	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/
		if (oldDataPack.choiceA.equals("gooutside")) {
			oldDataPack.eventFinished=true;			//并且点击了去上课这个按钮，那么宿舍事件结束
			return;									//直接返回，避免属性乱变
		}
		oldDataPack.stateA="";//StateC用于触发游戏
		//StateC用于触发游戏
		//StateA用于判断上午下午
		//StateB用于判断是否上课
		
		/*******************************************
		 * 在下面进行dataPack的处理
		 * this.characterIQ=50;
			this.characterEQ=50;
			this.characterlucky=50;
			this.characterArt=50;
			this.characterHealth=100;		//可变的
			this.characterHappiness=50;
			this.characterEnergy=100;
			this.studyProgress=0;			//每个学期的进度
			this.studyAim=999999;			//每个学期的目标
		 *******************************************/
		/*		START OF YOUR CODE		*/	
		if(oldDataPack.characterHealth<0) {
			oldDataPack.notification="我死了。";
		}
		Random r = new Random();
		int randomTime = r.nextInt(2) + 1;
		int randomSnore = r.nextInt(10) + 1;  // 生成被吵醒的随机数
		int randomGame = r.nextInt(10) + 1;  // 生成被吵醒的随机数

		
		switch(oldDataPack.choiceA) {
			case "sleep":
				oldDataPack.notification="<html>睡醒了呢！头脑有些不清醒，但是健康和体力都增加了，人的心情也变好了。";
				if(oldDataPack.time<24 && oldDataPack.time>6){
					oldDataPack.time+=1;		//小憩一会儿，时间+1
					oldDataPack.characterEnergy+=10;
					oldDataPack.characterHealth+=1;
					oldDataPack.characterHappiness+=2;
					oldDataPack.notification += "<br>过去了1小时，心情值+2，健康值+2，体力回复10点</html>";

				}else if(randomSnore <= 5 && oldDataPack.time<3 ) {// 0点睡觉，50%触发被呼噜打醒
					oldDataPack.time=3;			//3点被吵醒
					oldDataPack.trigSubEvent = true; // 触发子事件
					oldDataPack.stateA="被吵醒";
					oldDataPack.notification += "<br>到了三点……</html>";
				}else { // 正常24点睡觉，然后睡醒
					oldDataPack.trigSubEvent = false;  // 吵醒之后睡着，取消子事件
					oldDataPack.time=7+randomTime;	//睡大觉，有机率睡爆，时间等于7点+0～2（原本的版本是计数器+1） 
					oldDataPack.characterEnergy=100;
					oldDataPack.characterHealth+=5;
					oldDataPack.characterHappiness+=5;
					oldDataPack.notification += "<br>睡到了早上，健康+5，心情+5，体力回复满</html>";
				}
				break;
			case "selfstudy":
				if(oldDataPack.characterEnergy<5) {
					oldDataPack.notification="我没有力气读书了。";
					break;
				}if(oldDataPack.characterHealth<10) {
					oldDataPack.notification="我身体很不舒服。";
					break;
				}else{
					oldDataPack.time+=1;		//自习需要耗时，时间+1（原本的版本是计数器+1）

					oldDataPack.notification="<html>写会儿作业，身体变得有些疲劳，微微有些不适";

					oldDataPack.characterEnergy-=5;
					oldDataPack.characterHappiness-=1;
					oldDataPack.studyProgress+=1;
					oldDataPack.notification += "<br>学习进度+1，心情值-1，体力消耗5点</html>";
					if(randomGame <= 5) {
						oldDataPack.stateA="game";	
						oldDataPack.time+=5;
						oldDataPack.trigSubEvent = true; // 触发子事件
					}
					break;
				}
			case "wakehimup":
				oldDataPack.trigSubEvent = false;  // 吵醒之后叫醒，取消子事件
				oldDataPack.time=7+randomTime;	//睡大觉，有机率睡爆，时间等于7点+0～2（原本的版本是计数器+1） 
				oldDataPack.notification="<html>虽然感觉安静了些，但还是觉得有些尴尬，社交力和心情稍稍下降了";
				oldDataPack.characterHappiness -= 2;
				oldDataPack.characterEQ -= 1;
				oldDataPack.characterHealth -= 1;
				oldDataPack.characterEnergy += 5;
				oldDataPack.notification += "<br>社交力-1，健康值-1，心情值-2，体力回复5点</html>";
				break;
				
			case "stayup":
				oldDataPack.trigSubEvent = false;  // 吵醒之后待着，取消子事件
				oldDataPack.time=7+randomTime;	//睡大觉，有机率睡爆，时间等于7点+0～2（原本的版本是计数器+1） 
				oldDataPack.notification = "<html>后面睡得都不太好，白天告诉了舍友，舍友非常抱歉";
				oldDataPack.characterEQ += 1;
				oldDataPack.characterHealth -= 1;
				oldDataPack.characterHappiness += 3;
				oldDataPack.characterEnergy += 5;	
				oldDataPack.notification += "<br>社交力+1，健康值-1，心情值+3，体力回复5点</html>";
				break;
		}
		if (oldDataPack.time>=8 && oldDataPack.time<=10) { //只在特定时间可以去上课
			oldDataPack.stateB="classtime";				   //用于判断是否显示按钮
		}else if(oldDataPack.time>=13 && oldDataPack.time<=15){
			oldDataPack.stateB="classtime";
		}else if(oldDataPack.trigSubEvent){				   //如果触发被吵醒子事件	
			oldDataPack.stateB="othertime";
		}else{
			oldDataPack.stateB="othertime";
		}		
		if (oldDataPack.characterHealth<=0)
			JOptionPane.showMessageDialog(null, "你猝死了", "", JOptionPane.ERROR_MESSAGE);//弹出猝死界面
		/*		END OF YOUR CODE		*/
		return;
	}
}
