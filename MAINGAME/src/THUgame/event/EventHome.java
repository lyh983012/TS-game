package THUgame.event;
import java.util.Random;

import javax.swing.JOptionPane;

import THUgame.datapack.DataPack;


/*
 * 起始页面
 * 
 * --DIALOG--
 * update:20191011 16:28
 * via：黄天翼
 * 更新：-
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
