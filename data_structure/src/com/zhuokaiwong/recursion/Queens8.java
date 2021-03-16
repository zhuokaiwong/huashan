package com.zhuokaiwong.recursion;

public class Queens8 {

	// 定义一个max变量来表示共有多少个皇后
	int max = 4;
	int[] array = new int[max];
	static int count = 0;
	static int judgeCount = 0;

	// 定义数组array来保存皇后放置位置的结果，比如arr={0,4,7,5,2,6,1,3}
	public static void main(String[] args) {
		Queens8 queens8 = new Queens8();
		queens8.check(0);
		System.out.printf("一共有%d种解法%n", count);
		System.out.printf("一共判断了%d次", judgeCount);
	}

	// 编写一个方法来放置皇后
	private void check(int n) {
		if (n == max) {// n=8时其实8个皇后已经放好
			print();
			return;
		}
		// 依次放入皇后，并且判断是否冲突
		for (int i = 0; i < max; i++) {
			// 先把当前这个皇后n放到该行的第1列
			array[n] = i;
			// 判断当放置第n个皇后到第i列时是否冲突
			if (judge(n)) {// 如果不冲突
				// 接着放n+1个皇后，开始递归
				check(n + 1);
			}
			// 如果冲突就继续执行array[n]=i;即将第n个皇后放置在本行的后移的一个位置
		}
	}

	// 查看当我们放置第n个皇后后就去检测该皇后是否和前面已经摆放的皇后有冲突
	/**
	 * 
	 * @param n 表示第n个皇后
	 * @return
	 */
	private boolean judge(int n) {
		judgeCount++;
		for (int i = 0; i < n; i++) {
			// 说明
			// 1. array[i] == array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
			// 2. Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后在同一斜线上
			// 比如 n=1 把皇后放置在第二列 array[1]=1
			if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
				return false;
			}
			/*if (array[i] == array[n] || (n - i) == Math.abs(array[n] - array[i])) {
				return false;
			}*/
		}
		return true;
	}

	// 写一个方法用来输出皇后摆放后的位置
	private void print() {
		count++;
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
