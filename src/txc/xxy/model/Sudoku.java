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
	private int[][] gezi;	//现在格子3*3的状态
	//private String geziString;	//格子的String对象
	/**
	  * 创建一个新的实例 Sudoku. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  */
	public Sudoku(int[][] gezi) {
		this.gezi = gezi;
	}

	/**
	 * 克隆一份格子数组--深度复制
	 * @return
	 */
	public int[][] cloneGezi() {
		int[][] cloneObj = new int[3][3];
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				cloneObj[i][j] = gezi[i][j];
			}
		}
		return cloneObj;
	}


	public String getGeziString() {
		//初始化,geziString;
		StringBuilder sb = new StringBuilder(9);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				sb.append(gezi[i][j]);
			}
		}
		return sb.toString();
	}

	public void ChangeModel(int value){
		//找到值等于value的格子的坐标
		//找到值等于8的格子(空格子)的坐标
		int moveX=0,moveY=0,nullX=0,nullY=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(gezi[i][j] == value){
					moveX = i;
					moveX = j;
				}
				if(gezi[i][j] == 8){
					nullX = i;
					nullY = j;
				}
			}
		}
		//换值
		gezi[nullX][nullY] = value;
		gezi[moveX][moveY] = 8;
	}

	public int getX(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(gezi[i][j] == 8){
					return i;
				}
			}
		}
		return -1;
	}
	public int getY(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(gezi[i][j] == 8){
					return j;
				}
			}
		}
		return -1;
	}




	@Override
	public int hashCode() {
		return getGeziString().hashCode();
	}

	//判断状态是否一样
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Sudoku){
			return getGeziString().equals(obj.toString());
		}
		return false;
	}

	@Override
	public String toString() {
		return getGeziString();
	}

	public static void main(String[] args) {
		int[][] x = {{1,2,3},{4,5,6},{7,8,9}};
		Sudoku sudoku = new Sudoku(x);
		sudoku.hashCode();
	}
}
