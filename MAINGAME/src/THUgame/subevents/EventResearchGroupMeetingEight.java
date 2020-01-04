package THUgame.subevents;
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


public class EventResearchGroupMeetingEight extends EventBase{

	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.count==12) {
			oldDataPack.eventFinished=true;
			oldDataPack.studyProgress=oldDataPack.studyProgress+5;
		}
		else{
		
			oldDataPack.count = oldDataPack.count + 1;
		}
		oldDataPack.time=18;
		
		return;
	}
}
