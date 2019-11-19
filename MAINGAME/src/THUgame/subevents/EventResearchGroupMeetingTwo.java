package THUgame.subevents;
import THUgame.event.EventBase;
import THUgame.datapack.DataPack;

/*
 * 科研分支对话，组会2
 * 基于多次多重选择后台模板改进
 * 
 * --DIALOG--
 * update:20191108
 * via：黄天翼
 * 更新：
 * 		
 * */


public class EventResearchGroupMeetingTwo extends EventBase{

	public void actOn(DataPack oldDataPack) {
		
		oldDataPack.time=18;
		if(oldDataPack.count==13) {
			oldDataPack.eventFinished=true;
		}
		else if(oldDataPack.count==2){
			if(true/*ResearchData.numberofPaperRead>0科研进度！=0*/) {
				oldDataPack.count=5;
			}
			else{
				oldDataPack.count=3;
			}
		}
		else if(oldDataPack.count==4) {
			/*科研线结束*/
			oldDataPack.joinResearch=false;
			oldDataPack.eventFinished=true;
		}
		else if(oldDataPack.count==7) {
			if(true/*ResearchData.i_map>0找到方向*/) {
				oldDataPack.count=8;
			}
			else {
				oldDataPack.count=9;
			}
		}
		else{
			oldDataPack.count = oldDataPack.count + 1;
		}
		return;
	}
}
