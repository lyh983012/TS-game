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
						dataPackage.ID=30003;
					else if(dataPackage.stateA.equals("继续")) 
						dataPackage.ID=30004;
					break;	
				case 30003:
					dataPackage.ID=30001;
					break;
				case 30001:
					dataPackage.ID=30000;
					break;
				case 30000:
					dataPackage.ID=30002;
					break;
				case 30002:
					dataPackage.ID=0;//选择界面过后，进入游戏界面
					break;
				case 30004:
					if(dataPackage.stateA.equals("backhome")) {
						dataPackage.ID=-1;//如果没有选择，回到主界面开始新游戏
					}else {
						dataPackage.ID=0;//如果选择了，就进入宿舍
				        System.out.println("s");
					}
					break;
    			case 0://dom界面
    				if(dataPackage.choiceA.equals("gooutside")) {
    					dataPackage.ID=3;
    				}else if(dataPackage.stateB.equals("enrollOrganization")) {  // dom界面后进入招新界面
    					dataPackage.ID = 20001; 
    				}else if(dataPackage.stateA.equals("OrgNotification")) {  // dom界面后进入招新通知时间
    					dataPackage.ID = 200011; 
    				}
    				break;
				case 1://上午界面
					if(dataPackage.choiceA.equals("back")){
    					dataPackage.ID=3;
					}
    				break;	
				case 2://下午界面
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
					}else if(dataPackage.choiceA=="clickGoToSTA"){
						dataPackage.ID=20016;
					}else if(dataPackage.choiceA.equals("clickGoToSUPE")) {
						int inEvent = 0;  // 标记是否处于子事件的发生时间
							if (dataPackage.term == 1 && dataPackage.week == 1 && 
								dataPackage.date == 4 &&
								dataPackage.time >= 13 && dataPackage.time <= 14) {
								inEvent = 2;
							}

							if (dataPackage.term == 1 && dataPackage.week == 2 && 
								dataPackage.date == 4 &&
								dataPackage.time >= 21 && dataPackage.time <= 22) {
								if (dataPackage.SUPEmentor == 1) inEvent = 3; //  汪师傅才会触发517A的第一次活动
							}

							if (dataPackage.term == 1 && dataPackage.week == 3 && 
								dataPackage.date == 2 &&
								dataPackage.time >= 22 && dataPackage.time <= 23) {
								inEvent = 4; // 第二次例会
							}

						// 正式T2W1D2 22:00-23:00
						// 调试T1W3D4 22:00-23:00	
							if (dataPackage.term == 2 && dataPackage.week == 1 && 
								dataPackage.date == 2 &&
								dataPackage.time >= 22 && dataPackage.time <= 23) {
								inEvent = 5; // 第三次例会
							}
						// 正式T2W1D6 10:00-12:00
						// 调试T1W3D5 10:00-12:00
							if (dataPackage.term == 2 && dataPackage.week == 1 && 
								dataPackage.date == 6 &&
								dataPackage.time >= 10 && dataPackage.time <= 12) {
								inEvent = 6; // 第二次活动
							}
						// 正式T2W2D2 22:00-23:00
						// 正式T1W3D6 10:00-12:00	
							if (dataPackage.term == 2 && dataPackage.week == 2 && 
								dataPackage.date == 2 &&
								dataPackage.time >= 22 && dataPackage.time <= 23) {
								inEvent = 7; // 第四次例会
							}
						if (inEvent > 0) {
							dataPackage.ID=200010 + inEvent;  //20001X, X>=2
						}else {
							dataPackage.ID = 200010;
						}
					}else if (dataPackage.choiceA.equals("clickGoToC")) {
						boolean inevent = false;  // 标记是否处于子事件的发生时间
						if (dataPackage.term == 1 && dataPackage.week == 2 && 
							dataPackage.date == 4 &&
							dataPackage.time >= 21 && dataPackage.time <= 22) {
							if (dataPackage.SUPEmentor == 2)   inevent = true; //  章师傅才会触发C楼的第二次活动
						}
						if (inevent) {
							dataPackage.ID=200013;  //20001X, X>=2
						}else {
							dataPackage.ID = 2000131;
						}
					}else if (dataPackage.choiceA.equals("clickGoToEast")){
						// 正式T2W2D4 12:00-13:00
						// 调试T1W3D7 12:00-13:00
						if (dataPackage.term == 2 && dataPackage.week == 2 && 
							dataPackage.date == 4 &&
							dataPackage.time >= 12 && dataPackage.time <= 13) {
							dataPackage.ID=200018;
						}
					}
					//TODO:MAP中加入517A
					break;
				case 20016://STA
					dataPackage.ID=3;/*存疑*/
					break;
				case 20001: // 招新界面结束后，进入宿舍dom界面
					dataPackage.ID=0;
					break;
				case 200011: // 招新通知事件结束后，进入宿舍dom界面
					dataPackage.ID=0;
					break;
				case 200012:
					dataPackage.ID=0;
					break;
				case 200013:	// 体育部第一次活动事件结束后，进入宿舍界面
					dataPackage.ID=0;
					break;
				case 200014:	// 体育部第二次例会结束后，进入宿舍界面
					dataPackage.ID=0;
					break;
				case 200015:	// 体育部第三次例会结束后，进入宿舍界面
					dataPackage.ID=0;
					break;
				case 200016:	// 体育部第二次活动结束后，进入宿舍界面
					dataPackage.ID=0;
					break;
				case 200017:	// 体育部第四次例会结束后，进入宿舍界面
					dataPackage.ID=0;
					break;
				case 200018:	// 体育部第三次活动结束后，进入宿舍界面
					dataPackage.ID=0;
					break;
				case 200010:    // 到了体育部但是什么也没有发生
					dataPackage.ID = 3;
					break;
				case 2000131:    // 到了C楼但是什么也没有发生
					switch (dataPackage.choiceA) {
					case "backToMap":
						dataPackage.ID = 3;
						break;
					case "goToMarket":
						dataPackage.ID = 2000132;
						break;
					}
					break;
				case 2000132:    // 到了超市但是什么也没有发生
					dataPackage.ID = 3;
					break;
				//TODO:case20001X 体育部例会
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
