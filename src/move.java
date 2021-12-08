import java.awt.Point;

public class move {
	Point NO_MATCH_NODE = new Point(-1,-1);
	int offset = 80; //棋盤旁邊的空格
	int space = 80; //每個點的間距
	int radius = 15;
	Point nodeId;
	
	public Boolean CanBePlaced(int x, int y)
	{
		nodeId = FindTheNode(x, y);
		
		if(nodeId ==NO_MATCH_NODE)
			return false;
		
		return true;
	}
	
	//---判斷點選座標是否有抓到點---
	public Point FindTheNode (int x,int y) {
		int Node_x = FindTheNode(x);
		if(Node_x==-1)
			return NO_MATCH_NODE;
		
		int Node_y = FindTheNode(y);
		if(Node_y==-1)
			return NO_MATCH_NODE;
		
		return new Point(Node_x, Node_y);
	}
	
	//---判斷單個點是不是在節點的範圍內---
	int FindTheNode (int pos) {
		pos -= offset; //扣掉旁邊
		
		int Quotient = pos / space;  //左邊的點
		int remainder = pos % space; //間距間的位置
		
		if(remainder <= radius)  //在左邊節點的範圍內
			return Quotient;
		else if(remainder >= space - radius)//在右邊節點的範圍內
			return ++Quotient;
		else 
			return -1;  
	}
}
