package pattern.structure.adapter;

/**
 * AudioPlayer
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class AudioPlayer implements MediaPlayer{
    MediaAdapter mediaAdapter;
    @Override
    public void play(AudioType audioType, String fileName) {

        //播放 mp3 音乐文件的内置支持
        if(audioType == AudioType.MP3){
            System.out.println("Playing mp3 file. Name: "+ fileName);
        }
        //mediaAdapter 提供了播放其他文件格式的支持
        else if(audioType == AudioType.MP4
                || audioType == AudioType.VCL){
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else{
            System.out.println("Invalid media. "+
                    audioType + " format not supported");
        }
    }
}
