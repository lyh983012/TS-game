package THUgame.subevents;
import java.util.Random;

import THUgame.datapack.DataPack;
import THUgame.event.EventBase;

public class EventClubActivityFour extends EventBase{
	Random r = new Random();
	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.choiceA.equals("Back")) {
			oldDataPack.time+=1;                   //返回耗时1小时
			oldDataPack.eventFinished = true;      //返回
		}
		else if (oldDataPack.count==10 && oldDataPack.choiceB.equals("GoElection")) {
			oldDataPack.ClubCount=5;               //社团进度变成5
			oldDataPack.count=11;
		}
		else if (oldDataPack.count==10 && oldDataPack.choiceB.equals("NoElection")) { //选择不竞选
			oldDataPack.characterEQ-=2;
			oldDataPack.inClubPorcess+=5;          //社团进度+5
			oldDataPack.ClubCount=5;               //社团进度变成5
			oldDataPack.count=26;
		}
		else if (oldDataPack.count==13) {          //发表完竞选感言
			int success = r.nextInt(10) + 1; 
			oldDataPack.time+=2;                   //竞选耗时2小时
			oldDataPack.characterEnergy-=10;       //消耗能量10
			if (oldDataPack.inClubPorcess>=50||success<=8) { //成为合唱队队长
				oldDataPack.ClubCharacter=4;           //队长
				oldDataPack.inClubPorcess+=15;         //社团进度+15
				oldDataPack.characterEQ+=3;
				oldDataPack.characterHappiness+=5;     //幸福感+5 
				oldDataPack.characterArt+=3;           //艺术属性+5 
				oldDataPack.count=14;
			}
			else if (success>8) {                      //竞选失败
				oldDataPack.inClubPorcess+=10;         //社团进度+10
				oldDataPack.characterEQ+=2;
				oldDataPack.characterHappiness-=5;     //幸福感-5 
				oldDataPack.count=17;
			}		
		}
		else if (oldDataPack.count==16) {
			oldDataPack.count=21;
		}
		else if (oldDataPack.count==20) {
			oldDataPack.count=22;
		}
		else if (oldDataPack.count==25) {
			oldDataPack.characterlucky+=5;     //幸运值+5 
			oldDataPack.count=28;
		}
		else oldDataPack.count=oldDataPack.count+1;
				
	}
}
