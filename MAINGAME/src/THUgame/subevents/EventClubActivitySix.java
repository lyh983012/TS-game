package THUgame.subevents;

import THUgame.datapack.DataPack;
import java.util.Random;
import THUgame.event.EventBase;

public class EventClubActivitySix extends EventBase{
	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.choiceA.equals("Back")) {
			oldDataPack.time+=1;                   //返回耗时1小时
			oldDataPack.eventFinished = true;      //返回
		}
		else if (oldDataPack.count==13) {
			oldDataPack.ClubCount=7;                //社团进度变成7
			oldDataPack.time+=3;                    //时间+3
			oldDataPack.inClubPorcess+=10;          //社团进度+10
			oldDataPack.characterArt+=5;            //艺术属性+5
			oldDataPack.characterEnergy-=20; 
			oldDataPack.count=14;
		}

		else if(oldDataPack.count==25) {
			oldDataPack.characterEQ+=2;
			oldDataPack.characterHappiness+=5;
			oldDataPack.characterlucky+=3;
			oldDataPack.count=26;
		}
		else oldDataPack.count=oldDataPack.count+1;
				
	}
}
