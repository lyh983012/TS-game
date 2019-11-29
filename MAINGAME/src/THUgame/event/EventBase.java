package THUgame.event;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
	
	public void saveGame(){
		ObjectOutputStream oos = null;
        try {
        	File file = new File(oldDataPack.name+oldDataPack.studentID+"_gameSaved.thu");
        	//判断文件是否已存在
        	if(!file.exists()) {
        		try{
        			file.createNewFile();
        		}catch(IOException e){
           			e.printStackTrace();
           		}
        	}
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(oldDataPack);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
