package THUgame.event;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import THUgame.datapack.DataPack;

/*
 * 
 * update:20191114
 * via：林逸晗
 * 更新：加入读存档
 * 
 * */

public class EventSaveAndLoad extends EventBase{
	public void actOn(DataPack oldDataPack) {
		
		if(!(oldDataPack.choiceA.equals(""))) {
			File file = new File(oldDataPack.choiceA);
		    ObjectInputStream ois = null;
		    try {
		        ois = new ObjectInputStream(new FileInputStream(file));
		        oldDataPack.copyElements((DataPack)ois.readObject());
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	        oldDataPack.stateA = "startgame";
	        oldDataPack.eventFinished = true;

		}else {
			oldDataPack.stateA = "backhome";
			oldDataPack.eventFinished = true;
		}
	}
}