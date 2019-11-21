package THUgame.tool;

import THUgame.tool.Courses;

public class CourseGrade{
	/* 已选课课程成绩：
	 * 
	 * 包含如下字段：
	 * 指向代表该课程的Courses对象的引用
	 * 课程成绩，前几个学期的课程给出课程成绩，采用等级制，例"A+","F","W"，本学期已选课但还未给成绩的课程课程成绩记为"CS"(course selected)
			本学期已提交选课但还未抽签的课程即为"TR"(to be "randomed")
				（这是在选课过程中的一个“暂态”，当EventCourseRegistration结束时会对所有标记"TR"的课程进行抽签，选课结束后不会有课程标记为"TR"）
	 * 学期：这门课是在哪个学期上的
	 */
	public Courses course;
	public String grade="CS";
	public int term=0;
	
	public CourseGrade(){
	}
	
	public CourseGrade(Courses course, String grade, int term){
		this.course=course;
		this.grade=grade;
		this.term=term;
	}
	
	public CourseGrade(Courses course, int term){
		this.course=course;
		this.grade="TR"; //默认已选课还未抽签 
		this.term=term;
	}
}