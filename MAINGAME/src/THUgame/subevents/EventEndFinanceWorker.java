package THUgame.subevents;
import THUgame.event.EventBase;
import THUgame.datapack.DataPack;

/*
 *结局：一切专业转金融
 * 基于科研分支对话改进
 * 基于多次多重选择后台模板改进
 * 
 * --DIALOG--
 * update:20191108
 * via：黄天翼
 * 更新：
 * 		
 * */


public class EventEndFinanceWorker extends EventBase{

	@Override
	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.count==5) {
			oldDataPack.eventFinished=true;
		}
		else{
			oldDataPack.count = oldDataPack.count + 1;
		}
		return;
	}
}
