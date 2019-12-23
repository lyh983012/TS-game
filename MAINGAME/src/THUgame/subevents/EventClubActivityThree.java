package THUgame.subevents;

import THUgame.datapack.DataPack;
import java.util.Random;
import THUgame.event.EventBase;

public class EventClubActivityThree extends EventBase{
	Random r = new Random();
	@Override
	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.inClubPorcess>=50)
			oldDataPack.ClubCharacter=4;
		if (oldDataPack.count==10 && oldDataPack.choiceA.equals("Back")) {
			oldDataPack.characterHappiness-=2;     //幸福感-2
			oldDataPack.characterEQ-=2;            //情商-2
			oldDataPack.time+=1;                   //返回耗时1小时
			oldDataPack.eventFinished = true;      //返回
		}
		else if (oldDataPack.count==22 && oldDataPack.choiceA.equals("Back")) {
			oldDataPack.time+=1;                   //返回耗时1小时
			oldDataPack.eventFinished = true;      //返回
		}
		else if (oldDataPack.count==6) {
			oldDataPack.time+=3;                   //培训时间3小时
			oldDataPack.inClubPorcess+=10;         //社团进度+10
			oldDataPack.characterEnergy-=15;       //体力值-15
			oldDataPack.characterArt+=5;           //艺术值提高5
			oldDataPack.ClubCount=4;               //社团进度变成4
			oldDataPack.count=7;
		}
		else if (oldDataPack.count==10 && oldDataPack.choiceA.equals("BoysDay")) {
			oldDataPack.count=11;
		}
		else if (oldDataPack.count==14) {
			oldDataPack.characterEnergy-=10;       //体力值-10
			oldDataPack.inClubPorcess+=5;         //社团进度+5
			oldDataPack.characterEQ+=2;            //情商+2
			oldDataPack.time+=1;                   //1小时
			oldDataPack.characterHappiness+=5;     //幸福感+5 
			oldDataPack.count=15;
		}
		else if (oldDataPack.count==15 && oldDataPack.choiceB.equals("IamAfraid")) {
			oldDataPack.characterHappiness-=5;
			oldDataPack.count=20;
		}
		else if (oldDataPack.count==17) {
			if (oldDataPack.sex.equals("girl")) {
				oldDataPack.FallinLove=true;            //女生告白直接成功
				oldDataPack.characterHappiness+=20;
				oldDataPack.count=18;
			}
			else {
				int success = r.nextInt(3) + 1;         //男生告白有66.66%的概率成功
				if (success>=2) {
					oldDataPack.FallinLove=true;
					oldDataPack.characterHappiness+=20;
					oldDataPack.count=18;
				} else {
					oldDataPack.characterHappiness-=10;
					oldDataPack.characterlucky+=2;
					oldDataPack.count=19;
				}
			}
		}
		else if (oldDataPack.count==18||oldDataPack.count==19) {
			oldDataPack.count=22;
		}
		else oldDataPack.count=oldDataPack.count+1;
				
	}
}
