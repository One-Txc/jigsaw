/**
 * @Title: SudokuExt.java
 * @Package txc.xxy.model
 * @Description: TODO
 * Copyright: Copyright (c) 2011 
 * Company:
 * 
 * @author Txc
 * @date 2016年7月26日 下午5:43:19
 * @version V1.0
 */

package txc.xxy.model;

/**
  * @ClassName: SudokuExt
  * @Description: TODO
  * @author Txc
  * @date 2016年7月26日 下午5:43:19
  *
  */

public class SudokuExt extends Sudoku{

	private SudokuExt oldGezi;
	private String tishi; //移动提示

	/**
	  * 创建一个新的实例 SudokuExt.
	  * @param gezi
	  */
	public SudokuExt(int[][] gezi,SudokuExt geziOld, String tishi) {
		super(gezi);
		this.oldGezi = geziOld;
		this.tishi = tishi;
	}
	public boolean hasOldGezi(){
		if(oldGezi == null){
			return false;
		}
		return true;
	}



	public String getTishi() {
		return tishi;
	}
	public SudokuExt getOldGezi() {
		return oldGezi;
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	//判断状态是否一样
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
