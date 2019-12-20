package THUgame.subevents;
import java.util.Random;

import javax.swing.JOptionPane;


import THUgame.datapack.DataPack;
import THUgame.event.EventBase;


/*
 * 招新事件 * 
 * 
 *  ---- LOG ----
 *  update:20191123
 *  via：余冬杰
 *  更新：招新的大致过程明确了
 *  TODO:
 *      1.
 * 
 * */

public class EventOrgEnroll extends EventBase{

	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/
		if (oldDataPack.stateA.equals("endEnroll")) {    //事件按照流程结束
			oldDataPack.eventFinished=true;
			oldDataPack.time += 1;
			return;
		} 
		
		/*		START OF YOUR CODE		*/	
		if(oldDataPack.characterHealth<0) {
			oldDataPack.notification="我死了。";
		}
		Random r = new Random();
		int randomValue = r.nextInt(8) + 1;
		
		switch(oldDataPack.count) {
			case 0:
				oldDataPack.notification = "<html>你好，我又来啦！恭喜你被报名的学生组织录用啦，这是你的聘书！</html>";
				break;
			case 1:
				oldDataPack.notification = "<html>哇！还有聘书呀。</html>";
				break;
			case 2:
				oldDataPack.notification="";
				if (oldDataPack.joinSA)
					oldDataPack.notification += "<html>学生会：每周三晚上体育部有例会，地点就在宿舍楼的517A公共活动室。<br>";	
				if (oldDataPack.joinSTA)
					oldDataPack.notification += "<html>科协：每周二,每周五都需要外出去科协实验室值日,随时都可以去哦<br>";	
				break;
			case 3:
				oldDataPack.notification = "<html>好的好的，一定准时！</html>";	
				oldDataPack.stateA = "endEnroll";
				break;
		}
		if (oldDataPack.characterHealth<=0)
			JOptionPane.showMessageDialog(null, "你猝死了", "", JOptionPane.ERROR_MESSAGE);//弹出猝死界面
		/*		END OF YOUR CODE		*/
		
		oldDataPack.count = oldDataPack.count + 1;
		return;
	}
}
