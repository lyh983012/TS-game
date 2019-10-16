package THUgame.event;

import THUgame.datapack.DataPack;

public class EventBackground extends EventBase{
	public void actOn(DataPack oldDataPack) {
		oldDataPack.count = oldDataPack.count + 1;
		if(oldDataPack.count == 3) {
			oldDataPack.eventFinished = true;
		}
	}
}
