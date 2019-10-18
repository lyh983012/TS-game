package THUgame.event;

import THUgame.datapack.DataPack;

abstract public class EventBase {
	public DataPack oldDataPack;
	abstract public void actOn(DataPack dataPackage);
}
