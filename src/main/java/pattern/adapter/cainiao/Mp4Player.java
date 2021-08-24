package pattern.adapter.cainiao;

public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        //to do nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file.name:" + fileName);
    }
}
