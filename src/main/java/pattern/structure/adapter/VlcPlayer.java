package pattern.structure.adapter;

/**
 * VlcPlayer
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
