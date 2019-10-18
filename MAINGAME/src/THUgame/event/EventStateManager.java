package THUgame.event;
import THUgame.datapack.DataPack;

/*
 * 时间管理类
 * 
 * ---DIALOG---
 * update:20191018
 * via：林逸晗
 * 更新：加入统一的时间管理，每个分支事件只需要维护time，而不需要动其他东西
 * 
 * */

public class EventStateManager extends EventBase{
	public void actOn(DataPack dataPackage) {
		/*时间处理*/
		if(dataPackage.time>=24) {	//看是否变成下一天，记得查bug
			dataPackage.time%=24;
			dataPackage.date+=1;
		}
		if(dataPackage.date>=8){
			dataPackage.date%=7;
			dataPackage.week+=1;
		}
		if(dataPackage.week>=5){
			dataPackage.week%=4;
			dataPackage.term+=1;
		}
		
		if(dataPackage.characterIQ>100)dataPackage.characterIQ=100;
		if(dataPackage.characterIQ<0)dataPackage.characterIQ=0;
		if(dataPackage.characterEQ>100)dataPackage.characterEQ=100;
		if(dataPackage.characterEQ<0)dataPackage.characterEQ=0;
		if(dataPackage.characterlucky>100)dataPackage.characterlucky=100;
		if(dataPackage.characterlucky<0)dataPackage.characterlucky=0;
		if(dataPackage.characterArt>100)dataPackage.characterArt=100;
		if(dataPackage.characterArt<0)dataPackage.characterArt=0;
		if(dataPackage.characterHealth>100)dataPackage.characterHealth=100;
		if(dataPackage.characterHealth<0)dataPackage.characterHealth=0;
		if(dataPackage.characterHappiness>100)dataPackage.characterHappiness=100;
		if(dataPackage.characterHappiness<0)dataPackage.characterHappiness=0;
		if(dataPackage.characterEnergy>100)dataPackage.characterEnergy=100;
		if(dataPackage.characterEnergy<0)dataPackage.characterEnergy=0;
		if(dataPackage.studyProgress>dataPackage.studyAim)dataPackage.studyProgress=dataPackage.studyAim;
		if(dataPackage.studyProgress<0)dataPackage.studyProgress=0;
		
		
		switch(dataPackage.date) {
			case 1:
				dataPackage.todayMorningClass="微积分A";//表示早上的课
				dataPackage.todayAfternoonClass="抽象代数";//表示早上的课
				break;
			case 2:
				dataPackage.todayMorningClass="----";//表示早上的课
				dataPackage.todayAfternoonClass="抽象代数";//表示早上的课
				break;
			case 3:
				dataPackage.todayMorningClass="体育课";//表示早上的课
				dataPackage.todayAfternoonClass="英语课";//表示早上的课
				break;
			case 4:
				dataPackage.todayMorningClass="微积分A";//表示早上的课
				dataPackage.todayAfternoonClass="微积分A习题课";//表示早上的课
				break;
			case 5:
				dataPackage.todayMorningClass="机械制图";//表示早上的课
				dataPackage.todayAfternoonClass="大学语文";//表示早上的课
				break;
			case 6:
				dataPackage.todayMorningClass="----";//表示早上的课
				dataPackage.todayAfternoonClass="经济学双学位课";//表示早上的课
				break;
			case 7:
				dataPackage.todayMorningClass="----";//表示早上的课
				dataPackage.todayAfternoonClass="----";//表示早上的课
				break;
		}
		if(dataPackage.time>=12){
			dataPackage.todayMorningClass="----";//表示早上的课
		}
		if(dataPackage.time>=18){
			dataPackage.todayAfternoonClass="----";//表示早上的课
		}
	}

}
