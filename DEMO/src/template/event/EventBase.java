package template.event;

import template.datapack.DataPack;

abstract public class EventBase {
	public DataPack nextDataPack;
	abstract public DataPack actOn(DataPack dataPackage);
}
