package THUgame.subevents;
import THUgame.event.EventBase;
import THUgame.datapack.DataPack;

/*
 * 科研分支对话，组会6
 * 基于多次多重选择后台模板改进
 * 
 * --DIALOG--
 * update:20191108
 * via：黄天翼
 * 更新：
 * 		
 * */


public class EventResearchMeetingNotice extends EventBase{

	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.count==0) {
			oldDataPack.eventFinished=true;
		}	
		return;
	}
}
