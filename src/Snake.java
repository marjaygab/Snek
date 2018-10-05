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
    Blk_Coordinates head;
    ArrayList<Blk_Coordinates> blocks;
    ActionListener gameTime = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        }
    };
    javax.swing.Timer timer = new Timer(1000,gameTime);

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
            g.setColor(Color.RED);
            g.fillRect(blocks.get(i).x,blocks.get(i).y,10,10);
            System.out.println("i: " +i+ " " + "x: " +blocks.get(i).x+ " " + "y: " +blocks.get(i).y);
        }

        g.setColor(Color.BLUE);
        g.fillRect(foodx,foody,10,10);

        if((blocks.get(0).x-10 == foodx || blocks.get(0).x+10 == foodx) && ((blocks.get(0).y >=foody-8 && blocks.get(0).y <= foody+8) || blocks.get(0).y==foody)){
            foodx = this.randomize();
            foody = this.randomize();

        }else if((blocks.get(0).y-10 == foody || blocks.get(0).y+10 == foody) && ((blocks.get(0).x >=foodx-8 && blocks.get(0).x <= foodx+8) || blocks.get(0).x==foodx)){
            foodx = this.randomize();
            foody = this.randomize();
        }


    }

    public void redraw(){
        this.repaint();
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
                            int temp = blocks.get(0).x;
                            Collections.rotate(blocks,1);
                            blocks.get(0).x = temp + 10;
                        redraw();
                    }
                }
            };
            timer = new Timer(10,gameTime);
            timer.start();
        }else if (keyEvent.getKeyCode() == 37){
            timer.stop();
            gameTime = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(blocks.get(0).x <=0){
                        timer.stop();
                    }else {
                        int temp = blocks.get(0).x;
                            Collections.rotate(blocks,1);
                            blocks.get(0).x= temp - 10;

                        redraw();
                    }
                }
            };
            timer = new Timer(10,gameTime);
            timer.start();
        }else if (keyEvent.getKeyCode() == 38){
            timer.stop();
            gameTime = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(blocks.get(0).y <=0){
                        timer.stop();
                    }else {
                        int temp = blocks.get(0).y;
                        Collections.rotate(blocks,1);
                            blocks.get(0).y= temp - 10;

                        redraw();
                    }
                }
            };
            timer = new Timer(10,gameTime);
            timer.start();
        }else if (keyEvent.getKeyCode() == 40){
            timer.stop();
            gameTime = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(blocks.get(0).y >=500){
                        timer.stop();
                    }else {
                        int temp = blocks.get(0).y;
                        Collections.rotate(blocks,1);
                            blocks.get(0).y= temp + 10;

                        redraw();
                    }
                }
            };
            timer = new Timer(10,gameTime);
            timer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        timer.stop();
    }

    public int randomize(){
        int value = 0;
        Random random = new Random();
        value = random.nextInt(500/move_interval) * move_interval;

        return value;
    }
}
