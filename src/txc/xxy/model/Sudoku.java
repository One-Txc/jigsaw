/**
 * @Title: State.java
 * @Package txc.xxy.model
 * @Description: TODO
 * Copyright: Copyright (c) 2011 
 * Company:
 * 
 * @author Txc
 * @date 2016年7月26日 下午5:03:46
 * @version V1.0
 */

package txc.xxy.model;

/**
  * @ClassName: Sudoku
  * @Description: 九宫格状态对象；9代表空白
  * @author Txc
  * @date 2016年7月26日 下午5:03:46
  *
  */
public class Sudoku {
	private int[][] gezi;	//现在格子的状态
	private int x;	//空白格的x左表
	private int y;	//空白格的y左表
	private String geziString;	//格子的String对象
	/**
	  * 创建一个新的实例 Sudoku. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  */
	public Sudoku(int[][] gezi) {
		this.gezi = gezi;
		//初始化x,y,geziString;
		StringBuilder sb = new StringBuilder(10);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				sb.append(gezi[i][j]);
				if(gezi[i][j] == 9){
					x = i;
					y = j;
				}
			}
		}
		geziString = sb.toString();
	}
	
	public int[][] getGezi() {
		int[][] xx = {{gezi[0][0],gezi[0][1],gezi[0][2]},{gezi[1][0],gezi[1][1],gezi[1][2]},{gezi[2][0],gezi[2][1],gezi[2][2]}};
		return xx;
	}

	public void setGezi(int[][] gezi) {
		this.gezi = gezi;
	}
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getGeziString() {
		return geziString;
	}

	public void setGeziString(String geziString) {
		this.geziString = geziString;
	}

	@Override
	public int hashCode() {
		return geziString.hashCode();
	}

	//判断状态是否一样
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Sudoku){
			return geziString.equals(obj.toString());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return geziString;
	}
	public static void main(String[] args) {
		int[][] x = {{1,2,3},{4,5,6},{7,8,9}};
		Sudoku sudoku = new Sudoku(x);
		sudoku.hashCode();
	}
	
	public void ChangeModel(int value){
		int xc=0,yc=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(gezi[i][j] == value){
					xc = i;
					yc = j;
				}
			}
		}
		gezi[x][y] = gezi[xc][yc];
		gezi[xc][yc] = 9;
		x = xc;
		y = yc;
		StringBuilder sb = new StringBuilder(10);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				sb.append(gezi[i][j]);
			}
		}
		geziString = sb.toString();
	}

}
