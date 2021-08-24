package pattern.bridge.cainiao;

public class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: greem , radius: " + radius + ",x: " + x+",y:" + y);
    }
}
