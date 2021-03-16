package com.zhuokaiwong.sort;

import java.util.Arrays;

public class InsertSort {

	public static void main(String[] args) {
		int[] arr = { 101, 34, 119, 1 };
		insertSort(arr);
	}

	public static void insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int insertVal = arr[i];
			int insertIndex = i - 1;
//			int temp = 0;
			while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex];
				insertIndex--;
				/*temp = arr[insertIndex];
				arr[insertIndex] = insertVal;
				arr[insertIndex + 1] = temp;
				insertIndex--;*/
			}
			if (insertIndex != i) {
				arr[insertIndex + 1] = insertVal;
			}
		}
		System.out.println(Arrays.toString(arr));
	}

	// 插入排序
	public static void insertSortDemo(int[] arr) {
		// 第1轮{101,34,119,1} => {34,101,119,1}
		// 定义待插入的数
		int insertVal = arr[1];
		int insertIndex = 1 - 1;// 即arr[1]的前面这个数的下标
		// 给insertVal找到插入的位置
		while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
			arr[insertIndex + 1] = arr[insertIndex];
			insertIndex--;
		}
		// 当退出while循环的时候说明插入位置找到
		arr[insertIndex + 1] = insertVal;
		System.out.println("第1轮插入排序后:" + Arrays.toString(arr));

		// 第2轮{34,101,119,1} => {34,101,119,1}
		// 定义待插入的数
		insertVal = arr[2];
		insertIndex = 2 - 1;// 即arr[1]的前面这个数的下标
		// 给insertVal找到插入的位置
		while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
			arr[insertIndex + 1] = arr[insertIndex];
			insertIndex--;
		}
		// 当退出while循环的时候说明插入位置找到
		arr[insertIndex + 1] = insertVal;
		System.out.println("第2轮插入排序后:" + Arrays.toString(arr));

		// 第3轮{34,101,119,1} => {1,34,101,119}
		// 定义待插入的数
		insertVal = arr[3];
		insertIndex = 3 - 1;// 即arr[1]的前面这个数的下标
		// 给insertVal找到插入的位置
		while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
			arr[insertIndex + 1] = arr[insertIndex];
			insertIndex--;
		}
		// 当退出while循环的时候说明插入位置找到
		arr[insertIndex + 1] = insertVal;
		System.out.println("第3轮插入排序后:" + Arrays.toString(arr));
	}

}
