import java.io.IOException;

public class thread implements Runnable {
	public void run() {
		String http;
		String data;
		net N = new net();
		while(board.status == "2") {
			if(board.isme==true)
			{
				if(board.isok=true)
				{
					http = "http://140.138.147.44:6004/you_r_fired//play?SID=1052040&room_id="+board.room_id+"&type="+moveEvent.chess_type+"&x="+ moveEvent.x+"&y="+ moveEvent.y;
					board.isme =false;
				}
				else {
					http = "http://140.138.147.44:6004/you_r_fired/wait?SID=s1072040&room_id="+ board.room_id;
					try {
						data = N.getdata(http);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
		}
	}
}


