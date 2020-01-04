package THUgame.event;

import java.util.Random;

import THUgame.datapack.DataPack;
import THUgame.tool.Courses;
import THUgame.tool.CourseGrade;

public class EventCourseRegistration extends EventBase{
	/* choiceA表示在WindowCourseRegistration上点击了什么按钮
	 * 		选择必修课="R"
	 * 		选择限选课="RE"
	 * 		选择任选课="E"
	 * 		选择重修课="F"
	 * 		提交选课="Submit" //即提交想选还没选的课程
	 * 		删除选课="Delete" //即删除已经选中的课程
	 * 		用户点击提交选课/删除选课之后返回信息界面上的“OK”按钮=“MsgSDOK” //此时应跳至选择必修/限选/任选/重修课之一（由EventCourseRegistration类保存应该跳至哪个界面）
	 * 		结束选课="Finish" //用户点击“结束选课”，接下来进行抽签并抽签结果
	 * 		抽签结束，显示抽签结果="RandomResultFinish" //抽签结束，显示抽签结果（哪几门课每抽中，或者全中了），该界面上有OK按钮，用户点击该按钮进入“显示课表”界面
	 * 		选课事件完全结束="AllFinish" //抽签完成后显示课表，该界面上有OK按钮，用户点击该按钮进入下一个事件
	 * 		
	 * choiceB的含义：
	 * 	当choiceA=“提交选课”时，choiceB列出此次提交选课选择的课程，以课程号表示，格式按照Courses中定义的将Courses[]转换成String的方式
	 * 	当choiceA=“删除选课”时，choiceB列出此次删除选课选择的课程，以课程号表示，格式同上
	 * 	其它choiceB=""
	 * 
	 * 
	 * stateA表示当前窗口应该显示哪个界面
	 * 		选择必修课="R"
	 * 		选择限选课="RE"
	 * 		选择任选课="E"
	 * 		选择重修课="F"
	 * 		提交选课/删除选课返回信息="MsgSD" //message submit/delete
	 * 		结束选课返回信息="MsgFinish"
	 * 		选课结束显示课表=“TimeTable”
	 * 
	 * stateB的含义：
	 * 	当stateA=“选择必修课/限选课/任选课/重修课”，stateB列出满足条件的课的课程号，格式同上
	 * 	当stateA=“提交选课/删除选课/结束选课返回信息”，stateB返回要输出的提示信息（包括choiceA=“提交选课”的时候哪些课没有提交成功（或者所有课都提交成功了），
	 * 		choiceA=“删除选课”的时候哪些课没有删除成功（或者全删除成功了），choiceA=“Finish”的时候列出抽签没抽中的课）
	 * 	当stateA=“选课结束显示课表”时，stateB列出课表中的课程的课程号，格式同上
	 * 		
	 * stateC的含义：
	 * 	当stateA=“选择必修课/限选课/任选课/重修课”，stateC列出已选课的课的课程号，格式同上
	 * 	其它stateC=""
	 */
	
	/* 注：
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
	
	static String courseListPanelContent="R"; //记录courseListPanel中显示的是选择必修课/限选课/任选课还是重修课，必修课="R"，限选课="RE"，任选课="E"，重修课="F"
	//需要一个这样的变量记录，是因为窗口有时需要画返回一些信息的界面，画这些界面的时候DataPack里面没有保存窗体上一次显示的是选择什么课，如果没有这样一个变量记录，在信息窗口结束之后不知道该返回哪个界面

	/*
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
	*/
	
	@Override
	public void actOn(DataPack oldDataPack) {
		this.oldDataPack=oldDataPack; //因为需要在__getSelectedCourses()函数中共享oldDataPack的数据，所以要加上这句，更新基类中定义的oldDataPack
		
		if(oldDataPack.choiceA.equals("AllFinish")){
			oldDataPack.eventFinished=true;
			return;
		}
		
		int MAX_COURSE_NUM=Courses.courseListCount; //最多可能有多少门课程（避免数组越界）  【认为它是C语言中的const变量】
		Courses courseToShow[]=new Courses[MAX_COURSE_NUM]; //将要在Windows里面显示的课程列表
		int courseCount=0; //课程列表中的课程数
		
		if(oldDataPack.choiceA.equals("Finish")) {
			int selectedCourseCount=Courses.__getSelectedCoursesCount(oldDataPack);
			int selectedCourseTotalHour=Courses.__getCoursesTotalHour(Courses.__getSelectedCourses(oldDataPack), Courses.__getSelectedCoursesCount(oldDataPack));
			if(selectedCourseCount < Courses.COURSE_MIN_COUNT_IN_ONE_TERM
				|| selectedCourseTotalHour < Courses.MIN_TOTAL_HOUR_IN_ONE_TERM_AFTER_REGISTRATION) {
				oldDataPack.stateA="MsgSD"; //以MsgSD而不是MsgFinish返回回去——选课事件不结束
				oldDataPack.stateB="【系统提示】选课数目或学分过少，请重新选课！\n每学期至少选择"+Courses.COURSE_MIN_COUNT_IN_ONE_TERM+"门课，至少选择"+Courses.MIN_TOTAL_HOUR_IN_ONE_TERM_AFTER_REGISTRATION+"学时";
				return;
			}
			if(selectedCourseCount > Courses.COURSE_MAX_COUNT_IN_ONE_TERM) {
				oldDataPack.stateA="MsgSD"; //同上
				oldDataPack.stateB="【系统提示】选课数目过多，请重新选课！\n每学期至多选择"+Courses.COURSE_MAX_COUNT_IN_ONE_TERM+"门课";
				return;
			}
			
			int index=oldDataPack.courseGradeCount-1; //遍历courseGrade，抽签
			while(index>=0 && oldDataPack.courseGrade[index].term == oldDataPack.term) { //是本学期的课程
				if(oldDataPack.courseGrade[index].grade.equals("TR")) {
					Courses currentCourse=oldDataPack.courseGrade[index].course;
					if(currentCourse.classVacancy>0) { //有课余量
						oldDataPack.courseGrade[index].grade="CS";
					}
					else { //没有课余量，抽签
						Random R=new Random();
						int r=R.nextInt(currentCourse.classCapacity-currentCourse.classVacancy);
						if(r<currentCourse.classCapacity) { //抽签抽到了
							oldDataPack.courseGrade[index].grade="CS";
						}
						else { //抽签没有抽到
							courseToShow[courseCount]=currentCourse; //courseToShow存储摇号没摇上的课
							courseCount++;
							//下面：从oldDataPack.courseGrade中删掉这个课：和列表中最后一个课"交换"，然后CNT--;
							oldDataPack.courseGrade[index]=oldDataPack.courseGrade[oldDataPack.courseGradeCount-1];
							oldDataPack.courseGradeCount--;
						}
					}
				}
				index--;
			}
			oldDataPack.stateA="MsgFinish";
			if(0 == courseCount) 
				oldDataPack.stateB="我仿佛使用了金手指，欧气满满！所有课程全部成功选上！！";
			else {
				String msg="啊！几门课掉了：";
				int i;
				for(i=0;i<courseCount;i++) {
					msg=msg+'\n'+courseToShow[i].courseTitle+"课程，课程号"+courseToShow[i].courseID+"；";
				}
				oldDataPack.stateB=msg;
			}
			
			return;
		}
		
		if(oldDataPack.choiceA.equals("RandomResultFinish")) {
			//显示课表
			oldDataPack.stateA="TimeTable";
			oldDataPack.stateB=Courses.coursesToStr(Courses.__getSelectedCourses(oldDataPack), Courses.__getSelectedCoursesCount(oldDataPack));
			return;
		}
		
		if(oldDataPack.choiceA.equals("MsgSDOK")) {
			oldDataPack.choiceA=courseListPanelContent; //转到下面对应的分支中
			//注：处理choiceA == "MsgSDOK"一定要在"R","RE","E","F"之前，这样才能通过修改oldDataPack.choiceA的方式跳转到对应的分支
		}
		
		if(oldDataPack.choiceA.equals("R") || oldDataPack.choiceA.equals("RE") || oldDataPack.choiceA.equals("E")) {
			//选择必修课/限选课/任选课
			int index=0;
			courseCount=0;
			String coursePropertyLabel = oldDataPack.choiceA; //取值为R/RE/E，表示这一次要显示的课程的属性（必修/限选/任选）
			//遍历一遍courseList，看哪些课程属性与这次要显示的课程属性相同，且是这学期开的课
			for(index=0;index<Courses.courseListCount;index++) {
				if(Courses.courseList[index].courseProperty.equals(coursePropertyLabel) 
						&& oldDataPack.term == Courses.courseList[index].courseTerm) {
					courseToShow[courseCount]=Courses.courseList[index];
					courseCount++;
				}
			}
			oldDataPack.stateA=coursePropertyLabel;
			oldDataPack.stateB=Courses.coursesToStr(courseToShow, courseCount);
			oldDataPack.stateC=Courses.coursesToStr(Courses.__getSelectedCourses(oldDataPack), Courses.__getSelectedCoursesCount(oldDataPack));
			courseListPanelContent=coursePropertyLabel;
			
			return;
		}
		
		if(oldDataPack.choiceA.equals("F")) { //选择重修课
			int index=0;
			courseCount=0;
			for(index=0;index<oldDataPack.courseGradeCount;index++) { //遍历oldDataPack.courseGrade，找出所有挂了还没重修的课
				if(oldDataPack.courseGrade[index].grade.equals("F")) { //这门课曾经挂了
					int j;
					boolean hasFailed=false; //标记这门课是不是以前挂过，这次又挂了
					for(j=0; j<courseCount; j++) { //在曾经挂过且还没重修的课程列表courseToShow中搜索这门课是不是之前挂过
						if(oldDataPack.courseGrade[index].course == courseToShow[j]) {
							hasFailed=true;
							break;
						}
					}
					if(! hasFailed) {
						courseToShow[courseCount]=oldDataPack.courseGrade[index].course;
						courseCount++;
					}
				}
				else if(!(oldDataPack.courseGrade[index].grade.equals("F") ||
						oldDataPack.courseGrade[index].grade.equals("W") ||
						oldDataPack.courseGrade[index].grade.equals("CS") ||
						oldDataPack.courseGrade[index].grade.equals("TR"))
						){ //这门课已经获得成绩：  判断一下这门课是不是重修以前挂过的课
					int j;
					boolean retake=false; //这门课是否是重修曾经挂过的课
					int retakeCourseIndex=-1; //如果这门课确实是重修曾经挂过的课，那么把那门曾经挂了的课在courseToShow中的下标记录在这个变量中
					for(j=0; j<courseCount; j++) { //遍历曾经挂过且还没重修的课程列表courseToShow
						if(courseToShow[j] == oldDataPack.courseGrade[index].course) {
							retake=true;
							retakeCourseIndex=j;
							break;
						}
					}
					if(retake) { //如果这门课确实是重修之前的某一门挂了的课，则删掉那门重修的课
						courseToShow[retakeCourseIndex]=courseToShow[--courseCount]; // //dsa风格代码
					}
				}
			}
			oldDataPack.stateA="F";
			oldDataPack.stateB=Courses.coursesToStr(courseToShow, courseCount);
			oldDataPack.stateC=Courses.coursesToStr(Courses.__getSelectedCourses(oldDataPack), Courses.__getSelectedCoursesCount(oldDataPack));
			courseListPanelContent="F";
			
			return;
		}
		
		Courses coursesChoosed[]=new Courses[MAX_COURSE_NUM+2]; //在前台窗口选择的课程（可以是要选的课，或者要删除的课）
		int coursesChoosedCount;
		
		if(oldDataPack.choiceA.equals("Submit")) { //提交选课
			coursesChoosedCount=Courses.strToCoursesCount(oldDataPack.choiceB);
			coursesChoosed=Courses.strToCourses(oldDataPack.choiceB);
			int i;
			for(i=0;i<coursesChoosedCount;i++) {
				int j;
				boolean flag=false;
				//遍历oldDataPack.courseGrade，看这门课之前选没选过
				for(j=oldDataPack.courseGradeCount-1; j>=0; j--) {
					if(
						(	(oldDataPack.courseGrade[j].course.equals(coursesChoosed[i])) && 
							(! oldDataPack.courseGrade[j].grade.equals("W")) &&
							(! oldDataPack.courseGrade[j].grade.equals("F"))
						) //这门课之前选过，且不是挂了或者退了的课
						|| 
						(	oldDataPack.courseGrade[j].term == oldDataPack.term &&
							Courses.classTimeConflict(oldDataPack.courseGrade[j].course, coursesChoosed[i])
						) //这门课和之前选过的另一门这学期的课时间冲突了
					) {
						courseToShow[courseCount++]=coursesChoosed[i]; //在返回的信息中指出这门课已经选过了，或者时间冲突了
						flag=true;
						break;
					}
				}
				if(false == flag) { //这门课之前没选过，这次可以选
					oldDataPack.courseGrade[oldDataPack.courseGradeCount++]=new CourseGrade(coursesChoosed[i], oldDataPack.term);
				}
			}
			
			oldDataPack.stateA="MsgSD";
			if(0 == courseCount) oldDataPack.stateB="提交选课成功！";
			else {
				String msg="【系统提示】以下课程已修或已经提交选课，或者与其它已选课程时间冲突，提交选课失败：";
				for(i=0;i<courseCount;i++) {
					msg=msg+'\n'+courseToShow[i].courseTitle+"课程，课程号"+courseToShow[i].courseID+"；";
				}
				oldDataPack.stateB=msg;
			}
		}
		
		if(oldDataPack.choiceA.equals("Delete")) { //删除选课
			coursesChoosedCount=Courses.strToCoursesCount(oldDataPack.choiceB);
			coursesChoosed=Courses.strToCourses(oldDataPack.choiceB);
			int i;
			for(i=0;i<coursesChoosedCount;i++) {
				int j;
				for(j=oldDataPack.courseGradeCount-1;j>=0;j--) //从后向前遍历oldDataPack.courseGrade，找出要删除的课
					if(oldDataPack.courseGrade[j].course.courseID.equals(coursesChoosed[i].courseID)) { //updated on Dec25,2019 （不能拿.equal比两个自定义对象的值是否相等
						//删除操作：把要删除的一项和数组中最后一项交换，并count--
						//实际上不需要交换，直接把数组最后一项拿过来覆盖要删除的这一项即可
						oldDataPack.courseGrade[j]=oldDataPack.courseGrade[(oldDataPack.courseGradeCount--)-1];
						break;
					}
			}
			oldDataPack.stateA="MsgSD";
			oldDataPack.stateB="【系统提示】删除选课成功！";
		}
	}
}