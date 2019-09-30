package template.datapack;

/*
 * template version 1.1
 * 数据包模板（不可改）
 * update:20190928 19:30
 * */


public class DataPack {

	/* 环境状态的值 */
	public String date;
	public String term;
	public int dayOrNight;
	
	/* 人物状态的值 */
	public String name="";
	public String studentID="";
	public int characterIQ=0;
	public int characterEQ=0;
	public int characterHealth=0;
	public int characterHappiness=0;
	public int characterStrength=0;
	
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
	public String notification="";//表示对话信息
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
	public boolean trigSubEvent = false;
	
	public void clearEventState() {
		notification="";//表示对话信息
		choiceA="";//表示事件的A选项
		choiceB="";//表示事件的B选项
		choiceC="";//表示事件的C选项
		choiceD="";//表示事件的D选项
		choiceE="";//表示事件的E选项...不要再多了
		stateA="";//表示事件的A状态
		stateB="";//表示事件的B状态
		stateC="";//表示事件的C状态
		stateD="";//表示事件的D状态
		stateE="";//表示事件的D状态...不要再多了
		SendSelfdefine="";//如果还需要更多信息，自己使用分隔符进行数据传输
		feedbackSelfdefine="";//如果还需要更多信息，自己使用分隔符进行数据传输
		count=0;//表示第几次点击该事件
		eventFinished=false;
		trigSubEvent = false;
	}
	public DataPack(String type){
		if(type.equals("demo")) {
			this.ID=0;
			this.date="0927";
			this.term="大四上";
			this.dayOrNight=0;
			this.name="神秘杰哥";
			this.studentID="2016******";
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
