package com.zhuokaiwong.sparsearray;

public class SparseArray {

	public static void main(String[] args) {
		int[][] chessArr = new int[18][11];
		chessArr[1][2] = 1;
		chessArr[2][3] = 2;
		chessArr[4][5] = 2;
		System.out.println("原始的二维数组");
		for (int[] row : chessArr) {
			for (int item : row) {
				System.out.printf("%d\t", item);
			}
			System.out.println();
		}
		/*System.out.println("---------");
		for (int i = 0; i < chessArr.length; i++) {
			System.out.println(chessArr.length);
			for (int j = 0; j < chessArr[i].length; j++) {
				System.out.print(chessArr[i][j] + "\t");
			}
			System.out.println();
		}*/
		// 将二维数组转稀疏数组的思路
		// 1.先遍历二维数组，得到非0数据的个数
		int sum = 0;
		for (int i = 0; i < chessArr.length; i++) {
			for (int j = 0; j < chessArr[i].length; j++) {
				if (chessArr[i][j] != 0) {
					sum++;
				}
			}
		}
		System.out.println("sum=" + sum);
		// 创建对应的稀疏数组
		int[][] sparseArr = new int[sum + 1][3];
		// 给稀疏数组赋值
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;

		// 遍历二维数组，将非0的值存放到sparseArr中
		int count = 0;
		for (int i = 0; i < chessArr.length; i++) {
			for (int j = 0; j < chessArr[i].length; j++) {
				if (chessArr[i][j] != 0) {
					count++;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = chessArr[i][j];
				}
			}
		}

		// 输出稀疏数组
		System.out.println();
		System.out.println("得到的稀疏数组：");
		for (int i = 0; i < sparseArr.length; i++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
		}

		// 将稀疏数组恢复成原始二维数组
		// 1.先读取稀疏数组的第一行，根据第一行的数据创建原始的二维数组
		int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
		// 2.再读取稀疏数组后几行的数据，并赋给原始的二维数组即可
		System.out.println("从稀疏数组恢复后的二维数组");
		for (int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		for (int[] row : chessArr2) {
			for (int item : row) {
				System.out.printf("%d\t", item);
			}
			System.out.println();
		}
	}

}
