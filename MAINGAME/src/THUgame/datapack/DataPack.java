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
	public int characterIQ=0;
	public int characterEQ=0;
	public int characterHealth=0;
	public int characterHappiness=0;
	public int characterEnergy=0;
	public int characterStrength=0;
	/*******************************************************
	 * 人物隐藏属性模板  
	 *******************************************************/
	public boolean nightOwl=false;
	public boolean scientificMad=false;
	public boolean sportsMan=false;
	public boolean eSportsPlayer=false;
	public boolean computerExpert=false;
	public boolean nextWinner=false;
	public boolean richPerson=false;
	/*******************************************************
	 * 自己定义的分支事件的ID，之后写在同一个文件中方便查阅
	 *例如：考试的事件ID:"123123；
	 *demo事件的ID为0，正式分支从1开始
	 *将转场动画、成就系统显示、初始等都设计为分支事件
	 *******************************************************/
	public int ID=0;
	/*******************************************************
	 * 
	 * 用于事件的构建的属性，尽量大家统一一下
	 * 
	 *******************************************************/
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
			this.name="神秘杰哥";
			this.studentID="2016000001";
			this.characterIQ=50;
			this.characterEQ=50;
			this.characterHealth=90;
			this.characterHappiness=50;
			this.characterStrength=10;
			this.count=0;
			this.eventFinished=false;
			this.stateA="自习";
		}
		if(type.equals("choice demo")) {
			this.ID=-1;
			this.date=1;
			this.term=1;
			this.name="神秘杰哥";
			this.studentID="2016000001";
			this.characterIQ=50;
			this.characterEQ=50;
			this.characterHealth=90;
			this.characterHappiness=50;
			this.characterStrength=10;
			this.count=0;
			this.eventFinished=false;
			this.stateA="自习";
		}
	}
		
}
