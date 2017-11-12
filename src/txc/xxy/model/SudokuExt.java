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
	
	public String getTishi() {
		return tishi;
	}
	public void setTishi(String tishi) {
		this.tishi = tishi;
	}
	/**
	  * 创建一个新的实例 SudokuExt. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  * @param gezi
	  */
	public SudokuExt(int[][] gezi,SudokuExt geziOld, String tishi) {
		super(gezi);
		this.oldGezi = geziOld;
		this.tishi = tishi;
	}
	public SudokuExt getOldGezi() {
		return oldGezi;
	}
	public void setOldGezi(SudokuExt oldGezi) {
		this.oldGezi = oldGezi;
	}
	public boolean hasOldGezi(){
		if(oldGezi == null){
			return false;
		}
		return true;
	}
}
