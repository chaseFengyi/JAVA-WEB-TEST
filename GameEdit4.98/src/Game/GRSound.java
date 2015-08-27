package Game;
import sun.audio.AudioStream;
public class GRSound{
	public String sourcename="";
	public AudioStream sound;
	public GRSound(String soundname,AudioStream sound){
		sourcename=soundname;
		this.sound=sound;
	}
	public void setSound(String soundname,AudioStream sound){
		sourcename=soundname;
		this.sound=sound;
	}
}