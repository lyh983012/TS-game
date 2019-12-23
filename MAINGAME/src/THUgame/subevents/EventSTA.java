package THUgame.subevents;

import THUgame.datapack.DataPack;
import THUgame.event.EventBase;

public class EventSTA extends EventBase{
	@Override
	public void actOn(DataPack oldDataPack) {
		/*test*/
		if(oldDataPack.choiceA.equals("challenge")){   //zwj
			oldDataPack.stateA="21001.png";
			oldDataPack.stateB="";
			oldDataPack.time+=4;					
			oldDataPack.characterEnergy -= 40;
			oldDataPack.characterHealth-= 3;
			System.out.println("以前的挑战杯进度");
			System.out.println(oldDataPack.progressChallengeCup);
			oldDataPack.progressChallengeCup += 10;
			System.out.println("现在的挑战杯进度");
			System.out.println(oldDataPack.progressChallengeCup);
			oldDataPack.STcap += 7;
			oldDataPack.notification += "<html>抓紧时间把挑战杯项目做一下<br>过去了4小时，体力下降40点，挑战杯进度+10, 科创能力+7</html>";
		}
		if(oldDataPack.contibuteSTA>=20)
			oldDataPack.beAChairMan=true;
			oldDataPack.notification += "<html>因为我对科协的贡献足够多，我成了实验室的管理员<br>";
		/*test*/
		if(oldDataPack.beAChairMan) {
			if(oldDataPack.choiceA.equals("server")){
				oldDataPack.stateA="3.gif";
				oldDataPack.stateB="gif";
				oldDataPack.time+=4;		
				oldDataPack.characterEnergy -= 30;
				oldDataPack.characterHealth -= 3;
				oldDataPack.contibuteSTA += 1;
				oldDataPack.STcap += 5;
				oldDataPack.notification += "<html>我来康康服务器发生了啥<br>过去了4小时，体力下降40点，对科协的贡献+1, 科创能力+5</html>";
			}
			if(oldDataPack.choiceA.equals("doc")){
				oldDataPack.stateA="2.gif";
				oldDataPack.stateB="gif";
				oldDataPack.time+=2;		
				oldDataPack.characterEnergy -= 15;
				oldDataPack.characterHealth -= 3;
				oldDataPack.contibuteSTA += 1;
				oldDataPack.notification += "<html>我需要写写材料啦<br>过去了2小时，体力下降20点，对科协的贡献+1</html>";
			}
			if(oldDataPack.choiceA.equals("back")){
				oldDataPack.time+=1;
				oldDataPack.eventFinished=true;
			}
		}else {
			if(oldDataPack.choiceA.equals("3d")){
				oldDataPack.stateA="1.gif";
				oldDataPack.stateB="gif";
				oldDataPack.time+=4;		
				oldDataPack.characterEnergy -= 30;
				oldDataPack.characterHealth -= 3;
				oldDataPack.contibuteSTA += 1;
				oldDataPack.STcap += 5;
				oldDataPack.notification += "<html>我选择练习使用3D打印机<br>过去了4小时，健康值-3，体力下降40点，对科协的贡献+1, 科创能力+5</html>";
			}
			if(oldDataPack.choiceA.equals("laser")){
				oldDataPack.stateA="2.gif";
				oldDataPack.stateB="gif";
				oldDataPack.time+=2;		
				oldDataPack.characterEnergy-=20;
				oldDataPack.characterHealth-=5;
				oldDataPack.contibuteSTA += 1;
				oldDataPack.STcap += 5;
				oldDataPack.notification += "<html>我选择练习激光切割机的使用<br>过去了2小时，健康值-5，体力下降20点，对科协的贡献+1，科创能力+5</html>";
			}
			if(oldDataPack.choiceA.equals("tool")){
				oldDataPack.stateA="640-1.jpeg";
				oldDataPack.stateB="";
				oldDataPack.time+=4;		//小憩一会儿，时间+1
				oldDataPack.characterEnergy-=40;
				oldDataPack.contibuteSTA += 5;
				oldDataPack.STcap += 1;
				oldDataPack.notification += "<html>我选择帮助科协整理工具架<br>过去了4小时，体力下降40点, 对科协的贡献+5，科创能力+1</html>";
			}
			if(oldDataPack.choiceA.equals("back")){
				oldDataPack.time+=1;
				oldDataPack.eventFinished=true;
			}
		}
	}
}

