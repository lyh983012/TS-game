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


public class EventResearchGroupMeetingFive extends EventBase{

	public void actOn(DataPack oldDataPack) {
		
		oldDataPack.time=18;
		if(oldDataPack.count==12) {
			oldDataPack.eventFinished=true;
		}
		else if(oldDataPack.count==0){
			if(oldDataPack.paperFinished>0) {
				oldDataPack.count=2;
			}
			else if(oldDataPack.researchDataPackage.hasJeep||oldDataPack.researchDataPackage.hasGun||oldDataPack.researchDataPackage.hasSled||oldDataPack.researchDataPackage.hasInsulationSuit){
				oldDataPack.count=1;
			}
			else {
				oldDataPack.count=3;
			}
		}
		else if(oldDataPack.count==1||oldDataPack.count==2) {
			oldDataPack.count=5;
		}
		else if(oldDataPack.count==4) {
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
