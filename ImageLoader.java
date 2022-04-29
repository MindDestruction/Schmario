import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;

import javax.imageio.ImageIO;

public class ImageLoader {

  public static void render(Graphics g, BufferedImage img, int x, int y, int width, int height) {
    g.drawImage(img, x, y, width, height, null);
  }
}