package template.event;
import java.util.Random;

import javax.swing.JOptionPane;

import template.datapack.DataPack;

public class Start extends EventBase{
	public void actOn(DataPack oldDataPack) {
		oldDataPack.eventFinished=true;
	}

}
