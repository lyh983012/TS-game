package THUgame.event;
import java.util.Iterator;
import THUgame.datapack.DataPack;
import THUgame.tool.CourseGrade;
import THUgame.tool.Courses;

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
					dataPackage.ID=0;//进入宿舍系统
					break;
				case 4:
					dataPackage.ID=0;//选颗界面过后，进入游戏界面
					break;
				case 5:
					dataPackage.ID=0;//退颗界面过后，进入游戏界面
					break;
				case 30004:
					if(dataPackage.stateA.equals("backhome")) {
						dataPackage.ID=-1;//如果没有选择，回到主界面开始新游戏
					}else {
						dataPackage.ID=0;//如果选择了，就进入宿舍
					}
					break;
    			case 0://dom界面
    				if(dataPackage.choiceA.equals("gooutside")) {
    					dataPackage.ID=3;
    				}
    				if(dataPackage.choiceA.equals("takeExam")) {
    					dataPackage.ID=3;
    				}
    				if(dataPackage.choiceA.equals("readMessage_research_login")) {
    					dataPackage.ID=21000;//进入阅读信息
    				}
    				if(dataPackage.choiceA.equals("readMessage_research_result")) {
    					dataPackage.ID=21001;//进入阅读信息
    				}
    				if(dataPackage.choiceA.equals("need_course_reg")) {
    					Courses.courseListInit(dataPackage.term);
    					dataPackage.ID=4;//选课
    				}
    				if(dataPackage.choiceA.equals("need_course_withdraw")) {
    					Courses.courseListInit(dataPackage.term);
    					dataPackage.ID=5;//退课
    				}
    				if(dataPackage.choiceA.equals("endgame")) {
    					dataPackage.ID=40000;//animated
    				}
    				if(dataPackage.choiceA.equals("readpaper")) {
    					dataPackage.ID=21010;//animated
    				}
    				break;
				case 20016://STA
					dataPackage.ID=3;//to map
					break;
				case 21000:
					dataPackage.ID=0;
					break;
				case 21001:
					dataPackage.ID=0;
					break;
				case 21002:
					dataPackage.ID=3;//to map
					break;
				case 21003:
					dataPackage.ID=3;//to map
					break;
				case 21004:
					dataPackage.ID=3;//to map
					break;
				case 21005:
					dataPackage.ID=3;//to map
					break;
				case 21006:
					dataPackage.ID=3;//to map
					break;
				case 21007:
					dataPackage.ID=3;//to map
					break;
				case 21008:
					dataPackage.ID=3;//to map
					break;
				case 21009:
					dataPackage.ID=3;//to map
					break;
				case 21010:
					dataPackage.ID=0;//to dom
					break;
				case 40000:
					dataPackage.ID=40001;
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
					if(dataPackage.choiceA.equals("clickbackToDom")) {
						dataPackage.ID=0;
					}else if(dataPackage.choiceA.equals("clickGoToClassAfternoon")){
						dataPackage.ID=2;
					}else if(dataPackage.choiceA.equals("clickGoToClassMorning")){
						dataPackage.ID=1;
					}else if(dataPackage.choiceA.equals("clickGoToSTA")){
						dataPackage.ID=20016;
					}else if(dataPackage.choiceA.equals("clickGoToExam")){
						dataPackage.ID=20003;
					}else if(dataPackage.choiceA.equals("clickGoToLab1")) {
						dataPackage.ID=21002;
					}else if(dataPackage.choiceA.equals("clickGoToLab2")) {
						dataPackage.ID=21003;
					}else if(dataPackage.choiceA.equals("clickGoToLab3")) {
						dataPackage.ID=21004;
					}else if(dataPackage.choiceA.equals("clickGoToLab4")) {
						dataPackage.ID=21005;
					}else if(dataPackage.choiceA.equals("clickGoToLab5")) {
						dataPackage.ID=21006;
					}else if(dataPackage.choiceA.equals("clickGoToLab6")) {
						dataPackage.ID=21007;
					}else if(dataPackage.choiceA.equals("clickGoToLab7")) {
						dataPackage.ID=21008;
					}else if(dataPackage.choiceA.equals("clickGoToLab8")) {
						dataPackage.ID=21009;
					}
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
		dataPackage.todayMorningClass="----";//表示早上的课
		dataPackage.todayAfternoonClass="----";//表示早上的课
		for(int i=0;i<dataPackage.courseGradeCount;i++) {
			CourseGrade tmp=dataPackage.courseGrade[i];
			Courses course=tmp.course;
			if(course.courseTerm==dataPackage.term)
				if(course.classTime1==dataPackage.date*10+1 || course.classTime2==dataPackage.date*10+1) {
					dataPackage.todayMorningClass=course.courseTitle;//表示早上的课
				}else if (course.classTime1==dataPackage.date*10+2 || course.classTime2==dataPackage.date*10+2) {
					dataPackage.todayAfternoonClass=course.courseTitle;
				}
		}
		if(dataPackage.time>=12){
			dataPackage.todayMorningClass="----";//表示早上的课
		}
		if(dataPackage.time>=18){
			dataPackage.todayAfternoonClass="----";//表示早上的课
		}
	}

}
