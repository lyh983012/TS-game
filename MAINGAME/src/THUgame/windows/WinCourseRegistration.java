package THUgame.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;


import javax.swing.border.LineBorder;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.tool.Courses;


import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
/*
 * 
 **/


public class WinCourseRegistration extends WinBase{
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 		这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 		写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 		所有必要实现的接口都实现了。
	 * 
	 *************************************************************/
	static private class mouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		private JFrame frame;
		private JButton button;
		private int mode;
		
		public mouseListener(int i){
			this.mode=i;
		}
		
		public void setFrame(JFrame frame) {
			this.frame=frame;
		}
		
		public void setButton(JButton button) {
			this.button=button;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			/*		START OF YOUR CODE		*/
			String state=dataPackage.stateA; //因为dataPackage.stateA在下面重复用到，所以取了一个别名state
			if(state.equals("R") || state.equals("RE") || state.equals("E") || state.equals("F")) {
				//窗口处于显示必修/限选/任选/重修课的状态，此时才能触发"选择**课","提交选课","删除选课","结束选课"这些按钮
				switch(mode) {
				case 0: //按钮0=“选择必修课”
					dataPackage.choiceA="R";
					break;
				case 1: //按钮1=“选择限选课”
					dataPackage.choiceA="RE";
					break;
				case 2: //按钮2=“选择任选课”
					dataPackage.choiceA="E";
					break;
				case 3: //按钮3=“选择重修课”
					dataPackage.choiceA="F";
					break;
				case 4: //按钮4=“提交选课”
					Courses courseChecked[]=new Courses[courseToSelectCount+2]; //已选中的课程 //+2以防万一
					int i;
					int courseCheckedCount=0;
					for(i=0;i<courseToSelectCount;i++) {
						if(true == courseToSelectCheckBoxChecked[i]) 
							courseChecked[courseCheckedCount++]=courseToSelect[i];
					}
					if(0 == courseCheckedCount) { //用户啥都没选就点了提交
						return; //保持在前窗口中，不跳转到后台的event处理函数中
					}
					else {
						dataPackage.choiceA="Submit";
						dataPackage.choiceB=Courses.coursesToStr(courseChecked, courseCheckedCount);
					}
					break;
				case 5: //按钮5=“删除选课”
					Courses selectedCoursesChecked[]=new Courses[selectedCoursesCount+2]; //已选中的课程 //+2以防万一
					int j;
					int selectedCoursesCheckedCount=0;
					for (j=0;j<selectedCoursesCount;j++) {
						if(true == selectedCoursesCheckBoxChecked[j]) 
							selectedCoursesChecked[selectedCoursesCheckedCount++]=selectedCourses[j];
					}
					if(0 == selectedCoursesCheckedCount) { //用户啥都没选就点了提交
						return;
					}
					else {
						dataPackage.choiceA="Delete";
						dataPackage.choiceB=Courses.coursesToStr(selectedCoursesChecked, selectedCoursesCheckedCount);
					}
					break;
				case 6: //按钮6=“结束选课”
					dataPackage.choiceA="Finish";
					break;
				default: //用户点了其它按钮直接return
					return;
				}
			}
			
			if(state.equals("MsgSD") || state.equals("MsgFinish")) { //窗口中显示的是msgPanel
				if(7 == mode) { //此时用户只能点击msgOKButton
					if(state.equals("MsgSD")) {
						//这是提交/删除选课返回的信息
						dataPackage.choiceA="MsgSDOK";
					}
					if(state.equals("MsgFinish")) {
						//这是结束选课返回的信息
						dataPackage.choiceA="RandomResultFinish";
					}
				}
				else //窗口中显示的是msgPanel，用户如果点击了除了msgOKButton的其它按钮就直接return
					return;
			}
			
			if(state.equals("TimeTable")) { //窗口中显示的是timeTablePanel，此时用户只能点击timeTableOKButton
				if(8 == mode)
					dataPackage.choiceA="AllFinish";
				else //窗口中显示的是timeTablePanel，此时用户只能点击timeTableOKButton，用户点击了其它按钮就直接return
					return;
			}
			/*		END OF YOUR CODE		*/
			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
			EventManager.dataPackage=dataPackage;
			synchronized(mainGame) {
				mainGame.notify();
			}
			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	
	static private class JSBAdjustmentListener implements AdjustmentListener{ //JSB refers to JScrollBar

		private int mode;
		private JScrollBar scrollBar;
		JPanel courseSelectionPanel;
		
		JSBAdjustmentListener(int _mode, JScrollBar _scrollBar){
			mode=_mode;
			scrollBar=_scrollBar;
		}
		
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			// TODO Auto-generated method stub
			switch (mode) {
			case 1: //courseToSelectPanel中的滚动条
				CourseEntry.repaintCourseToSelectEntryAll(courseToSelect, courseToSelectCheckBoxChecked, courseToSelectCount, scrollBar.getValue());
				//根据滚动条现在的位置（scrollBar.getValue()）重新绘制courseToSelectPanel
				break;
			case 2: //selectedCoursesPanel中的滚动条
				CourseEntry.repaintSelectedCoursesEntryAll(selectedCourses, selectedCoursesCheckBoxChecked, selectedCoursesCount, scrollBar.getValue());
				break; 
			}
			courseSelectionPanel.repaint();
		}	
	}
	
	
	static private class JCBItemListener implements ItemListener{ //JCB: JCheckBox

		int mode;
		private JScrollBar scrollBar;
		
		JCBItemListener(int _mode, JScrollBar _scrollBar){
			mode=_mode;
			scrollBar=_scrollBar;
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			switch(mode) {
			case 0: case 1: case 2: case 3: case 4: case 5:  //courseToSelectPanel中的CheckBox
				CourseEntry.updateCourseToSelectChecked(courseToSelectCheckBoxChecked, scrollBar.getValue(), mode);
				break;
			case 6: case 7: case 8: case 9: case 10: case 11: //selectedCoursesPanel中的CheckBox
				CourseEntry.updateSelectedCoursesChecked(selectedCoursesCheckBoxChecked, scrollBar.getValue(), mode-6);
				break;
			}
		} //CB refers to CheckBox
		
	}
	
	
	static private class CourseEntry{  
		//
		/* 每个CourseEntry的对象表示courseToSelectPanel中的一行
		 * CourseToSelectPanel中的各个控件的属性由这个类来维护
		 * （要显示的所有课程的列表courseToSelect的属性是由EventCourseRegister传递给WinCourseRegister的，所以由WinCourseRegister来维护）
		 * 
		 */
		JCheckBox courseChooseCheckBox;
		JLabel courseIDLabel;
		JLabel courseTitleLabel;
		JLabel classTimeLabel;
		JLabel coursePropertyLabel;
		JLabel totalHourLabel;
		JLabel classCapacityLabel;
		JLabel classVacancyLabel;
		JLabel courseFeaturesLabel;

		static CourseEntry courseToSelectEntry[]; //可选课程
		static int COURSE_TO_SELECT_PANEL_CAPACITY;
		static CourseEntry selectedCoursesEntry[]; //已选课程
		static int SELECTED_COURSES_PANEL_CAPACITY;
		
		static void setCourseToSelectEntry(CourseEntry _courseToSelectEntry[]) {
			courseToSelectEntry=_courseToSelectEntry;
		}
		
		static void setSelectedCoursesEntry(CourseEntry _selectedCoursesEntry[]) {
			selectedCoursesEntry=_selectedCoursesEntry;
		}
		
		static void set_COURSE_TO_SELECT_PANEL_CAPACITY(int _COURSE_TO_SELECT_PANEL_CAPACITY) {
			COURSE_TO_SELECT_PANEL_CAPACITY=_COURSE_TO_SELECT_PANEL_CAPACITY;
		}
		
		static void set_SELECTED_COURSES_PANEL_CAPACITY(int _SELECTED_COURSES_PANEL_CAPACITY) {
			SELECTED_COURSES_PANEL_CAPACITY=_SELECTED_COURSES_PANEL_CAPACITY;
		}
		
		CourseEntry(JCheckBox courseChooseCheckBox,
				JLabel courseIDLabel,
				JLabel courseTitleLabel,
				JLabel classTimeLabel,
				JLabel coursePropertyLabel,
				JLabel totalHourLabel,
				JLabel classCapacityLabel,
				JLabel classVacancyLabel,
				JLabel courseFeaturesLabel
		){
			this.courseChooseCheckBox=courseChooseCheckBox;
			this.courseIDLabel=courseIDLabel;
			this.courseTitleLabel=courseTitleLabel;
			this.classTimeLabel=classTimeLabel;
			this.coursePropertyLabel=coursePropertyLabel;
			this.totalHourLabel=totalHourLabel;
			this.classCapacityLabel=classCapacityLabel;
			this.classVacancyLabel=classVacancyLabel;
			this.courseFeaturesLabel=courseFeaturesLabel;
		}
		
		private void __repaintCourseEntry(Courses courses[], boolean checkBoxChecked[], int coursesCount, 
				int scrollBarPos, int lineNo) { //lineNo是“行号”，取值范围[0, COURSE_TO_SELECT_PANEL_CAPACITY)
			//注：这个函数是类的私有方法，对外的接口是下面的setCourseEntry()
			/* 
			 * 	函数功能： 更新显示课程的Panel中的第lineNo行显示的内容
			 * */
			int index=lineNo+scrollBarPos; //“这一行要显示的内容是courses中下标为几的对象？”
			if(index>=coursesCount) { //当coursesCount < COURSE_TO_SELECT_PANEL_CAPACITY时，有可能出现这种情况，此时这一行应该不显示任何东西
				courseChooseCheckBox.setVisible(false);
				courseIDLabel.setVisible(false);
				courseTitleLabel.setVisible(false);
				classTimeLabel.setVisible(false);
				coursePropertyLabel.setVisible(false);
				totalHourLabel.setVisible(false);
				classCapacityLabel.setVisible(false);
				classVacancyLabel.setVisible(false);
				courseFeaturesLabel.setVisible(false);
				System.out.println("out_of_range");
				return;
			}
			courseChooseCheckBox.setSelected(checkBoxChecked[index]);
			courseIDLabel.setText(courses[index].courseID);
			courseTitleLabel.setText(courses[index].courseTitle);
			String strClassTime=""+(courses[index].classTime1 / 10)+"-"+(courses[index].classTime1 % 10);
			if(0 != courses[index].classTime2) strClassTime+=";"+(courses[index].classTime2 / 10)+"-"+(courses[index].classTime2 % 10);
			classTimeLabel.setText(strClassTime);
			switch(courses[index].courseProperty) {
			case "R":
				coursePropertyLabel.setText("必修");
				break;
			case "RE":
				coursePropertyLabel.setText("限选");
				break;
			case "E":
				coursePropertyLabel.setText("任选");
				break;
			}
			totalHourLabel.setText(""+courses[index].totalHour);
			classCapacityLabel.setText(""+courses[index].classCapacity);
			int numSelected=courses[index].classCapacity-courses[index].classVacancy; //选课人数
			classVacancyLabel.setText(""+numSelected);
			String strCourseFeatures="";
			//课程特色这部分还没开发
				strCourseFeatures="还没开发。。。。。。。。。。。。。。。。。";
			courseFeaturesLabel.setText(strCourseFeatures);
			courseChooseCheckBox.setVisible(true);
			courseIDLabel.setVisible(true);
			courseTitleLabel.setVisible(true);
			classTimeLabel.setVisible(true);
			coursePropertyLabel.setVisible(true);
			totalHourLabel.setVisible(true);
			classCapacityLabel.setVisible(true);
			classVacancyLabel.setVisible(true);
			courseFeaturesLabel.setVisible(true);
		}

		@SuppressWarnings("unused")
		static void repaintCourseToSelectEntry(Courses courseToSelect[], boolean checkBoxChecked[], int courseToSelectCount, 
				int scrollBarPos, int lineNo) {
			//注：这个函数是对外的接口，是静态的。外部经由CourseEntry类调用这个方法，这个函数再将要进行的操作利用__repaintCourseEntry()分配给某个具体的对象。
			//	这么处理是为了避免在courseEntry的下标和setCourseEntry的参数中需要给出相同的值（相同的信息被写了两次）
			courseToSelectEntry[lineNo].__repaintCourseEntry(courseToSelect, checkBoxChecked, courseToSelectCount, 
					scrollBarPos, lineNo);
		}
		
		@SuppressWarnings("unused")
		static void repaintSelectedCoursesEntry(Courses selectedCourses[], boolean checkBoxChecked[], int selectedCoursesCount, 
				int scrollBarPos, int lineNo) {
			selectedCoursesEntry[lineNo].__repaintCourseEntry(selectedCourses, checkBoxChecked, selectedCoursesCount, 
					scrollBarPos, lineNo);
		}
		
		static void repaintCourseToSelectEntryAll(Courses courseToSelect[], boolean checkBoxChecked[], int courseToSelectCount, 
				int scrollBarPos) {
			int lineNo=0;
			for(lineNo=0; lineNo<COURSE_TO_SELECT_PANEL_CAPACITY; lineNo++) {
				courseToSelectEntry[lineNo].__repaintCourseEntry(courseToSelect, checkBoxChecked, courseToSelectCount, 
						scrollBarPos, lineNo);
			}
		}
		
		static void repaintSelectedCoursesEntryAll(Courses selectedCourses[], boolean checkBoxChecked[], int selectedCoursesCount, 
				int scrollBarPos) {
			int lineNo=0;
			for(lineNo=0; lineNo<SELECTED_COURSES_PANEL_CAPACITY; lineNo++) {
				selectedCoursesEntry[lineNo].__repaintCourseEntry(selectedCourses, checkBoxChecked, selectedCoursesCount, 
						scrollBarPos, lineNo);
			}
		}
		
		//下面这几个函数的关系与上面类似。。。
		void __updateCourseChecked(boolean checkBoxChecked[], 
				 int scrollBarPos, int lineNo) {
			//用户点击了第lineNo行上的CheckBox，将其状态加载到checkBoxChecked数组里
			int index=lineNo+scrollBarPos; //“用户点击的是对应于courseToSelect[]数组里面下标为几的课程的CheckBox？”
			checkBoxChecked[index]=courseChooseCheckBox.isSelected();
		}
		
		static void updateCourseToSelectChecked(boolean checkBoxChecked[], 
				int scrollBarPos, int lineNo) {
			courseToSelectEntry[lineNo].__updateCourseChecked(checkBoxChecked, scrollBarPos, lineNo);
		}
		
		static void updateSelectedCoursesChecked(boolean checkBoxChecked[], 
				int scrollBarPos, int lineNo) {
			selectedCoursesEntry[lineNo].__updateCourseChecked(checkBoxChecked, scrollBarPos, lineNo);
		}
	}
	
	static Courses courseToSelect[]; //可选课程列表（在courseToSelectPanel里显示）
	static Courses selectedCourses[]; //已选课程列表（在SelectedcoursesPanel里显示）
	static int courseToSelectCount=0;
	static int selectedCoursesCount=0;
	static int COURSE_TO_SELECT_PANEL_CAPACITY=6; //（视为const）表示courseToSelectPanel里最多同时显示多少条课程
	static int SELECTED_COURSES_PANEL_CAPACITY=6; //同上
	//int courseToSelectScrollBarPos=0; //表示滚动条现在滚到了哪里
	//（滚动条位置直接从对象的value属性读出来即可，避免同一个变量在两个位置存储）
	static boolean courseToSelectCheckBoxChecked[]; //记录显示出来的可选课程列表中哪些课程的CheckBox被选中了
	static boolean selectedCoursesCheckBoxChecked[]; //同上


	/*************************************************************
	 * 	
	 * 【构造函数】
	 * 		不要新建JFrame窗口对象，而是把上层传进来的窗口对象里面的东西扔了，重新添加
	 * 
	 *************************************************************/
	public WinCourseRegistration(EventManager mainGame,JFrame frame) {
		
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.mainGame=mainGame;
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		/*************************************************************	
		 *【背景镶板】
		 * 		所有的组件都在里面，两个按钮直接用插件拖进去的
		 * 		这一部分按照流程做的话就会自然消失的，推荐直接在可视化界面编辑属性 
		 *************************************************************/
		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBackground(Color.WHITE);
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
		/*************************************************************	
		 * 【小事件】 
		 *  	这一部分需要用dataPackage.trigSubEvent决定是否绘制
		 *  	具体用法见MorninigClass窗口
		 *************************************************************/
		
		JButton selectRCourseButton = new JButton("选择必修课");
		selectRCourseButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		selectRCourseButton.setBounds(50, 25, 150, 50);
		backgroundPanel.add(selectRCourseButton);
		
		JButton selectRECourseButton = new JButton("选择限选课");
		selectRECourseButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		selectRECourseButton.setBounds(300, 25, 150, 50);
		backgroundPanel.add(selectRECourseButton);
		
		JButton selectFailedCourseButton = new JButton("选择重修课");
		selectFailedCourseButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		selectFailedCourseButton.setBounds(900, 25, 150, 50);
		backgroundPanel.add(selectFailedCourseButton);
		
		JButton selectECourseButton = new JButton("选择任选课");
		selectECourseButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		selectECourseButton.setBounds(600, 25, 150, 50);
		backgroundPanel.add(selectECourseButton);
		
		JButton finishButton = new JButton("结束选课");
		finishButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		finishButton.setBounds(800, 625, 150, 50);
		backgroundPanel.add(finishButton);
		JPanel msgPanel = new JPanel();
		msgPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		msgPanel.setBackground(new Color(255, 255, 240));
		msgPanel.setVisible(false);
		
		JPanel timetablePanel = new JPanel();
		timetablePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		timetablePanel.setBackground(new Color(255, 255, 240));
		timetablePanel.setBounds(100, 150, 800, 400);
		backgroundPanel.add(timetablePanel);
		timetablePanel.setLayout(null);
		timetablePanel.setVisible(false);
		
		JLabel timetableLabel = new JLabel("课表");
		timetableLabel.setBounds(5, 5, 24, 15);
		timetablePanel.add(timetableLabel);
		
		JButton timetableOKButton = new JButton("OK");
		timetableOKButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		timetableOKButton.setBounds(625, 325, 150, 50);
		timetablePanel.add(timetableOKButton);
		
		
		JLabel label_1 = new JLabel("上午");
		label_1.setBounds(30, 150, 45, 15);
		timetablePanel.add(label_1);
		
		JLabel label_2 = new JLabel("下午");
		label_2.setBounds(30, 250, 45, 15);
		timetablePanel.add(label_2);
		
		JLabel label_3 = new JLabel("星期一");
		label_3.setBounds(140, 50, 50, 15);
		timetablePanel.add(label_3);
		
		JLabel label_4 = new JLabel("星期二");
		label_4.setBounds(265, 50, 50, 15);
		timetablePanel.add(label_4);
		
		JLabel label_5 = new JLabel("星期三");
		label_5.setBounds(390, 50, 50, 15);
		timetablePanel.add(label_5);
		
		JLabel label_6 = new JLabel("星期四");
		label_6.setBounds(515, 50, 50, 15);
		timetablePanel.add(label_6);
		
		JLabel label_8 = new JLabel("星期五");
		label_8.setBounds(640, 50, 50, 15);
		timetablePanel.add(label_8);
		
		JTextArea timeTable11 = new JTextArea();
		timeTable11.setBackground(new Color(240, 255, 240));
		timeTable11.setWrapStyleWord(true);
		timeTable11.setFont(new Font("Dialog", Font.PLAIN, 20));
		timeTable11.setEditable(false);
		timeTable11.setBorder(new LineBorder(new Color(119, 136, 153)));
		timeTable11.setBounds(100, 100, 126, 101);
		timetablePanel.add(timeTable11);
		
		JTextArea timeTable12 = new JTextArea();
		timeTable12.setBackground(new Color(240, 255, 240));
		timeTable12.setWrapStyleWord(true);
		timeTable12.setFont(new Font("Dialog", Font.PLAIN, 20));
		timeTable12.setEditable(false);
		timeTable12.setBorder(new LineBorder(new Color(119, 136, 153)));
		timeTable12.setBounds(100, 200, 126, 100);
		timetablePanel.add(timeTable12);
		
		JTextArea timeTable21 = new JTextArea();
		timeTable21.setBackground(new Color(240, 255, 240));
		timeTable21.setWrapStyleWord(true);
		timeTable21.setFont(new Font("Dialog", Font.PLAIN, 20));
		timeTable21.setEditable(false);
		timeTable21.setBorder(new LineBorder(new Color(119, 136, 153)));
		timeTable21.setBounds(225, 100, 126, 101);
		timetablePanel.add(timeTable21);
		
		JTextArea timeTable22 = new JTextArea();
		timeTable22.setBackground(new Color(240, 255, 240));
		timeTable22.setWrapStyleWord(true);
		timeTable22.setFont(new Font("Dialog", Font.PLAIN, 20));
		timeTable22.setEditable(false);
		timeTable22.setBorder(new LineBorder(new Color(119, 136, 153)));
		timeTable22.setBounds(225, 200, 126, 100);
		timetablePanel.add(timeTable22);
		
		JTextArea timeTable31 = new JTextArea();
		timeTable31.setBackground(new Color(240, 255, 240));
		timeTable31.setWrapStyleWord(true);
		timeTable31.setFont(new Font("Dialog", Font.PLAIN, 20));
		timeTable31.setEditable(false);
		timeTable31.setBorder(new LineBorder(new Color(119, 136, 153)));
		timeTable31.setBounds(350, 100, 126, 101);
		timetablePanel.add(timeTable31);
		
		JTextArea timeTable32 = new JTextArea();
		timeTable32.setBackground(new Color(240, 255, 240));
		timeTable32.setWrapStyleWord(true);
		timeTable32.setFont(new Font("Dialog", Font.PLAIN, 20));
		timeTable32.setEditable(false);
		timeTable32.setBorder(new LineBorder(new Color(119, 136, 153)));
		timeTable32.setBounds(350, 200, 126, 100);
		timetablePanel.add(timeTable32);
		
		JTextArea timeTable41 = new JTextArea();
		timeTable41.setBackground(new Color(240, 255, 240));
		timeTable41.setWrapStyleWord(true);
		timeTable41.setFont(new Font("Dialog", Font.PLAIN, 20));
		timeTable41.setEditable(false);
		timeTable41.setBorder(new LineBorder(new Color(119, 136, 153)));
		timeTable41.setBounds(475, 100, 126, 101);
		timetablePanel.add(timeTable41);
		
		JTextArea timeTable42 = new JTextArea();
		timeTable42.setBackground(new Color(240, 255, 240));
		timeTable42.setWrapStyleWord(true);
		timeTable42.setFont(new Font("Dialog", Font.PLAIN, 20));
		timeTable42.setEditable(false);
		timeTable42.setBorder(new LineBorder(new Color(119, 136, 153)));
		timeTable42.setBounds(475, 200, 126, 100);
		timetablePanel.add(timeTable42);
		
		JTextArea timeTable51 = new JTextArea();
		timeTable51.setBackground(new Color(240, 255, 240));
		timeTable51.setWrapStyleWord(true);
		timeTable51.setFont(new Font("Dialog", Font.PLAIN, 20));
		timeTable51.setEditable(false);
		timeTable51.setBorder(new LineBorder(new Color(119, 136, 153)));
		timeTable51.setBounds(600, 100, 125, 101);
		timetablePanel.add(timeTable51);
		
		JTextArea timeTable52 = new JTextArea();
		timeTable52.setBackground(new Color(240, 255, 240));
		timeTable52.setWrapStyleWord(true);
		timeTable52.setFont(new Font("Dialog", Font.PLAIN, 20));
		timeTable52.setEditable(false);
		timeTable52.setBorder(new LineBorder(new Color(119, 136, 153)));
		timeTable52.setBounds(600, 200, 125, 100);
		timetablePanel.add(timeTable52);
		msgPanel.setBounds(244, 200, 495, 330);
		backgroundPanel.add(msgPanel);
		msgPanel.setLayout(null);
		
		JTextArea msgTextAera = new JTextArea();
		msgTextAera.setWrapStyleWord(true);
		msgTextAera.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		msgTextAera.setBorder(new LineBorder(new Color(119, 136, 153)));
		msgTextAera.setEditable(false);
		msgTextAera.setBounds(48, 34, 400, 200);
		msgPanel.add(msgTextAera);
		
		JButton msgOKButton = new JButton("OK");
		msgOKButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		msgOKButton.setBounds(301, 263, 150, 50);
		msgPanel.add(msgOKButton);
		
		
		/*************************************************************	
		 * 【放背景图】
		 * 		最后放。
		 *************************************************************/
		
		JPanel Background=new ImagePanel("imgsrc//Windom/dom3.jpg",0, 0, 1080, 720);
		
		JPanel courseSelectionPanel = new JPanel();
		courseSelectionPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		courseSelectionPanel.setBackground(new Color(240, 248, 255));
		courseSelectionPanel.setBounds(50, 100, 1000, 500);
		backgroundPanel.add(courseSelectionPanel);
		courseSelectionPanel.setLayout(null);
		
		JButton submitCourseButton = new JButton("提交选课");
		submitCourseButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		submitCourseButton.setBounds(650, 225, 150, 50);
		courseSelectionPanel.add(submitCourseButton);
		
		JButton deleteCourseButton = new JButton("删除选课");
		deleteCourseButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		deleteCourseButton.setBounds(825, 225, 150, 50);
		courseSelectionPanel.add(deleteCourseButton);
		
		JLabel courseSelectionLabel = new JLabel("可选课程：");
		courseSelectionLabel.setBounds(10, 10, 100, 25);
		courseSelectionPanel.add(courseSelectionLabel);
		
		JLabel deleteCourseLabel = new JLabel("已选定课程");
		deleteCourseLabel.setBounds(10, 275, 100, 25);
		courseSelectionPanel.add(deleteCourseLabel);
		
		JPanel courseToSelectPanel = new JPanel();
		courseToSelectPanel.setBackground(new Color(245, 255, 250));
		courseToSelectPanel.setBounds(25, 35, 900, 175);
		courseSelectionPanel.add(courseToSelectPanel);
		courseToSelectPanel.setLayout(null);
		
		JCheckBox courseChooseCheckBox0 = new JCheckBox("");
		courseChooseCheckBox0.setBounds(0, 25, 25, 25);
		courseToSelectPanel.add(courseChooseCheckBox0);
		
		JCheckBox courseChooseCheckBox1 = new JCheckBox("");
		courseChooseCheckBox1.setBounds(0, 50, 25, 25);
		courseToSelectPanel.add(courseChooseCheckBox1);
		
		JCheckBox courseChooseCheckBox2 = new JCheckBox("");
		courseChooseCheckBox2.setBounds(0, 75, 25, 25);
		courseToSelectPanel.add(courseChooseCheckBox2);
		
		JCheckBox courseChooseCheckBox3 = new JCheckBox("");
		courseChooseCheckBox3.setBounds(0, 100, 25, 25);
		courseToSelectPanel.add(courseChooseCheckBox3);
		
		JCheckBox courseChooseCheckBox4 = new JCheckBox("");
		courseChooseCheckBox4.setBounds(0, 125, 25, 25);
		courseToSelectPanel.add(courseChooseCheckBox4);
		
		JCheckBox courseChooseCheckBox5 = new JCheckBox("");
		courseChooseCheckBox5.setBounds(0, 150, 25, 25);
		courseToSelectPanel.add(courseChooseCheckBox5);
		
		JLabel courseIDLabel = new JLabel("课程号");
		courseIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		courseIDLabel.setBounds(25, 0, 60, 25);
		courseToSelectPanel.add(courseIDLabel);
		
		JLabel courseIDLabel0 = new JLabel("");
		courseIDLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		courseIDLabel0.setBounds(25, 25, 60, 25);
		courseToSelectPanel.add(courseIDLabel0);
		
		JLabel courseIDLabel1 = new JLabel("");
		courseIDLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		courseIDLabel1.setBounds(25, 50, 60, 25);
		courseToSelectPanel.add(courseIDLabel1);
		
		JLabel courseIDLabel2 = new JLabel("");
		courseIDLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		courseIDLabel2.setBounds(25, 75, 60, 25);
		courseToSelectPanel.add(courseIDLabel2);
		
		JLabel courseIDLabel3 = new JLabel("");
		courseIDLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		courseIDLabel3.setBounds(25, 100, 60, 25);
		courseToSelectPanel.add(courseIDLabel3);
		
		JLabel courseIDLabel4 = new JLabel("");
		courseIDLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		courseIDLabel4.setBounds(25, 125, 60, 25);
		courseToSelectPanel.add(courseIDLabel4);
		
		JLabel courseIDLabel5 = new JLabel("");
		courseIDLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		courseIDLabel5.setBounds(25, 150, 60, 25);
		courseToSelectPanel.add(courseIDLabel5);
		
		JLabel courseTitleLabel = new JLabel("课程名");
		courseTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		courseTitleLabel.setBounds(90, 0, 100, 25);
		courseToSelectPanel.add(courseTitleLabel);
		
		JLabel courseTitleLabel0 = new JLabel("");
		courseTitleLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		courseTitleLabel0.setBounds(90, 25, 100, 25);
		courseToSelectPanel.add(courseTitleLabel0);
		
		JLabel courseTitleLabel1 = new JLabel("");
		courseTitleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		courseTitleLabel1.setBounds(90, 50, 100, 25);
		courseToSelectPanel.add(courseTitleLabel1);
		
		JLabel courseTitleLabel2 = new JLabel("");
		courseTitleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		courseTitleLabel2.setBounds(90, 75, 100, 25);
		courseToSelectPanel.add(courseTitleLabel2);
		
		JLabel courseTitleLabel3 = new JLabel("");
		courseTitleLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		courseTitleLabel3.setBounds(90, 100, 100, 25);
		courseToSelectPanel.add(courseTitleLabel3);
		
		JLabel courseTitleLabel4 = new JLabel("");
		courseTitleLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		courseTitleLabel4.setBounds(90, 125, 100, 25);
		courseToSelectPanel.add(courseTitleLabel4);
		
		JLabel courseTitleLabel5 = new JLabel();
		courseTitleLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		courseTitleLabel5.setBounds(90, 150, 100, 25);
		courseToSelectPanel.add(courseTitleLabel5);
		
		JLabel classTimeLabel = new JLabel("时间");
		classTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		classTimeLabel.setBounds(195, 0, 50, 25);
		courseToSelectPanel.add(classTimeLabel);
		
		JLabel classTimeLabel0 = new JLabel("");
		classTimeLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		classTimeLabel0.setBounds(195, 25, 50, 25);
		courseToSelectPanel.add(classTimeLabel0);
		
		JLabel classTimeLabel1 = new JLabel("");
		classTimeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		classTimeLabel1.setBounds(195, 50, 50, 25);
		courseToSelectPanel.add(classTimeLabel1);
		
		JLabel classTimeLabel2 = new JLabel("");
		classTimeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		classTimeLabel2.setBounds(195, 75, 50, 25);
		courseToSelectPanel.add(classTimeLabel2);
		
		JLabel classTimeLabel3 = new JLabel("");
		classTimeLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		classTimeLabel3.setBounds(195, 100, 50, 25);
		courseToSelectPanel.add(classTimeLabel3);
		
		JLabel classTimeLabel4 = new JLabel("");
		classTimeLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		classTimeLabel4.setBounds(195, 125, 50, 25);
		courseToSelectPanel.add(classTimeLabel4);
		
		JLabel classTimeLabel5 = new JLabel("");
		classTimeLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		classTimeLabel5.setBounds(195, 150, 50, 25);
		courseToSelectPanel.add(classTimeLabel5);
		
		JLabel coursePropertyLabel = new JLabel("属性");
		coursePropertyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		coursePropertyLabel.setBounds(250, 0, 50, 25);
		courseToSelectPanel.add(coursePropertyLabel);
		
		JLabel coursePropertyLabel0 = new JLabel("");
		coursePropertyLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		coursePropertyLabel0.setBounds(250, 25, 50, 25);
		courseToSelectPanel.add(coursePropertyLabel0);
		
		JLabel coursePropertyLabel1 = new JLabel("");
		coursePropertyLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		coursePropertyLabel1.setBounds(250, 50, 50, 25);
		courseToSelectPanel.add(coursePropertyLabel1);
		
		JLabel coursePropertyLabel2 = new JLabel("");
		coursePropertyLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		coursePropertyLabel2.setBounds(250, 75, 50, 25);
		courseToSelectPanel.add(coursePropertyLabel2);
		
		JLabel coursePropertyLabel3 = new JLabel("");
		coursePropertyLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		coursePropertyLabel3.setBounds(250, 100, 50, 25);
		courseToSelectPanel.add(coursePropertyLabel3);
		
		JLabel coursePropertyLabel4 = new JLabel("");
		coursePropertyLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		coursePropertyLabel4.setBounds(250, 125, 50, 25);
		courseToSelectPanel.add(coursePropertyLabel4);
		
		JLabel coursePropertyLabel5 = new JLabel("");
		coursePropertyLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		coursePropertyLabel5.setBounds(250, 150, 50, 25);
		courseToSelectPanel.add(coursePropertyLabel5);
		
		JLabel totalHourLabel = new JLabel("学时");
		totalHourLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalHourLabel.setBounds(305, 0, 40, 25);
		courseToSelectPanel.add(totalHourLabel);
		
		JLabel totalHourLabel0 = new JLabel("");
		totalHourLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		totalHourLabel0.setBounds(305, 25, 40, 25);
		courseToSelectPanel.add(totalHourLabel0);
		
		JLabel totalHourLabel1 = new JLabel("");
		totalHourLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		totalHourLabel1.setBounds(305, 50, 40, 25);
		courseToSelectPanel.add(totalHourLabel1);
		
		JLabel totalHourLabel2 = new JLabel("");
		totalHourLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		totalHourLabel2.setBounds(305, 75, 40, 25);
		courseToSelectPanel.add(totalHourLabel2);
		
		JLabel totalHourLabel3 = new JLabel("");
		totalHourLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		totalHourLabel3.setBounds(305, 100, 40, 25);
		courseToSelectPanel.add(totalHourLabel3);
		
		JLabel totalHourLabel4 = new JLabel("");
		totalHourLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		totalHourLabel4.setBounds(305, 125, 40, 25);
		courseToSelectPanel.add(totalHourLabel4);
		
		JLabel totalHourLabel5 = new JLabel("");
		totalHourLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		totalHourLabel5.setBounds(305, 150, 40, 25);
		courseToSelectPanel.add(totalHourLabel5);
		
		JLabel classCapacityLabel = new JLabel("容量");
		classCapacityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		classCapacityLabel.setBounds(350, 0, 40, 25);
		courseToSelectPanel.add(classCapacityLabel);
		
		JLabel classCapacityLabel0 = new JLabel("");
		classCapacityLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		classCapacityLabel0.setBounds(350, 25, 40, 25);
		courseToSelectPanel.add(classCapacityLabel0);
		
		JLabel classCapacityLabel1 = new JLabel("");
		classCapacityLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		classCapacityLabel1.setBounds(350, 50, 40, 25);
		courseToSelectPanel.add(classCapacityLabel1);
		
		JLabel classCapacityLabel2 = new JLabel("");
		classCapacityLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		classCapacityLabel2.setBounds(350, 75, 40, 25);
		courseToSelectPanel.add(classCapacityLabel2);
		
		JLabel classCapacityLabel3 = new JLabel("");
		classCapacityLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		classCapacityLabel3.setBounds(350, 100, 40, 25);
		courseToSelectPanel.add(classCapacityLabel3);
		
		JLabel classCapacityLabel4 = new JLabel("");
		classCapacityLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		classCapacityLabel4.setBounds(350, 125, 40, 25);
		courseToSelectPanel.add(classCapacityLabel4);
		
		JLabel classCapacityLabel5 = new JLabel("");
		classCapacityLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		classCapacityLabel5.setBounds(350, 150, 40, 25);
		courseToSelectPanel.add(classCapacityLabel5);
		
		JLabel classVacancyLabel = new JLabel("已选课人数");
		classVacancyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		classVacancyLabel.setBounds(395, 0, 78, 25);
		courseToSelectPanel.add(classVacancyLabel);
		
		JLabel classVacancyLabel0 = new JLabel("");
		classVacancyLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		classVacancyLabel0.setBounds(395, 25, 60, 25);
		courseToSelectPanel.add(classVacancyLabel0);
		
		JLabel classVacancyLabel1 = new JLabel("");
		classVacancyLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		classVacancyLabel1.setBounds(395, 50, 60, 25);
		courseToSelectPanel.add(classVacancyLabel1);
		
		JLabel classVacancyLabel2 = new JLabel("");
		classVacancyLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		classVacancyLabel2.setBounds(395, 75, 60, 25);
		courseToSelectPanel.add(classVacancyLabel2);
		
		JLabel classVacancyLabel3 = new JLabel("");
		classVacancyLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		classVacancyLabel3.setBounds(395, 100, 60, 25);
		courseToSelectPanel.add(classVacancyLabel3);
		
		JLabel classVacancyLabel4 = new JLabel("");
		classVacancyLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		classVacancyLabel4.setBounds(395, 125, 60, 25);
		courseToSelectPanel.add(classVacancyLabel4);
		
		JLabel classVacancyLabel5 = new JLabel("");
		classVacancyLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		classVacancyLabel5.setBounds(395, 150, 60, 25);
		courseToSelectPanel.add(classVacancyLabel5);
		
		JLabel courseFeaturesLabel = new JLabel("课程特色");
		courseFeaturesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		courseFeaturesLabel.setBounds(489, 0, 150, 25);
		courseToSelectPanel.add(courseFeaturesLabel);
		
		JLabel courseFeaturesLabel0 = new JLabel("");
		courseFeaturesLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		courseFeaturesLabel0.setBounds(489, 25, 150, 25);
		courseToSelectPanel.add(courseFeaturesLabel0);
		
		JLabel courseFeaturesLabel1 = new JLabel("");
		courseFeaturesLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		courseFeaturesLabel1.setBounds(489, 50, 150, 25);
		courseToSelectPanel.add(courseFeaturesLabel1);
		
		JLabel courseFeaturesLabel2 = new JLabel("");
		courseFeaturesLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		courseFeaturesLabel2.setBounds(489, 75, 150, 25);
		courseToSelectPanel.add(courseFeaturesLabel2);
		
		JLabel courseFeaturesLabel3 = new JLabel("");
		courseFeaturesLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		courseFeaturesLabel3.setBounds(489, 100, 150, 25);
		courseToSelectPanel.add(courseFeaturesLabel3);
		
		JLabel courseFeaturesLabel4 = new JLabel("");
		courseFeaturesLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		courseFeaturesLabel4.setBounds(489, 125, 150, 25);
		courseToSelectPanel.add(courseFeaturesLabel4);
		
		JLabel courseFeaturesLabel5 = new JLabel("");
		courseFeaturesLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		courseFeaturesLabel5.setBounds(489, 150, 150, 25);
		courseToSelectPanel.add(courseFeaturesLabel5);
		
		JScrollBar courseToSelectPanelScrollBar = new JScrollBar();
		courseToSelectPanelScrollBar.setBounds(925, 35, 17, 175);
		courseSelectionPanel.add(courseToSelectPanelScrollBar);
		Background.setBounds(0, 0, 1080, 720);
		backgroundPanel.add(Background);
		Background.setLayout(null);
				
		JPanel selectedCoursesPanel = new JPanel();
		selectedCoursesPanel.setBackground(new Color(245, 255, 250));
		selectedCoursesPanel.setLayout(null);
		selectedCoursesPanel.setBounds(25, 300, 900, 175);
		courseSelectionPanel.add(selectedCoursesPanel);
		
		JCheckBox courseSelectedCheckBox0 = new JCheckBox("");
		courseSelectedCheckBox0.setBounds(0, 25, 25, 25);
		selectedCoursesPanel.add(courseSelectedCheckBox0);
		
		JCheckBox courseSelectedCheckBox1 = new JCheckBox("");
		courseSelectedCheckBox1.setBounds(0, 50, 25, 25);
		selectedCoursesPanel.add(courseSelectedCheckBox1);
		
		JCheckBox courseSelectedCheckBox2 = new JCheckBox("");
		courseSelectedCheckBox2.setBounds(0, 75, 25, 25);
		selectedCoursesPanel.add(courseSelectedCheckBox2);
		
		JCheckBox courseSelectedCheckBox3 = new JCheckBox("");
		courseSelectedCheckBox3.setBounds(0, 100, 25, 25);
		selectedCoursesPanel.add(courseSelectedCheckBox3);
		
		JCheckBox courseSelectedCheckBox4 = new JCheckBox("");
		courseSelectedCheckBox4.setBounds(0, 125, 25, 25);
		selectedCoursesPanel.add(courseSelectedCheckBox4);
		
		JCheckBox courseSelectedCheckBox5 = new JCheckBox("");
		courseSelectedCheckBox5.setBounds(0, 150, 25, 25);
		selectedCoursesPanel.add(courseSelectedCheckBox5);
		
		JLabel label = new JLabel("课程号");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(25, 0, 60, 25);
		selectedCoursesPanel.add(label);
		
		JLabel selectedCourseIDLabel0 = new JLabel("");
		selectedCourseIDLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseIDLabel0.setBounds(25, 25, 60, 25);
		selectedCoursesPanel.add(selectedCourseIDLabel0);
		
		JLabel selectedCourseIDLabel1 = new JLabel("");
		selectedCourseIDLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseIDLabel1.setBounds(25, 50, 60, 25);
		selectedCoursesPanel.add(selectedCourseIDLabel1);
		
		JLabel selectedCourseIDLabel2 = new JLabel("");
		selectedCourseIDLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseIDLabel2.setBounds(25, 75, 60, 25);
		selectedCoursesPanel.add(selectedCourseIDLabel2);
		
		JLabel selectedCourseIDLabel3 = new JLabel("");
		selectedCourseIDLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseIDLabel3.setBounds(25, 100, 60, 25);
		selectedCoursesPanel.add(selectedCourseIDLabel3);
		
		JLabel selectedCourseIDLabel4 = new JLabel("");
		selectedCourseIDLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseIDLabel4.setBounds(25, 125, 60, 25);
		selectedCoursesPanel.add(selectedCourseIDLabel4);
		
		JLabel selectedCourseIDLabel5 = new JLabel("");
		selectedCourseIDLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseIDLabel5.setBounds(25, 150, 60, 25);
		selectedCoursesPanel.add(selectedCourseIDLabel5);
		
		JLabel label_7 = new JLabel("课程名");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(90, 0, 100, 25);
		selectedCoursesPanel.add(label_7);
		
		JLabel selectedCourseTitleLabel0 = new JLabel("");
		selectedCourseTitleLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseTitleLabel0.setBounds(90, 25, 100, 25);
		selectedCoursesPanel.add(selectedCourseTitleLabel0);
		
		JLabel selectedCourseTitleLabel1 = new JLabel("");
		selectedCourseTitleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseTitleLabel1.setBounds(90, 50, 100, 25);
		selectedCoursesPanel.add(selectedCourseTitleLabel1);
		
		JLabel selectedCourseTitleLabel2 = new JLabel("");
		selectedCourseTitleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseTitleLabel2.setBounds(90, 75, 100, 25);
		selectedCoursesPanel.add(selectedCourseTitleLabel2);
		
		JLabel selectedCourseTitleLabel3 = new JLabel("");
		selectedCourseTitleLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseTitleLabel3.setBounds(90, 100, 100, 25);
		selectedCoursesPanel.add(selectedCourseTitleLabel3);
		
		JLabel selectedCourseTitleLabel4 = new JLabel("");
		selectedCourseTitleLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseTitleLabel4.setBounds(90, 125, 100, 25);
		selectedCoursesPanel.add(selectedCourseTitleLabel4);
		
		JLabel selectedCourseTitleLabel5 = new JLabel("");
		selectedCourseTitleLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseTitleLabel5.setBounds(90, 150, 100, 25);
		selectedCoursesPanel.add(selectedCourseTitleLabel5);
		
		JLabel label_14 = new JLabel("时间");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(195, 0, 50, 25);
		selectedCoursesPanel.add(label_14);
		
		JLabel selectedClassTimeLabel0 = new JLabel("");
		selectedClassTimeLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassTimeLabel0.setBounds(195, 25, 50, 25);
		selectedCoursesPanel.add(selectedClassTimeLabel0);
		
		JLabel selectedClassTimeLabel1 = new JLabel("");
		selectedClassTimeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassTimeLabel1.setBounds(195, 50, 50, 25);
		selectedCoursesPanel.add(selectedClassTimeLabel1);
		
		JLabel selectedClassTimeLabel2 = new JLabel("");
		selectedClassTimeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassTimeLabel2.setBounds(195, 75, 50, 25);
		selectedCoursesPanel.add(selectedClassTimeLabel2);
		
		JLabel selectedClassTimeLabel3 = new JLabel("");
		selectedClassTimeLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassTimeLabel3.setBounds(195, 100, 50, 25);
		selectedCoursesPanel.add(selectedClassTimeLabel3);
		
		JLabel selectedClassTimeLabel4 = new JLabel("");
		selectedClassTimeLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassTimeLabel4.setBounds(195, 125, 50, 25);
		selectedCoursesPanel.add(selectedClassTimeLabel4);
		
		JLabel selectedClassTimeLabel5 = new JLabel("");
		selectedClassTimeLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassTimeLabel5.setBounds(195, 150, 50, 25);
		selectedCoursesPanel.add(selectedClassTimeLabel5);
		
		JLabel label_21 = new JLabel("属性");
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setBounds(250, 0, 50, 25);
		selectedCoursesPanel.add(label_21);
		
		JLabel selectedCoursePropertyLabel0 = new JLabel("");
		selectedCoursePropertyLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCoursePropertyLabel0.setBounds(250, 25, 50, 25);
		selectedCoursesPanel.add(selectedCoursePropertyLabel0);
		
		JLabel selectedCoursePropertyLabel1 = new JLabel("");
		selectedCoursePropertyLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCoursePropertyLabel1.setBounds(250, 50, 50, 25);
		selectedCoursesPanel.add(selectedCoursePropertyLabel1);
		
		JLabel selectedCoursePropertyLabel2 = new JLabel("");
		selectedCoursePropertyLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCoursePropertyLabel2.setBounds(250, 75, 50, 25);
		selectedCoursesPanel.add(selectedCoursePropertyLabel2);
		
		JLabel selectedCoursePropertyLabel3 = new JLabel("");
		selectedCoursePropertyLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCoursePropertyLabel3.setBounds(250, 100, 50, 25);
		selectedCoursesPanel.add(selectedCoursePropertyLabel3);
		
		JLabel selectedCoursePropertyLabel4 = new JLabel("");
		selectedCoursePropertyLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCoursePropertyLabel4.setBounds(250, 125, 50, 25);
		selectedCoursesPanel.add(selectedCoursePropertyLabel4);
		
		JLabel selectedCoursePropertyLabel5 = new JLabel("");
		selectedCoursePropertyLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCoursePropertyLabel5.setBounds(250, 150, 50, 25);
		selectedCoursesPanel.add(selectedCoursePropertyLabel5);
		
		JLabel label_28 = new JLabel("学时");
		label_28.setHorizontalAlignment(SwingConstants.CENTER);
		label_28.setBounds(305, 0, 40, 25);
		selectedCoursesPanel.add(label_28);
		
		JLabel selectedTotalHourLabel0 = new JLabel("");
		selectedTotalHourLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		selectedTotalHourLabel0.setBounds(305, 25, 40, 25);
		selectedCoursesPanel.add(selectedTotalHourLabel0);
		
		JLabel selectedTotalHourLabel1 = new JLabel("");
		selectedTotalHourLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		selectedTotalHourLabel1.setBounds(305, 50, 40, 25);
		selectedCoursesPanel.add(selectedTotalHourLabel1);
		
		JLabel selectedTotalHourLabel2 = new JLabel("");
		selectedTotalHourLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectedTotalHourLabel2.setBounds(305, 75, 40, 25);
		selectedCoursesPanel.add(selectedTotalHourLabel2);
		
		JLabel selectedTotalHourLabel3 = new JLabel("");
		selectedTotalHourLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		selectedTotalHourLabel3.setBounds(305, 100, 40, 25);
		selectedCoursesPanel.add(selectedTotalHourLabel3);
		
		JLabel selectedTotalHourLabel4 = new JLabel("");
		selectedTotalHourLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		selectedTotalHourLabel4.setBounds(305, 125, 40, 25);
		selectedCoursesPanel.add(selectedTotalHourLabel4);
		
		JLabel selectedTotalHourLabel5 = new JLabel("");
		selectedTotalHourLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		selectedTotalHourLabel5.setBounds(305, 150, 40, 25);
		selectedCoursesPanel.add(selectedTotalHourLabel5);
		
		JLabel label_35 = new JLabel("容量");
		label_35.setHorizontalAlignment(SwingConstants.CENTER);
		label_35.setBounds(350, 0, 40, 25);
		selectedCoursesPanel.add(label_35);
		
		JLabel selectedClassCapacityLabel0 = new JLabel("");
		selectedClassCapacityLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassCapacityLabel0.setBounds(350, 25, 40, 25);
		selectedCoursesPanel.add(selectedClassCapacityLabel0);
		
		JLabel selectedClassCapacityLabel1 = new JLabel("");
		selectedClassCapacityLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassCapacityLabel1.setBounds(350, 50, 40, 25);
		selectedCoursesPanel.add(selectedClassCapacityLabel1);
		
		JLabel selectedClassCapacityLabel2 = new JLabel("");
		selectedClassCapacityLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassCapacityLabel2.setBounds(350, 75, 40, 25);
		selectedCoursesPanel.add(selectedClassCapacityLabel2);
		
		JLabel selectedClassCapacityLabel3 = new JLabel("");
		selectedClassCapacityLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassCapacityLabel3.setBounds(350, 100, 40, 25);
		selectedCoursesPanel.add(selectedClassCapacityLabel3);
		
		JLabel selectedClassCapacityLabel4 = new JLabel("");
		selectedClassCapacityLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassCapacityLabel4.setBounds(350, 125, 40, 25);
		selectedCoursesPanel.add(selectedClassCapacityLabel4);
		
		JLabel selectedClassCapacityLabel5 = new JLabel("");
		selectedClassCapacityLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassCapacityLabel5.setBounds(350, 150, 40, 25);
		selectedCoursesPanel.add(selectedClassCapacityLabel5);
		
		JLabel label_42 = new JLabel("已选课人数");
		label_42.setHorizontalAlignment(SwingConstants.CENTER);
		label_42.setBounds(395, 0, 90, 25);
		selectedCoursesPanel.add(label_42);
		
		JLabel selectedClassVacancyLabel0 = new JLabel("");
		selectedClassVacancyLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassVacancyLabel0.setBounds(395, 25, 60, 25);
		selectedCoursesPanel.add(selectedClassVacancyLabel0);
		
		JLabel selectedClassVacancyLabel1 = new JLabel("");
		selectedClassVacancyLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassVacancyLabel1.setBounds(395, 50, 60, 25);
		selectedCoursesPanel.add(selectedClassVacancyLabel1);
		
		JLabel selectedClassVacancyLabel2 = new JLabel("");
		selectedClassVacancyLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassVacancyLabel2.setBounds(395, 75, 60, 25);
		selectedCoursesPanel.add(selectedClassVacancyLabel2);
		
		JLabel selectedClassVacancyLabel3 = new JLabel("");
		selectedClassVacancyLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassVacancyLabel3.setBounds(395, 100, 60, 25);
		selectedCoursesPanel.add(selectedClassVacancyLabel3);
		
		JLabel selectedClassVacancyLabel4 = new JLabel("");
		selectedClassVacancyLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassVacancyLabel4.setBounds(395, 125, 60, 25);
		selectedCoursesPanel.add(selectedClassVacancyLabel4);
		
		JLabel selectedClassVacancyLabel5 = new JLabel("");
		selectedClassVacancyLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		selectedClassVacancyLabel5.setBounds(395, 150, 60, 25);
		selectedCoursesPanel.add(selectedClassVacancyLabel5);
		
		JLabel label_49 = new JLabel("课程特色");
		label_49.setHorizontalAlignment(SwingConstants.CENTER);
		label_49.setBounds(489, 0, 150, 25);
		selectedCoursesPanel.add(label_49);
		
		JLabel selectedCourseFeaturesLabel0 = new JLabel("");
		selectedCourseFeaturesLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseFeaturesLabel0.setBounds(489, 25, 150, 25);
		selectedCoursesPanel.add(selectedCourseFeaturesLabel0);
		
		JLabel selectedCourseFeaturesLabel1 = new JLabel("");
		selectedCourseFeaturesLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseFeaturesLabel1.setBounds(489, 50, 150, 25);
		selectedCoursesPanel.add(selectedCourseFeaturesLabel1);
		
		JLabel selectedCourseFeaturesLabel2 = new JLabel("");
		selectedCourseFeaturesLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseFeaturesLabel2.setBounds(489, 75, 150, 25);
		selectedCoursesPanel.add(selectedCourseFeaturesLabel2);
		
		JLabel selectedCourseFeaturesLabel3 = new JLabel("");
		selectedCourseFeaturesLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseFeaturesLabel3.setBounds(489, 100, 150, 25);
		selectedCoursesPanel.add(selectedCourseFeaturesLabel3);
		
		JLabel selectedCourseFeaturesLabel4 = new JLabel("");
		selectedCourseFeaturesLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseFeaturesLabel4.setBounds(489, 125, 150, 25);
		selectedCoursesPanel.add(selectedCourseFeaturesLabel4);
		
		JLabel selectedCourseFeaturesLabel5 = new JLabel("");
		selectedCourseFeaturesLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCourseFeaturesLabel5.setBounds(489, 150, 150, 25);
		selectedCoursesPanel.add(selectedCourseFeaturesLabel5);
		
		JScrollBar selectedCoursesPanelScrollBar = new JScrollBar();
		selectedCoursesPanelScrollBar.setBounds(925, 300, 17, 175);
		courseSelectionPanel.add(selectedCoursesPanelScrollBar);
		
		
		

		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		mouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		mouseListener.mainGame=mainGame;
		
		//设置各个按钮的鼠标监听器
		mouseListener clickSelectRCourseButton=new mouseListener(0);
		mouseListener clickSelectRECourseButton=new mouseListener(1);
		mouseListener clickSelectECourseButton=new mouseListener(2);
		mouseListener clickSelectFailedCourseButton=new mouseListener(3);
		mouseListener clickSubmitCourseButton=new mouseListener(4);
		mouseListener clickDeleteCourseButton=new mouseListener(5);
		mouseListener clickFinishButton=new mouseListener(6);
		mouseListener clickMsgOKButton=new mouseListener(7);
		mouseListener clickTimetableOKButton=new mouseListener(8);
		
		clickSelectRCourseButton.setButton(selectRCourseButton);
		selectRCourseButton.addMouseListener(clickSelectRCourseButton);
		clickSelectRECourseButton.setButton(selectRECourseButton);
		selectRECourseButton.addMouseListener(clickSelectRECourseButton);
		clickSelectECourseButton.setButton(selectECourseButton);
		selectECourseButton.addMouseListener(clickSelectECourseButton);
		clickSelectFailedCourseButton.setButton(selectFailedCourseButton);
		selectFailedCourseButton.addMouseListener(clickSelectFailedCourseButton);
		clickSubmitCourseButton.setButton(submitCourseButton);
		submitCourseButton.addMouseListener(clickSubmitCourseButton);
		clickDeleteCourseButton.setButton(deleteCourseButton);
		deleteCourseButton.addMouseListener(clickDeleteCourseButton);
		clickFinishButton.setButton(finishButton);
		finishButton.addMouseListener(clickFinishButton);
		clickMsgOKButton.setButton(msgOKButton);
		msgOKButton.addMouseListener(clickMsgOKButton);
		clickTimetableOKButton.setButton(timetableOKButton);
		timetableOKButton.addMouseListener(clickTimetableOKButton);
		
		
		JSBAdjustmentListener courseToSelectPanelJSBListener=new JSBAdjustmentListener(1, courseToSelectPanelScrollBar);
		courseToSelectPanelScrollBar.addAdjustmentListener(courseToSelectPanelJSBListener);
		
		JCBItemListener courseToSelectPanelJCB0Listener=new JCBItemListener(0, courseToSelectPanelScrollBar);
		JCBItemListener courseToSelectPanelJCB1Listener=new JCBItemListener(1, courseToSelectPanelScrollBar);
		JCBItemListener courseToSelectPanelJCB2Listener=new JCBItemListener(2, courseToSelectPanelScrollBar);
		JCBItemListener courseToSelectPanelJCB3Listener=new JCBItemListener(3, courseToSelectPanelScrollBar);
		JCBItemListener courseToSelectPanelJCB4Listener=new JCBItemListener(4, courseToSelectPanelScrollBar);
		JCBItemListener courseToSelectPanelJCB5Listener=new JCBItemListener(5, courseToSelectPanelScrollBar);
		courseChooseCheckBox0.addItemListener(courseToSelectPanelJCB0Listener);
		courseChooseCheckBox1.addItemListener(courseToSelectPanelJCB1Listener);
		courseChooseCheckBox2.addItemListener(courseToSelectPanelJCB2Listener);
		courseChooseCheckBox3.addItemListener(courseToSelectPanelJCB3Listener);
		courseChooseCheckBox4.addItemListener(courseToSelectPanelJCB4Listener);
		courseChooseCheckBox5.addItemListener(courseToSelectPanelJCB5Listener);

		JSBAdjustmentListener selectedCoursesPanelJSBListener=new JSBAdjustmentListener(2, selectedCoursesPanelScrollBar);
		selectedCoursesPanelScrollBar.addAdjustmentListener(selectedCoursesPanelJSBListener);
		
		selectedCoursesPanelJSBListener.courseSelectionPanel = courseSelectionPanel;
		courseToSelectPanelJSBListener.courseSelectionPanel = courseSelectionPanel;
		
		JCBItemListener selectedCoursesPanelJCB0Listener=new JCBItemListener(6, selectedCoursesPanelScrollBar);
		JCBItemListener selectedCoursesPanelJCB1Listener=new JCBItemListener(7, selectedCoursesPanelScrollBar);
		JCBItemListener selectedCoursesPanelJCB2Listener=new JCBItemListener(8, selectedCoursesPanelScrollBar);
		JCBItemListener selectedCoursesPanelJCB3Listener=new JCBItemListener(9, selectedCoursesPanelScrollBar);
		JCBItemListener selectedCoursesPanelJCB4Listener=new JCBItemListener(10, selectedCoursesPanelScrollBar);
		JCBItemListener selectedCoursesPanelJCB5Listener=new JCBItemListener(11, selectedCoursesPanelScrollBar);
		courseSelectedCheckBox0.addItemListener(selectedCoursesPanelJCB0Listener);
		courseSelectedCheckBox1.addItemListener(selectedCoursesPanelJCB1Listener);
		courseSelectedCheckBox2.addItemListener(selectedCoursesPanelJCB2Listener);
		courseSelectedCheckBox3.addItemListener(selectedCoursesPanelJCB3Listener);
		courseSelectedCheckBox4.addItemListener(selectedCoursesPanelJCB4Listener);
		courseSelectedCheckBox5.addItemListener(selectedCoursesPanelJCB5Listener);
		
		
		//下面代码段：将窗口中的控件传递给下方的CourseEntry类，之后就在CourseEntry中维护
		CourseEntry tempCourseEntry[]=new CourseEntry[COURSE_TO_SELECT_PANEL_CAPACITY];
		tempCourseEntry[0]=new CourseEntry(courseChooseCheckBox0,
				courseIDLabel0,
				courseTitleLabel0,
				classTimeLabel0,
				coursePropertyLabel0,
				totalHourLabel0,
				classCapacityLabel0,
				classVacancyLabel0,
				courseFeaturesLabel0
		);
		tempCourseEntry[1]=new CourseEntry(courseChooseCheckBox1,
				courseIDLabel1,
				courseTitleLabel1,
				classTimeLabel1,
				coursePropertyLabel1,
				totalHourLabel1,
				classCapacityLabel1,
				classVacancyLabel1,
				courseFeaturesLabel1
		);
		tempCourseEntry[2]=new CourseEntry(courseChooseCheckBox2,
				courseIDLabel2,
				courseTitleLabel2,
				classTimeLabel2,
				coursePropertyLabel2,
				totalHourLabel2,
				classCapacityLabel2,
				classVacancyLabel2,
				courseFeaturesLabel2
		);
		tempCourseEntry[3]=new CourseEntry(courseChooseCheckBox3,
				courseIDLabel3,
				courseTitleLabel3,
				classTimeLabel3,
				coursePropertyLabel3,
				totalHourLabel3,
				classCapacityLabel3,
				classVacancyLabel3,
				courseFeaturesLabel3
		);
		tempCourseEntry[4]=new CourseEntry(courseChooseCheckBox4,
				courseIDLabel4,
				courseTitleLabel4,
				classTimeLabel4,
				coursePropertyLabel4,
				totalHourLabel4,
				classCapacityLabel4,
				classVacancyLabel4,
				courseFeaturesLabel4
		);
		tempCourseEntry[5]=new CourseEntry(courseChooseCheckBox5,
				courseIDLabel5,
				courseTitleLabel5,
				classTimeLabel5,
				coursePropertyLabel5,
				totalHourLabel5,
				classCapacityLabel5,
				classVacancyLabel5,
				courseFeaturesLabel5
		);
		CourseEntry.setCourseToSelectEntry(tempCourseEntry);
		CourseEntry.set_COURSE_TO_SELECT_PANEL_CAPACITY(COURSE_TO_SELECT_PANEL_CAPACITY);
		CourseEntry temptempCourseEntry[]=new CourseEntry[SELECTED_COURSES_PANEL_CAPACITY];
		temptempCourseEntry[0]=new CourseEntry(courseSelectedCheckBox0,
				selectedCourseIDLabel0,
				selectedCourseTitleLabel0,
				selectedClassTimeLabel0,
				selectedCoursePropertyLabel0,
				selectedTotalHourLabel0,
				selectedClassCapacityLabel0,
				selectedClassVacancyLabel0,
				selectedCourseFeaturesLabel0
		);
		temptempCourseEntry[1]=new CourseEntry(courseSelectedCheckBox1,
				selectedCourseIDLabel1,
				selectedCourseTitleLabel1,
				selectedClassTimeLabel1,
				selectedCoursePropertyLabel1,
				selectedTotalHourLabel1,
				selectedClassCapacityLabel1,
				selectedClassVacancyLabel1,
				selectedCourseFeaturesLabel1
		);
		temptempCourseEntry[2]=new CourseEntry(courseSelectedCheckBox2,
				selectedCourseIDLabel2,
				selectedCourseTitleLabel2,
				selectedClassTimeLabel2,
				selectedCoursePropertyLabel2,
				selectedTotalHourLabel2,
				selectedClassCapacityLabel2,
				selectedClassVacancyLabel2,
				selectedCourseFeaturesLabel2
		);
		temptempCourseEntry[3]=new CourseEntry(courseSelectedCheckBox3,
				selectedCourseIDLabel3,
				selectedCourseTitleLabel3,
				selectedClassTimeLabel3,
				selectedCoursePropertyLabel3,
				selectedTotalHourLabel3,
				selectedClassCapacityLabel3,
				selectedClassVacancyLabel3,
				selectedCourseFeaturesLabel3
		);
		temptempCourseEntry[4]=new CourseEntry(courseSelectedCheckBox4,
				selectedCourseIDLabel4,
				selectedCourseTitleLabel4,
				selectedClassTimeLabel4,
				selectedCoursePropertyLabel4,
				selectedTotalHourLabel4,
				selectedClassCapacityLabel4,
				selectedClassVacancyLabel4,
				selectedCourseFeaturesLabel4
		);
		temptempCourseEntry[5]=new CourseEntry(courseSelectedCheckBox5,
				selectedCourseIDLabel5,
				selectedCourseTitleLabel5,
				selectedClassTimeLabel5,
				selectedCoursePropertyLabel5,
				selectedTotalHourLabel5,
				selectedClassCapacityLabel5,
				selectedClassVacancyLabel5,
				selectedCourseFeaturesLabel5
		);
		CourseEntry.setSelectedCoursesEntry(temptempCourseEntry);
		CourseEntry.set_SELECTED_COURSES_PANEL_CAPACITY(SELECTED_COURSES_PANEL_CAPACITY);
		
		JTextArea timeTable[][]=new JTextArea[5][2];
		timeTable[0][0]=timeTable11;
		timeTable[1][0]=timeTable21;
		timeTable[2][0]=timeTable31;
		timeTable[3][0]=timeTable41;
		timeTable[4][0]=timeTable51;
		timeTable[0][1]=timeTable12;
		timeTable[1][1]=timeTable22;
		timeTable[2][1]=timeTable32;
		timeTable[3][1]=timeTable42;
		timeTable[4][1]=timeTable52;
		
		int i,j;
		for(i=0;i<=4;i++)
			for(j=0;j<=1;j++)
				timeTable[i][j].setLineWrap(true);
		//设置自动换行
		
		JComponent toDisenable[]=new JComponent[21+2]; //在显示msgPanel和timeTablePanel的时候要把这些控件enable设为false
		toDisenable[0]=selectRCourseButton;
		toDisenable[1]=selectRECourseButton;
		toDisenable[2]=selectECourseButton;
		toDisenable[3]=selectFailedCourseButton;
		toDisenable[4]=submitCourseButton;
		toDisenable[5]=deleteCourseButton;
		toDisenable[6]=finishButton;
		toDisenable[7]=courseChooseCheckBox0;
		toDisenable[8]=courseChooseCheckBox1;
		toDisenable[9]=courseChooseCheckBox2;
		toDisenable[10]=courseChooseCheckBox3;
		toDisenable[11]=courseChooseCheckBox4;
		toDisenable[12]=courseChooseCheckBox5;
		toDisenable[13]=courseSelectedCheckBox0;
		toDisenable[14]=courseSelectedCheckBox1;
		toDisenable[15]=courseSelectedCheckBox2;
		toDisenable[16]=courseSelectedCheckBox3;
		toDisenable[17]=courseSelectedCheckBox4;
		toDisenable[18]=courseSelectedCheckBox5;
		toDisenable[19]=courseToSelectPanelScrollBar;
		toDisenable[20]=selectedCoursesPanelScrollBar;
		int toDisenableJComponentCount=21;
		
		//下放代码块中注意初始化必要的局部变量
		switch(dataPackage.stateA) {
		case "R":
		case "RE":
		case "E":
		case "F":
			courseToSelectCount=Courses.strToCoursesCount(dataPackage.stateB);
			courseToSelect=Courses.strToCourses(dataPackage.stateB);
			courseToSelectCheckBoxChecked=new boolean[courseToSelectCount+2]; //（+2以防万一）
			for(i=0;i<courseToSelectCount;i++) {
				courseToSelectCheckBoxChecked[i]=false;
			}			
			/*
			//滚动条可取值的范围，取值范围为 [0, max(courseToSelectCount-COURSE_TO_SELECT_PANEL_CAPACITY+1, 1) ) 
			int scrollBarMax; 
			scrollBarMax=courseToSelectCount-COURSE_TO_SELECT_PANEL_CAPACITY+1;
			if(scrollBarMax<1) scrollBarMax=1;
			*/
			//上面这部分代码对JScrollBar理解有误（scrollbar中包含了这个功能——extent属性值实现的即为这个功能
			courseToSelectPanelScrollBar.setMaximum(courseToSelectCount);
			courseToSelectPanelScrollBar.setVisibleAmount(COURSE_TO_SELECT_PANEL_CAPACITY);
			courseToSelectPanelScrollBar.setValue(0); //滚动条初始位置设为0
			CourseEntry.repaintCourseToSelectEntryAll(courseToSelect, courseToSelectCheckBoxChecked, courseToSelectCount, 0);
			
			selectedCoursesCount=Courses.strToCoursesCount(dataPackage.stateC);
			selectedCourses=Courses.strToCourses(dataPackage.stateC);
			selectedCoursesCheckBoxChecked=new boolean[selectedCoursesCount+2]; //+2以防万一
			for(i=0;i<selectedCoursesCount;i++) {
				selectedCoursesCheckBoxChecked[i]=false;
			}
			selectedCoursesPanelScrollBar.setMaximum(selectedCoursesCount);
			selectedCoursesPanelScrollBar.setVisibleAmount(SELECTED_COURSES_PANEL_CAPACITY);
			selectedCoursesPanelScrollBar.setValue(0);
			CourseEntry.repaintSelectedCoursesEntryAll(selectedCourses, selectedCoursesCheckBoxChecked, selectedCoursesCount, 0);
			
			msgPanel.setVisible(false);
			timetablePanel.setVisible(false);
			
			break;
			
		case "MsgSD":
		case "MsgFinish":
			for (i=0;i<toDisenableJComponentCount;i++) {
				toDisenable[i].setEnabled(false);
			} //把除了msgPanel上的OK按钮以外的按钮、复选框、滚动条都设置成不可用，避免触发bug
			msgTextAera.setLineWrap(true); //自动换行
			msgTextAera.setText(dataPackage.stateB);
			msgPanel.repaint();
			msgPanel.setVisible(true);
			timetablePanel.setVisible(false);
			submitCourseButton.setEnabled(false);
			break;
			
		case "TimeTable":
			for (i=0;i<toDisenableJComponentCount;i++) {
				toDisenable[i].setEnabled(false);
			} //把除了msgPanel上的OK按钮以外的按钮、复选框、滚动条都设置成不可用，避免触发bug
			Courses coursesInTimeTable[]=Courses.strToCourses(dataPackage.stateB);
			int coursesInTimeTableCount=Courses.strToCoursesCount(dataPackage.stateB);
			for(j=0;j<coursesInTimeTableCount;j++) {
				int thisClassTime=coursesInTimeTable[j].classTime1;
				timeTable[thisClassTime/10-1][thisClassTime%10-1].setText(coursesInTimeTable[j].courseTitle);
				thisClassTime=coursesInTimeTable[j].classTime2;
				if(thisClassTime != 0) 
					timeTable[thisClassTime/10-1][thisClassTime%10-1].setText(coursesInTimeTable[j].courseTitle);
			}
			msgPanel.setVisible(false);
			timetablePanel.setVisible(true);
			break;
			
		case "":
			//首次进入这个事件，还没有重绘窗口，窗口里面啥都没显示，此时刷新一次“选择必修课”
			dataPackage.choiceA="R";
			break;
		}
		
		/*		END OF YOUR CODE		*/
    	    	
    	/*****************************************************************				
		 * 【恢复显示】
		 * 必须存在的四行代码！！！！
		 ******************************************************************/
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.getContentPane().removeAll();
		frame.getContentPane().add(backgroundPanel);
		frame.getContentPane().repaint();
    	frame.setVisible(true);
    	//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
    	
    	//首次进入这个事件，还没有重绘窗口，窗口里面啥都没显示，此时刷新一次“选择必修课”
    	if(dataPackage.stateA.equals("")) {
    		EventManager.dataPackage=dataPackage;
			synchronized(mainGame) {
				mainGame.notify();
			}
    	}
	}
}