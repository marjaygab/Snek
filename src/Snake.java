import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.awt.*;

public class Snake extends JPanel implements KeyListener {

    int xstart = 50;
    int ystart = 50;
    int xend = 40;
    int yend = 10;
    int foodx = 250;
    int foody = 250;
    int move_interval = 10;
    int game_speed = 200;

    Blk_Coordinates head;
    ArrayList<Blk_Coordinates> blocks;
    ActionListener gameTime = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        }
    };
    javax.swing.Timer timer = new Timer(game_speed,gameTime);

    public Snake(){
        blocks = new ArrayList<>();
         head = new Blk_Coordinates(xstart,ystart);
        timer.start();
        blocks.add(head);
        blocks.add(new Blk_Coordinates(xstart,ystart-10));
        blocks.add(new Blk_Coordinates(xstart,ystart-20));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i=0;i<blocks.size();i++){
            if(i==0){
                g.setColor(Color.GREEN);
            }else{
                g.setColor(Color.RED);
            }
            g.fillRect(blocks.get(i).x,blocks.get(i).y,10,10);
        }

        g.setColor(Color.BLUE);
        g.fillRect(foodx,foody,10,10);

        if(isCollided()){
            foodx = this.randomize();
            foody = this.randomize();
        }


    }

    public void redraw(){
        this.repaint();
    }
    public boolean isCollided(){
        if((blocks.get(0).x-10 == foodx || blocks.get(0).x+10 == foodx) && ((blocks.get(0).y >=foody-8 && blocks.get(0).y <= foody+8) || blocks.get(0).y==foody)){
            return true;

        }else if((blocks.get(0).y-10 == foody || blocks.get(0).y+10 == foody) && ((blocks.get(0).x >=foodx-8 && blocks.get(0).x <= foodx+8) || blocks.get(0).x==foodx)){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if (keyEvent.getKeyCode() == 39){
            timer.stop();

            gameTime = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(blocks.get(0).x >=500){
                        timer.stop();
                    }else{
                            int tempx = blocks.get(0).x;
                            int tempy = blocks.get(0).y;
                            int lasttempx = blocks.get(blocks.size()-1).x;
                            int lasttempy = blocks.get(blocks.size()-1).x;
                            Collections.rotate(blocks,1);
                            blocks.get(0).x = tempx + 10;
                            blocks.get(0).y = tempy;
                        if (isCollided()){
                            blocks.add(new Blk_Coordinates(lasttempx,lasttempy));
                        }
                        redraw();
                    }
                }
            };
            timer = new Timer(game_speed,gameTime);
            timer.start();
        }else if (keyEvent.getKeyCode() == 37){
            timer.stop();
            gameTime = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(blocks.get(0).x <=0){
                        timer.stop();
                    }else {
                        int tempx = blocks.get(0).x;
                        int tempy = blocks.get(0).y;
                        int lasttempx = blocks.get(blocks.size()-1).x;
                        int lasttempy = blocks.get(blocks.size()-1).x;
                            Collections.rotate(blocks,1);
                            blocks.get(0).x= tempx - 10;
                            blocks.get(0).y = tempy;
                        if (isCollided()){
                            blocks.add(new Blk_Coordinates(lasttempx,lasttempy));
                        }
                        redraw();
                    }
                }
            };
            timer = new Timer(game_speed,gameTime);
            timer.start();
        }else if (keyEvent.getKeyCode() == 38){
            timer.stop();
            gameTime = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(blocks.get(0).y <=0){
                        timer.stop();
                    }else {
                        int tempy= blocks.get(0).y;
                        int tempx = blocks.get(0).x;
                        int lasttempx = blocks.get(blocks.size()-1).x;
                        int lasttempy = blocks.get(blocks.size()-1).x;
                        Collections.rotate(blocks,1);
                            blocks.get(0).y= tempy - 10;
                            blocks.get(0).x = tempx;
                        if (isCollided()){
                            blocks.add(new Blk_Coordinates(lasttempx,lasttempy));
                        }
                        redraw();
                    }
                }
            };
            timer = new Timer(game_speed,gameTime);
            timer.start();
        }else if (keyEvent.getKeyCode() == 40){
            timer.stop();
            gameTime = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(blocks.get(0).y >=500){
                        timer.stop();
                    }else {
                        int tempy = blocks.get(0).y;
                        int tempx = blocks.get(0).x;
                        int lasttempx = blocks.get(blocks.size()-1).x;
                        int lasttempy = blocks.get(blocks.size()-1).x;

                        Collections.rotate(blocks,1);

                            blocks.get(0).y= tempy + 10;
                            blocks.get(0).x = tempx;
                            if (isCollided()){
                                blocks.add(new Blk_Coordinates(lasttempx,lasttempy));
                            }
                        redraw();
                    }
                }
            };
            timer = new Timer(game_speed,gameTime);
            timer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    public int randomize(){
        int value = 0;
        Random random = new Random();
        value = random.nextInt(500/move_interval) * move_interval;

        return value;
    }
}
