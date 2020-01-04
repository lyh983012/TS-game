package THUgame.subevents;
import THUgame.event.EventBase;
import THUgame.datapack.DataPack;

/*
 * 科研分支对话，组会3
 * 基于多次多重选择后台模板改进
 * 
 * --DIALOG--
 * update:20191108
 * via：黄天翼
 * 更新：
 * 		
 * */


public class EventResearchGroupMeetingThree extends EventBase{

	public void actOn(DataPack oldDataPack) {
		
		oldDataPack.time=18;
		if(oldDataPack.count==10) {
			oldDataPack.eventFinished=true;
		}
		else if(oldDataPack.count==0){
			if(oldDataPack.researchDataPackage.i_map==0) {
				oldDataPack.count=1;
			}
			else{
				oldDataPack.count=3;
			}
		}
		else if(oldDataPack.count==2) {
			/*科研线结束*/
			oldDataPack.joinResearch=false;
			oldDataPack.eventFinished=true;
		}
		else{
			oldDataPack.count = oldDataPack.count + 1;
		}
		return;
	}
}
