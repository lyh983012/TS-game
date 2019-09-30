package template.event;
import template.datapack.DataPack;


/*
 * template version 1.0
 * 分支事件后台模板
 * update:20190927 18:39
 * */

public class EventTemplate {
	
	public DataPack nextDataPack;
	public DataPack actOn(DataPack oldDataPack) {
		/* 		在下面进行dataPack的处理	*/
		/*		START OF YOUR CODE		*/

		nextDataPack=oldDataPack;
		
		/*		END OF YOUR CODE		*/
		return nextDataPack;
	}
}
