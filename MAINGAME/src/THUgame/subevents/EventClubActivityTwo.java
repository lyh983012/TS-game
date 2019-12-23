package THUgame.subevents;

import THUgame.datapack.DataPack;
import THUgame.event.EventBase;
/*
 * 完成此事件后ClubCount=3，下一次进入ClubActivityThree事件
 * 如果不完成此事件，下一次仍是本事件
 */

public class EventClubActivityTwo extends EventBase{
	
	@Override
	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.inClubPorcess>=50)
			oldDataPack.ClubCharacter=4;
		if (oldDataPack.choiceA.equals("Back")) {
			oldDataPack.time+=1;                   //例会耗时1小时
			oldDataPack.eventFinished = true;      //返回
		}
		else if (oldDataPack.count==7) {
			oldDataPack.time+=2;                   //培训时间2小时
			oldDataPack.inClubPorcess+=10;         //社团进度+10
			oldDataPack.characterEnergy-=15;       //体力值-15
			oldDataPack.characterArt+=5;           //艺术值提高5
			oldDataPack.ClubCount=3;              //社团进度3
			oldDataPack.count=8;
		}
		else if (oldDataPack.count==13 && oldDataPack.choiceA.equals("ExtraTrain")) {
			oldDataPack.characterEnergy-=10;       //体力值-10
			oldDataPack.characterHealth-=2;        //健康之-2
			oldDataPack.inClubPorcess+=10;         //社团进度+10
			oldDataPack.time+=2;                   //2小时
			oldDataPack.characterArt+=2;           //艺术值提高5
			oldDataPack.count=14;
			
		}
		else if (oldDataPack.count==13 && oldDataPack.choiceA.equals("ToHospital")) {
			oldDataPack.characterEnergy-=10;         //体力值-10
			oldDataPack.characterEQ+=10;            //情商提高3
			oldDataPack.time+=2;                    //2小时
			oldDataPack.characterlucky+=5;          //幸运值+5
			oldDataPack.characterHappiness+=10;     //幸福感+10
			oldDataPack.count=16;
		}
		else if (oldDataPack.count==15) {
			oldDataPack.count=19;
		}
		else oldDataPack.count = oldDataPack.count + 1;
		
	}
}
