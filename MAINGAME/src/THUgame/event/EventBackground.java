package THUgame.event;

import THUgame.datapack.DataPack;

/*
* --DIALOG--
* version 1.0
* via hty/jsh
* update:20191018 00:59
* 更新：-
*/

public class EventBackground extends EventBase{
	@Override
	public void actOn(DataPack oldDataPack) {
		oldDataPack.count = oldDataPack.count + 1;
		if(oldDataPack.count == 3) {
			oldDataPack.eventFinished = true;
		}
	}
}
