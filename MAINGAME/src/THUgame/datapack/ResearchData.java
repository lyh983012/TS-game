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

 	//在刚进入科研线的时候调用这个构造函数
	public ResearchData() {
		/*
		//基础地图的事件，事件序号是0xx
		BasisMapEvent[0] = new ResearchMapEvent(001, "反应堆计算SN方法",
		 	"我阅读了一篇关于反应堆计算SN方法的英文文献，智力值增加了1点", 1, 0, 0);
		BasisMapEvent[1] = new ResearchMapEvent(002, "压水堆系统综述",
			"我阅读了一篇对压水堆核电站做了一个综述的中文文献，读完后感觉还是不太明白", 0, 0, 0);
		BasisMapEvent[2] = new ResearchMapEvent(003, "重水堆系统综述",
			"我阅读了一篇对重水堆核电站做了一个综述的英文文献，智力值增加了0.5", 0.5, 0);

		//森林地图（反应堆物理理论）的事件，事件序号是1xx
		DesertMapEvent[0] = new ResearchMapEvent(101, "随机抽样法S/U分析",
			"我阅读了一篇利用随机抽样法进行S/U分析的英文文献，智力值增加了0.5", 0.5, 1, 0);
		DesertMapEvent[1] = new ResearchMapEvent(102, "时间序列分析代间相关性",
			"我阅读了一篇结合时间序列分析来计算真实方差的英文文献，智力值增加了1", 1, 1, 0);
		DesertMapEvent[11] = new ResearchMapEvent(112, "一次失败的数值模拟", 
			"我尝试将文献中的时间序列分析法应用到我们实验室的程序中，但这太难了，我最后放弃了", 0, 1, 0);
		DesertMapEvent[12] = new ResearchMapEvent(113, "突然的灵感",
			"我今天稍微打了个盹，脑中突然想出了一种新的减方差方法，智力值增加了1点", 1, 1, 114);
		DesertMapEvent[13] = new ResearchMapEvent(114, "巨大的突破",
			"我试着在程序中加入了这种新的减方差方法，并且取得了不错的结果！智力值增加了1点", 1, 1, 0);
		DesertMapEvent[14] = new ResearchMapEvent(115, "数值模拟的结果不对！",
			"我把我的新方法迁移到另一个模型里，但是数值模拟的结果不对！让人绝望", 0, 1, 0);
		DesertMapEvent[19] = new ResearchMapEvent(120, "我终于完成了新物理方法的开发",
			"在多次失败之后，我终于把完成了新物理方法的开发和测试，我可以开始撰写论文了", 1, 1, 999);

	}

	//目前计划设计120个科研地图事件，其中基础地图对应40个，其余四个分支地图各包含20个（这样的话，可能会有重复）
	public ResearchMapEvent BasisMapEvent[40] = {};
	public ResearchMapEvent DesertMapEvent[20] = {};
	public ReserachMapEvent ForestMapEvent[20] = {};
	public ResearchMapEvent IceBorneMapEvent[20] = {};
	public ResearchMapEvent VolcanoMapEvent[20] = {};
	
	*/
}

public class ResearchMapEvent{
	
	
	public int id = 0;
	/*
	public String title = "";
	public String content = "";
	public int effect = 0;  //对智力值的提高
	public int class = 0;  
	public int eventafter = 0; //该事件之后可能发生的后续事件编号，无后续事件为0，表示研究结束为999.
	//class 表示该文献属于哪一类，其中0表示基础文献，1表示和反应堆物理理论有关的文献，2表示和反应堆热工模拟有关的文献，
	//    3表示和反应堆模拟有关的文献，4表示和反应堆设计有关的文献

	//地图上事件类的构造函数
	public ResearchMapEvent(int id0, String title0, String content0, int effect0, int class0, int eventafter0){
		id = id0;
		title = title0;
		content = content0;
		effect = effect0;
		class = class0；
		eventafter = eventafter0;
*/
	}

}