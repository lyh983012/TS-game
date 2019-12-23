package THUgame.event;
import THUgame.event.EventBase;
import THUgame.datapack.DataPack;

/*
 * 科研分支对话，组会8
 * 基于多次多重选择后台模板改进
 * 
 * --DIALOG--
 * update:20191108
 * via：黄天翼
 * 更新：
 * 		
 * */


public class EventLeave extends EventBase{

	@Override
	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.count==2) {
			oldDataPack.eventFinished=true;
		}
		else{
			oldDataPack.count = oldDataPack.count + 1;
		}
		return;
	}
}
