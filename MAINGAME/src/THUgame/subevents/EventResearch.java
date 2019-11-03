package THUgame.subevents;

import THUgame.datapack.*;
import THUgame.event.EventBase;

public class EventResearch extends EventBase{
	static public ResearchData researchDataPackage;
	
	public void actOn(DataPack oldDataPack) {
		if(oldDataPack.choiceA.equals("back")){
			oldDataPack.time+=1;
			oldDataPack.eventFinished=true;
		}
		else if(oldDataPack.choiceA.equals("RandomEffect")) {
			researchDataPackage.numberofPaperRead += 1;
			
		}
		else if(oldDataPack.choiceA.equals("Desertmap")) {
			researchDataPackage.i_map =1;
		}
		else if(oldDataPack.choiceA.equals("Forestmap")) {
			researchDataPackage.i_map =2;
		}
		else if(oldDataPack.choiceA.equals("IceBornemap")) {
			researchDataPackage.i_map =3;
		}
		else if(oldDataPack.choiceA.equals("Volcanomap")) {
			researchDataPackage.i_map =4;
		}
		
	}
}
