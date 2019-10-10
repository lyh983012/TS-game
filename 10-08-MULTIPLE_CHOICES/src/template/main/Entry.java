package template.main;

import template.datapack.DataPack;

/*
 * template version 1.1
 * 游戏的入口
 * 运行这个java就可以开始游戏拉～
 * 不需要改！
 * 维护底层进程 以及 图形界面进程
 * update:20190928 19:30
 * */
public class Entry {

	    public DataPack dataPackage;
		private static EventManager mainGame;
		private static WindowManager mainGUI;
	 
	   	public static void main(String args[]) {
		    //DataPack dataPackage=new DataPack("demo");
	   		DataPack dataPackage=new DataPack("choice demo");
		    EventManager.dataPackage=dataPackage;
		    WindowManager.dataPackage=dataPackage;
			/*********************************		
			 * 
			 * dataPackage对所有类都是静态的
			 * 意思是 每个类都维护同一个dataPack，方便全局同步
			 * 
			 *********************************/
		    
		    mainGame = new EventManager();
		    mainGUI = new WindowManager();
		    mainGame.set(mainGUI);
			mainGUI.set(mainGame);
			
		    mainGame.start();
			mainGame.run();
	   }   
	}



