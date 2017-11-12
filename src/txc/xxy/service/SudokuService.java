/**
 * @Title: SudokuService.java
 * @Package txc.xxy.service
 * @Description: TODO
 * Copyright: Copyright (c) 2011 
 * Company:
 * 
 * @author Txc
 * @date 2016年7月26日 下午5:38:06
 * @version V1.0
 */

package txc.xxy.service;

import java.util.HashSet;

import txc.xxy.model.SudokuExt;

/**
  * @ClassName: SudokuService
  * @Description: TODO
  * @author Txc
  * @date 2016年7月26日 下午5:38:06
  *
  */

public class SudokuService {
	/*
	  * <p>Title: sudokuChange</p>
	  * <p>Description: 九宫格转化函数</p>
	  * <p>Description: 传入的九宫格进行移动转换所能获得的其他状态</p>
	  * @return
	  */
	public HashSet<SudokuExt> sudokuChange(SudokuExt sudokeExt){
		HashSet<SudokuExt> SudokuExts = new HashSet<SudokuExt>();
		SudokuExt gezi = sudokeExt;
		int x = sudokeExt.getX();
		int y = sudokeExt.getY();
		if((x-1)>=0){
			int[][] gezi1 = gezi.getGezi().clone();
			int t = gezi1[x][y];
			gezi1[x][y] = gezi1[x-1][y];
			gezi1[x-1][y] = t;
			SudokuExts.add(new SudokuExt(gezi1, gezi, "下移"));
		}
		if((x+1)<3){
			int[][] gezi1 = gezi.getGezi().clone();
			int t = gezi1[x][y];
			gezi1[x][y] = gezi1[x+1][y];
			gezi1[x+1][y] = t;
			SudokuExts.add(new SudokuExt(gezi1, gezi, "上移"));
		}
		if((y-1)>=0){
			int[][] gezi1 = gezi.getGezi().clone();
			int t = gezi1[x][y];
			gezi1[x][y] = gezi1[x][y-1];
			gezi1[x][y-1] = t;
			SudokuExts.add(new SudokuExt(gezi1, gezi, "右移"));
		}
		if((y+1)<3){
			int[][] gezi1 = gezi.getGezi().clone();
			int t = gezi1[x][y];
			gezi1[x][y] = gezi1[x][y+1];
			gezi1[x][y+1] = t;
			SudokuExts.add(new SudokuExt(gezi1, gezi, "左移"));
		}
		return SudokuExts;
	}
	
	public SudokuExt jieda(SudokuExt begin, SudokuExt end){
		HashSet<SudokuExt> SudokuExts = new HashSet<SudokuExt>();
		HashSet<SudokuExt> SudokuExtNew = new HashSet<SudokuExt>();
		HashSet<SudokuExt> SudokuExt2 = new HashSet<SudokuExt>();
		SudokuExts.add(begin);
		if(begin.equals(end)){
			return begin;
		}
		for(;;){
			SudokuExtNew.clear();
			for(SudokuExt s:SudokuExts){
				SudokuExt2 = sudokuChange(s);
				for(SudokuExt s2:SudokuExt2){
					if(s2.equals(end)){
						System.out.println("结束-----束结");
						return s2;
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
