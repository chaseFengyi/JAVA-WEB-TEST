package Game;
import sun.audio.*;
public class SystemTool{
	public static String getDirectory(){
		String str=System.getProperty("user.dir");
		str+="/Gameresource/";
		return str;
	}
	public static void PlaySound(AudioStream sound){
		AudioPlayer.player.start(sound);
	}
	public static void StopSound(AudioStream sound){
		AudioPlayer.player.stop(sound);
	}
}