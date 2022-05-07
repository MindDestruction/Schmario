/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 05.11.2021
  * @author 
  */
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Game implements Runnable {

  private Display display;
  public int width, height;
  public String title;
  private Game game;
  private Map map;
  private Player player;
  private Inventory inventory;
  
  private boolean running = false;
  private Thread thread;
  
  private BufferStrategy bs;
  private Graphics g;
  
  //Input
  private KeyManager keyManager;
  
  public Game(String title, int width, int height){
    this.width = width;
    this.height = height;
    this.title = title;
    this.game = this;
    keyManager = new KeyManager();
  }
  
  
  
  public Map getMap() {
    return this.map;
  }
  
  
  
  private void init(){
    display = new Display(title, width, height);
    display.getFrame().addKeyListener(keyManager);
    
    try {
      Assets.init();
    } catch(IOException e) {
      e.printStackTrace(); 
    }
    
    inventory = new Inventory();

    map = new Map();
    map.createRooms();
    
    player = new Player(this.game, this.map, this.inventory, map.getStartX() * map.getTILE_WIDTH_AND_HEIGHT(), map.getStartY() * map.getTILE_WIDTH_AND_HEIGHT());
  }
  
  private void tick(){
    keyManager.tick();  
    map.tick();
    player.tick();
    inventory.tick();
  }
  
  private void render(){
    bs = display.getCanvas().getBufferStrategy();
    if(bs == null){
      display.getCanvas().createBufferStrategy(3);
      return;
    }
    g = bs.getDrawGraphics();
    //Clear Screen
    g.clearRect(0, 0, width, height);
    
    map.render(g);
    player.render(g);
    inventory.render(g);
    
    bs.show();
    g.dispose();
  }
  
  public void run(){
    
    init();
    map.createRooms();
    
    int fps = 60;
    double timePerTick = 1000000000 / fps;
    double delta = 0;
    long now;
    long lastTime = System.nanoTime();
    long timer = 0;
    int ticks = 0;
    
    while(running){
      now = System.nanoTime();
      delta += (now - lastTime) / timePerTick;
      timer += now - lastTime;
      lastTime = now;
      
      if(delta >= 1){
        tick();
        render();
        ticks++;
        delta--;
      }
      
      if(timer >= 1000000000){
        System.out.println("Ticks and Frames: " + ticks);
        ticks = 0;
        timer = 0;
      }
    }
    
    stop();
    
  }
  
  public KeyManager getKeyManager(){
    return keyManager;
  }
  
  public synchronized void start(){
    if(running)
      return;
    running = true;
    thread = new Thread(this);
    thread.start();
  }
  
  public synchronized void stop(){
    if(!running)
      return;
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
}
