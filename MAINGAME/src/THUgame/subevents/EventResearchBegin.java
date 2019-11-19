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


public class EventResearchBegin extends EventBase{

	public void actOn(DataPack oldDataPack) {
		

		if(oldDataPack.count==10) {
			oldDataPack.eventFinished=true;
		}
		else if(oldDataPack.count==2){
			if(oldDataPack.choiceA=="Yes") {
				oldDataPack.count=3;
			}
			else if(oldDataPack.choiceA=="No"){
				oldDataPack.count=4;
			}
		}
		else if(oldDataPack.count==3) {
			oldDataPack.count=5;
		}
		else if(oldDataPack.count==4) {
			oldDataPack.eventFinished=true;
		}
		else if(oldDataPack.count==5) {
			if(oldDataPack.characterIQ<50) {//[need to be discussed]
				oldDataPack.count=6;
			}
			else {
				oldDataPack.count=7;
			}
		}
		else if(oldDataPack.count==6) {
			oldDataPack.eventFinished=true;
		}
		else if(oldDataPack.count==8) {
			if(oldDataPack.choiceA=="Yes") {
				oldDataPack.count=9;
			}
			else if(oldDataPack.choiceA=="No"){
				oldDataPack.count=10;
			}
		}
		else if(oldDataPack.count==9) {
			oldDataPack.joinResearch=true;
			oldDataPack.eventFinished=true;
		}
		else{
			oldDataPack.count = oldDataPack.count + 1;
		}
		return;
	}
}
