package com.zhuokaiwong.sort;

import java.util.Arrays;

public class ShellSort {

	public static void main(String[] args) {
		int[] arr = { 119, 34, 101, 1, 4 };
		shellSort2Demo(arr);
	}

	public static void shellSort2(int[] arr) {
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				int j = i;
				int temp = arr[j];
				if (arr[j] < arr[j - gap]) {
					while (j - gap >= 0 && temp < arr[j - gap]) {
						arr[j] = arr[j - gap];
						j -= gap;
					}
					arr[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}

	public static void shellSort2Demo(int[] arr) {
		// 第1轮排序
		for (int i = 2; i < arr.length; i++) {
			int j = i;// 待插入位置
			int temp = arr[j];
			if (arr[j] < arr[j - 2]) {
				/*while (j - 2 >= 0 && temp < arr[j - 2]) {
					arr[j] = arr[j - 2];
					j -= 2;
				}*/
				for (; j - 2 >= 0 && temp < arr[j - 2]; j -= 2) {
					arr[j] = arr[j - 2];
				}
				arr[j] = temp;
			}
		}
		System.out.println("第1轮移位排序后:" + Arrays.toString(arr));

		// 第2轮排序
		for (int i = 1; i < arr.length; i++) {
			int j = i;
			int temp = arr[j];
			if (arr[j] < arr[j - 1]) {
				/*while (j - 1 >= 0 && temp < arr[j - 1]) {
					arr[j] = arr[j - 1];
					j -= 1;
				}*/
				for (; j - 1 >= 0 && temp < arr[j - 1]; j -= 1) {
					arr[j] = arr[j - 1];
				}
				arr[j] = temp;
			}
		}
		System.out.println("第2轮移位排序后:" + Arrays.toString(arr));
	}

	public static void shellSort2Demo2(int[] arr) {
		// 第1轮排序
		int temp = 0;
		for (int i = 2; i < arr.length; i++) {
			int j = i - 2;
			while (j >= 0) {
				if (arr[j] > arr[j + 2]) {
					temp = arr[j];
					arr[j] = arr[j + 2];
					arr[j + 2] = temp;
					j -= 2;
				}
			}
		}
		System.out.println("第1轮移位排序后:" + Arrays.toString(arr));

		/*int temp2 = 0;
		for (int i = 2; i < arr.length; i++) {
			for (int j = i - 2; j >= 0; j -= 2) {
				if (arr[j] > arr[j + 2]) {
					temp2 = arr[j];
					arr[j] = arr[j + 2];
					arr[j + 2] = temp2;
				}
			}
		}
		System.out.println("第1轮移位排序后:" + Arrays.toString(arr));*/

		/*// 第2轮排序
		for (int i = 1; i < arr.length; i++) {
			int j = i;
			int temp = arr[j];
			if (arr[j] < arr[j - 1]) {
				while (j - 1 >= 0 && temp < arr[j - 1]) {
					arr[j] = arr[j - 1];
					j -= 1;
				}
				arr[j] = temp;
			}
		}
		System.out.println("第2轮移位排序后:" + Arrays.toString(arr));*/
	}
}
