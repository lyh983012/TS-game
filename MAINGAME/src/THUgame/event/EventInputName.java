package THUgame.event;

import THUgame.datapack.DataPack;

/*
* --DIALOG--
* version 1.0
* via hty/jsh
* update:20191018 00:59
* 更新：-
*/

public class EventInputName extends EventBase{
	@Override
	public void actOn(DataPack oldDataPack) {
		if(!(oldDataPack.name==null))
			oldDataPack.eventFinished = true;
	}
}
