package pattern.structure.adapter;

/**
 * Mp4Player
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name : " + fileName);
    }
}
