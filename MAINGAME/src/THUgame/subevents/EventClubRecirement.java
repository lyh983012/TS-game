package THUgame.subevents;

import THUgame.datapack.DataPack;
import java.util.Random;
import THUgame.event.EventBase;

/*
 * 社团支线——社团招新
 * ---DIALOG---
 * create:20191123
 * via：卢宇芳
 * DataPack更新：加入int inClubPorcess和int ClubCharacter
 * 事件：社团招新，Week1, Day4
 * 
 * */

public class EventClubRecirement extends EventBase{
	public void actOn(DataPack oldDataPack) {

		if(oldDataPack.choiceA.equals("back")) {
			oldDataPack.time+=2;
			oldDataPack.eventFinished = true;
		} 
		else if (oldDataPack.count ==4 && oldDataPack.choiceA.equals("JoinClub")) {
			oldDataPack.joinClub=true;          //加入了社团
			oldDataPack.ClubCount=1;            //社团事件进度为1
			oldDataPack.inClubPorcess+=10;      //加入社团有了进度
			oldDataPack.characterHappiness+=2;
			oldDataPack.characterArt+=2;
			oldDataPack.count=5;			
		}
		else {
			oldDataPack.count = oldDataPack.count + 1;
		}
		
	}
}

