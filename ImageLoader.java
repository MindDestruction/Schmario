import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class ImageLoader {

  public static void render(Graphics g, BufferedImage img, int x, int y, int width, int height) {
    g.drawImage(img, x, y, width, height, null);
  }
}