package THUgame.subevents;
import java.util.Random;

import javax.swing.JOptionPane;

import THUgame.datapack.DataPack;
import THUgame.event.EventBase;

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

public class EventDabianClass extends EventBase{

	Random r = new Random();
	int randomMark = r.nextInt(8) + 1;
	public void actOn(DataPack oldDataPack) {
		
		/*******************************************
		 * 事件结束
		 *******************************************/
		int mark = oldDataPack.progressChallengeCup-randomMark;
		if (mark >= 93)
			oldDataPack.ChalPrize = 0;
		else if (mark >= 85)
			oldDataPack.ChalPrize = 1;
		else if (mark >= 70)
			oldDataPack.ChalPrize = 2;
		else if (mark >= 45)
			oldDataPack.ChalPrize = 3;
		else
			oldDataPack.ChalPrize = 4;
		
		int Ori_Prize = -1;
		int ori_mark = oldDataPack.progressChallengeCup;
		if (ori_mark >= 93)
			Ori_Prize = 0;
		else if (ori_mark >= 85)
			Ori_Prize = 1;
		else if (ori_mark >= 70)
			Ori_Prize = 2;
		else if (ori_mark >= 45)
			Ori_Prize = 3;
		else
			Ori_Prize = 4;
		
		int Gap = Ori_Prize - oldDataPack.ChalPrize; //>1则说明表现不佳或竞争激烈
		
		if (oldDataPack.choiceA.equals("back") ) {
			oldDataPack.eventFinished=true;		//如果选择回宿舍或者去上下午的课，终止
				oldDataPack.time=12;	
				oldDataPack.joinChallengeCup=false;
			return;								//直接返回，避免属性乱变
		}
		

		switch(oldDataPack.count) {
		case 0:
			oldDataPack.stateA = "独白";
			oldDataPack.notification="终于到了这一天，希望这一学期的努力能够让我取得一个好成绩吧";
			System.out.println("0");
			break;
		case 1:
			oldDataPack.notification="忐忑地走进了答辩教室...抓紧最后的时间再熟悉下讲稿......";
			System.out.println("1");
			break;
		case 2:
			oldDataPack.notification="等待真是漫长啊...下一个就到我了";
			System.out.println("2");
			break;
		case 3:
			oldDataPack.notification="该我上场了，放松，放松";
			System.out.println("3");
			break;
		case 4:
			oldDataPack.stateA = "我";
			String temp = "<html>尊敬的各位评委<br>我们的项目是";
			String temp2 = "......";
			oldDataPack.notification=temp+oldDataPack.ChalengeName+temp2;
			System.out.println("4");
			break;
		case 5:
			oldDataPack.stateA="独白";
			oldDataPack.notification="终于结束了，现在到场外等会，马上就要公布结果了";
			break;
		case 6:
			oldDataPack.stateA = "老师";
			oldDataPack.notification="获奖名单公布了";
			break;
		case 7:
			System.out.println("最终得分是");
			System.out.println(mark);
			switch(oldDataPack.ChalPrize) {
			case 0:
				oldDataPack.stateA = "独白";
				oldDataPack.notification="啊，我们是特等奖！努力果然没有白费！一定要好好庆祝下！";
				break;
			case 1:
				oldDataPack.stateA = "独白";
				if(Gap == 0)
					oldDataPack.notification="啊，我们是一等奖！还不错的成绩！";
				else
					oldDataPack.notification="<html>啊，我们是一等奖，还不错的成绩。<br>本以为我们做的挺不错可以拿特等奖的，但是大家都很强啊！";
				break;
			case 2:
				oldDataPack.stateA = "独白";
				oldDataPack.notification="<html>啊，我们是二等奖！<br>还算是有收获吧，要是我再努力点，或许就有更好的成绩了";
				break;
			case 3:
				oldDataPack.stateA = "独白";
				oldDataPack.notification="<html>啊，我们是三等奖。<br>当初一直想着要认真完成这个项目，但我的自我管理能力还是太差了。<br>如果能投入更多精力，就不会这样了";
				break;
			case 4:
				oldDataPack.stateA = "独白";
				oldDataPack.notification="<html>啊，我们没有获奖...<br>当初一腔热血想着要认真完成这个项目，但后来却咸鱼了起来...<br>这个结果也在意料之中，如果能投入更多精力，就不会这样了";
				break;
			}
			oldDataPack.joinChallengeCup=false;
			break;
		}			
		/*******************************************
		 * 在下面进行dataPack的处理
		 *******************************************/
		/*		START OF YOUR CODE		*/	

		
	
		return;
	}
}
