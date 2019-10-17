package THUgame.event;

import THUgame.datapack.DataPack;

/*
* --DIALOG--
* version 1.0
* via 林逸晗
* update:20190930 00:59
* 更新：-
*/

abstract public class EventBase {
	public DataPack oldDataPack;
	abstract public void actOn(DataPack dataPackage);
}
