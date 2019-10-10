package template.event;
import java.util.Random;

import javax.swing.JOptionPane;

import template.datapack.DataPack;

/*
 * 多次多重选择后台模板
 *  
 * template version 1.0
 * update:20191009 20:28
 * 问题：
 * 		需要解决首次进入可视化界面时count自动为1的反常情况。
 * */


public class EventChoice extends EventBase{

	public void actOn(DataPack oldDataPack) {
		
		oldDataPack.stateInEvent = oldDataPack.stateInEvent + oldDataPack.choiceA;
		
		if(oldDataPack.count==8) {
			oldDataPack.eventFinished=true;
			int allnighter,scientific,sports,player,volunteer,money,expert;
			expert = 0;
			allnighter = 0;
			scientific = 0;
			sports = 0;
			player = 0;
			volunteer = 0;
			money = 0;
			
			char thischoice;
			//第一题
			thischoice = oldDataPack.stateInEvent.charAt(0);
			switch(thischoice) {
				case 'A':
					
					break;
				case 'B':
					allnighter = allnighter + 1;
					break;
				case 'C':
					allnighter = allnighter + 2;
					break;
			}
			//第二题
			thischoice = oldDataPack.stateInEvent.charAt(1);
			switch(thischoice) {
			case 'A':
				allnighter = allnighter + 1;
				break;
			case 'B':
				break;
			}
			//第三题
			thischoice = oldDataPack.stateInEvent.charAt(2);
			switch(thischoice) {
			case 'A':
				break;
			case 'B':
				scientific = scientific + 1;
				break;
			case 'C':
				scientific = scientific + 2;
				break;
			}
			//第四题
			thischoice = oldDataPack.stateInEvent.charAt(3);
			switch(thischoice) {
			case 'A':
				sports = sports + 1;
				break;
			case 'B':
				player = player + 1;
				break;
			case 'C':
				scientific = scientific + 1;
				break;
			case 'D':
				volunteer = volunteer + 1;
				break;
			}
			//第五题
			thischoice = oldDataPack.stateInEvent.charAt(4);
			switch(thischoice) {
			case 'A':

				break;
			case 'B':
				player = player + 1;
				break;
			case 'C':
				scientific = scientific - 1;
				break;
			case 'D':
				volunteer = volunteer - 1;
				break;
			}
			//第六题
			thischoice = oldDataPack.stateInEvent.charAt(5);
			switch(thischoice) {
			case 'A':
				scientific = scientific + 1;
				break;
			case 'B':
				player = player + 1;
				break;
			case 'C':
				allnighter = allnighter - 1;
				break;
			case 'D':
				break;
			}
			//第七题
			thischoice = oldDataPack.stateInEvent.charAt(6);
			switch(thischoice) {
			case 'A':
				
				break;
			case 'B':
				money = money + 1;
				break;
			case 'C':
				expert = expert + 1;
				break;
			}
			//第八题
			thischoice = oldDataPack.stateInEvent.charAt(7);
			switch(thischoice) {
			case 'A':
				scientific = scientific + 1;
				break;
			case 'B':
				money = money + 1;
				break;
			case 'C':
				volunteer = volunteer + 1;
				break;
			case 'D':
				player = player + 1;
				break;
			case 'E':
				sports = sports + 1;
				break;
			}
			if(allnighter>=3) {
				oldDataPack.nightOwl = true;
			}
			if(scientific>=5) {
				oldDataPack.scientificMad = true;
			}
			if(allnighter<=2&&sports>=2) {
				oldDataPack.sportsMan = true;
			}
			if(player>=4) {
				oldDataPack.eSportsPlayer=true;
			}
			if(expert>=1) {
				oldDataPack.computerExpert=true;
			}
			oldDataPack.count = oldDataPack.count + 1;	
			oldDataPack.eventFinished = true;
		}
		oldDataPack.count = oldDataPack.count + 1;
		return;
	}
}
