
public class row {
	public boolean can_move(int old_x, int old_y, int new_x, int new_y, int type) {
		if(type == 0 || type == 100)
			return king(old_x, old_y, new_x, new_y, type);
		
		else if(type == 1 || type == 2 || type == 101 || type == 102)
			return Soldier(old_x, old_y, new_x, new_y, type);
		
		else if(type == 3 || type == 4 || type == 103 || type == 104)
			return elephant(old_x, old_y, new_x, new_y, type);
		return true;
	}
	
	//---判斷將或是帥---
	boolean king(int old_x, int old_y, int new_x, int new_y, int type)
	{
		if(type==0)
		{
			//一次只能走一步
			if( Math.abs(new_x - old_x) > 1 || Math.abs(new_y - old_y) > 1 || (Math.abs(new_x - old_x) == 1 && new_y != old_y) || (Math.abs(new_y - old_y) ==1 && new_x != old_x))
				return false;
			
			//---不能超出方格(橫)---
			if(new_x != 3 && new_x != 4 && new_x != 5)
				return false;
			
			//---不能超出方格(直)---
			if(new_y != 0 && new_y != 1 && new_y != 2)
				return false;
			
			//---不能吃同色---
			if(board.b[new_y][new_x] <= 15 && board.b[new_y][new_x] >= 0)
				return false;
		}
		
		else if(type==100)
		{
			//一次只能走一步
			if( Math.abs(new_x - old_x) > 1 || Math.abs(new_y - old_y) > 1 || (Math.abs(new_x - old_x) == 1 && new_y != old_y) || (Math.abs(new_y - old_y) ==1 && new_x != old_x))
				return false;
			
			//---不能超出方格(橫)---
			if(new_x != 3 && new_x != 4 && new_x != 5)
				return false;
			
			//---不能超出方格(直)---
			if(new_y != 7 && new_y != 8 && new_y != 9)
				return false;
			
			//---不能吃同色---
			if(board.b[new_y][new_x]>15)
				return false;
		}
		
		return true;
	}
	
	//---判斷士---
	boolean Soldier(int old_x, int old_y, int new_x, int new_y, int type)
	{
		
		if(type == 1 || type == 2)
		{
			//一次走一步 而且只能斜走
			if( !( ((new_x - old_x) == 1 && (new_y - old_y == 1)) || ((new_x - old_x) == -1 && (new_y - old_y == -1)) || ((new_x - old_x) == 1 && (new_y - old_y == -1)) || ((new_x - old_x) == -1 && (new_y - old_y == 1)) ))
				return false;
			
			//---不能超出方格(橫)---
			if(new_x != 3 && new_x != 4 && new_x != 5)
				return false;
			
			//---不能超出方格(直)---
			if(new_y != 0 && new_y != 1 && new_y != 2)
				return false;
			
			//---不能吃同色---
			if(board.b[new_y][new_x] <= 15 && board.b[new_y][new_x] >= 0)
				return false;
		}
		
		else if(type == 101 || type == 102)
		{
			//一次走一步 而且只能斜走
			if( !( ((new_x - old_x) == 1 && (new_y - old_y == 1)) || ((new_x - old_x) == -1 && (new_y - old_y == -1)) || ((new_x - old_x) == 1 && (new_y - old_y == -1)) || ((new_x - old_x) == -1 && (new_y - old_y == 1)) ))
				return false;
			
			//---不能超出方格(橫)---
			if(new_x != 3 && new_x != 4 && new_x != 5)
				return false;
			
			//---不能超出方格(直)---
			if(new_y != 7 && new_y != 8 && new_y != 9)
				return false;
			
			//---不能吃同色---
			if(board.b[new_y][new_x]>15)
				return false;
		}
		
		return true;
	}
	
	boolean elephant (int old_x, int old_y, int new_x, int new_y, int type)
	{
		if(type == 3 || type == 4)
		{
			//一次走兩步  而且只能斜走
			if( !( ((new_x - old_x) == 2 && (new_y - old_y == 2)) || ((new_x - old_x) == -2 && (new_y - old_y == -2)) || ((new_x - old_x) == 2 && (new_y - old_y == -2)) || ((new_x - old_x) == -2 && (new_y - old_y == 2)) ))
				return false;
			
			//判斷道路有沒有被擋住++
			if(((new_x - old_x) == 2 && (new_y - old_y == 2)))
			{
				if(board.b[new_y - 1][new_x - 1] != -1)
					return false;
			}
			
			//判斷道路有沒有被擋住--
			if(((new_x - old_x) == -2 && (new_y - old_y == -2)))
			{
				if(board.b[new_y + 1][new_x + 1] != -1)
					return false;
			}
			
			//判斷道路有沒有被擋住-+
			if(((new_x - old_x) == -2 && (new_y - old_y == 2)))
			{
				if(board.b[new_y + 1][new_x - 1] != -1)
					return false;
			}
			
			//判斷道路有沒有被擋住+-
			if(((new_x - old_x) == 2 && (new_y - old_y == -2)))
			{
				if(board.b[new_y - 1][new_x + 1] != -1)
					return false;
			}
			
			//不能過河
			if(new_y > 4)
				return false;
			
			//---不能吃同色---
			if(board.b[new_y][new_x] <= 15 && board.b[new_y][new_x] >= 0)
				return false;
		}
		
		else if(type == 103 || type == 104)
		{
			//一次走兩步 而且只能斜走
			if( !( ((new_x - old_x) == 2 && (new_y - old_y == 2)) || ((new_x - old_x) == -2 && (new_y - old_y == -2)) || ((new_x - old_x) == 2 && (new_y - old_y == -2)) || ((new_x - old_x) == -2 && (new_y - old_y == 2)) ))
				return false;
			
			//判斷道路有沒有被擋住++
			if(((new_x - old_x) == 2 && (new_y - old_y == 2)))
			{
				if(board.b[new_y - 1][new_x - 1] != -1)
					return false;
			}
			
			//判斷道路有沒有被擋住--
			if(((new_x - old_x) == -2 && (new_y - old_y == -2)))
			{
				if(board.b[new_y + 1][new_x + 1] != -1)
					return false;
			}
			
			//判斷道路有沒有被擋住-+
			if(((new_x - old_x) == -2 && (new_y - old_y == 2)))
			{
				if(board.b[new_y + 1][new_x - 1] != -1)
					return false;
			}
			
			//判斷道路有沒有被擋住+-
			if(((new_x - old_x) == 2 && (new_y - old_y == -2)))
			{
				if(board.b[new_y - 1][new_x + 1] != -1)
					return false;
			}
			
			//不能過河
			if(new_y < 5)
				return false;
			
			//---不能吃同色---
			if(board.b[new_y][new_x]>15)
				return false;
		}
		
		return true;
	}
}


