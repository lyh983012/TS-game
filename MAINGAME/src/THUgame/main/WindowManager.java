package THUgame.main;

import javax.swing.*;

import THUgame.datapack.DataPack;
import THUgame.event.EventCeremony;
import THUgame.subwindows.WinClubRecirument;
import THUgame.subwindows.WinEnd2050_1;
import THUgame.subwindows.WinEndAnimate;
import THUgame.subwindows.WinEndCivilServant;
import THUgame.subwindows.WinEndDropOut;
import THUgame.subwindows.WinEndFinanceWorker;
import THUgame.subwindows.WinEndProgrammer;
import THUgame.subwindows.WinEndWhiteCollar;
import THUgame.subwindows.WinMarket;
import THUgame.subwindows.WinOrgEnroll;
import THUgame.subwindows.WinOrganization;
import THUgame.subwindows.WinResearch;
import THUgame.subwindows.WinResearchBegin;
import THUgame.subwindows.WinSTA;
import THUgame.subwindows.WinSUPE0;
import THUgame.subwindows.WinSUPE1;
import THUgame.subwindows.WinSUPE21;
import THUgame.subwindows.WinSUPE22;
import THUgame.subwindows.WinSUPE3;
import THUgame.subwindows.WinSUPE4;
import THUgame.subwindows.WinSUPE5;
import THUgame.subwindows.WinSUPE6;
import THUgame.subwindows.WinSUPE7;
import THUgame.windows.WinBase;
import THUgame.windows.WinCeremony;
import THUgame.windows.WinCeremonyLec;
import THUgame.windows.WinChoice;
import THUgame.windows.WinCourseRegistration;
import THUgame.windows.WinCourseWithdraw;
import THUgame.windows.WinHome;
import THUgame.windows.WinInDom;
import THUgame.windows.WinInputName;
import THUgame.windows.WinLeave;
import THUgame.windows.WinMap;
import THUgame.windows.WinMorningClass;
import THUgame.windows.WinNoonClass;
import THUgame.windows.WinSaveAndLoad;
import THUgame.windows.WinBackground;
import THUgame.windows.WinWelcome;
import THUgame.subwindows.WinResearchGroupmeetingOne;
import THUgame.subwindows.WinResearchGroupmeetingTwo;
import THUgame.subwindows.WinResearchGroupmeetingThree;
import THUgame.subwindows.WinResearchGroupmeetingFour;
import THUgame.subwindows.WinResearchGroupmeetingFive;
import THUgame.subwindows.WinResearchGroupmeetingSix;
import THUgame.subwindows.WinResearchGroupmeetingSeven;
import THUgame.subwindows.WinResearchGroupmeetingEight;
import THUgame.subwindows.WinResearchMeetingNotice;
import THUgame.subwindows.WinCBuilding;
import THUgame.subwindows.WinClubActivityFive;
import THUgame.subwindows.WinClubActivityFour;
import THUgame.subwindows.WinClubActivityOne;
import THUgame.subwindows.WinClubActivitySeven;
import THUgame.subwindows.WinClubActivitySix;
import THUgame.subwindows.WinClubActivityThree;
import THUgame.subwindows.WinClubActivityTwo;
/*
 * template version 1.1
 * 窗口管理线程
 * update:20190928 19:30
 * */

public class WindowManager extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private EventManager mainGame;
	private WinBase generator;
	private JFrame mainFrame;
	static public DataPack dataPackage;
    public void set(EventManager mainGame){
    	this.mainGame=mainGame;
    	
    }
	public WindowManager(){
		
		this.mainFrame = new JFrame();
		mainFrame.setBounds(0, 0, 1080, 720);
		mainFrame.setResizable(false);

		/******************************************************************				
		 * 为分支事件窗口注册数据包
		 * 所有需要用到数据包的事件窗口都需要注册
		 * 数据包在所有类中都是静态（共享）的
		 * 这样设计是为了使用数据包进行初始化
		 ******************************************************************/
		/*		START OF YOUR CODE		*/
		WinInDom.dataPackage=dataPackage;
		WinMap.dataPackage=dataPackage;
		WinMorningClass.dataPackage=dataPackage;
		WinNoonClass.dataPackage=dataPackage;
		WinChoice.dataPackage=dataPackage;
		WinHome.dataPackage = dataPackage;
		WinBackground.dataPackage=dataPackage;
		WinWelcome.dataPackage=dataPackage;
		WinSTA.dataPackage=dataPackage;
		WinInputName.dataPackage=dataPackage;
		WinSaveAndLoad.dataPackage=dataPackage;
		WinResearchBegin.dataPackage=dataPackage;
		WinResearchGroupmeetingOne.dataPackage=dataPackage;
		WinResearchGroupmeetingTwo.dataPackage=dataPackage;
		WinResearchGroupmeetingThree.dataPackage=dataPackage;
		WinResearchGroupmeetingFour.dataPackage=dataPackage;
		WinResearchGroupmeetingFive.dataPackage=dataPackage;
		WinResearchGroupmeetingSix.dataPackage=dataPackage;
		WinResearchGroupmeetingSeven.dataPackage=dataPackage;
		WinResearchGroupmeetingEight.dataPackage=dataPackage;
		WinCourseRegistration.dataPackage=dataPackage;
		WinEnd2050_1.dataPackage=dataPackage;
		WinEndAnimate.dataPackage=dataPackage;
		WinResearch.dataPackage=dataPackage;
		WinCourseWithdraw.dataPackage=dataPackage;
		WinCeremony.dataPackage=dataPackage;
		WinCeremonyLec.dataPackage=dataPackage;
		WinLeave.dataPackage=dataPackage;
		WinEndCivilServant.dataPackage=dataPackage;
		WinEndDropOut.dataPackage=dataPackage;
		WinEndFinanceWorker.dataPackage=dataPackage;
		WinEndProgrammer.dataPackage=dataPackage;
		WinEndWhiteCollar.dataPackage=dataPackage;
		WinOrganization.dataPackage=dataPackage;
		WinOrgEnroll.dataPackage=dataPackage;
		WinSUPE1.dataPackage = dataPackage;
		WinSUPE21.dataPackage = dataPackage;
		WinSUPE22.dataPackage = dataPackage;
		WinCBuilding.dataPackage = dataPackage;
		WinMarket.dataPackage = dataPackage;
		WinSUPE3.dataPackage = dataPackage;
		WinSUPE4.dataPackage = dataPackage;
		WinSUPE5.dataPackage = dataPackage;
		WinSUPE6.dataPackage = dataPackage;
		WinSUPE7.dataPackage = dataPackage;
		WinClubRecirument.dataPackage=dataPackage;
		WinClubActivityOne.dataPackage=dataPackage;
		WinClubActivityTwo.dataPackage=dataPackage;
		WinClubActivityThree.dataPackage=dataPackage;
		WinClubActivityFour.dataPackage=dataPackage;
		WinClubActivityFive.dataPackage=dataPackage;
		WinClubActivitySix.dataPackage=dataPackage;
		WinClubActivitySeven.dataPackage=dataPackage;
		/*		END OF YOUR CODE		*/
	}
	
	public void repaint(){
		/******************************************************************		
		 * 使用ID选择触发的分支事件
		 * 只需要将「游戏对象」和「窗口对象」传进构造函数即可
		 * 前提：对应的分支对象类按照模板编写
		 ******************************************************************/
		/*		START OF YOUR CODE		*/
		switch(dataPackage.ID) {
			case -1:
				generator = new WinHome(mainGame,mainFrame);
				break;
			case 0:
				generator = new WinInDom(mainGame,mainFrame);
				break;
			case 1:
				generator = new WinMorningClass(mainGame,mainFrame);
				break;	
			case 2:
				generator = new WinNoonClass(mainGame,mainFrame);
				break;	
			case 3:
				generator = new WinMap(mainGame,mainFrame);
				break;
			case 4:
				generator = new WinCourseRegistration(mainGame,mainFrame);
				break;
			case 5:
				generator = new WinCourseWithdraw(mainGame,mainFrame);
				break;
			case 6:
				generator = new WinCeremony(mainGame,mainFrame);
				break;
			case 7:
				generator = new WinCeremonyLec(mainGame,mainFrame);
				break;
			case 8:
				generator = new WinLeave(mainGame,mainFrame);
				break;
			case 20001:
				generator = new WinOrganization(mainGame, mainFrame);
				break;
			case 200010:
				generator = new WinSUPE0(mainGame, mainFrame);
				break;
			case 200011:
				generator = new WinOrgEnroll(mainGame, mainFrame);
				break;
			case 200012:
				generator = new WinSUPE1(mainGame, mainFrame);
				break;
			case 200013:	
				if (dataPackage.SUPEmentor == 1) {
					generator = new WinSUPE21(mainGame, mainFrame);
				}
				else if (dataPackage.SUPEmentor == 2) {
					generator = new WinSUPE22(mainGame, mainFrame);
				}
				break;
			case 2000131:
				generator = new WinCBuilding(mainGame,mainFrame);
				break;
			case 2000132:
				generator = new WinMarket(mainGame,mainFrame);
				break;
			case 200014:
				generator = new WinSUPE3(mainGame,mainFrame);
				break;
			case 200015:
				generator = new WinSUPE4(mainGame,mainFrame);
				break;
			case 200016:
				generator = new WinSUPE5(mainGame,mainFrame);
				break;
			case 200017:
				generator = new WinSUPE6(mainGame,mainFrame);
				break;
			case 200018:
				generator = new WinSUPE7(mainGame,mainFrame);
				break;
			case 20016:
				generator = new WinSTA(mainGame,mainFrame);
				break;
			case 21000:
				generator = new WinResearchBegin(mainGame,mainFrame);
				break;
			case 21001:
				generator = new WinResearchMeetingNotice(mainGame,mainFrame);
				break;
			case 21002:
				generator = new WinResearchGroupmeetingOne(mainGame,mainFrame);
				break;
			case 21003:
				generator = new WinResearchGroupmeetingTwo(mainGame,mainFrame);
				break;
			case 21004:
				generator = new WinResearchGroupmeetingThree(mainGame,mainFrame);
				break;
			case 21005:
				generator = new WinResearchGroupmeetingFour(mainGame,mainFrame);
				break;
			case 21006:
				generator = new WinResearchGroupmeetingFive(mainGame,mainFrame);
				break;
			case 21007:
				generator = new WinResearchGroupmeetingSix(mainGame,mainFrame);
				break;
			case 21008:
				generator = new WinResearchGroupmeetingSeven(mainGame,mainFrame);
				break;
			case 21009:
				generator = new WinResearchGroupmeetingEight(mainGame,mainFrame);
				break;
			case 21010:
				generator = new WinResearch(mainGame,mainFrame);
				break;
			case 30000:
				generator = new WinChoice(mainGame,mainFrame);
				break;
			case 30001:
				generator = new WinBackground(mainGame,mainFrame);
				break;
			case 30002:
				generator = new WinWelcome(mainGame,mainFrame);
				break;
			case 30003:
				generator = new WinInputName(mainGame,mainFrame);
				break;
			case 30004:
				generator = new WinSaveAndLoad(mainGame,mainFrame);
				break;
			case 40000:
				generator= new WinEndAnimate(mainGame,mainFrame);
				break;
			case 40001:
				generator= new WinEnd2050_1(mainGame,mainFrame);
				break;
			case 40002:
				generator= new WinEndCivilServant(mainGame,mainFrame);
				break;
			case 40006:
				generator= new WinEndDropOut(mainGame,mainFrame);
				break;
			case 40004:
				generator= new WinEndFinanceWorker(mainGame,mainFrame);
				break;
			case 40005:
				generator= new WinEndProgrammer(mainGame,mainFrame);
				break;
			case 40003:
				generator= new WinEndWhiteCollar(mainGame,mainFrame);
				break;
			case 50000:
				generator = new WinClubRecirument(mainGame,mainFrame);
				break;
			case 50001:
				generator = new WinClubActivityOne(mainGame,mainFrame);
				break;
			case 50002:
				generator = new WinClubActivityTwo(mainGame,mainFrame);
				break;
			case 50003:
				generator = new WinClubActivityThree(mainGame,mainFrame);
				break;
			case 50004:
				generator = new WinClubActivityFour(mainGame,mainFrame);
				break;
			case 50005:
				generator = new WinClubActivityFive(mainGame,mainFrame);
				break;
			case 50006:
				generator = new WinClubActivitySix(mainGame,mainFrame);
				break;
			case 50007:
				generator = new WinClubActivitySeven(mainGame,mainFrame);
				
		}
		/*		END OF YOUR CODE		*/
	}
}
