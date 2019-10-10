package THUgame.event;
import java.util.Random;

import javax.swing.JOptionPane;

import THUgame.datapack.DataPack;

public class EventStart extends EventBase{
	public void actOn(DataPack oldDataPack) {
		oldDataPack.eventFinished=true;
	}

}
