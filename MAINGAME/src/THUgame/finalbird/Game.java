package THUgame.finalbird;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinExam;
//需要初始化的时候更改maingame和datapackage
public class Game {

    public static final int PIPE_DELAY = 100;
    
    private Boolean paused;
	static public EventManager mainGame;
	static public DataPack dataPackage;
    private int pauseDelay;
    private int restartDelay;
    private int pipeDelay;
    static public int revive;
    
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private Keyboard keyboard;

    static public int score;
    public int distance;
    public Boolean gameover;
    public Boolean started;
    public ExamPanel gamepanel;
    public Game(ExamPanel G,EventManager mainGame,DataPack dataPackage) {
    	this.mainGame=mainGame;
    	this.dataPackage=dataPackage;
    	score = 0;
    	gamepanel = G;
    	revive = Game.dataPackage.studyProgress / 25 + 1;
        keyboard = Keyboard.getInstance();
        restart();
        
    }

    public void restart() {
        paused = false;
        started = false;
        gameover = false;        
        pauseDelay = 0;
        restartDelay = 0;
        pipeDelay = 0;
        //通过调用datapackage中的学习进度影响distance,建议直接=studyProgress*0.75+175，distance应该被初始化成175-250之间
    	distance = Game.dataPackage.studyProgress * 1/2 + 175;
        bird = new Bird();
        pipes = new ArrayList<Pipe>();
    }

    public void update() {
        watchForStart();

        if (!started)
            return;

        watchForPause();
        watchForReset();

        if (paused)
            return;

        bird.update();

        if (gameover)
            return;

        movePipes();
        checkForCollisions();
    }

    public ArrayList<Render> getRenders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(new Render(0, 0, "src/imgsrc/FinalBird/background2 (2).png"));
        for (Pipe pipe : pipes)
            renders.add(pipe.getRender());
        renders.add(new Render(0, 100, "src/imgsrc/FinalBird/foreground.png"));
        renders.add(bird.getRender());
        return renders;
    }

    private void watchForStart() {
       if ((!started) && (keyboard.isDown(KeyEvent.VK_SPACE))) {
            started = true;
        }
    }

    private void watchForPause() {
        if (pauseDelay > 0)
            pauseDelay--;
        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }

    private void watchForReset() {
        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
        	if(revive <=0)
        		return;
        		
            revive -- ;
            restart();
            restartDelay = 10;
            return;
        }
    }


	private void movePipes() {
        pipeDelay--;

        if (pipeDelay < 0) {
            pipeDelay = PIPE_DELAY;
            Pipe northPipe = null;
            Pipe southPipe = null;

            // Look for pipes off the screen
            for (Pipe pipe : pipes) {
                if (pipe.x - pipe.width < 0) {
                    if (northPipe == null) {
                        northPipe = pipe;
                    } else if (southPipe == null) {
                        southPipe = pipe;
                        break;
                    }
                }
            }

            if (northPipe == null) {
                Pipe pipe = new Pipe("north");
                pipes.add(pipe);
                northPipe = pipe;
            } else {
                northPipe.reset();
            }

            if (southPipe == null) {
                Pipe pipe = new Pipe("south");
                pipes.add(pipe);
                southPipe = pipe;
            } else {
                southPipe.reset();
            }

            northPipe.y = southPipe.y + southPipe.height + distance;

        }

        for (Pipe pipe : pipes) {
            pipe.update();
        }
    }

    private void checkForCollisions() {

        for (Pipe pipe : pipes) {
            if (pipe.collides(bird.x, bird.y, bird.width, bird.height)) {
                gameover = true;
                bird.dead = true;
            } else if ((pipe.x - bird.x)==1 && pipe.orientation.equalsIgnoreCase("south")) {
                score+=3;
                distance -= 5;
            }
        }

        // 地面和鸟的撞击
        if (bird.y + bird.height > WinExam.HEIGHT - 200) {
            gameover = true;
            bird.y = WinExam.HEIGHT - 200 - bird.height;
        }
        
    }
}
