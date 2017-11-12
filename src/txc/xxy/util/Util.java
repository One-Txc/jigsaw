/**
 * @Title: Util.java
 * @Package txc.xxy.util
 * @Description: TODO
 * Copyright: Copyright (c) 2011 
 * Company:
 * 
 * @author Txc
 * @date 2016年7月27日 上午9:45:30
 * @version V1.0
 */

package txc.xxy.util;

import txc.xxy.model.Sudoku;

/**
  * @ClassName: Util
  * @Description: TODO
  * @author Txc
  * @date 2016年7月27日 上午9:45:30
  *
  */

public class Util {
	//XX,YY转化为拼图模型
	public Sudoku XXYYToSudoke(int[] xx, int[] yy){
		int[][] gezi = xxyyToGezi(xx,yy);
		return new Sudoku(gezi);
	}
	//XX,YY转化为3*3数组
	public int[][] xxyyToGezi(int[] xx, int[] yy){
		int[][] gezi = new int[3][3];
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				gezi[i][j] = help(xx[i*3+j],yy[i*3+j]);
			}
		}
		return gezi;
	}
	
	public int help(int x,int y){
		if(x == 100){
			if(y == 100){
				return 1;
			}else if(y == 200){
				return 4;
			}else if(y == 300){
				return 7;
			}
		}else if(x == 200){
			if(y == 100){
				return 2;
			}else if(y == 200){
				return 5;
			}else if(y == 300){
				return 8;
			}
		}else if(x == 300){
			if(y == 100){
				return 3;
			}else if(y == 200){
				return 6;
			}else if(y == 300){
				return 9;
			}
		}
		return 999;
	}
}
