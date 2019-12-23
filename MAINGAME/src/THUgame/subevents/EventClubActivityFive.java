package THUgame.subevents;

import THUgame.datapack.DataPack;
import THUgame.event.EventBase;

public class EventClubActivityFive extends EventBase{
	@Override
	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.choiceA.equals("Back")) {
			oldDataPack.time+=1;                   //返回耗时1小时
			oldDataPack.eventFinished = true;      //返回
		}
		else if (oldDataPack.count==13 && oldDataPack.choiceB.equals("GoSing")) {
			oldDataPack.ClubCount=6;                //社团进度变成6
			oldDataPack.characterEQ+=2;
			oldDataPack.inClubPorcess+=10;          //社团进度+10
			oldDataPack.characterArt+=3;           //艺术属性+3
			oldDataPack.count=14;
		}
		else if (oldDataPack.count==13 && oldDataPack.choiceB.equals("NoSing")) {
			oldDataPack.ClubCount=6;                //社团进度变成6
			oldDataPack.characterEQ-=2;
			oldDataPack.inClubPorcess+=5;          //社团进度+5
			oldDataPack.characterEnergy-=10;       //消耗能量10
			oldDataPack.count=31;
		}
		else if (oldDataPack.count==16 && oldDataPack.choiceB.equals("GoInvite")) {
			oldDataPack.characterHappiness+=5;     //幸福感+5 
			oldDataPack.inClubPorcess+=5;          //社团进度+5
			oldDataPack.characterEQ+=2;
			oldDataPack.characterlucky+=5;         //幸运值+5 
			oldDataPack.characterEnergy-=20;       //消耗能量20
			oldDataPack.count=17;	
		}
		else if (oldDataPack.count==16 && oldDataPack.choiceB.equals("NoInvite")) {
			oldDataPack.characterEQ-=2;
			oldDataPack.characterEnergy-=15;       //消耗能量15
			oldDataPack.count=26;	
		}
		else if(oldDataPack.count==21 && oldDataPack.v) {
			oldDataPack.count=24;
		}
		else if(oldDataPack.count==21 && !oldDataPack.FallinLove) {
			oldDataPack.count=22;
		}
		else if(oldDataPack.count==25) {
			oldDataPack.count=29;
		}
		else if(oldDataPack.count==30) {
			oldDataPack.count=36;
		}
		else if(oldDataPack.count==40) {
			oldDataPack.time+=3;
			oldDataPack.count=41;
		}
		else oldDataPack.count=oldDataPack.count+1;
				
	}
}
