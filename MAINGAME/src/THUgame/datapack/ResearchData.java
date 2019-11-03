package THUgame.datapack;

/* 存储科研地图的数据包
 * 
 * update: 20191101
 * via: 江世航
 * 此数据包包含五个科研地图的状态数组，每个数组都表示一个5*7的方格地图
 */

public class ResearchData {
	
	public int numberofPaperRead=0;  //阅读的文献数目
	public int TheoryPaperRead=0;  //阅读的理论文献
	public int ThermalPaperRead=0;  //阅读的热工文献
	public int SimulationPaperRead=0;  //阅读的模拟方面的文献
	public int DesignPaperRead=0;  //阅读的设计方面的文献
	public int CurrentPaper=0;  //阅读的上一篇文献号
	
	public boolean hasKettle = false; //是否有水壶
	public boolean hasJeep = false;  //是否有越野车
	public boolean hasAxe = false;  //是否有斧头
	public boolean hasGun = false;  //是否有猎枪
	public boolean hasSnowBoots = false;  //是否有雪地靴
	public boolean hasSled = false;  //是否有雪橇
	public boolean hasTrekkingPole = false;  //是否有登山杖
	public boolean hasInsulationSuit = false;  //是否有隔热服
	
	
	/* 科研格子状态：
	 * 0：已探索
	 * 1：普通未知
	 * 2：普通沙漠
	 * 3：高级沙漠
	 * 4：普通丛林
	 * 5：高级丛林
	 * 6：普通冰原
	 * 7：高级冰原
	 * 8：普通火山
	 * 9：高级火山
	 * 10：海域
	 */
	public int i_map = 0; //显示的地图，0表示基本地图，1表示沙漠地图，2表示丛林地图，3表示冰原地图，4表示火山地图
	
	public int BasisMap[] = {10,1,1,1,2,1,4,
			                 10,1,1,1,1,1,1,
			                 10,1,1,1,1,1,6,
			                 10,1,1,1,1,1,1,
			                 0,1,10,10,1,1,8};
	
	// 阅读的论文的名字
	public String BasisPaperName[] = {
			
	};
	
	// 阅读论文时显示的内容
	public String BasisReflection[] = {
			
	};
	
	// 阅读的论文的类别
	public int BasisPaperType[] = {};
	
	
	public int DesertMap[] = {2,2,2,2,2,2,2,
			                  2,2,2,2,2,2,2,
			                  2,2,2,2,2,2,2,
			                  2,2,2,2,2,2,2,
			                  0,10,10,2,2,2,2};
	
	public int ForestMap[] = {4,4,4,4,4,4,4,
			                  4,4,4,4,4,4,4,
			                  10,4,4,4,4,4,4,
			                  4,4,4,4,4,4,4,
			                  0,10,4,4,4,4,4};
	
	public int IceBorneMap[] = {6,6,6,6,6,6,6,
			                    6,6,6,6,6,6,6,
			                    6,6,6,6,6,6,6,
			                    6,6,6,10,10,6,6,
			                    0,6,6,10,6,6,6};
	
	public int VolcanoMap[] = {8,8,8,8,8,8,8,
			                   8,8,8,8,8,8,8,
			                   8,8,8,8,8,8,8,
			                   10,10,8,8,8,8,8,
			                   0,8,8,8,8,8,8};

	public ResearchData() {
	
	}
}
