package THUgame.event;
import java.util.Random;

import javax.swing.JOptionPane;

import THUgame.datapack.DataPack;


/*
 * 招新事件 * 
 * 
 * --DIALOG--
 *  update:20191029
 *  via：余冬杰
 * 更新：我要开始写招新界面啦
 * 
 * */

public class EventOrganization extends EventBase{

	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/
		if (oldDataPack.choiceA.equals("gooutside")) {
			oldDataPack.eventFinished=true;			//并且点击了去上课这个按钮，那么宿舍事件结束
			return;									//直接返回，避免属性乱变
		}
		
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
		int randomValue = r.nextInt(8) + 1;
		int randomTime = r.nextInt(2) + 1;
		int randomSnore = r.nextInt(10) + 1;  // 生成被吵醒的随机数
		
		switch(oldDataPack.choiceA) {
			case "sleep":
				oldDataPack.notification="<html>睡醒了呢！头脑有些不清醒，但是健康和体力都增加了，人的心情也变好了。";
				if(oldDataPack.time<24 && oldDataPack.time>6){
					oldDataPack.time+=1;		//小憩一会儿，时间+1
					oldDataPack.characterEnergy+=10;
					oldDataPack.characterHealth+=1;
					oldDataPack.characterHappiness+=2;
					oldDataPack.notification += "<br>过去了1小时，心情值+2，健康值+2，体力回复10点</html>";
				}else if(randomSnore <= 5 && oldDataPack.time<3 ) { 
												// 0点睡觉，50%触发被呼噜打醒
					oldDataPack.time=3;			//3点被吵醒
					oldDataPack.trigSubEvent = true; // 触发子事件
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
					
					break;
				}
			case "wakehimup":
				
				break;
				
			case "stayup":
				
				break;
		}
		if (oldDataPack.time>=8 && oldDataPack.time<=10) { //只在特定时间可以去上课
			oldDataPack.stateA="上早上课"; 				   //判断上午还是下午
			oldDataPack.stateB="classtime";				   //用于判断是否显示按钮
		}else if(oldDataPack.time>=13 && oldDataPack.time<=15){
			oldDataPack.stateA="上下午课"; 				   //同理
			oldDataPack.stateB="classtime";
		}else if(oldDataPack.trigSubEvent){				   //如果触发被吵醒子事件
			oldDataPack.stateA="被吵醒";
			oldDataPack.stateB="othertime";
		}else{
			oldDataPack.stateA="自习";
			oldDataPack.stateB="othertime";
		}	
		if (oldDataPack.characterHealth<=0)
			JOptionPane.showMessageDialog(null, "你猝死了", "", JOptionPane.ERROR_MESSAGE);//弹出猝死界面
		/*		END OF YOUR CODE		*/
		return;
	}
}
