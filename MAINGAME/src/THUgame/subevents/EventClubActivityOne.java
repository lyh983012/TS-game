package THUgame.subevents;

import THUgame.datapack.DataPack;
import THUgame.event.EventBase;

/*
 * 完成此事件后ClubCount=2，下一次进入ClubActivityTwo事件
 * 如果不完成此事件，下一次仍是本事件
 */
public class EventClubActivityOne extends EventBase{
	public void actOn(DataPack oldDataPack) {
		/*test*/
		if(oldDataPack.inClubPorcess>=50)
			oldDataPack.ClubCharacter=4;
		/*test*/
		if (oldDataPack.choiceA.equals("Back")) {
			oldDataPack.time+=1;                   //例会耗时1小时
			oldDataPack.eventFinished = true;      //返回
		}
		else if (oldDataPack.count ==5 && oldDataPack.choiceA.equals("External")) {
			oldDataPack.ClubCount=2;              //社团事件进度+1
			oldDataPack.ClubCharacter=1;          //外联部
			oldDataPack.characterEnergy-=10;      //体力值-10
			oldDataPack.inClubPorcess+=10;        //社团进度+10
			oldDataPack.characterEQ+=3;           //情商提高3
			oldDataPack.time+=2;                  //2小时
			oldDataPack.characterlucky+=5;
			oldDataPack.count=6;
		}
		else if (oldDataPack.count ==5 && oldDataPack.choiceA.equals("Inline")) {
			oldDataPack.ClubCount=2;               //社团事件进度+1
			oldDataPack.ClubCharacter=2;           //内联部
			oldDataPack.characterEnergy-=15;       //体力值-15
			oldDataPack.inClubPorcess+=15;         //团结内部感情，社团进度+15
			oldDataPack.characterEQ+=3;            //情商提高3
			oldDataPack.time+=3;                   //3小时
			oldDataPack.count=7;
		}
		else if (oldDataPack.count ==5 && oldDataPack.choiceA.equals("Advertise")) {
			oldDataPack.ClubCount=2;               //社团事件进度+1
			oldDataPack.ClubCharacter=3;           //宣传部
			oldDataPack.characterEnergy-=10;       //体力值-10
			oldDataPack.time+=2;                   //耗时2小时
			oldDataPack.characterEQ+=2;           //情商提高2
			oldDataPack.inClubPorcess+=10;         //社团进度+10
			oldDataPack.characterArt+=3;           //艺术属性提高3
			oldDataPack.count=8;
		}
		else if (oldDataPack.count ==6) oldDataPack.count = 9;
		else if (oldDataPack.count ==7) oldDataPack.count = 10;
		else if (oldDataPack.count ==8) oldDataPack.count = 11;
		else if (oldDataPack.count==9||oldDataPack.count==10||oldDataPack.count==11) oldDataPack.count=12;
		else {
			oldDataPack.count = oldDataPack.count + 1;
		}
		
	}
}
