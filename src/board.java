import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;



public class board {
	static int b[][]= {
			{7, 5, 3, 1, 0, 2, 4, 6, 8},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, 9, -1, -1, -1, -1, -1, 10, -1},
			{11, -1, 12, -1, 13, -1, 14, -1, 15},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{111, -1, 112, -1, 113, -1, 114, -1, 115},
			{-1, 109, -1, -1, -1, -1, -1, 110, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{107, 105, 103, 101, 100, 102, 104, 106, 108}
	};
	static Mycanvas canvas;
	static boolean isme = true;
	static String status;
	static boolean isok = false;
	static int room_id=0;
	public static void main(String[] args) throws IOException {
		int i = 0;
		board b = new board();
		//---------------第一步
		//net N = new net();
		String http = "http://";
		String data="";
		//data = N.getdata(http); //與伺服器溝通
		//System.out.println("房號 對手"+data);
		//String[] ss = data.split("\\s+"); //切割字串 以空白為基準
		//room_id = Integer.parseInt(ss[0]); //取得房號  string to int
		
		//---------------
		http = "http:";
		//data = N.getdata(http);
		//System.out.println("連接房間"+data);
		
		
		
		if(data=="sucess")
		{
			http = "";
			//data = N.getdata(http);
			//ss = data.split("\\s+");  //獲得狀態
			//status = ss[0];
			//if(ss[1]=="1")
				//isme = false;
			//thread t = new thread();
			//Thread thread=new Thread(t);
			//thread.start();
		}
		
	}
	
	

	public board() {
		//---frame初始化---
		Frame frame = new Frame();
		frame.setSize(800, 880);
		frame.setVisible(true);
		frame.addWindowListener(new End());
		
		//---畫布canvas初始化---
		canvas = new Mycanvas();
		canvas.setSize(800, 880);
		canvas.setBackground(new Color(183,146,115));
		canvas.addMouseListener(new moveEvent());  //增加滑鼠事件
		//---背景canvas初始化---
		
		frame.add(canvas);
		
		
	}
}

//---實現關掉視窗的功能---
class End extends WindowAdapter{
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
}

//---棋盤畫布---
class Mycanvas extends Canvas{

	@Override
	public void paint(Graphics g) {
		int L = 880; //canvas 長 (y) 
		int W = 800; //canvas 寬  ( x)
		int space = 80; //正方形邊長
		
		//---橫的有十條線---
		for(int i = 1; i <= 10; i++) {   
			g.drawLine(space, space * i,  W-space,  space * i);
		}
		//---直的有九條線---
		
		//上半部直線
		for(int i = 1; i <= 9; i++) {   
			g.drawLine(space * i, space, space * i, 5 * space);
		}
		
		//下半部直線
		for(int i = 1; i <= 9; i++) {   
			g.drawLine(i * space, 6 * space, i * space, L - space);
		}
		//---畫剩餘的部分---
		//中間的兩條直線
		g.drawLine(space, 5 * space, space, L- 5 * space);
		g.drawLine(W - space, 5 * space, W - space, L- 5 * space);
		//叉叉(上)
		g.drawLine(4 * space, space, 6 * space, 3 * space);
		g.drawLine(4 * space, 3 * space, 6 * space, space);
		//叉叉(下)
		g.drawLine(4 * space, L - space, 6 * space, L - 3 * space);
		g.drawLine(4 * space, L - 3 * space, 6 * space, L - space);
		
		
		//---畫象棋---
		String file_name; //圖片檔案名稱
		BufferedImage in; //圖片存取空間
		
		//---讀取整個棋盤---
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 9; j++)
			{
				if(board.b[i][j] != -1)
				{
					file_name = getName(board.b[i][j]);
					try {
						in = ImageIO.read(new FileInputStream(file_name));
						g.drawImage(in, (j+1) * space - 25, (i+1) * space - 25, 50, 50, this);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		
		
	}
	
	//---取得檔案名稱---
	public String getName(int x) {
		String t;
		t="C:\\Users\\mcken\\eclipse-workspace\\Chess\\src\\Image\\"+ x +".png";
		return t; 
	}
}
class moveEvent extends MouseAdapter{
	//---按住滑鼠鈕的事件---
	
	Point old; //原始位置
	Point now; //現在下棋的位置
	static int x;
	static int y;
	static int chess_type; //棋子的種類
	move m = new move(); //取得move的功能
	row r = new row(); //取得move的功能
	@Override
	public void mousePressed(MouseEvent e) {  
		//System.out.println("Press:("+e.getX()+","+e.getY()+")"); //debug
		if(m.CanBePlaced(e.getX(), e.getY())){
			old=m.FindTheNode(e.getX(), e.getY());
			chess_type = board.b[old.y][old.x]; // 動哪顆棋
			System.out.println(old.x+","+old.y); //debug
			
		}
	}
	//---放開滑鼠鈕的事件---  ==>檢查順序 1.檢查有沒有抓到節點2.檢查有沒有移動棋子3.檢查規則正不正確
	@Override
	public void mouseReleased(MouseEvent e) {
		if(m.CanBePlaced(e.getX(), e.getY())){
			now = m.FindTheNode(e.getX(), e.getY());
			if(now.x != old.x || now.y != old.y)
			{
				if(r.can_move(old.x, old.y, now.x, now.y, chess_type))
				{
					board.isok=true;
					board.b[old.y][old.x] = -1;
					board.b[now.y][now.x] = chess_type;
					board.canvas.repaint();
					x= now.x;
					y= now.y;
				}
			}
			System.out.println(now.x+","+now.y); //debug
			
		}
	}
	
}

class net
{
	String getdata(String in) throws IOException
	{
		String x="";
		URL url=new URL(in);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.connect();
		BufferedInputStream is=new BufferedInputStream(conn.getInputStream());
		byte []tmp =new byte[1024];
		int len =0;
		final Charset UTF_8=Charset.forName("BIG5");
		while((len=is.read(tmp))!=-1){
			String value =new String(tmp,UTF_8);
			x+=value;
		}
		is.close();
		return x;
	}
}