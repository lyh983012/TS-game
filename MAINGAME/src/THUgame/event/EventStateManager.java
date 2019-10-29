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
		/*********************************		
		 * 
		 * 在数据包被处理完之后，判断是否发生分支事件转移
		 * 	
		 *********************************/
		if (dataPackage.eventFinished==true){
    		switch(dataPackage.ID) {
				case -1://开始界面过后，进入选择界面
					if(dataPackage.stateA.equals("新游戏")) 
						dataPackage.ID=30001;
					break;	
    			case 0://dom界面后进入教室
    				if(dataPackage.choiceA.equals("gooutside")) {
    					dataPackage.ID=3;
    				}else if(dataPackage.stateB.equals("enrollOrganization")) {  // dom界面后进入招新界面
    					dataPackage.ID = 20001; 
    				}
    				break;
				case 1://上午界面后进入dom
					if(dataPackage.choiceA.equals("back")){
    					dataPackage.ID=3;
					}
    				break;	
				case 2://下午界面后进入dom
					if(dataPackage.choiceA.equals("back")){
    					dataPackage.ID=3;
					}
					break;	
				case 3://MAP
					if(dataPackage.choiceA=="clickbackToDom") {
						dataPackage.ID=0;
					}else if(dataPackage.choiceA=="clickGoToClassAfternoon"){
						dataPackage.ID=2;
					}else if(dataPackage.choiceA=="clickGoToClassMorning"){
						dataPackage.ID=1;
					}
					break;
				case 30000://选择界面过后，进入游戏界面
					dataPackage.ID=30002;
					break;
				case 30001:
					dataPackage.ID=30000;
					break;
				case 30002:
					dataPackage.ID=0;
					break;
				case 20001: // 招新界面结束后，进入宿舍dom界面
					dataPackage.ID=0;
					break;
        		}
        		dataPackage.clearEventState();//复原状态，以免别人的分支出问题
		}
		/*********************************		
		 * 属性限制
		 *********************************/
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
		/*********************************		
		 * 固定课表
		 *********************************/
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
