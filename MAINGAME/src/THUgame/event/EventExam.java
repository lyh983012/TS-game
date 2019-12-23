package THUgame.event;

import THUgame.datapack.DataPack;

public class EventExam extends EventBase{
	@Override
	public void actOn(DataPack oldDataPack) {
		oldDataPack.eventFinished=true;
	}
}

