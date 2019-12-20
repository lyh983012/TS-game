package THUgame.subevents;

import THUgame.datapack.DataPack;
import java.util.Random;
import THUgame.event.EventBase;

public class EventClubActivitySeven extends EventBase{
	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.choiceA.equals("Back")) {
			oldDataPack.time+=1;                   //返回耗时1小时
			oldDataPack.eventFinished = true;      //返回
		}
		else if (oldDataPack.count==8) {
			oldDataPack.ClubCount=8;                //社团进度变成8
			oldDataPack.time+=3;                    //时间+3
			oldDataPack.inClubPorcess+=10;          //社团进度+10
			oldDataPack.characterArt+=10;            //艺术属性+5
			oldDataPack.characterEnergy-=20; 
			oldDataPack.characterHappiness+=5;
			oldDataPack.count=9;
		}
		else if (oldDataPack.count==23 && oldDataPack.choiceB.equals("Collectivism")) {
			oldDataPack.characterEQ+=2;
			oldDataPack.inClubPorcess+=5;          //社团进度+10
			oldDataPack.characterlucky+=5;           //艺术属性+3
			oldDataPack.count=24;
		}
		else if (oldDataPack.count==23 && oldDataPack.choiceB.equals("Individualism")) {
			oldDataPack.characterEQ-=2;
			oldDataPack.characterlucky-=5;           //艺术属性+3
			oldDataPack.count=29;
		}
		else if(oldDataPack.count==28) {
			oldDataPack.count=36;
		}
		else oldDataPack.count=oldDataPack.count+1;
				
	}
}
