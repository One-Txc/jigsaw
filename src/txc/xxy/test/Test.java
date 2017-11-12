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
		int [][] start = {{1,6,3},{4,9,8},{7,5,2}};
		int [][] jieshu = {{1,4,7},{2,5,8},{3,6,9}};
		SudokuExt begin = new SudokuExt(start, null, null);
		SudokuExt end = new SudokuExt(jieshu, null, null);
		
		SudokuService ss = new SudokuService();
		
		HashSet<SudokuExt> SudokuExts = new HashSet<SudokuExt>();
		HashSet<SudokuExt> SudokuExtNew = new HashSet<SudokuExt>();
		HashSet<SudokuExt> SudokuExt2 = new HashSet<SudokuExt>();
		SudokuExts.add(begin);
		if(begin.equals(end)){
			System.out.println("结束啦啦啦啦");
			return;
		}
		for(;;){
			SudokuExtNew.clear();
			for(SudokuExt s:SudokuExts){
				SudokuExt2 = ss.sudokuChange(s);
				for(SudokuExt s2:SudokuExt2){
					if(s2.equals(end)){
						System.out.println("000000000");
						while(s2.hasOldGezi()){
							System.out.println(s2.toString()+s2.getTishi());
							s2 = s2.getOldGezi();
						}
						System.out.println(s2.toString()+s2.getTishi());
						return;
					}
				}
				SudokuExtNew.addAll(SudokuExt2);
			}
			SudokuExtNew.removeAll(SudokuExts);
			SudokuExts.clear();
			SudokuExts.addAll(SudokuExtNew);
		}	
	}
}
