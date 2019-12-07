package THUgame.subwindows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;

import THUgame.datapack.*;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinBase;

/*
 * update: 20191101
 * via: 江世航
 * 更新：科研的主界面，表示一张科研地图
 */

public class WinResearch extends WinBase{

	static private class demoMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		
		private JFrame frame;
		private JButton button;
		private int mode;
		
		public int JudgeSelectValid(int i, int i_map) {
			if(3<=dataPackage.time && dataPackage.time<8) {
				dataPackage.notification="时间不早了，舍友早已睡着，我还是熄灯吧";
				return 0;
			}
			// 如果点击的区域是海域或已经被点击过，则返回无效
			switch(i_map) {
			case 0:
				if (dataPackage.characterEnergy<30) {
					dataPackage.notification="体力不足";
					return 0;
				}
			case 1:
			case 2:
			case 3:
			case 4:
				if (dataPackage.characterEnergy<55) {
					dataPackage.notification="体力不足";
					return 0;
				}
			}
			switch(i_map) {
			case 0:
				if (dataPackage.researchDataPackage.BasisMap[i] == 10 || dataPackage.researchDataPackage.BasisMap[i] == 0) {
					dataPackage.notification="什么东西";
					return 0;
				}
				break;
			case 1:
				if (dataPackage.researchDataPackage.DesertMap[i] == 10 || dataPackage.researchDataPackage.DesertMap[i] == 0) {
					dataPackage.notification="什么东西";
					return 0;
				}
				break;
			case 2:
				if (dataPackage.researchDataPackage.ForestMap[i] == 10 || dataPackage.researchDataPackage.ForestMap[i] == 0) {
					dataPackage.notification="什么东西";
					return 0;
				}
				break;
			case 3:
				if (dataPackage.researchDataPackage.IceBorneMap[i] == 10 || dataPackage.researchDataPackage.IceBorneMap[i] == 0) {
					dataPackage.notification="什么东西";
					return 0;
				}
				break;
			case 4:
				if (dataPackage.researchDataPackage.VolcanoMap[i] == 10 || dataPackage.researchDataPackage.VolcanoMap[i] == 0) {
					dataPackage.notification="什么东西";
					return 0;
				}
				break;
			}

			int i_valid = 0;
			// 判断点击区域周围的区域是否已经被点击
			switch(i_map) {
			case 0:
				if (i%7 != 0) {
					if (dataPackage.researchDataPackage.BasisMap[i-1] == 0)
						i_valid = 1;
				}
				if (i%7 != 6) {
					if (dataPackage.researchDataPackage.BasisMap[i+1] == 0)
						i_valid = 1;
				}
				if (i/7 != 0) {
					if (dataPackage.researchDataPackage.BasisMap[i-7] == 0)
						i_valid = 1;
				}
				if (i/7 != 4) {
					if (dataPackage.researchDataPackage.BasisMap[i+7] == 0)
						i_valid = 1;
				}
				break;
			case 1:
				if (i%7 != 0) {
					if (dataPackage.researchDataPackage.DesertMap[i-1] == 0)
						i_valid = 1;
				}
				if (i%7 != 6) {
					if (dataPackage.researchDataPackage.DesertMap[i+1] == 0) {
						i_valid = 1;
						}
				}
				if (i/7 != 0) {
					if (dataPackage.researchDataPackage.DesertMap[i-7] == 0)
						i_valid = 1;
				}
				if (i/7 != 4) {
					if (dataPackage.researchDataPackage.DesertMap[i+7] == 0)
						i_valid = 1;
				}
				break;
			case 2:
				if (i%7 != 0) {
					if (dataPackage.researchDataPackage.ForestMap[i-1] == 0)
						i_valid = 1;
				}
				if (i%7 != 6) {
					if (dataPackage.researchDataPackage.ForestMap[i+1] == 0)
						i_valid = 1;
				}
				if (i/7 != 0) {
					if (dataPackage.researchDataPackage.ForestMap[i-7] == 0)
						i_valid = 1;
				}
				if (i/7 != 4) {
					if (dataPackage.researchDataPackage.ForestMap[i+7] == 0)
						i_valid = 1;
				}
				break;
			case 3:
				if (i%7 != 0) {
					if (dataPackage.researchDataPackage.IceBorneMap[i-1] == 0)
						i_valid = 1;
				}
				if (i%7 != 6) {
					if (dataPackage.researchDataPackage.IceBorneMap[i+1] == 0)
						i_valid = 1;
				}
				if (i/7 != 0) {
					if (dataPackage.researchDataPackage.IceBorneMap[i-7] == 0)
						i_valid = 1;
				}
				if (i/7 != 4) {
					if (dataPackage.researchDataPackage.IceBorneMap[i+7] == 0)
						i_valid = 1;
				}
				break;
			case 4:
				if (i%7 != 0) {
					if (dataPackage.researchDataPackage.VolcanoMap[i-1] == 0)
						i_valid = 1;
				}
				if (i%7 != 6) {
					if (dataPackage.researchDataPackage.VolcanoMap[i+1] == 0)
						i_valid = 1;
				}
				if (i/7 != 0) {
					if (dataPackage.researchDataPackage.VolcanoMap[i-7] == 0)
						i_valid = 1;
				}
				if (i/7 != 4) {
					if (dataPackage.researchDataPackage.VolcanoMap[i+7] == 0)
						i_valid = 1;
				}
				break;
			}
			return i_valid;
			
		}
		
		public demoMouseListener(int i){
			this.mode=i;
		}
		
		public void setFrame(JFrame frame) {
			this.frame=frame;
		}
		
		public void setButton(JButton button) {
			this.button=button;
		}
		
		public void setMode(int mode) {
			this.mode=mode;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			/*		START OF YOUR CODE		*/
			if(safeGuardCount==0) {
				safeGuardCount++;
			if(mode==99){
				dataPackage.choiceA="back";
			}
			else if(mode==101) {
				dataPackage.choiceA="confirmTool";
			}
			else if(mode==102) {
				dataPackage.choiceA="confirmThesis";
			}
			else if(mode==105) {
				dataPackage.choiceA="confirmHelp";
			}
			else if(mode==999) {
				dataPackage.choiceA="FinishResearch";
			}
			
			// 判断点击的方格是否有效，如果无效则返回
			else if(JudgeSelectValid(mode, dataPackage.researchDataPackage.i_map) != 1) {
				return;
			}
			// 把点击的方格变成绿色，表示该文献已经阅读
			else {
				if (dataPackage.researchDataPackage.i_map == 1) {
					if (dataPackage.researchDataPackage.DesertMap[mode]==2) {
						dataPackage.researchDataPackage.DesertMap[mode] = 0;
						dataPackage.researchDataPackage.ThesisOrResearch = 0;
						}
					else if(dataPackage.researchDataPackage.hasJeep) {
						dataPackage.researchDataPackage.DesertMap[mode] = 0;
						dataPackage.researchDataPackage.ThesisOrResearch = 1;
					}
					else {
						dataPackage.notification = "我还没有获得越野车";
						return;
					}
				}
				else if (dataPackage.researchDataPackage.i_map == 2) {
					if (dataPackage.researchDataPackage.ForestMap[mode]==4) {
						dataPackage.researchDataPackage.ForestMap[mode] = 0;
						dataPackage.researchDataPackage.ThesisOrResearch = 0;
					}
					else if(dataPackage.researchDataPackage.hasGun) {
						dataPackage.researchDataPackage.ForestMap[mode] = 0;
						dataPackage.researchDataPackage.ThesisOrResearch = 1;
					}
					else {
						dataPackage.notification = "我还没有获得猎枪";
						return;
					}
				}
				else if (dataPackage.researchDataPackage.i_map == 3){
					if (dataPackage.researchDataPackage.IceBorneMap[mode]==6) {
						dataPackage.researchDataPackage.IceBorneMap[mode] = 0;
						dataPackage.researchDataPackage.ThesisOrResearch = 0;
					}
					else if(dataPackage.researchDataPackage.hasSled) {
						dataPackage.researchDataPackage.IceBorneMap[mode] = 0;
						dataPackage.researchDataPackage.ThesisOrResearch = 1;
					}
					else {
						dataPackage.notification = "我还没有获得雪橇";
						return;
					}
				}
				else if (dataPackage.researchDataPackage.i_map == 4){
					if (dataPackage.researchDataPackage.VolcanoMap[mode]==8) {
						dataPackage.researchDataPackage.VolcanoMap[mode] = 0;
						dataPackage.researchDataPackage.ThesisOrResearch = 0;
					}
					else if(dataPackage.researchDataPackage.hasInsulationSuit) {
						dataPackage.researchDataPackage.VolcanoMap[mode] = 0;
						dataPackage.researchDataPackage.ThesisOrResearch = 1;
					}
					else {
						dataPackage.notification = "我还没有获得隔热服";
						return;
					}
				}
				if (mode == 4 && dataPackage.researchDataPackage.i_map == 0)
					dataPackage.choiceA="Desertmap";
				else if (mode == 6 && dataPackage.researchDataPackage.i_map == 0) {
					dataPackage.choiceA="Forestmap";
				}
				else if (mode == 20 && dataPackage.researchDataPackage.i_map == 0) {
					dataPackage.choiceA="IceBornemap";
				}
				else if (mode == 34 && dataPackage.researchDataPackage.i_map == 0) {
					dataPackage.choiceA="Volcanomap";
				}
				else { 
					dataPackage.researchDataPackage.BasisMap[mode] = 0;
					dataPackage.choiceA="RandomEffect";
				}
			}
			}
			
			/*		END OF YOUR CODE		*/
			synchronized(mainGame) {
				mainGame.notify();
			}
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}	
	
	public void setFieldButtonIcon(int i, JButton button) {
		if (i==0) {
			setIcon("/imgsrc/WinResearch/Discovered.png", button);
		}
		else if (i==1) {
			setIcon("/imgsrc/WinResearch/BasisNormal.png", button);
		}
		else if (i==2) {
			setIcon("/imgsrc/WinResearch/DesertNormal.png", button);
		}
		else if (i==3) {
			setIcon("/imgsrc/WinResearch/DesertHard.png", button);
		}
		else if (i==4) {
			setIcon("/imgsrc/WinResearch/ForestNormal.png", button);
		}
		else if (i==5) {
			setIcon("/imgsrc/WinResearch/ForestHard.png", button);
		}
		else if (i==6) {
			setIcon("/imgsrc/WinResearch/IceBorneNormal.png", button);
		}
		else if (i==7) {
			setIcon("/imgsrc/WinResearch/IceBorneHard.png", button);
		}
		else if (i==8) {
			setIcon("/imgsrc/WinResearch/VolcanoNormal.png", button);
		}
		else if (i==9) {
			setIcon("/imgsrc/WinResearch/VolcanoHard.png", button);
		}
		else if (i==10) {
			setIcon("/imgsrc/WinResearch/Sea.png", button);
		}
	}
	
	public WinResearch(EventManager mainGame, JFrame frame) {
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				this.mainGame=mainGame;
				//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
				/*************************************************************	
				 *【背景镶板】
				 *************************************************************/
				JPanel backgroundPanel=new JPanel();
				backgroundPanel.setBackground(Color.WHITE);
				backgroundPanel.setBounds(0, 0, 1080, 720);
				backgroundPanel.setLayout(null);
				
				/*************************************************************
				 * 【小事件提示框】
				 */
				JPanel guidePanel = new JPanel(); //在首次进入科研地图的时候提示玩家如何做
				guidePanel.setBackground(new Color(255, 255, 204));
				guidePanel.setBounds(253, 129, 536, 398);
				guidePanel.setLayout(null);
				
					JPanel guideBackgroundImage = new ImagePanel("imgsrc//shootGame/eb.png",0, 0, 536, 398);
					guideBackgroundImage.setOpaque(false);
					guideBackgroundImage.setBounds(0, 0, 536, 398);
					guideBackgroundImage.setLayout(null);
					
					JLabel guideLabel = new JLabel("<html>恭喜你，终于踏上科研之路,欢迎在科研岛上探索。"
							+ "浅绿色的区域表示你已阅读的文献/进行的研究，点击周围的区域来阅读文献</html>");
					guideLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
					guideLabel.setBounds(63, 107, 400, 136);
					guidePanel.add(guideLabel);
					
					JButton HelpConfirmButton = new JButton("确认");
					HelpConfirmButton.setBounds(190, 272, 190, 47);
					guidePanel.add(HelpConfirmButton);
					
					guidePanel.add(guideBackgroundImage);
				
				if(dataPackage.researchDataPackage.firstEnter==true)
					backgroundPanel.add(guidePanel);
				
				JPanel noticePanelforThesis = new JPanel();//在读完一篇文章后显示的小事件框
				noticePanelforThesis.setBackground(new Color(255, 255, 204));
				noticePanelforThesis.setBounds(253, 129, 536, 398);
				noticePanelforThesis.setLayout(null);
					
					JPanel noticeBackgroundforThesis = new ImagePanel("imgsrc//shootGame/eb.png",0, 0, 536, 398);
					noticeBackgroundforThesis.setOpaque(false);
					noticeBackgroundforThesis.setBounds(0, 0, 536, 398);
					noticeBackgroundforThesis.setLayout(null);
				
					JLabel ThesisConfirmLabel = new JLabel("<html>"+dataPackage.notification+"</html>");
					ThesisConfirmLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
					ThesisConfirmLabel.setBounds(63, 107, 400, 68);
					noticePanelforThesis.add(ThesisConfirmLabel);
					
					JButton ThesisConfirmButton = new JButton("确认");
					ThesisConfirmButton.setBounds(190, 242, 190, 47);
					noticePanelforThesis.add(ThesisConfirmButton);
					
					noticePanelforThesis.add(noticeBackgroundforThesis);
				
				if(dataPackage.researchDataPackage.justReadThesis==true)
					backgroundPanel.add(noticePanelforThesis);
				
				JPanel noticePanelforTool = new JPanel();//在获得道具的时候显示的小事件框
				noticePanelforTool.setBackground(new Color(255, 255, 204));
				noticePanelforTool.setBounds(253, 129, 536, 398);
				noticePanelforTool.setLayout(null);
			
					JPanel noticeBackgroundforTool = new ImagePanel("imgsrc//shootGame/eb.png",0, 0, 536, 398);
					noticeBackgroundforTool.setOpaque(false);
					noticeBackgroundforTool.setBounds(0, 0, 536, 398);
					noticeBackgroundforTool.setLayout(null);
				
					JLabel lblNewLabel_1 = new JLabel("恭喜你获得了道具——"+dataPackage.researchDataPackage.tool);
					lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
					lblNewLabel_1.setBounds(63, 107, 400, 68);
					noticePanelforTool.add(lblNewLabel_1);
					
					JButton ToolConfirmButton = new JButton("确认");
					ToolConfirmButton.setBounds(190, 242, 190, 47);
					noticePanelforTool.add(ToolConfirmButton);
					
					noticePanelforTool.add(noticeBackgroundforTool);
				
				if(dataPackage.researchDataPackage.justGetTool==true)
					backgroundPanel.add(noticePanelforTool);
				
				JPanel noticePanelforFinish = new JPanel();//在研究结束，可以开始写论文时的提示
				noticePanelforFinish.setBackground(new Color(255, 255, 204));
				noticePanelforFinish.setBounds(253, 129, 536, 398);
				noticePanelforFinish.setLayout(null);
			
					JPanel noticeBackgroundforFinish = new ImagePanel("imgsrc//shootGame/eb.png",0, 0, 536, 398);
					noticeBackgroundforFinish.setOpaque(false);
					noticeBackgroundforFinish.setBounds(0, 0, 536, 398);
					noticeBackgroundforFinish.setLayout(null);
				
					JLabel FinishConfirmLabel = new JLabel("我可以开始写论文了");
					FinishConfirmLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
					FinishConfirmLabel.setBounds(63, 107, 400, 68);
					noticePanelforTool.add(FinishConfirmLabel);
					
					JButton FinishConfirmButton = new JButton("确认");
					FinishConfirmButton.setBounds(190, 242, 190, 47);
					noticePanelforTool.add(FinishConfirmButton);
					
					noticePanelforTool.add(noticeBackgroundforFinish);
				
				if(dataPackage.researchDataPackage.justGetTool==true)
					backgroundPanel.add(noticePanelforTool);
				
				/*************************************************************	
				 * 【镶对话框】
				 *************************************************************/
				JPanel dialogPack = new JPanel();
				dialogPack.setBounds(340, 475, 689, 189);
				dialogPack.setOpaque(false);//注意要设成透明的
				dialogPack.setLayout(null);
				
					JPanel dialogPanel = new JPanel();//第1个subPanel，放控件
					dialogPanel.setBounds(0, 0, 700, 189);//(0, 0, 宽, 高);
					
					JPanel dialogBackgoundPanel = new ImagePanel("imgsrc//Dialog.png",0, 0, 689, 189);	//第2个subPanel，放图
																										//(0, 0, 宽, 高);
					dialogBackgoundPanel.setBounds(0, 0, 689, 189);//(0, 0, 宽, 高);
					dialogBackgoundPanel.setOpaque(false);//注意要设成透明的
					dialogPanel.setOpaque(false);		//注意要设成透明的
					dialogPanel.setLayout(null);
					
					JLabel dialogName = new JLabel();
					dialogName.setBounds(17, 6, 89, 40);
					dialogPanel.add(dialogName);
					dialogName.setText("独白");
					
					JLabel dialogContent = new JLabel();
					dialogName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
					dialogContent.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
					dialogContent.setBounds(15, 42, 650, 141);
					dialogPanel.add(dialogContent);
					
					if (!dataPackage.notification.equals(""))//设置对话内容
						dialogContent.setText("<html>"+dataPackage.notification+"</html>");
					else
						dialogContent.setText("<html>我要开始读文献了～读文献是科研的起步</html>");//设置默认对话内容
				
				dialogPack.add(dialogPanel);		//注意：先放的在上层，所以先放带控件的
				dialogPack.add(dialogBackgoundPanel);
				backgroundPanel.add(dialogPack);
				
				/************************************************************
				 * 【道具区域图标】
				 ************************************************************/				
				if (dataPackage.researchDataPackage.hasAxe == true) {
				JButton toolButton = new JButton();
				toolButton.setBorderPainted(false);
				toolButton.setBounds(740,120,80,80);
				toolButton.setContentAreaFilled(false);
				setIcon("/imgsrc/WinResearch/axe.png", toolButton);
				backgroundPanel.add(toolButton);
					}
				if (dataPackage.researchDataPackage.hasGun == true) {
					JButton toolButton = new JButton();
					toolButton.setBorderPainted(false);
					toolButton.setBounds(860,120,80,80);
					toolButton.setContentAreaFilled(false);
					setIcon("/imgsrc/WinResearch/gun.png", toolButton);
					backgroundPanel.add(toolButton);
					}
				if (dataPackage.researchDataPackage.hasKettle == true) {
					JButton toolButton = new JButton();
					toolButton.setBorderPainted(false);
					toolButton.setBounds(740,220,80,80);
					toolButton.setContentAreaFilled(false);
					setIcon("/imgsrc/WinResearch/kettle.png", toolButton);
					backgroundPanel.add(toolButton);
					}
				if (dataPackage.researchDataPackage.hasJeep == true) {
					JButton toolButton = new JButton();
					toolButton.setBorderPainted(false);
					toolButton.setBounds(860,220,80,80);
					toolButton.setContentAreaFilled(false);
					setIcon("/imgsrc/WinResearch/jeep.png", toolButton);
					backgroundPanel.add(toolButton);
					}
				if (dataPackage.researchDataPackage.hasSnowBoots == true) {
					JButton toolButton = new JButton();
					toolButton.setBorderPainted(false);
					toolButton.setBounds(740,320,80,80);
					toolButton.setContentAreaFilled(false);
					setIcon("/imgsrc/WinResearch/snowboot.png", toolButton);
					backgroundPanel.add(toolButton);
					}
				if (dataPackage.researchDataPackage.hasSled == true) {
					JButton toolButton = new JButton();
					toolButton.setBorderPainted(false);
					toolButton.setBounds(860,320,80,80);
					toolButton.setContentAreaFilled(false);
					setIcon("/imgsrc/WinResearch/sled.png", toolButton);
					backgroundPanel.add(toolButton);
					}
				if (dataPackage.researchDataPackage.hasTrekkingPole == true) {
					JButton toolButton = new JButton();
					toolButton.setBorderPainted(false);
					toolButton.setBounds(740,420,80,80);
					toolButton.setContentAreaFilled(false);
					setIcon("/imgsrc/WinResearch/trekkingpole.png", toolButton);
					backgroundPanel.add(toolButton);
					}
				if (dataPackage.researchDataPackage.hasInsulationSuit == true) {
					JButton toolButton = new JButton();
					toolButton.setBorderPainted(false);
					toolButton.setBounds(860,420,80,80);
					toolButton.setContentAreaFilled(false);
					setIcon("/imgsrc/WinResearch/insulationsuit.png", toolButton);
					backgroundPanel.add(toolButton);
					}
				
				
				/*************************************************************	
				 *【地图区域按钮】
				 *************************************************************/
				
				JButton[] FieldButtonlist = new JButton[35];

				for (int i_y = 0; i_y < 5; i_y++) {
					for (int i_x = 0; i_x < 7; i_x++) {
						FieldButtonlist[i_x+7*i_y] = new JButton();
						FieldButtonlist[i_x+7*i_y].setBorderPainted(false);
						FieldButtonlist[i_x+7*i_y].setBounds(300+60*i_x, 120+60*i_y, 60, 60);
						FieldButtonlist[i_x+7*i_y].setContentAreaFilled(false);
						if(dataPackage.researchDataPackage.i_map == 0)
							setFieldButtonIcon(dataPackage.researchDataPackage.BasisMap[i_x+7*i_y], FieldButtonlist[i_x+7*i_y]);
						else if(dataPackage.researchDataPackage.i_map == 1)
							setFieldButtonIcon(dataPackage.researchDataPackage.DesertMap[i_x+7*i_y], FieldButtonlist[i_x+7*i_y]);
						else if(dataPackage.researchDataPackage.i_map == 2)
							setFieldButtonIcon(dataPackage.researchDataPackage.ForestMap[i_x+7*i_y], FieldButtonlist[i_x+7*i_y]);
						else if(dataPackage.researchDataPackage.i_map == 3)
							setFieldButtonIcon(dataPackage.researchDataPackage.IceBorneMap[i_x+7*i_y], FieldButtonlist[i_x+7*i_y]);
						else if(dataPackage.researchDataPackage.i_map == 4)
							setFieldButtonIcon(dataPackage.researchDataPackage.VolcanoMap[i_x+7*i_y], FieldButtonlist[i_x+7*i_y]);
						backgroundPanel.add(FieldButtonlist[i_x+7*i_y]);

					}
				}

				
				JButton backButton = new JButton("离开");
				backButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
				backButton.setBounds(150, 600, 80, 50);

				backgroundPanel.add(backButton);
				
				/*************************************************************	
				 * 【镶时钟】
				 *************************************************************/
				JPanel timePack = new JPanel();
				timePack.setLayout(null);
				timePack.setOpaque(false);//注意要设成透明的
				timePack.setBounds(66, 32, 195, 90);
				
					JPanel timePanel = new JPanel();
					timePanel.setBounds(0, 0, 195, 90);
					JPanel timeBackgoundPanel = new ImagePanel("imgsrc//taili.png",0, 0, 195, 90);	
					timeBackgoundPanel.setBounds(0, 0, 195, 90);
					
					timeBackgoundPanel.setOpaque(false);//注意要设成透明的
					timePanel.setOpaque(false);//注意要设成透明的
					timePanel.setLayout(null);
					
					JLabel timeText = new JLabel("当前时间为："+String.valueOf(dataPackage.time)+" 时");
					timeText.setBounds(6, 60, 172, 16);
					timePanel.add(timeText);
					
					JLabel dateText = new JLabel("今天是：第"+String.valueOf(dataPackage.term)+"学期"+String.valueOf(dataPackage.week)+"周"+String.valueOf(dataPackage.date)+"日");
					dateText.setBounds(6, 35, 172, 16);
					timePanel.add(dateText);
					
				timePack.add(timePanel);
				timePack.add(timeBackgoundPanel);
				backgroundPanel.add(timePack);
				/*************************************************************	
				 * 【镶属性】
				 *************************************************************/

				JPanel panel = new JPanel();
				panel.setBounds(66, 140, 197, 290);
				backgroundPanel.add(panel);
				panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
				panel.setLayout(null);
				
				JLabel StudentIDLable = new JLabel("学号");
				StudentIDLable.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
				StudentIDLable.setBounds(26, 78, 32, 16);
				panel.add(StudentIDLable);
				
				JTextPane nameShow = new JTextPane();
				nameShow.setEditable(false);
				nameShow.setBounds(84, 42, 73, 20);
				panel.add(nameShow);
				
				JLabel nameLable = new JLabel("姓名");
				nameLable.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				nameLable.setBounds(26, 42, 32, 24);
				panel.add(nameLable);
				
				JTextPane IDshow = new JTextPane();
				IDshow.setEditable(false);
				IDshow.setBounds(84, 76, 73, 20);
				panel.add(IDshow);
				
				JProgressBar healthBar = new JProgressBar();
				healthBar.setBounds(70, 119, 114, 20);
				panel.add(healthBar);
				
				JProgressBar Bar_progress = new JProgressBar();
				Bar_progress.setBounds(70, 139, 114, 20);
				panel.add(Bar_progress);
				
				JProgressBar Bar_Energy = new JProgressBar();
				Bar_Energy.setBounds(70, 165, 114, 20);
				panel.add(Bar_Energy);
				
				JProgressBar Bar_happiness = new JProgressBar();
				Bar_happiness.setBounds(70, 187, 114, 20);
				panel.add(Bar_happiness);
				
				JLabel label_workProgress = new JLabel("学习进度");
				label_workProgress.setBounds(10, 142, 52, 16);
				panel.add(label_workProgress);
				
				JLabel label_Energy = new JLabel("体力值");
				label_Energy.setBounds(10, 166, 52, 16);
				panel.add(label_Energy);
				
				JLabel label_health = new JLabel("健康值");
				label_health.setBounds(10, 118, 52, 16);
				panel.add(label_health);
				
				JLabel label_happy = new JLabel("心   情");
				label_happy.setBounds(10, 189, 52, 16);
				panel.add(label_happy);
				
				JLabel label_social = new JLabel("社交能力:");
				label_social.setBounds(10, 219, 92, 16);
				panel.add(label_social);
				
				
				JLabel label_Art = new JLabel("才艺能力:");
				label_Art.setBounds(10, 245, 92, 16);
				panel.add(label_Art);
				
				JLabel label_IQ = new JLabel("智商:");
				label_IQ.setBounds(100, 219, 84, 16);
				panel.add(label_IQ);
				
				JLabel label_lucky = new JLabel("幸运值:");
				label_lucky.setBounds(100, 245, 84, 16);
				panel.add(label_lucky);
					
				
				/*************************************************************	
				 * 【放背景图】
				 *************************************************************/

				switch(dataPackage.researchDataPackage.i_map) {
				case 0:
					JPanel Background=new ImagePanel("imgsrc//WinResearch/Basis.png",0, 0, 1080, 720);
					Background.setBounds(0, 0, 1080, 720);
					backgroundPanel.add(Background);
					Background.setLayout(null);
					break;
				case 1:
					JPanel Background1=new ImagePanel("imgsrc//WinResearch/Desert.png",0, 0, 1080, 720);
					Background1.setBounds(0, 0, 1080, 720);
					backgroundPanel.add(Background1);
					Background1.setLayout(null);
					break;
				case 2:
					JPanel Background2=new ImagePanel("imgsrc//WinResearch/Forest.png",0, 0, 1080, 720);
					Background2.setBounds(0, 0, 1080, 720);
					backgroundPanel.add(Background2);
					Background2.setLayout(null);
					break;
				case 3:
					JPanel Background3=new ImagePanel("imgsrc//WinResearch/IceBorne.png",0, 0, 1080, 720);
					Background3.setBounds(0, 0, 1080, 720);
					backgroundPanel.add(Background3);
					Background3.setLayout(null);
					break;
				case 4:
					JPanel Background4=new ImagePanel("imgsrc//WinResearch/Volcano.png",0, 0, 1080, 720);
					Background4.setBounds(0, 0, 1080, 720);
					backgroundPanel.add(Background4);
					Background4.setLayout(null);
					break;
				}
				
				
				
				

				/*****************************************************************				
				 * 【细节设定】
				 *****************************************************************/
				/*		START OF YOUR CODE		*/
				
				Bar_progress.setValue(dataPackage.studyProgress);
				Bar_Energy.setValue(dataPackage.characterEnergy);
				Bar_happiness.setValue(dataPackage.characterHappiness);
				healthBar.setValue(dataPackage.characterHealth);//进度条设置进度
				Bar_progress.setStringPainted(true);
				Bar_Energy.setStringPainted(true);//开启进度条显示字
				Bar_happiness.setStringPainted(true);
				healthBar.setStringPainted(true);
				Bar_progress.setString(String.format("%d",dataPackage.studyProgress));
				Bar_Energy.setString(String.format("%d",dataPackage.characterEnergy));
				Bar_happiness.setString(String.format("%d",dataPackage.characterHappiness));
				healthBar.setString(String.format("%d",dataPackage.characterHealth));//进度条显示字
				IDshow.setText(dataPackage.studentID);//显示学号
				nameShow.setText(dataPackage.name);//显示名字
				
				label_social.setText("社交能力:"+dataPackage.characterEQ);
				label_Art.setText("才艺能力:"+dataPackage.characterArt);
				label_IQ.setText("智商:"+dataPackage.characterIQ);
				label_lucky.setText("幸运值:"+dataPackage.characterlucky);

				/*********************************************			
				 * 【鼠标动作的设置】
				 ********************************************/
				demoMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
				demoMouseListener.mainGame=mainGame;
				
				demoMouseListener clickBack=new demoMouseListener(99);//设置鼠标监听器，发生99号事件
				demoMouseListener clickConfirmTool=new demoMouseListener(101);
				demoMouseListener clickConfirmThesis=new demoMouseListener(102);
				demoMouseListener clickConfirmHelp=new demoMouseListener(105);
				demoMouseListener clickConfirmFinish=new demoMouseListener(999);
				
				clickBack.setButton(backButton);
				
				demoMouseListener[] clickFieldButton = new demoMouseListener[35];
				for (int i_y = 0; i_y < 5; i_y++) {
					for (int i_x = 0; i_x < 7; i_x++) {	
						clickFieldButton[i_x+7*i_y] = new demoMouseListener(i_x+7*i_y);
						clickFieldButton[i_x+7*i_y].setButton(FieldButtonlist[i_x+7*i_y]);
						FieldButtonlist[i_x+7*i_y].addMouseListener(clickFieldButton[i_x+7*i_y]);

					}
				}
				

				backButton.addMouseListener(clickBack);//2号事件是 去上课按钮 被点击
				ToolConfirmButton.addMouseListener(clickConfirmTool); //返回事件
				ThesisConfirmButton.addMouseListener(clickConfirmThesis);
				HelpConfirmButton.addMouseListener(clickConfirmHelp);
				FinishConfirmButton.addMouseListener(clickConfirmFinish);

				/*		END OF YOUR CODE		*/
		    	    	
		    	/*****************************************************************				
				 * 【恢复显示】
				 * 必须存在的四行代码！！！！
				 ******************************************************************/
				//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
				frame.getContentPane().removeAll();
				frame.getContentPane().add(backgroundPanel);
				frame.getContentPane().repaint();
		    	frame.setVisible(true);
		    	//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	}
}
