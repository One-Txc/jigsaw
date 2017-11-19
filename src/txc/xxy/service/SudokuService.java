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

import txc.xxy.model.SudokuExt;

import java.util.HashSet;

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
		HashSet<SudokuExt> SudokuExts = new HashSet<>();
		SudokuExt gezi = sudokeExt;
		int x = sudokeExt.getX();
		int y = sudokeExt.getY();
		if((x-1)>=0){
			int[][] gezi1 = gezi.cloneGezi();
			int t = gezi1[x][y];
			gezi1[x][y] = gezi1[x-1][y];
			gezi1[x-1][y] = t;
			SudokuExts.add(new SudokuExt(gezi1, gezi, "下移"));
		}
		if((x+1)<=2){
			int[][] gezi1 = gezi.cloneGezi();
			int t = gezi1[x][y];
			gezi1[x][y] = gezi1[x+1][y];
			gezi1[x+1][y] = t;
			SudokuExts.add(new SudokuExt(gezi1, gezi, "上移"));
		}
		if((y-1)>=0){
			int[][] gezi1 = gezi.cloneGezi();
			int t = gezi1[x][y];
			gezi1[x][y] = gezi1[x][y-1];
			gezi1[x][y-1] = t;
			SudokuExts.add(new SudokuExt(gezi1, gezi, "右移"));
		}
		if((y+1)<=2){
			int[][] gezi1 = gezi.cloneGezi();
			int t = gezi1[x][y];
			gezi1[x][y] = gezi1[x][y+1];
			gezi1[x][y+1] = t;
			SudokuExts.add(new SudokuExt(gezi1, gezi, "左移"));
		}
		return SudokuExts;
	}
	
	public SudokuExt jieda(SudokuExt begin, SudokuExt end){

		HashSet<SudokuExt> alreadyHandleList = new HashSet<>();			//已经处理的集合
		HashSet<SudokuExt> needHandleList = new HashSet<>();			//待处理的集合
		needHandleList.add(begin);
		for(;;){
			for(SudokuExt sudoku:needHandleList){
				if(sudoku.equals(end)){
					System.out.println("结束-----束结");
					return sudoku;
				}
				alreadyHandleList.add(sudoku);
			}

			HashSet<SudokuExt> sonOfNeedSudokuList = new HashSet<>(16);
			for(SudokuExt sudoku:needHandleList){
				HashSet<SudokuExt> newSudokuList = sudokuChange(sudoku);
				for(SudokuExt newSudoku:newSudokuList){
					if(!alreadyHandleList.contains(newSudoku)){
						sonOfNeedSudokuList.add(newSudoku);
					}
				}
			}
			needHandleList = sonOfNeedSudokuList;
		}

	}
}
