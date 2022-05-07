public class Item {
  private String text;
  private String texture;
  private boolean isVisible;
 


  public Item (String text, String texture, boolean isVisible) {
    this.text = text;
    this.texture = texture;
    this.isVisible = isVisible;
  }
  
  public String getText() {
    return text;
  }
  
  public String getTextureName() {
    return texture;
  }

  public boolean isVisible() {
    return isVisible;
  }
}
