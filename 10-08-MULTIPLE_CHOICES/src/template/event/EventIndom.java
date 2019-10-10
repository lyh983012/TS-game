package template.event;
import java.util.Random;

import javax.swing.JOptionPane;

import template.datapack.DataPack;


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
		Random r = new Random();
		int randomValue = r.nextInt(8) + 1;
		int randomTime = r.nextInt(2) + 1;
		switch(oldDataPack.choiceA) {
			case "sleep":
				if(oldDataPack.time<24 && oldDataPack.time>6){
					oldDataPack.time+=1;		//小憩一会儿，时间+1（原本的版本是计数器+1）
					if(oldDataPack.time>=24) {	//看是否变成下一天，记得查bug
						oldDataPack.time%=24;
						oldDataPack.date+=1;
					}
				}else {
					oldDataPack.time=7+randomTime;	//睡大觉，有机率睡爆，时间等于7点+0～2（原本的版本是计数器+1） 
				}
				oldDataPack.characterIQ-=randomValue;
				oldDataPack.characterHealth+=2;
				oldDataPack.characterHappiness+=5;
				oldDataPack.characterEnergy+=5;
				break;
			case "selfstudy":
				oldDataPack.time+=1;		//自习需要耗时，时间+1（原本的版本是计数器+1）
				if(oldDataPack.time>=24) {  //看是否变成下一天
					oldDataPack.time%=24;
					oldDataPack.date+=1;
				}
				oldDataPack.characterIQ+=randomValue;
				if(oldDataPack.characterEnergy>10)
					oldDataPack.characterEnergy-=8;
				else
					oldDataPack.characterHealth-=5;
				oldDataPack.characterEnergy-=5;
				oldDataPack.characterHappiness-=2;
				break;
		}
		if (oldDataPack.time==8 || oldDataPack.time==10) { //只在特定时间可以去上课
			oldDataPack.stateA="上早上课"; 				   //判断上午还是下午
			oldDataPack.stateB="classtime";				   //用于判断是否显示按钮
		}else if(oldDataPack.time==13 || oldDataPack.time==15){
			oldDataPack.stateA="上下午课"; 				   //同理
			oldDataPack.stateB="classtime";
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
