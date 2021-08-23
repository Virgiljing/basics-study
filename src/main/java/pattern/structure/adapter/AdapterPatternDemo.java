package pattern.structure.adapter;

/**
 * AdapterPatternDemo
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play(AudioType.MP3,"beyond the horizon.mp3");
        audioPlayer.play(AudioType.MP4, "alone.mp4");
        audioPlayer.play(AudioType.VCL, "far far away.vlc");
        audioPlayer.play(AudioType.AVI, "mind me.avi");
    }
}
