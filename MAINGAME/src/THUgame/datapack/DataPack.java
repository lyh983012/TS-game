package THUgame.datapack;

/*数据包模板（请保持最新）
 * template version 1.1
 * 
 * update:20191018 01:30
 * update:20191006 19:30
 * 增加了时间
 * update:20190928 19:30
 * 

 * */


public class DataPack {

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
	
	
	/*******************************************************
	 * 自己定义的分支事件的ID，之后写在同一个文件中方便查阅
	 *例如：考试的事件ID:"123123；
	 *demo事件的ID为0，正式分支从1开始
	 *将转场动画、成就系统显示、初始等都设计为分支事件
	 *******************************************************/
	public int ID=0;
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
	public String todayAfternoonClass="";//表示早上的课
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
	
	public void clearEventState() {
		notification="";//表示对话信息
		todayMorningClass="";//表示早上的课
		todayAfternoonClass="";//表示早上的课
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
		count=0;
	}
	public DataPack(String type){
		if(type.equals("demo")) {
			this.ID=0;
			this.date=1;
			this.term=1;
			this.name="2016000001";
			this.studentID="";
			this.characterIQ=50;
			this.characterEQ=50;
			this.characterHealth=90;
			this.characterHappiness=50;
			this.count=0;
			this.eventFinished=false;
			this.stateA="自习";
		}
		if(type.equals("choice demo")) {
			this.ID=-1;
			this.date=1;
			this.term=1;
			this.name="特奖杰哥";
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
		}
	}
		
}
