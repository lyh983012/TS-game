package THUgame.event;
import java.util.Random;

import javax.swing.JOptionPane;

import THUgame.datapack.DataPack;


/*
 * 招新事件 * 
 * 
 *  ---- LOG ----
 *  update:20191029
 *  via：余冬杰
 *  更新：招新的大致过程明确了
 *  TODO:
 *      1.接受/拒绝凌艺涵的对话框
 *      2.招新问卷（按钮、背景素材）
 *      3.填完问卷如何存储属性
 * 
 * */

public class EventOrganization extends EventBase{

	public void actOn(DataPack oldDataPack) {
		/*******************************************
		 * 事件结束
		 * 		转换一个标记，必要时存储一些信息
		 *******************************************/
		if (oldDataPack.choiceB.equals("closeQuestionnaire")) {
			oldDataPack.notification = "思来想去还是没有报名……";
			oldDataPack.eventFinished=true;			//并且点击了关闭问卷这个按钮，那么招新事件结束
			oldDataPack.time += 1;
			return;									//直接返回，避免属性乱变
		} else if (oldDataPack.stateA == "quitEnroll") {
			oldDataPack.eventFinished=true;			//拒绝了招新人员，那么招新事件结束
			return;									//直接返回，避免属性乱变
		}
		
		/*******************************************
		 * count = 0 第一次进入该事件，有人敲门，点击 下一步 ，count++
		 * count = 1 介绍来意，点击下一步，count++
		 * count = 2 
		 *     弹出对话框：是否加入社工或社团组织呢
		 * count = 3
		 *     接受：社工，要不要试试呢
		 *     拒绝：算啦，还是专心学习吧
		 *         同时弹出招新问卷
		 * count = 4
		 *     接受招新问卷结果，修改人物属性
		 *
		 *     第3天下午7点，公布聘书，并通知第一次会议
		 *******************************************/
		/*		START OF YOUR CODE		*/	
		if(oldDataPack.characterHealth<0) {
			oldDataPack.notification="我死了。";
		}
		Random r = new Random();
		int randomValue = r.nextInt(8) + 1;
		
		switch(oldDataPack.count) {
			case 0:
				oldDataPack.notification = "“咚咚咚。”似乎有人在敲宿舍门";
				break;
			case 1:
				if (oldDataPack.date == 2) { // 第2天发布招新
					oldDataPack.notification = "<html>您好！我是新雅书院学生会的凌艺涵。今天是系学生会各部门招新，请问你有兴趣填一下问卷吗？</html>。";
				} else {                     // 第3天公布聘书，并发布第一次会议通知
					oldDataPack.notification = "我又来啦，恭喜你被学生会体育部录用了，这是你的聘书！";
				}
				break;
			case 2:
				if (oldDataPack.date == 2) { // 第2天弹出接受/拒绝对话框
					oldDataPack.notification = "陷入抉择……";
				} else {                     // 第3天公布聘书，并发布第一次会议通知
					oldDataPack.notification = "咦，还有聘书呀！";
				}
				
				break;
			case 3:
				if (oldDataPack.date == 2) {
					if (oldDataPack.choiceA == "agree") {
						oldDataPack.notification = "<html>社工呀，要不试试吧！</html>";
					} else if (oldDataPack.choiceA == "refuse") {
						oldDataPack.notification = "算啦，还是专心学习吧";
						oldDataPack.stateA = "quitEnroll";
					}
				}
				
				break;
			case 4:
				
				
				break;

		}
		if (oldDataPack.characterHealth<=0)
			JOptionPane.showMessageDialog(null, "你猝死了", "", JOptionPane.ERROR_MESSAGE);//弹出猝死界面
		/*		END OF YOUR CODE		*/
		oldDataPack.count = oldDataPack.count + 1;
		return;
	}
}
