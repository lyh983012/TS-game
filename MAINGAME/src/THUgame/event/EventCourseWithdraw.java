package THUgame.event;

import THUgame.datapack.DataPack;
import THUgame.tool.Courses;

public class EventCourseWithdraw extends EventBase{
	/* choiceA表示在WindowCourseRegistration上点击了什么按钮
	 * 		提交退课="W"
	 * 		用户点击退课之后返回信息界面上的“OK”按钮=“MsgOK” //接下来的state一定是"CS"
	 * 		结束退课="Finish" //用户点击“结束退课”，接下来显示课表
	 * 		退课事件完全结束="AllFinish" //显示课表，该界面上有OK按钮，用户点击该按钮进入下一个事件
	 * 		
	 * choiceB的含义：
	 * 	当choiceA=“提交退课”时，choiceB列出此次提交退课选择的课程，以课程号表示，格式按照Courses中定义的将Courses[]转换成String的方式
	 * 	其它choiceB=""
	 * 
	 * 
	 * stateA表示当前窗口应该显示哪个界面
	 * 		显示已选课程="CS" //refers to courses selected
	 * 		提交退课返回信息="MsgW"
	 * 		退课结束显示课表=“TimeTable”
	 * 
	 * stateB的含义：
	 * 	当stateA=“显示已选课程”，stateB列出满足条件的课的课程号，格式同上
	 * 	当stateA=“提交退课返回信息”，stateB返回要输出的提示信息（“退课成功”）
	 * 	当stateA=“选课结束显示课表”时，stateB列出课表中的课程的课程号，格式同上
	 * 	
	 */
	
	/* 注： 【这些是选课界面的备注直接复制过来的。。。。。】
	 * 1. 规定课程在DataPack中的courseGrade数组中按照学期升序排列，这样找本学期的课程可以从数组结尾开始找，提高效率；
	 * 2. 在Event中而不是Window中实现切换界面，是因为切换界面需要找出所有要在当前选课界面中列出的课程，查询这个的逻辑稍复杂，故在event中实现
	 * 3. 按照现在“选择重修课”的实现方式，曾经挂过的课既可以在“选择必修/限选/任选”课程中选，也可以在“选择重修课”中选
	 * 4. “选择重修课”的逻辑目前基于课程成绩只有A+,A,...,D-,F,W,CS,TR几个选项，如果加入了其他选项需要检查逻辑是不是还成立（主要是第一个else if(!F W CS TR)的问题）
	 * 5. DataPack的constructor里面courseGrade=new CourseGrade[ ] 一句中数组的长度取决于每学期至多选多少门课，（以及一共几个学期），
	 * 			在这选课事件里要实现每学期至多选多少课的限制
	 * 6. 按照现在的设计，提交选课时如果提交的课程中有两门课时间冲突，则其中一门课会被选上。
	 * 
	 * #. 再写新的事件的时候注意一下，应该先统一设计类图。。。。。。。。因为可以在设计之后大致考虑一下如何实现，然后把实现过程中的相同的步骤先统一写成一个私有函数（修改设计），不要写代码的时候再改。。。并且统一变量的命名规则
	 * 
	 */
	private int __getSelectedCoursesCount() {
		//返回本学期选了多少门课（即成绩为"CS"或者"TR"的课程，或者等价地说本学期课程中成绩不是"W"的课程）
		
		//从后向前搜索oldDataPack.courseGrade
		int i;
		int count=0;
		int thisTerm=oldDataPack.term;
		i=oldDataPack.courseGradeCount-1;
		while(i>=0 && oldDataPack.courseGrade[i].term == thisTerm) {
			if(! oldDataPack.courseGrade[i].grade.equals("W")) count++;
			i--;
		}
		return count;
	}
	
	private Courses[] __getSelectedCourses() {
		//返回本学期选了哪几门课（即成绩为"CS"或者"TR"的课程，或者等价地说本学期课程中成绩不是"W"的课程）
		
		//从后向前搜索oldDataPack.courseGrade
		int i;
		int count;
		int thisTerm=oldDataPack.term;
		Courses ret[]=new Courses[Courses.COURSE_MAX_COUNT_IN_ONE_TERM+2]; //+2以防万一
		count=0;
		i=oldDataPack.courseGradeCount-1;
		while(i>=0 && oldDataPack.courseGrade[i].term == thisTerm) {
			if(! oldDataPack.courseGrade[i].grade.equals("W")) {
				ret[count++]=oldDataPack.courseGrade[i].course;
			}
			i--;
		}
		return ret;
	}
	
	@Override
	public void actOn(DataPack oldDataPack) {
		this.oldDataPack=oldDataPack; //因为需要在__getSelectedCourses()函数中共享oldDataPack的数据，所以要加上这句，更新基类中定义的oldDataPack
		
		if(oldDataPack.choiceA.equals("AllFinish")){
			oldDataPack.eventFinished=true;
			return;
		}
		
		if(oldDataPack.choiceA.equals("Finish")) {
			//显示课表
			oldDataPack.stateA="TimeTable";
			oldDataPack.stateB=Courses.coursesToStr(__getSelectedCourses(), __getSelectedCoursesCount());
			return;
		}

		if(oldDataPack.choiceA.equals("MsgOK")){
			oldDataPack.stateA="CS";
			oldDataPack.stateB=Courses.coursesToStr(__getSelectedCourses(), __getSelectedCoursesCount());
			return;
		}

		int MAX_COURSE_NUM=Courses.courseListCount; //最多可能有多少门课程（避免数组越界）  【认为它是C语言中的const变量】
		Courses coursesChoosed[]=new Courses[MAX_COURSE_NUM+2]; //在前台窗口选择的课程（可以是要选的课，或者要删除的课）
		int coursesChoosedCount;
		
		if(oldDataPack.choiceA.equals("W")) { //退课
			coursesChoosedCount=Courses.strToCoursesCount(oldDataPack.choiceB);
			coursesChoosed=Courses.strToCourses(oldDataPack.choiceB);
			int i;
			for(i=0;i<coursesChoosedCount;i++) {
				int j;
				for(j=oldDataPack.courseGradeCount-1;j>=0;j--) //从后向前遍历oldDataPack.courseGrade，找出要删除的课
					if(oldDataPack.courseGrade[j].course.equals(coursesChoosed[i])) {
						//退课记"W"
						oldDataPack.courseGrade[j].grade="W";
						break;
					}
			}
			oldDataPack.stateA="MsgW";
			oldDataPack.stateB="退课成功！";
		}
	}
}