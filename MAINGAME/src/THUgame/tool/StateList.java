package THUgame.tool;

public class StateList {
	static public String getEventNotification(int term,int week,int date,boolean joinreaserch,boolean joinclub,boolean joinSTA,boolean joinTZB,boolean joinSA) {
		String note="<html><br><br>";
		switch(term){
		case 1:
			switch(week){
			case 1:
				if (date==1)
					note+="今天是新学期第一天，需要选课。<br>";
				if (date==4)
					note+="今天有各种学生组织的报名，只此一次哦。<br>";
				break;
			case 2:
				if (date==4 && joinclub)
					note+="今天有社团的例会。得记得去。<br>";
				if ((date==5||date==2) && joinSTA)
					note+="今天有科协科创实验室的值日。得记得去。<br>";
				break;
			case 3:
				if (date==4 && joinclub)
					note+="今天有社团的培训。得记得去。<br>";
				if ((date==5||date==2) && joinSTA)
					note+="今天有科协科创实验室的值日。得记得去。<br>";
				break;
			case 4:
				if (date==4 && joinclub)
					note+="今天有社团的培训。得记得去。<br>";
				if ((date==5||date==2) && joinSTA)
					note+="今天有科协科创实验室的值日。得记得去。<br>";
				if (date==5)
					note+="明天期末考啊啊啊！！千万不要忘记睡过头啊！！。<br>";
				if (date==6)
					note+="今天期末考啊啊啊！！千万不要忘记去考场啊！！。<br>";
				break;	
			}
			break;
		case 2:
			switch(week){
			case 1:
				if (date==1)
					note+="今天是新学期第一天，需要选课。<br>";
				if (date==1)
					note+="今晚还有著名科创赛事——挑战杯的报名。<br>";
				if ((date==5||date==2) && joinSTA)
					note+="今天有科协科创实验室的值日。得记得去。<br>";
				break;
			case 2:
				if ((date==5||date==2) && joinSTA)
					note+="今天有科协科创实验室的值日。得记得去。<br>";
				break;
			case 3:
				if (date==6 && joinTZB)
					note+="今天挑战杯答辩，别忘记去了。<br>";
				if ((date==5||date==2) && joinSTA)
					note+="今天有科协科创实验室的值日。得记得去。<br>";
				break;				
			case 4:
				if ((date==5||date==2) && joinSTA)
					note+="今天有科协科创实验室的值日。得记得去。<br>";
				if (date==5)
					note+="明天期末考啊啊啊！！千万不要忘记睡过头啊！！。<br>";
				if (date==6)
					note+="今天期末考啊啊啊！！千万不要忘记去考场啊！！。<br>";
				break;				
			}
			break;
		case 3:
			switch(week){
			case 1:	
				if (date==1)
					note+="今天是新学期第一天，需要选课。<br>";
				if (date==2)
					note+="今天SRT报名，让我好好想想。<br>";
				if (date==4 && joinreaserch)
					note+="今天下午有SRT组会，记得外出参加。<br>";
				break;				
			case 2:
				if (date==4 && joinreaserch)
					note+="今天下午有SRT组会，记得外出参加。<br>";
				break;				
			case 3:
				if (date==4 && joinreaserch)
					note+="今天下午有SRT组会，记得外出参加。<br>";
				break;				
			case 4:
				if (date==4 && joinreaserch)
					note+="今天下午有SRT组会，记得外出参加。<br>";
				if (date==5)
					note+="明天期末考啊啊啊！！千万不要忘记睡过头啊！！。<br>";
				if (date==6)
					note+="今天期末考啊啊啊！！千万不要忘记去考场啊！！。<br>";
				break;	
			}
			break;
		case 4:
			switch(week){
			case 1:
				if (date==1)
					note+="今天是新学期第一天，需要选课。<br>";
				if (date==4 && joinreaserch)
					note+="今天下午有SRT组会，记得外出参加。<br>";
				break;				
			case 2:
				if (date==4 && joinreaserch)
					note+="今天下午有SRT组会，记得外出参加。<br>";
				break;				
			case 3:
				if (date==4 && joinreaserch)
					note+="今天下午有SRT组会，记得外出参加。<br>";
				break;				
			case 4:
				if (date==4 && joinreaserch)
					note+="今天下午有SRT组会，记得外出参加。<br>";
				if (date==5)
					note+="明天期末考啊啊啊！！千万不要忘记睡过头啊！！。<br>";
				if (date==6)
					note+="今天期末考啊啊啊！！千万不要忘记去考场啊！！。<br>";
				break;				
			}
			break;	
		
		}
		note+="</html>";
		return note;
	}
}
