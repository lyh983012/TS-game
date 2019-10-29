package THUgame.tool;

/*
 * Course Management System 课程管理系统
	 * 表示开设的所有课程
	 * 每一门课是Courses类的一个实例
	 * 在????????的时候初始化
 * update: 20191023	
	 
	 */


public class Courses{
	/* 课程属性：显示在选课界面里的
	 * 包含如下内容
	 * 
	 * 课程号：该课程的唯一标识，格式参照《清华大学学生手册·清华大学课程编号说明》
	 * 课程名
	 * 上课时间1,2：11表示周一第1节课，72表示周日第2节课，以此类推；每周只上一次的课，用classTime1表示，classTime2=0，
			每周上2次的课，两次上课时间分别放在classTime1和classTime2里面
	 * 必修/限选/任选：必修="R"(required)，任选="E"(Elective)，限选="RE"(restricted elective)
	 * 限选课程组：如果本课程属性为“限选”，则在这个变量里记录从哪几门课里至少选几门，格式为"总课程数,其它课编号（逗号分隔）,至少选几门”，
	 		例如"4,10420013,10420023,10420033,2"表示从本对象代表的课、10420013、10420023、10420033这四门课中至少选2门上
	* 总学时：用来标识对学习这门课的“进度要求”（即需要上多少次课及自习多少次）
	* 课容量
	* 课余量（课余量预设好，或者选课前根据课程隐藏属性随机生成。如果课余量>0则一定可以选上课，如果课余量<=0（选课人数>=课容量）则需要参加抽签） 
	* 课程特色：	文素/文核/新生研讨课/。。。，文素="CCQ"(course of cultural quality)，文核=“CCCQ"(Core course of cultural quality)，
			新生研讨课="FS"(Freshman seminar)。。。同一门课有多个特色属性的在字符串里用逗号分开
	* 上课学期：这门课开设的学期，上学期=1/下学期=2/上下学期都开=3/暑季学期=4
	* 上课地点：室内=1，室外=2（用于背景图的绘制、选择游戏）
	 * 
	 */
	public String courseID=""; //课程号
	public String courseTitle=""; //课程名
	public int classTime1=0; //上课时间1
	public int classTime2=0; //上课时间2
	public String courseProperty=""; //必修/限选/任选
	public String RECourseGroup=""; //限选课程组
	public int totalHour=0; //总学时 
	public int classCapacity=0; //课容量
	public int classVacancy=0; //课余量
	public String courseFeatures=""; //课程特色
	public int courseTerm=1; //上课学期
	public int classPlace=1; //上课地点
	
	/* 隐藏属性： to be implemented
	 * 
	 */
	

	/* 所有已开设课程列表*/
	public static Courses[] courseList;
	public static int courseListCount=0; //已开设课程总数（courseList中有效数据的个数）
	
	/* default constructor */
	Courses(){
		courseListCount++;
	}
	
	/* constructor */
	Courses(String courseID,
			String courseTitle,
			int classTime1,
			int classTime2,
			String courseProperty,
			String RECourseGroup,
			int totalHour,
			int classCapacity,
			int classVacancy,
			String courseFeatures,
			int courseTerm,
			int classPlace){
		this.courseID=courseID; //课程号
		this.courseTitle=courseTitle; //课程名
		this.classTime1=classTime1; //上课时间1
		this.classTime2=classTime2; //上课时间2
		this.courseProperty=courseProperty; //必修/限选/任选
		this.RECourseGroup=RECourseGroup; //限选课程组
		this.totalHour=totalHour; //总学时
		this.classCapacity=classCapacity; //课容量
		this.classVacancy=classVacancy; //课余量
		this.courseFeatures=courseFeatures; //课程特色
		this.courseTerm=courseTerm; //上课学期
		this.classPlace=classPlace; //上课地点
		
		courseListCount++;
	}

	/* 初始化课程列表 courseList: 目前采用人工录入的方式*/
	public static void courseListInit() {
		courseList=new Courses [30];
		
		courseList[courseListCount]=new Courses("10421055", //课程号
				"微积分A(1)", //课程名
				32,52, //上课时间
				"R", //必修/限选/任选
				"", //限选课程组
				15, //总学时
				150, //课容量
				20, //课余量
				"", //课程特色
				1, //上课学期
				1); //上课地点
		
		courseList[courseListCount]=new Courses("10421324",  //课程号
				"线性代数(1)", //课程名
				31,51, //上课时间
				"R", //必修/限选/任选
				"", //限选课程组
				15, //总学时
				150, //课容量
				30, //课余量
				"", //课程特色
				1, //上课学期
				1); //上课地点
		
		courseList[courseListCount]=new Courses("20120163",  //课程号
				"机械设计基础A(1)", //课程名
				41,0, //上课时间
				"R", //必修/限选/任选
				"", //限选课程组
				12, //总学时
				80, //课容量
				10, //课余量
				"", //课程特色
				1, //上课学期
				1); //上课地点
		
		courseList[courseListCount]=new Courses("10430484", //课程号
				"大学物理B(1)", //课程名
				11,42, //上课时间
				"R", //必修/限选/任选
				"", //限选课程组
				12, //总学时
				150, //课容量
				40, //课余量
				"", //课程特色
				2, //上课学期
				1); //上课地点
		
		courseList[courseListCount]=new Courses("10420165", //课程号
				"微积分A(2)", //课程名
				32,52, //上课时间
				"R", //必修/限选/任选
				"", //限选课程组
				15, //总学时
				150, //课容量
				25, //课余量
				"", //课程特色
				2, //上课学期
				1); //上课地点
		
		courseList[courseListCount]=new Courses("10430484", //课程号
				"大学物理B(2)", //课程名
				11,42, //上课时间
				"R", //必修/限选/任选
				"", //限选课程组
				12, //总学时
				150, //课容量
				35, //课余量
				"", //课程特色
				1, //上课学期
				1); //上课地点

		courseList[courseListCount]=new Courses("10421102", //课程号
				"线性代数(2)", //课程名
				21,0, //上课时间
				"RE", //必修/限选/任选
				"4,10420243,10420262,10420252,2", //限选课程组
				8, //总学时
				150, //课容量
				50, //课余量
				"", //课程特色
				2, //上课学期
				1); //上课地点
		
		courseList[courseListCount]=new Courses("10420252", //课程号
				"复变函数引论", //课程名
				21,0, //上课时间
				"RE", //必修/限选/任选
				"4,10420243,10420262,10421102,2", //限选课程组
				10, //总学时
				150, //课容量
				50, //课余量
				"", //课程特色
				3, //上课学期
				1); //上课地点
		
		courseList[courseListCount]=new Courses("10420262", //课程号
				"数理方程引论", //课程名
				41,0, //上课时间
				"RE", //必修/限选/任选
				"4,10420243,10420252,10421102,2", //限选课程组
				10, //总学时
				150, //课容量
				50, //课余量
				"", //课程特色
				3, //上课学期
				1); //上课地点

		courseList[courseListCount]=new Courses("10420243", //课程号
				"随机数学方法", //课程名
				52,0, //上课时间
				"RE", //必修/限选/任选
				"4,10420262,10420252,10421102,2", //限选课程组
				10, //总学时
				150, //课容量
				50, //课余量
				"", //课程特色
				3, //上课学期
				1); //上课地点
		

		courseList[courseListCount]=new Courses("10220012", //课程号
				"计算机硬件技术基础", //课程名
				11,0, //上课时间
				"E", //必修/限选/任选
				"", //限选课程组
				10, //总学时
				100, //课容量
				40, //课余量
				"", //课程特色
				3, //上课学期
				1); //上课地点

		courseList[courseListCount]=new Courses("10220012", //课程号
				"现代生物学导论", //课程名
				12,0, //上课时间
				"E", //必修/限选/任选
				"", //限选课程组
				8, //总学时
				150, //课容量
				40, //课余量
				"", //课程特色
				1, //上课学期
				1); //上课地点
	}
	
	/*把一个Courses[]数组转化成用字符串表示，格式如下：
	 * "课程总数N,课程1课程号,课程2课程号, ... ,课程N课程号"
	 */
	public static String coursesToStr(Courses[] course, int count) {
		int index=0;
		String ret=""; //返回值要在窗口中显示的课程
		for(index=0;index<count;index++) {
			ret+=","+course[index].courseID;
		}
		ret=count+ret; //第一个操作数不是String是int有没有重载运算符+？
		return ret;
	}
	
	/*将上述格式的字符串转换回Courses[]数组，返回课程总个数*/
	public static int strToCourses(String str, Courses[] ret) {
		
		return 0;
	}
}
	
