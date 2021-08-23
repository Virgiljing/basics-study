package pattern.structure.adapter;

/**
 * MediaAdapter
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMediaPlayer;
    public MediaAdapter(AudioType audioType){
        if (audioType == AudioType.VCL){
            advancedMediaPlayer = new VlcPlayer();
        }
        if (audioType == AudioType.MP4){
            advancedMediaPlayer = new Mp4Player();
        }
    }
    @Override
    public void play(AudioType audioType, String fileName) {
        if (audioType == AudioType.VCL){
            advancedMediaPlayer.playVlc(fileName);
        }
        if (audioType == AudioType.MP4){
            advancedMediaPlayer.playMp4(fileName);
        }

    }
}
