package THUgame.subevents;

import THUgame.datapack.*;
import THUgame.event.EventBase;
import java.util.*;

public class EventResearch extends EventBase{
	//static public DataPack.ResearchData researchDataPackage;
	
	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.choiceA.equals("back")){
			oldDataPack.time+=1;
			oldDataPack.eventFinished=true;
		}
		else if(oldDataPack.choiceA.equals("confirm")) {
			oldDataPack.researchDataPackage.justGetTool=false;
		}
		else if(oldDataPack.choiceA.equals("RandomEffect")) {
			
			boolean isEnergyEnough=ReadThesis(oldDataPack);
			if(!isEnergyEnough) {
				oldDataPack.notification="体力不足";
			}else {
				oldDataPack.researchDataPackage.numberofPaperRead += 1;
			}
		}
		else if(oldDataPack.choiceA.equals("Desertmap")) {
			if(oldDataPack.researchDataPackage.hasKettle)
				oldDataPack.researchDataPackage.i_map =1;
			else
				oldDataPack.notification = "我还没有获得水壶";
		}
		else if(oldDataPack.choiceA.equals("Forestmap")) {
			if(oldDataPack.researchDataPackage.hasAxe)
				oldDataPack.researchDataPackage.i_map =2;
			else
				oldDataPack.notification = "我还没有获得斧头";
		}
		else if(oldDataPack.choiceA.equals("IceBornemap")) {
			if(oldDataPack.researchDataPackage.hasSnowBoots)
				oldDataPack.researchDataPackage.i_map =3;
			else
				oldDataPack.notification = "我还没有获得雪地靴";
		}
		else if(oldDataPack.choiceA.equals("Volcanomap")) {
			if(oldDataPack.researchDataPackage.hasTrekkingPole)
				oldDataPack.researchDataPackage.i_map =4;
			else
				oldDataPack.notification = "我还没有获得登山杖";
		}
		oldDataPack.choiceA = "";
	}
	
	//每次阅读一篇文章或进行一个研究的时候，都调用这个函数
	public boolean ReadThesis(DataPack oldDataPack) {
		switch(oldDataPack.researchDataPackage.i_map) {
		case 0:
			return ReadBasis( oldDataPack);
		case 1:
			return ReadDesert( oldDataPack);
		case 2:
			return ReadForest( oldDataPack);
		case 3:
			return ReadIceBorne( oldDataPack);
		case 4:
			return ReadVolcano( oldDataPack);
		}
		return false;
	}
	
	public boolean ReadBasis(DataPack oldDataPack) {
		if (oldDataPack.characterEnergy<30)
			return false;
		else {
			oldDataPack.time+=2;
			oldDataPack.characterEnergy-=30;
			Random r = new Random();
			int number = r.nextInt(40);
			while(oldDataPack.researchDataPackage.ThesisRead.contains(number)) {
				number = r.nextInt(40);
			}
			oldDataPack.researchDataPackage.CurrentPaper = number;
			oldDataPack.researchDataPackage.ThesisRead.add(number);
			oldDataPack.characterIQ += 2*oldDataPack.researchDataPackage.BasisMapEvent[number].effect;
			oldDataPack.notification = oldDataPack.researchDataPackage.BasisMapEvent[number].content;
			// 增加阅读的文献数目
			switch(oldDataPack.researchDataPackage.BasisMapEvent[number].classin) {
			case 0:
				oldDataPack.researchDataPackage.TheoryPaperRead ++;
				oldDataPack.researchDataPackage.ThermalPaperRead ++;
				oldDataPack.researchDataPackage.DesignPaperRead ++;
				oldDataPack.researchDataPackage.SimulationPaperRead ++;
				break;
			case 1:
				oldDataPack.researchDataPackage.TheoryPaperRead ++;
				break;
			case 2:
				oldDataPack.researchDataPackage.ThermalPaperRead ++;
				break;
			case 3:
				oldDataPack.researchDataPackage.SimulationPaperRead ++;
				break;
			case 4:
				oldDataPack.researchDataPackage.DesignPaperRead ++;
				break;
			}
			oldDataPack.researchDataPackage.tool = "";
			if(oldDataPack.researchDataPackage.TheoryPaperRead ==10) {
				oldDataPack.researchDataPackage.hasAxe = true;
				oldDataPack.researchDataPackage.justGetTool = true;
				oldDataPack.researchDataPackage.tool += "斧头  ";
			}
			if(oldDataPack.researchDataPackage.ThermalPaperRead ==10) {
				oldDataPack.researchDataPackage.hasTrekkingPole = true;
				oldDataPack.researchDataPackage.justGetTool = true;
				oldDataPack.researchDataPackage.tool += "登山杖  ";
			}
			if(oldDataPack.researchDataPackage.DesignPaperRead ==10) {
				oldDataPack.researchDataPackage.hasKettle = true;
				oldDataPack.researchDataPackage.justGetTool = true;
				oldDataPack.researchDataPackage.tool += "水壶  ";
			}
			if(oldDataPack.researchDataPackage.SimulationPaperRead == 10) {
				oldDataPack.researchDataPackage.hasSnowBoots = true;
				oldDataPack.researchDataPackage.justGetTool = true;
				oldDataPack.researchDataPackage.tool += "雪地靴";
			}
			return true;
		}
	}
	
	public boolean ReadDesert(DataPack oldDataPack) {
		if (oldDataPack.characterEnergy<55)
			return false;
		else {
			oldDataPack.time+=4;
			oldDataPack.characterEnergy-=55; 
			Random r = new Random();
			int number = 0;
			switch(oldDataPack.researchDataPackage.ThesisOrResearch) {
			case 0:
				number = r.nextInt(10);
				while(oldDataPack.researchDataPackage.ThesisRead.contains(number+100)) {
					number = r.nextInt(10);
				}
				break;
			case 1:
				number = r.nextInt(10) + 10;
				while(oldDataPack.researchDataPackage.ThesisRead.contains(number+100)) {
					number = r.nextInt(10) + 10;
				}
			}
			oldDataPack.researchDataPackage.CurrentPaper = number;
			oldDataPack.researchDataPackage.ThesisRead.add(number+100);
			oldDataPack.characterIQ += 2*oldDataPack.researchDataPackage.DesertMapEvent[number].effect;
			oldDataPack.notification = oldDataPack.researchDataPackage.DesertMapEvent[number].content;
			oldDataPack.researchDataPackage.DesignPaperRead ++;
			if(oldDataPack.researchDataPackage.DesignPaperRead ==20) {
				oldDataPack.researchDataPackage.hasJeep = true;
				oldDataPack.researchDataPackage.justGetTool = true;
				oldDataPack.researchDataPackage.tool = "越野车";
			}
			return true;
		}
	}
	
	public boolean ReadForest(DataPack oldDataPack) {
		if (oldDataPack.characterEnergy<55)
			return false;
		else {
			oldDataPack.time+=4;
			oldDataPack.characterEnergy-=55;
			Random r = new Random();
			int number = 0;
			switch(oldDataPack.researchDataPackage.ThesisOrResearch) {
			case 0:
				number = r.nextInt(10);
				while(oldDataPack.researchDataPackage.ThesisRead.contains(number+200)) {
					number = r.nextInt(10);
				}
				break;
			case 1:
				number = r.nextInt(10) + 10;
				while(oldDataPack.researchDataPackage.ThesisRead.contains(number+200)) {
					number = r.nextInt(10) + 10;
				}
			}
			oldDataPack.researchDataPackage.CurrentPaper = number;
			oldDataPack.researchDataPackage.ThesisRead.add(number+200);
			oldDataPack.characterIQ += 2*oldDataPack.researchDataPackage.ForestMapEvent[number].effect;
			oldDataPack.notification = oldDataPack.researchDataPackage.ForestMapEvent[number].content;
			oldDataPack.researchDataPackage.TheoryPaperRead ++;
			if(oldDataPack.researchDataPackage.TheoryPaperRead ==20) {
				oldDataPack.researchDataPackage.hasGun = true;
				oldDataPack.researchDataPackage.justGetTool = true;
				oldDataPack.researchDataPackage.tool = "猎枪";
			}
			return true;
		}
	}

	public boolean ReadIceBorne(DataPack oldDataPack) {
		if (oldDataPack.characterEnergy<55)
			return false;
		else {
			oldDataPack.time+=4;
			oldDataPack.characterEnergy-=55;
			Random r = new Random();
			int number = 0;
			switch(oldDataPack.researchDataPackage.ThesisOrResearch) {
			case 0:
				number = r.nextInt(10);
				while(oldDataPack.researchDataPackage.ThesisRead.contains(number+300)) {
					number = r.nextInt(10);
				}
				break;
			case 1:
				number = r.nextInt(10) + 10;
				while(oldDataPack.researchDataPackage.ThesisRead.contains(number+300)) {
					number = r.nextInt(10) + 10;
				}
			}
			oldDataPack.researchDataPackage.CurrentPaper = number;
			oldDataPack.researchDataPackage.ThesisRead.add(number+300);
			oldDataPack.characterIQ += 2*oldDataPack.researchDataPackage.IceBorneMapEvent[number].effect;
			oldDataPack.notification = oldDataPack.researchDataPackage.IceBorneMapEvent[number].content;
			oldDataPack.researchDataPackage.SimulationPaperRead ++;
			if(oldDataPack.researchDataPackage.SimulationPaperRead ==20) {
				oldDataPack.researchDataPackage.hasSled = true;
				oldDataPack.researchDataPackage.justGetTool = true;
				oldDataPack.researchDataPackage.tool = "雪橇";
			}
			
			return true;
		}
	}
	
	public boolean ReadVolcano(DataPack oldDataPack) {
		if (oldDataPack.characterEnergy<55)
			return false;
		else {
			oldDataPack.time+=4;
			oldDataPack.characterEnergy-=55;
			Random r = new Random();
			int number = 0;
			switch(oldDataPack.researchDataPackage.ThesisOrResearch) {
			case 0:
				number = r.nextInt(10);
				while(oldDataPack.researchDataPackage.ThesisRead.contains(number+400)) {
					number = r.nextInt(10);
				}
				break;
			case 1:
				number = r.nextInt(10) + 10;
				while(oldDataPack.researchDataPackage.ThesisRead.contains(number+400)) {
					number = r.nextInt(10) + 10;
				}
			}
			oldDataPack.researchDataPackage.CurrentPaper = number;
			oldDataPack.researchDataPackage.ThesisRead.add(number+400);
			oldDataPack.characterIQ += 2*oldDataPack.researchDataPackage.VolcanoMapEvent[number].effect;
			oldDataPack.notification = oldDataPack.researchDataPackage.VolcanoMapEvent[number].content;
			oldDataPack.researchDataPackage.ThermalPaperRead ++;
			if(oldDataPack.researchDataPackage.ThermalPaperRead ==20) {
				oldDataPack.researchDataPackage.hasInsulationSuit = true;
				oldDataPack.researchDataPackage.justGetTool = true;
				oldDataPack.researchDataPackage.tool = "隔热服";
			}
			return true;
		}
	}
}
