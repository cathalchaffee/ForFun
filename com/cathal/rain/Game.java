//package com.cathal.rain;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
  //Canvas is a blank "Canvas" rectangle
  //Game inherits Canvas

  //?
  private static final long serialVersionUID = 1L;

  //instances for visualization
  public static int width = 300;
  public static int height = width/16*9;
  //multiply width and height by scale. ?
  public static int scale = 3;

  //process within a process. Sub Process
  private Thread thread;
  //Window WINDOW
  private JFrame frame;
  //program running or not
  private boolean running = false;

  public Game() {
    //Makes the dimensions of the canvas
    Dimension size = new Dimension(width*scale, height*scale);
    //canvas method. extends component
    setPreferredSize(size);
    //New Frame
    frame = new JFrame();
  }

  //Synchronized? No overlaps IMPORTANT
  public synchronized void start() {
      running = true;
      thread = new Thread(this, "Display");
      thread.start();
  }

  //properly stop the thread
  public synchronized void stop() {
    running = false;
    try {
      thread.join();
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
  }

  //keep Rendering the image
  //two parts, 1. logic of the game, process
  //2. Rendering images
  //Frames persecond FPS is different for every comp
  // Faster comp will move faster if not delt with
  // Need Timer Part update 60 times per sec
  // Need Rendering render fast as you can
  public void run() {
    while(running) {
      update();
      render();
    }
  }

  public void update() {

  }

  //buffer: temporary storage place, rendered but not ready yet
  //Fast, if you render straight on the screen
  //does every pixel updated live, you see black screen with
  //Single pixels. We want fully rendered pictures
  //Thus buffering
  //we need to create an area to store this
  public void render() {
    BufferStrategy bs = getBufferStrategy();
    //retrive bufferstrat from canvas
    //now create it, but not every time, when no screen is made
    //three, always use 3, store two images in memory
    if(bs==null) {
      createBufferStrategy(3);
      return;
    }

    //creates link between graphics and buffer
    Graphics g = bs.getDrawGraphics();
    //this is where you do your graphics
    //get Width/Height() is part of Canvas
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());
    //disposes of the current graphics otherwise they stack
    g.dispose();
    //swap buffers, display next buffer
    bs.show();
  }

  public static void main(String[] args) {
    //Main method doe. Program starts here. The alpha.
    // static, no relation to the rest of the class
    Game game = new Game();
    //cant edit size
    game.frame.setResizable(false);
    //Name the game
    game.frame.setTitle("Rain");
    //adds a component to frame, fills window
    game.frame.add(game);
    //sets the size of the gaem
    game.frame.pack();
    //close button on the top right corner, terminate
    //Without it would keep running program
    game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //center the window in the screen
    game.frame.setLocationRelativeTo(null);
    //shows the window
    game.frame.setVisible(true);

    game.start();
  }

}
