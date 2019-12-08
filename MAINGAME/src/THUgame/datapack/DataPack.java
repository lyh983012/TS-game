package THUgame.datapack;
import java.io.Serializable;

import THUgame.tool.CourseGrade;
import THUgame.tool.Courses;
import THUgame.tool.ResearchData;

/*数据包模板（请保持最新）
 * update:20191114 00:47
 * 实现对象序列化，是存读档的前提
 * 
 * template version 1.1
 * update:20191029 01:30
 * 增加了科协需要的贡献度、科创能力属性
 * 增加了课程表等成员
 * 
 * update:20191018 01:30
 * update:20191006 19:30
 * 增加了时间
 * update:20190928 19:30
 * 

 * */


public class DataPack implements Serializable , Cloneable{

	/* 环境状态的值 */
	public int date=1;
	public int week=1;
	public int term=1;
	public int time=8;
	/* *******************************************************
	 * 人物状态的值
	 * *******************************************************/
	public String name="";
	public String studentID="";
	public String sex="";
	public int characterIQ=50;
	public int characterEQ=50;
	public int characterlucky=50;
	public int characterArt=50;
	public int characterHealth=100;		//可变的
	public int characterHappiness=50;
	public int characterEnergy=100;
	public int studyProgress=0;			//每个学期的进度
	public int studyAim=999999;			//每个学期的目标
	/*******************************************************
	 * 人物隐藏属性
	 *******************************************************/
	public boolean nightOwl=false;		//夜猫子
	public boolean scientificMad=false;	//科研狂魔
	public boolean sportsMan=false;		//体育王者
	public boolean eSportsPlayer=false; //电竞选手
	public boolean computerExpert=false;//电脑高手
	public boolean nextWinner=false;	//社工大佬
	public boolean richPerson=false;	//家里有矿
	/*******************************************************
	 * 事件状态
	 *******************************************************/
	public boolean joinSTA=false;		//加入了科协				//state000
	public boolean joinClub=false;		//加入兴趣社团-通过隐藏属性设定社团名字
																//state001
	public boolean joinSA=false;		//加入学生会				//state002
	public boolean joinStudentFestival=false; //参加学生节		//state003
	public boolean joinFreshmanGame=false;	  //报名新生赛		//state004
	public boolean gainMedalinFreshmanGame=false;//新生赛获奖		//state005
	public boolean failedAClass=false;		//挂了一科			//state006
	public int joinChallengeCup=0;		//参加挑战杯的进度，max为100//state007
	public boolean joinResearch=false;	//加入SRT课题组			//state008
	public int paperFinished=0;			//paper的进度，max为100	//state009
	public boolean beAChairMan=false;	//当选主席				//state010
	public boolean paperPublised=false;	//paper发表了				//state011
	public boolean holdStudentFestival=false;//负责学生节筹办		//state012
	public boolean holdFreshmanGame=false;	 //负责新生赛筹办		//state013
	public int contibuteSTA=0;		//科协贡献度
	public int STcap=0;				//科创能力，和新生赛以及挑战杯等有关系
	
	/*******************************************************
	 * 自己定义的分支事件的ID，之后写在同一个文件中方便查阅
	 *例如：考试的事件ID:"123123；
	 *demo事件的ID为0，正式分支从1开始
	 *将转场动画、成就系统显示、初始等都设计为分支事件
	 *******************************************************/
	public int ID=0;
	/*******************************************************
	* 与课程有关的属性
	 * 已修课程成绩单courseGrade:每一门修过的课是CourseGrade类的一个对象
	 ********************************************************/
	public boolean course_selected=false;
	public CourseGrade[] courseGrade; //已修课程成绩单及本学期选课课程
	public int courseGradeCount=0; //“已修课程成绩单”数组中有多少个元素
	/*******************************************************
	 * stateInEvent表示在某个支线中走到了第几步，可以自己定义一下协议
	 * 例如：stateInEvent="1231";
	 * 在第一个判断分枝选择1
	 * 第二个判断分枝选择2
	 * 第三个判断分枝选择3
	 * 第四个判断分枝选择1
	 * ******************************************************/
	public String stateInEvent="";
	public String nextStep="";//表示下一步
	public String notification="";//表示对话信息
	public String todayMorningClass="";//表示早上的课
	public String todayAfternoonClass="";//表示下午的课
	public String choiceA="";//表示事件的A选项
	public String choiceB="";//表示事件的B选项
	public String choiceC="";//表示事件的C选项
	public String choiceD="";//表示事件的D选项
	public String choiceE="";//表示事件的E选项...不要再多了
	public String stateA="";//表示事件的A状态
	public String stateB="";//表示事件的B状态
	public String stateC="";//表示事件的C状态
	public String stateD="";//表示事件的D状态
	public String stateE="";//表示事件的D状态...不要再多了
	public String SendSelfdefine="";//如果还需要更多信息，自己使用分隔符进行数据传输
	public String feedbackSelfdefine="";//如果还需要更多信息，自己使用分隔符进行数据传输
	public int count=0;//表示第几次点击该事件
	public boolean eventFinished=false;
	public boolean trigSubEvent =false;
	
	public ResearchData researchDataPackage;
	
	public void clearEventState() {
		notification="";//表示对话信息
		todayMorningClass="";//表示早上的课
		todayAfternoonClass="";//表示下午的课
		choiceA="";//表示事件的A选项
		choiceB="";//表示事件的B选项
		choiceC="";//表示事件的C选项
		choiceD="";//表示事件的D选项
		choiceE="";//表示事件的E选项
		stateA="";//表示事件的A状态
		stateB="";//表示事件的B状态
		stateC="";//表示事件的C状态
		stateD="";//表示事件的D状态
		stateE="";//表示事件的D状态
		SendSelfdefine="";//如果还需要更多信息，自己使用分隔符进行数据传输
		feedbackSelfdefine="";//如果还需要更多信息，自己使用分隔符进行数据传输
		count=0;//表示第几次点击该事件
		eventFinished=false;
		trigSubEvent = false;
		nextStep="";
		stateInEvent="";
		
	}
	public DataPack(String type){
		if(type.equals("test")) {
			this.ID=-1;
			this.date=1;
			this.week=1;
			this.term=1;
			this.time=8;
			this.studentID="2016000001";
			this.characterIQ=70;
			this.characterEQ=50;
			this.characterlucky=50;
			this.characterArt=50;
			this.characterHealth=100;		//可变的
			this.characterHappiness=50;
			this.characterEnergy=100;
			this.studyProgress=0;			//每个学期的进度
			this.studyAim=999999;			//每个学期的目标
			this.count=0;
			this.eventFinished=false;
			this.stateA="自习";
			//课程相关属性
			this.researchDataPackage= new ResearchData();
			this.courseGrade=new CourseGrade[14*4]; //这里14*4是假设每学期最多选14门课（每学期最多选多少门课写在Courses类里了，注意同步
			this.courseGradeCount=0;
			//仅用于调试科研地图
			//this.joinResearch = true;
		}
		if(type.equals("main")) {
			this.ID=-1;
			this.studentID="2016000001";
			this.characterIQ=50;
			this.characterEQ=50;
			this.characterlucky=50;
			this.characterArt=50;
			this.characterHealth=100;		//可变的
			this.characterHappiness=50;
			this.characterEnergy=100;
			this.studyProgress=0;			//每个学期的进度
			this.studyAim=999999;			//每个学期的目标
			this.count=0;
			this.eventFinished=false;
			this.stateA="自习";
			//课程相关属性
			this.researchDataPackage= new ResearchData();
			this.courseGrade=new CourseGrade[14*4]; //这里14*4是假设每学期最多选14门课（每学期最多选多少门课写在Courses类里了，注意同步
			this.courseGradeCount=0;
		}
	}
	public void copyElements(DataPack tmp) {
		date=tmp.date;
		researchDataPackage=tmp.researchDataPackage;
		week=tmp.week;
		term=tmp.term;
		time=tmp.time;
		name=tmp.name;
		studentID=tmp.studentID;
		sex=tmp.sex;
		characterIQ=tmp.characterIQ;
		characterEQ=tmp.characterEQ;
		characterlucky=tmp.characterlucky;
		characterArt=tmp.characterArt;
		characterHealth=tmp.characterHealth;		//可变的
		characterHappiness=tmp.characterHappiness;
		characterEnergy=tmp.characterEnergy;
		studyProgress=tmp.studyProgress;			//每个学期的进度
		studyAim=tmp.studyAim;			//每个学期的目标
		nightOwl=tmp.nightOwl;		//夜猫子
		scientificMad=tmp.scientificMad;	//科研狂魔
		sportsMan=tmp.sportsMan;		//体育王者
		eSportsPlayer=tmp.eSportsPlayer; //电竞选手
		computerExpert=tmp.computerExpert;//电脑高手
		nextWinner=tmp.nextWinner;	//社工大佬
		richPerson=tmp.richPerson;	//家里有矿
	    joinSTA=tmp.joinSTA;		//加入了科协				//state000
		joinClub=tmp.joinClub;		//加入兴趣社团-通过隐藏属性设定社团名字												//state001
		joinSA=tmp.joinSA;		//加入学生会				//state002
		joinStudentFestival=tmp.joinStudentFestival; //参加学生节		//state003
		joinFreshmanGame=tmp.joinFreshmanGame;	  //报名新生赛		//state004
		gainMedalinFreshmanGame=tmp.gainMedalinFreshmanGame;//新生赛获奖		//state005
		failedAClass=tmp.failedAClass;		//挂了一科			//state006
		joinChallengeCup=tmp.joinChallengeCup;		//参加挑战杯的进度，max为100//state007
		joinResearch=tmp.joinResearch;	//加入SRT课题组			//state008
		paperFinished=tmp.paperFinished;			//paper的进度，max为100	//state009
		beAChairMan=tmp.beAChairMan;	//当选主席				//state010
		paperPublised=tmp.paperPublised;	//paper发表了				//state011
		holdStudentFestival=tmp.holdStudentFestival;//负责学生节筹办		//state012
		holdFreshmanGame=tmp.holdFreshmanGame;	 //负责新生赛筹办		//state013
		contibuteSTA=tmp.contibuteSTA;		//科协贡献度
		STcap=tmp.STcap;				//科创能力，和新生赛以及挑战杯等有关系
		ID=tmp.ID;
		courseGrade=tmp.courseGrade; //已修课程成绩单及本学期选课课程
		courseGradeCount=tmp.courseGradeCount; //“已修课程成绩单”数组中有多少个元素
		stateInEvent=tmp.stateInEvent;
		nextStep=tmp.nextStep;//表示下一步
		notification=tmp.notification;//表示对话信息
		todayMorningClass=tmp.todayMorningClass;//表示早上的课
		todayAfternoonClass=tmp.todayAfternoonClass;//表示早上的课
		choiceA=tmp.choiceA;//表示事件的A选项
		choiceB=tmp.choiceB;//表示事件的B选项
		choiceC=tmp.choiceC;//表示事件的C选项
		choiceD=tmp.choiceD;//表示事件的D选项
		choiceE=tmp.choiceE;//表示事件的E选项...不要再多了
		stateA=tmp.stateA;//表示事件的A状态		  stateB="";//表示事件的B状态
		stateB=tmp.stateB;//表示事件C状态
		stateC=tmp.stateC;//表示事件C状态
		stateD=tmp.stateD;//表示事的D状态
		stateE=tmp.stateE;//表示事件的D状态...不要再多了
		SendSelfdefine=tmp.SendSelfdefine;//如果还需要更多信息，自己使用分隔符进行数据传输
		feedbackSelfdefine=tmp.feedbackSelfdefine;//如果还需要更多信息，自己使用分隔符进行数据传输
		count=tmp.count;//表示第几次点击该事件
		eventFinished=tmp.eventFinished;
		trigSubEvent =tmp.trigSubEvent;
		course_selected=tmp.course_selected;
	}
}
