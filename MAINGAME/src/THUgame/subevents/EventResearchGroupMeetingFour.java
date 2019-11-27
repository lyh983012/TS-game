package THUgame.subevents;
import THUgame.event.EventBase;
import THUgame.datapack.DataPack;

/*
 * 科研分支对话，组会4
 * 基于多次多重选择后台模板改进
 * 
 * --DIALOG--
 * update:20191108
 * via：黄天翼
 * 更新：
 * 		
 * */


public class EventResearchGroupMeetingFour extends EventBase{

	public void actOn(DataPack oldDataPack) {
		
		oldDataPack.time=18;
		if(oldDataPack.count==6) {
			oldDataPack.characterIQ=oldDataPack.characterIQ+1;
			oldDataPack.studyProgress=oldDataPack.studyProgress+3;
			oldDataPack.eventFinished=true;
		}
		else{
			oldDataPack.count = oldDataPack.count + 1;
		}
		return;
	}
}
