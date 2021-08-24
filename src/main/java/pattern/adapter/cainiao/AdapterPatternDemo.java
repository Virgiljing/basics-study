package pattern.adapter.cainiao;

public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3","beyond the horizon.mp3");
        audioPlayer.play("MP4","alone.mp4");
        audioPlayer.play("VLC","far far away.vlc");
        audioPlayer.play("avi","mind me.avi");
    }
}
