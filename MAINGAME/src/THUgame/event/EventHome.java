package THUgame.event;
import java.util.Random;

import javax.swing.JOptionPane;

import THUgame.datapack.DataPack;

/*
 * 起始的Home页面
 *  
 * template version 1.0
 * update:20191011 16:28
 * 
 * 		
 * */


public class EventHome extends EventBase{

	public void actOn(DataPack oldDataPack) {

		if (oldDataPack.choiceA.equals("A")){
			oldDataPack.stateA = "新游戏";
			oldDataPack.eventFinished=true;
			return;
		}
		
	}
}
