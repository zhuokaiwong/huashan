package com.zhuokaiwong.recursion;

public class Maze {

	public static void main(String[] args) {
		// 先创建一个二维数组模拟迷宫
		int[][] maze = new int[8][7];
		// 使用1表示墙，把上下全部置为1
		for (int i = 0; i < 7; i++) {
			maze[0][i] = 1;
			maze[7][i] = 1;
		}

		// 把左右的墙都置为1
		for (int i = 0; i < 8; i++) {
			maze[i][0] = 1;
			maze[i][6] = 1;
		}

		// 设置挡板
		maze[3][1] = 1;
		maze[3][2] = 1;
		maze[1][2] = 1;
		maze[2][2] = 1;
		// 输出地图
		System.out.println("输出地图的情况");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}

		setWay(maze, 1, 1);
		// 输出新的地图，小球走过并标识过的递归
		System.out.println("输出新地图的情况");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 使用递归回溯来给小球找路
	// 出发位置(1,1),小球能到maze[6][5]位置则说明通路找到
	// 约定:当maze[i][j]为0表示该点没有走过，当为1时表示墙
	/**
	 * 
	 * @param maze 表示迷宫
	 * @param i    表示从哪个位置开始找
	 * @param j    表示从哪个位置开始找
	 * @return 如果找到通路就返回true否则就返回false
	 */
	public static boolean setWay(int[][] maze, int i, int j) {
		if (maze[6][5] == 2) {// 表示通路已经找到
			return true;
		} else {
			if (maze[i][j] == 0) {// 如果当前这个点还没有走过
				// 按照策略 "下->右->上->左" 走
				maze[i][j] = 2;// 假定该点可以走通
				if (setWay(maze, i + 1, j)) {// 向下走
					return true;
				} else if (setWay(maze, i, j + 1)) {// 向右走
					return true;
				} else if (setWay(maze, i - 1, j)) {// 向上走
					return true;
				} else if (setWay(maze, i, j - 1)) {// 向左走
					return true;
				} else {// 说明该点的假定是错误的
					maze[i][j] = 3;
					return false;
				}
			} else {
				return false;
			}
		}
	}
}
