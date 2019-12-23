package THUgame.subevents;
import java.util.Random;

import javax.swing.JOptionPane;

import THUgame.datapack.DataPack;
import THUgame.event.EventBase;


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

public class EventInDom2 extends EventBase{

	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/
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
		//boolean ChallengeCupEnable = true;   //挑战杯事件触发使能  zwj 这个要写在datapack里
		if (oldDataPack.choiceA.equals("back") ) {
			oldDataPack.eventFinished=true;		//如果选择回宿舍终止
			return;								//直接返回，避免属性乱变
		}
			oldDataPack.trigSubEvent = true; // 触发子事件
			oldDataPack.stateC="挑战杯报名";
			oldDataPack.choiceA="Chalsignup";
			
			switch(oldDataPack.count) {
			case 0:
				oldDataPack.notification="突然响起了敲门声，会是谁呢？";
				break;
			case 1:
				oldDataPack.stateB = "学长";
				if(oldDataPack.joinSTA) {
					oldDataPack.notification="<html>嗨，同学你好，我是我们系科协的，一年一度的挑战杯赛事开始报名了。<br>这是相关材料和报名表，了解一下吧。<br>而且作为我们科协成员，学长们还会分享额外的宝贵经验，以及提供技术指导哦。<br>想要参加的话填写一下报名表就可以了，再见了~</html>";
				}
				else
					oldDataPack.notification="<html>嗨，同学你好，我是我们系科协的，一年一度的挑战杯赛事开始报名了。<br>这是相关材料和报名表，想要参加的话填写一下报名表就可以了，再见了~</html>";
				break;
			case 2:
				oldDataPack.stateB = "我";
				oldDataPack.notification="嗯嗯...谢谢学长，我先看看。";
				break;
			case 3:
				oldDataPack.stateB = "手册";
				oldDataPack.notification="<html>清华“挑战杯”是清华大学“挑战杯”学生课外学术科技作品竞赛的简称，<br>由清华大学教务处、清华大学科研院、清华大学研究生院、共青团清华大学委员会、<br>清华大学学生科协主办，每年举办一次.<br>是我校历史最长，规模最大，水平最高的综合性学生课外学术科技作品竞赛...（省略2000字）</html>";
				break;
			case 4:
				oldDataPack.stateB = "我";
				oldDataPack.notification="要不要参加呢,感觉会有很多收获...但是当一条咸鱼也不错。";
				break;
			case 5:
				oldDataPack.stateA="make decission";
				//出现是否报名提示，如果参加，count=6，不参加则归0,记得清零四连!
				break;
			case 6:
				oldDataPack.stateB = "我";
				oldDataPack.notification="得给项目起个拉风的名字";
				break;
			case 7:
				oldDataPack.stateA="fill name";
				//出现项目名称填写窗口
				break;
			case 8:
				oldDataPack.stateB = "我";
				oldDataPack.notification="明天开始好好努力吧！报名了挑战杯，我也顺利被科协吸纳了，以后可以天天去科协做挑战杯。";
				//oldDataPack.notification="我想我还需要寻找几名强力搭档！";
				oldDataPack.count=10;//test
				break;
			case 9:
				oldDataPack.stateA="hire result";
				//播放一段动画然后显示招募结果
				break;
			case 10:
				oldDataPack.stateB = "我";
				oldDataPack.notification="明天开始好好努力吧！";
				break;
			case 11://结束报名
				oldDataPack.stateB = "独白";
				oldDataPack.ChallengeCupEnable = false;  //关闭挑战杯事件触发使能
				oldDataPack.DabianNoticeEnable = true;
				//System.out.println(oldDataPack.DabianNoticeEnable);
				oldDataPack.stateA="";
				oldDataPack.stateC="";
				oldDataPack.trigSubEvent = false; // 触发子事件关闭
				oldDataPack.domEventFinish = true;
				oldDataPack.choiceA = "reg_end";
				oldDataPack.eventFinished=true;	
				break;
			case 12://
				oldDataPack.stateB = "独白";
				oldDataPack.notification="做一条咸鱼又有何不可呢！";
				break;
			case 13://结束报名
				oldDataPack.stateB = "独白";
				oldDataPack.ChallengeCupEnable = false;  //关闭挑战杯事件触发使能
				oldDataPack.stateA="";
				oldDataPack.stateC="";
				oldDataPack.trigSubEvent = false; // 触发子事件关闭
				oldDataPack.domEventFinish = true;
				oldDataPack.choiceA = "reg_end";
				oldDataPack.eventFinished=true;	
				break;
			case 14://提醒结束
				oldDataPack.stateB = "独白";
				oldDataPack.stateA="";
				oldDataPack.stateC="";
				oldDataPack.trigSubEvent = false; // 触发子事件关闭
				oldDataPack.DabianNoticeEnable = false;
				oldDataPack.domEventFinish = true;
				oldDataPack.choiceA = "reg_end";
				oldDataPack.eventFinished=true;	
				break;
			}			
		
/*			switch(oldDataPack.choiceA) {
			case "reg_end":
				oldDataPack.trigSubEvent = false;  // 报名结束之后待着，取消子事件
				oldDataPack.time+=1;	//报名消耗时间，时间+1 
				oldDataPack.notification = "<html>接下来干什么呢";
				oldDataPack.characterEQ += 1;
				oldDataPack.characterHappiness += 1;
				oldDataPack.characterEnergy -= 1;	
				oldDataPack.notification += "<br>社交力+1，心情值-1，体力消耗2点</html>";
				break;
		}*/
		/*		END OF YOUR CODE		*/
		return;
	}
}
