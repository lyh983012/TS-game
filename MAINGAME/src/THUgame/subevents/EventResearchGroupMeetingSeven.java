package THUgame.subevents;
import THUgame.event.EventBase;
import THUgame.datapack.DataPack;

/*
 * 科研分支对话，组会7
 * 基于多次多重选择后台模板改进
 * 
 * --DIALOG--
 * update:20191108
 * via：黄天翼
 * 更新：
 * 		
 * */


public class EventResearchGroupMeetingSeven extends EventBase{

	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.count==11) {
			oldDataPack.eventFinished=true;
			oldDataPack.characterIQ=oldDataPack.characterIQ+1;
			oldDataPack.studyProgress=oldDataPack.studyProgress+3;
		}
		else{
		
			oldDataPack.count = oldDataPack.count + 1;
		}
		oldDataPack.time=18;
		
		return;
	}
}
