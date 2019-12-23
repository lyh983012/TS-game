package THUgame.tool;
import java.io.Serializable;
import java.util.*;

/* 存储科研地图的数据包
 * 
 * update: 20191101
 * via: 江世航
 * 此数据包包含五个科研地图的状态数组，每个数组都表示一个5*7的方格地图
 */

public class ResearchData implements Serializable{
	
	public boolean firstEnter = true;  //是否是首次进入科研地图
	
	public int numberofPaperRead=0;  //阅读的文献数目
	public int TheoryPaperRead=0;  //阅读的理论文献
	public int ThermalPaperRead=0;  //阅读的热工文献
	public int SimulationPaperRead=0;  //阅读的模拟方面的文献
	public int DesignPaperRead=0;  //阅读的设计方面的文献
	public int ResearchWorkDone=0; //进行的研究次数
	public int CurrentPaper=0;  //阅读的上一篇文献号
	
	public boolean hasKettle = false; //是否有水壶
	public boolean hasJeep = false;  //是否有越野车
	public boolean hasAxe = false;  //是否有斧头
	public boolean hasGun = false;  //是否有猎枪
	public boolean hasSnowBoots = false;  //是否有雪地靴
	public boolean hasSled = false;  //是否有雪橇
	public boolean hasTrekkingPole = false;  //是否有登山杖
	public boolean hasInsulationSuit = false;  //是否有隔热服
	
	public boolean justGetTool=false;
	public boolean justReadThesis=false;
	public String tool="";
	public int lastEvent = 0;
	public boolean researchFinished = false;
	
	
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
	
	// 阅读的论文的类别
	public int BasisPaperType[] = {};
	
	
	public int DesertMap[] = {2,2,2,3,3,3,3,
			                  2,2,2,2,3,3,3,
			                  2,2,2,2,2,3,3,
			                  2,2,2,2,2,3,3,
			                  0,10,10,2,3,3,3};
	
	public int ForestMap[] = {4,5,5,5,5,5,5,
			                  4,4,4,4,5,5,5,
			                  10,4,4,4,4,5,5,
			                  4,4,4,4,4,5,5,
			                  0,10,4,4,4,4,4};
	
	public int IceBorneMap[] = {6,6,6,6,7,7,7,
			                    6,6,6,6,7,7,7,
			                    6,6,6,6,7,7,7,
			                    6,6,6,10,10,7,7,
			                    0,6,6,10,7,7,7};
	
	public int VolcanoMap[] = {8,8,8,8,9,9,9,
			                   8,8,8,8,8,9,9,
			                   8,8,8,8,8,9,9,
			                   10,10,8,8,8,9,9,
			                   0,8,8,8,9,9,9};

	public Vector ThesisRead = new Vector();
	public int ThesisOrResearch = 0;  //在高级地图中，0表示阅读论文，1表示进行研究（需要在获得工具之后才能进行）
	
	//在刚进入科研线的时候调用这个构造函数
		public ResearchData() {
			//基础地图的事件，事件序号是0xx
			BasisMapEvent[0] = new ResearchMapEvent(1, "反应堆计算SN方法",
			 	"我阅读了一篇关于反应堆计算SN方法的英文文献，智力值增加了1点", 1, 1, 0);
			BasisMapEvent[1] = new ResearchMapEvent(2, "压水堆系统综述",
				"我阅读了一篇对压水堆核电站做了一个综述的中文文献，读完后感觉还是不太明白", 0, 0, 0);
			BasisMapEvent[2] = new ResearchMapEvent(3, "重水堆系统综述",
				"我阅读了一篇对重水堆核电站做了一个综述的英文文献，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[3] = new ResearchMapEvent(4, "高温气冷堆系统综述",
				"我阅读了一篇对高温气冷堆核电站做了一个综述的中文文献，智力值增加了0.5", 0.5, 4, 0);
			BasisMapEvent[4] = new ResearchMapEvent(5, "控制棒驱动装置",
				"我阅读了一篇讲控制棒驱动装置的英文文献，智力值增加了0.5", 0.5, 4, 0);
			BasisMapEvent[5] = new ResearchMapEvent(6, "核反应堆仪表",
				"我阅读了一篇关于核反应堆仪表的中文文献，但看完之后什么也没记住", 0, 0, 0);
			BasisMapEvent[6] = new ResearchMapEvent(7, "核电厂的选址问题",
				"我阅读了一篇关于核电站如何选址的中文文献，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[7] = new ResearchMapEvent(8, "核电站非能动安全",
				"我阅读了一篇关于核电站非能动安全的中文文献，智力值增加了0.5", 0.5, 3, 0);
			BasisMapEvent[8] = new ResearchMapEvent(9, "核电厂对周围环境的影响",
				"我阅读了一篇关于核电厂对周围环境可能造成的污染的中文文献，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[9] = new ResearchMapEvent(10, "反应堆计算的蒙卡方法综述",
				"我阅读了一篇对反应堆计算中使用的蒙卡方法做了一个综述的英文文献，智力值增加了1", 1, 0, 0);
			BasisMapEvent[10] = new ResearchMapEvent(11, "龙格库塔法",
				"我阅读了一篇介绍龙格库塔法的英文文献，智力值增加了1", 1, 0, 0);
			BasisMapEvent[11] = new ResearchMapEvent(12, "行波堆概念",
				"我阅读了一篇介绍行波堆的中文文献，没看懂···", 0, 0, 0);
			BasisMapEvent[12] = new ResearchMapEvent(13, "可控核聚变",
				"我阅读了一篇讲可控核聚变基本概念的中文文献，感觉拓展了思路，智力值增加了0.5", 0.5, 4, 0);
			BasisMapEvent[13] = new ResearchMapEvent(14, "反应堆计算PN方法",
				"我阅读了一篇介绍反应堆计算PN方法的英文文献，但感觉非常复杂，没太看懂", 0, 1, 0);
			BasisMapEvent[14] = new ResearchMapEvent(15, "马尔科夫链",
				"我阅读了一篇介绍马氏链的英文文献，智力值增加了1", 1, 1, 0);
			BasisMapEvent[15] = new ResearchMapEvent(16, "并行编程",
				"我阅读了一篇关于并行编程基本思想的英文文献，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[16] = new ResearchMapEvent(17, "神经网络算法",
				"我阅读了一篇介绍神经网络算法的英文文献，智力值增加了1", 1, 0, 0);
			BasisMapEvent[17] = new ResearchMapEvent(18, "贝叶斯网络",
				"我阅读了一篇介绍贝叶斯算法的英文文献，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[18] = new ResearchMapEvent(19, "傅里叶变换",
				"我阅读了一篇关于傅里叶变换在信号分析中的应用的中文文献，智力值增加了0.5", 0.5, 3, 0);
			BasisMapEvent[19] = new ResearchMapEvent(20, "随机微分方程",
				"我试图阅读一篇关于随机微分方程的英文文献，显然，这超出了我的能力范围···", 0, 1, 0);
			BasisMapEvent[20] = new ResearchMapEvent(21, "随机数发生器",
				"我阅读了一篇讲随机数发生器原理的英文文献，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[21] = new ResearchMapEvent(22, "热传导数值计算",
				"我阅读了一篇关于热传导数值计算的中文文献，但是写的似乎太简单了，对我的水平没什么帮助", 0, 2, 0);
			BasisMapEvent[22] = new ResearchMapEvent(23, "热对流数值计算",
				"我阅读了一篇关于热对流数值计算的中文文献，智力值增加了0.5", 0.5, 2, 0);
			BasisMapEvent[23] = new ResearchMapEvent(24, "学习python",
				"我用半天时间就学会了使用python，好高兴，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[24] = new ResearchMapEvent(25, "学习matlab",
				"我终于学会了使用MATLAB！智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[25] = new ResearchMapEvent(26, "提高C++编程能力",
				"导师说我的C++能力还有待提高···我要多看看primer。智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[26] = new ResearchMapEvent(27, "核燃料结构",
				"我阅读了一篇关于高温堆核燃料结构的中文文献，智力值增加了0.5", 0.5, 2, 0);
			BasisMapEvent[27] = new ResearchMapEvent(28, "核燃料循环",
				"我阅读了一篇关于核燃料从开采加工到核废料处理整个过程的中文文献，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[28] = new ResearchMapEvent(29, "编写一个简单的源迭代程序",
				"我今天试着编写了一个最简单的源迭代程序，能够正常运行并计算出正确的结果，好高兴，智力值增加了0.5", 1, 0, 0);
			BasisMapEvent[29] = new ResearchMapEvent(30, "中子时空动力学",
				"我阅读了一篇用准静态方法模拟中子时空动力学的英文文献，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[30] = new ResearchMapEvent(31, "氙振荡",
				"我阅读了一篇讲反应堆内氙振荡原理的英文文献，智力值增加了0.5", 0.5, 3, 0);
			BasisMapEvent[31] = new ResearchMapEvent(32, "裂变-聚变混合堆",
				"我阅读了一篇关于裂变-聚变混合堆的中文文献，讲得不甚明白", 0, 0, 0);
			BasisMapEvent[32] = new ResearchMapEvent(33, "湍流的模拟",
				"我阅读了一篇模拟湍流运动的英文文献，智力值增加了0.5", 0.5, 2, 0);
			BasisMapEvent[33] = new ResearchMapEvent(34, "压水堆核电站的经济性",
				"我阅读了一篇讲压水堆核电站的经济性的中文文献，感觉对设计核电站还是有用处的，智力值增加了0.5", 0.5, 4, 0);
			BasisMapEvent[34] = new ResearchMapEvent(35, "使用软件模拟核电厂运行",
				"我用老师提供的软件模拟核电厂的控制，这让我对核电厂更加熟悉了，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[35] = new ResearchMapEvent(36, "核数据格式说明手册",
				"我阅读了核数据格式说明手册，这能让我在之后的模拟计算中更方便地使用核数据，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[36] = new ResearchMapEvent(37, "自有气体模型",
				"我阅读了一篇关于自由气体模型的英文文献，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[37] = new ResearchMapEvent(38, "截面共振展宽",
				"我阅读了一篇介绍在不同温度下核截面共振展宽公式的英文文献，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[38] = new ResearchMapEvent(39, "燃耗计算",
				"我阅读了一篇介绍燃耗计算和临界计算耦合迭代的英文文献，智力值增加了0.5", 0.5, 0, 0);
			BasisMapEvent[39] = new ResearchMapEvent(40, "可靠性分析",
				"我阅读了一篇介绍如何对核电厂的可靠性进行分析的中文文献，智力值增加了0.5", 0.5, 3, 0);

			//森林地图（反应堆物理理论）的事件，事件序号是1xx
			ForestMapEvent[0] = new ResearchMapEvent(101, "随机抽样法S/U分析",
				"我阅读了一篇利用随机抽样法进行S/U分析的英文文献，智力值增加了0.5", 0.5, 1, 0);
			ForestMapEvent[1] = new ResearchMapEvent(102, "时间序列分析代间相关性",
				"我阅读了一篇结合时间序列分析来计算真实方差的英文文献，智力值增加了1", 1, 1, 0);
			ForestMapEvent[2] = new ResearchMapEvent(103, "低抽样问题",
				"我阅读了一篇分析在每代中子数很低情况下系统行为的英文文献，智力值增加了0.5", 0.5, 1, 0);
			ForestMapEvent[3] = new ResearchMapEvent(104, "方差低估计问题",
				"我阅读了一篇分析由源迭代导致的方差低估计问题的英文文献，对方差低估计的原理有了大致的了解，智力值增加了0.5", 0.5, 1, 0);
			ForestMapEvent[4] = new ResearchMapEvent(105, "新的方差统计方法",
				"我阅读了一篇通过改变统计组来消除方差低估计现象的英文文献，智力值增加了0.5", 0.5, 1, 0);
			ForestMapEvent[5] = new ResearchMapEvent(106, "全蒙卡方法TMC",
				"我阅读了一篇利用全蒙卡方法进行S/U分析的英文文献，智力值增加了0.5", 0.5, 1, 0);
			ForestMapEvent[6] = new ResearchMapEvent(107, "低抽样和燃耗计算",
				"我阅读了一篇分析低抽样现象对燃耗计算结果的影响的英文文献，智力值增加了0.5", 0.5, 1, 0);
			ForestMapEvent[7] = new ResearchMapEvent(108, "一阶不确定度分析法",
				"我阅读了一篇讲一阶不确定度分析法的英文文献，智力值增加了0.5", 0.5, 1, 0);
			ForestMapEvent[8] = new ResearchMapEvent(109, "马尔科夫链蒙卡方法",
				"我阅读了一篇对马尔科夫链蒙卡方法做综述的英文文献，智力值增加了1", 1, 1, 0);
			ForestMapEvent[9] = new ResearchMapEvent(110, "碰撞概率法",
				"我阅读了一篇关于碰撞概率法原理的英文文献，我认为它和蒙卡方法可能有某些联系，智力值增加了0.5", 0.5, 1, 0);
			ForestMapEvent[10] = new ResearchMapEvent(111, "试图结合确定论方法和蒙卡方法",
				"我坐在桌前，绞尽脑汁想把确定论和蒙卡方法结合起来，那一定是个了不起的发现！但似乎我还没摸到门道···", 0, 1, 0);
			ForestMapEvent[11] = new ResearchMapEvent(112, "一次失败的数值模拟", 
				"我尝试将文献中的时间序列分析法应用到我们实验室的程序中，但这太难了，我最后放弃了", 0, 1, 0);
			ForestMapEvent[12] = new ResearchMapEvent(113, "突然的灵感",
				"我今天稍微打了个盹，脑中突然想出了一种新的减方差方法，智力值增加了1点", 1, 1, 114);
			ForestMapEvent[13] = new ResearchMapEvent(114, "巨大的突破",
				"我试着在程序中加入了这种新的减方差方法，并且取得了不错的结果！智力值增加了1点", 1, 1, 0);
			ForestMapEvent[14] = new ResearchMapEvent(115, "数值模拟的结果不对！",
				"我把我的新方法迁移到另一个模型里，但是数值模拟的结果不对！让人绝望", 0, 1, 0);
			ForestMapEvent[15] = new ResearchMapEvent(116, "对代间相关性的推导",
				"我终于自己推导出了代间相关性的表达式！智力值增加了1点", 1, 1, 117);
			ForestMapEvent[16] = new ResearchMapEvent(117, "试着减弱代间相关性",
				"我趁热打铁，枕着昨天关于代间相关性的理解还很新鲜，我试着在理论上通过对抽样过程做调整减弱代间相关性，取得了一定的效果。智力值增加了1点", 1, 1, 118);
			ForestMapEvent[17] = new ResearchMapEvent(118, "和师兄讨论新方法",
				"我把我的方法告诉了实验室师兄，并从他那里得到了一些新的建议。智力值增加了1点", 1, 1, 0);
			ForestMapEvent[18] = new ResearchMapEvent(113, "debuging",
				"艰难的debug···半天时间就这么过去了。智力值增加了0.5点", 0.5, 1, 0);
			ForestMapEvent[19] = new ResearchMapEvent(120, "我终于完成了新物理方法的开发",
				"在多次失败之后，我终于把完成了新物理方法的开发和测试，我可以开始撰写论文了", 1, 1, 999);

			//火山地图（热工方向）

			//冰原地图（模拟方向）
			IceBorneMapEvent[0] = new ResearchMapEvent(301, "压水堆功率分布",
				"我今天阅读了一篇分析压水堆功率分布的中文文献。智力值提高了0.5", 0.5, 3, 0);
			IceBorneMapEvent[1] = new ResearchMapEvent(302, "机器学习应用",
				"我今天阅读了一篇利用机器学习方法预测反应堆功率变化的英文文献。智力值提高了1", 0.5, 3, 0);
			IceBorneMapEvent[2] = new ResearchMapEvent(303, "群常数计算",
				"我今天阅读了一篇计算群常数的英文文献。智力值提高了0.5", 0.5, 3, 0);
			IceBorneMapEvent[3] = new ResearchMapEvent(304, "氙浓度变化",
				"我今天阅读了一篇分析停堆后堆内氙浓度变化的英文文献。智力值提高了0.5", 0.5, 3, 0);
			IceBorneMapEvent[4] = new ResearchMapEvent(305, "瞬态分析",
				"我今天阅读了一篇分析提出控制棒瞬间反应性变化的英文文献。智力值提高了0.5", 0.5, 3, 0);
			IceBorneMapEvent[5] = new ResearchMapEvent(306, "高温堆建模方法",
				"我今天阅读了一篇研究高温堆内燃料球建模方法的中文文献。智力值提高了0.5", 0.5, 3, 0);
			IceBorneMapEvent[6] = new ResearchMapEvent(307, "沸水堆安全性分析",
				"我今天阅读了一篇分析沸水堆安全性的中文文献。智力值提高了0.5", 0.5, 3, 0);
			IceBorneMapEvent[7] = new ResearchMapEvent(308, "随机中子动力学",
				"我今天阅读了一篇研究随机中子动力学的英文文献。智力值提高了0.5", 0.5, 3, 0);
			IceBorneMapEvent[8] = new ResearchMapEvent(309, "SPERT实验堆模拟",
				"我今天阅读了一篇对SPERT建模并比较瞬态过程的英文文献。智力值提高了0.5", 0.5, 3, 0);
			IceBorneMapEvent[9] = new ResearchMapEvent(310, "事故建模",
				"我今天阅读了一篇分析压水堆事故情况下放射性泄漏情况的英文文献。智力值提高了0.5", 0.5, 3, 0);
			IceBorneMapEvent[10] = new ResearchMapEvent(311, "对压水堆建模",
				"我今天成功地对秦山核电站的压水堆进行了建模，但还没有计算出结果。智力值提高了0.5", 0.5, 3, 312);
			IceBorneMapEvent[11] = new ResearchMapEvent(312, "压水堆计算结果错误",
				"我计算出来的结果和实际不符合，这令我很失望，是否应该换个方向呢？", 0.5, 3, 313);
			IceBorneMapEvent[12] = new ResearchMapEvent(313, "压水堆计算结果正确",
				"事实证明，我决定坚持下来是正确的，我最终得到了正确的答案。智力值提高了1", 0.5, 3, 0);
			IceBorneMapEvent[13] = new ResearchMapEvent(314, "引入人工智能",
				"我在阅读了相关文献后，计划将当前最火的人工智能应用在反应堆的分析中，我在心里想了想该怎么实现。智力值提高了0.5", 0.5, 3, 315);
			IceBorneMapEvent[14] = new ResearchMapEvent(315, "尝试实现人工智能算法",
				"虽然想的很美，但是要把人工智能算法放在计算框架里并不容易，预测得到的结果和真实值偏离很大", 0.5, 3, 318);
			IceBorneMapEvent[15] = new ResearchMapEvent(316, "实现人工智能算法",
				"在无数次调参数之后，我的模型终于为我生成了正确的结果！智力值提高了1", 0.5, 3, 0);
			IceBorneMapEvent[16] = new ResearchMapEvent(317, "编写小型组件算例",
				"我接到任务，编写了一个小型的组件模型并对它进行了分析，对我的项目没有什么影响。智力值提高了0.5", 0.5, 3, 0);
			IceBorneMapEvent[17] = new ResearchMapEvent(318, "改变人工智能模型",
				"在多次调整参数未果后，我把人工智能模型从原来的决策树改成了神经网络。", 0.5, 3, 316);
			IceBorneMapEvent[18] = new ResearchMapEvent(319, "debuging",
				"艰难的debug···半天时间就这么过去了。智力值提高了0.5", 0.5, 3, 0);
			IceBorneMapEvent[0] = new ResearchMapEvent(320, "我终于实现了一种新的分析方法",
				"在这么多次尝试之后，我终于把人工智能模型放入了反应堆实时分析的框架中，我想我可以为此写篇paper了。智力值提高了1", 0.5, 3, 0);

			//沙漠地图（新堆型设计方向）
			DesertMapEvent[0] = new ResearchMapEvent(401, "熔盐堆堆芯设计",
				"我今天阅读了一篇关于熔盐堆堆芯设计的英文文献。智力值提高了0.5", 0.5, 4, 0);
			DesertMapEvent[1] = new ResearchMapEvent(402, "钠冷快堆结构",
				"我今天阅读了一篇关于钠冷快堆结构的中文文献，对钠冷快堆有了基本的了解。智力值提高了0.5", 0.5, 4, 0);
			DesertMapEvent[2] = new ResearchMapEvent(403, "钠冷快堆燃料",
				"我今天阅读了一篇关于钠冷快堆燃料的英文文献。智力值提高了0.5", 0.5, 4, 0);
			DesertMapEvent[3] = new ResearchMapEvent(404, "铅冷快堆传热设计",
				"我今天阅读了一篇关于铅冷快堆传热设计的英文文献。智力值提高了0.5", 0.5, 4, 0);
			DesertMapEvent[4] = new ResearchMapEvent(405, "熔盐堆安全设计",
				"我今天阅读了一篇关于熔盐堆能动和非能动安全设计的英文文献。智力值提高了0.5", 0.5, 4, 0);
			DesertMapEvent[5] = new ResearchMapEvent(406, "铅冷快堆模拟",
				"我今天阅读了一篇对铅冷快堆建模并分析的英文文献。智力值提高了1", 0.5, 4, 0);
			DesertMapEvent[6] = new ResearchMapEvent(407, "学习XMC的使用",
				"我今天学习了如何反应堆计算程序XMC建模并计算。智力值提高了0.5", 0.5, 4, 0);
			DesertMapEvent[7] = new ResearchMapEvent(408, "铅冷快堆安全设计",
				"我今天阅读了一篇关于铅冷快堆非能动安全设计的英文文献。智力值提高了0.5", 0.5, 4, 0);
			DesertMapEvent[8] = new ResearchMapEvent(409, "反应堆设计原理",
				"我今天阅读了一本反应堆设计原理的专著的部分章节。智力值提高了1", 0.5, 4, 0);
			DesertMapEvent[9] = new ResearchMapEvent(410, "裂变-聚变混合堆设计",
				"我今天阅读了一篇关于裂变-聚变混合堆设计的文献，但感觉它和我的课题关系不大", 0.5, 4, 0);
			DesertMapEvent[10] = new ResearchMapEvent(411, "设计铅冷快堆的堆芯结构",
				"我今天完成了铅冷快堆堆芯结构的设计。智力值提高了0.5", 0.5, 4, 412);
			DesertMapEvent[11] = new ResearchMapEvent(412, "用XMC给铅冷快堆建模",
				"我今天根据之前的设计参数，用XMC程序给铅冷快堆建模并开始了计算。智力值提高了0.5", 0.5, 4, 413);
			DesertMapEvent[12] = new ResearchMapEvent(413, "得到了初次计算结果",
				"计算的结果出来了···我的天，系统根本达不到临界水平。是否要换个堆型看看？", 0, 4, 414);
			DesertMapEvent[13] = new ResearchMapEvent(414, "重新建模",
				"我认真查看了之前建立的模型的结构，对堆芯尺寸和装料的量都做了调整。智力值提高了0.5", 0.5, 4, 415);
			DesertMapEvent[14] = new ResearchMapEvent(415, "得到了可靠的计算结果",
				"经过重新建模之后，终于让这个堆临界了···但我知道，要想真正让这种堆型能够商用，实验室的分析远远不够。智力值提高了1", 0.5, 4, 416);
			DesertMapEvent[15] = new ResearchMapEvent(416, "对模型进行燃耗计算",
				"我之前设计的模型还只是达到了临界，我还需要对它进行燃耗计算。智力值提高了0.5", 0.5, 4, 417);
			DesertMapEvent[16] = new ResearchMapEvent(417, "加入热工反馈",
				"在燃耗计算之后，我想还需要加入热工反馈，才可以判断它在真实条件下能否实现。智力值提高了0.5", 0.5, 4, 420);
			DesertMapEvent[17] = new ResearchMapEvent(418, "和实验室师兄交流",
				"我把自己的一些想法和实验室的师兄进行了交流，但师兄认为它们都很幼稚，我受到了打击。", 0, 4, 419);
			DesertMapEvent[18] = new ResearchMapEvent(419, "debuging",
				"艰难的debug···半天时间就这么过去了。智力值提高了0.5", 0.5, 4, 0);
			DesertMapEvent[19] = new ResearchMapEvent(420, "我的设计被核研院看中了",
				"我基于获得的灵感，又经过这许多努力，设计了一种新的快堆堆型，我的设计得到了核研院的认可。智力值提高了1", 0.5, 4, 0);

		}

		//目前计划设计120个科研地图事件，其中基础地图对应40个，其余四个分支地图各包含20个（这样的话，可能会有重复）
		public ResearchMapEvent[] BasisMapEvent = new ResearchMapEvent[40];
		public ResearchMapEvent[] DesertMapEvent = new ResearchMapEvent[20];
		public ResearchMapEvent[] ForestMapEvent = new ResearchMapEvent[20];
		public ResearchMapEvent[] IceBorneMapEvent = new ResearchMapEvent[20];
		public ResearchMapEvent[] VolcanoMapEvent = new ResearchMapEvent[20];

		public class ResearchMapEvent implements Serializable{
			/**得到的
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public int id = 0;
			public String title = "";
			public String content = "";
			public double effect = 0;  //对智力值的提高
			public int classin = 0;  
			public int eventafter = 0; //该事件之后可能发生的后续事件编号，无后续事件为0，表示研究结束为999.
			//class 表示该文献属于哪一类，其中0表示基础文献，1表示和反应堆物理理论有关的文献，2表示和反应堆热工模拟有关的文献，
			//    3表示和反应堆模拟有关的文献，4表示和反应堆设计有关的文献

			//地图上事件类的构造函数
			public ResearchMapEvent(int id0, String title0, String content0, double effect0, int classin0, int eventafter0){
				id = id0;
				title = title0;
				content = content0;
				effect = effect0;
				classin = classin0;
				eventafter = eventafter0;
			}
		}
}
