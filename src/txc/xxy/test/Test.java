/**
 * @Title: Test.java
 * @Package txc.xxy.test
 * @Description: TODO
 * Copyright: Copyright (c) 2011 
 * Company:
 * 
 * @author Txc
 * @date 2016年7月26日 下午6:51:09
 * @version V1.0
 */

package txc.xxy.test;

import java.util.HashSet;

import txc.xxy.model.SudokuExt;
import txc.xxy.service.SudokuService;

/**
  * @ClassName: Test
  * @Description: TODO
  * @author Txc
  * @date 2016年7月26日 下午6:51:09
  *
  */

public class Test {
	public static void main(String[] args) {
		int [][] start = {
				{0,1,2},
				{3,8,4},
				{6,7,5}
		};
		int [][] jieshu = {
				{0,1,2},
				{3,4,5},
				{6,7,8}
		};
		SudokuExt begin = new SudokuExt(start, null, null);
		SudokuExt end = new SudokuExt(jieshu, null, null);
		
		SudokuService ss = new SudokuService();

		end = ss.jieda(begin,end);

		while(end.hasOldGezi()){
			System.out.println(end.toString()+"--"+end.getTishi());
			end = end.getOldGezi();
		}
		System.out.println(end.toString()+end.getTishi());

	}
}
