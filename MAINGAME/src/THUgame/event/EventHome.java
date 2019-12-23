package THUgame.event;
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

	@Override
	public void actOn(DataPack oldDataPack) {

		if (oldDataPack.choiceA.equals("A")){
			oldDataPack.stateA = "新游戏";
			oldDataPack.eventFinished=true;
			return;
		}
		if (oldDataPack.choiceA.equals("B")){
			oldDataPack.stateA = "继续";
			oldDataPack.eventFinished=true;
			return;
		}
		
	}
}
