package com.zhuokaiwong.queue;

import java.util.Scanner;

public class ArrayQueueDemo {

	public static void main(String[] args) {
		// 创建一个队列
		ArrayQueue queue = new ArrayQueue(3);
		String key = "";
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("s(show):显示队列");
			System.out.println("e(exit):退出程序");
			System.out.println("a(add):添加数据到队列");
			System.out.println("g(get):从队列读取数据");
			System.out.println("h(head):查看队列的头数据");
			System.out.print("请输入：");
			key = scanner.next();// 接受一个字符
			switch (key) {
			case "s":
				try {
					queue.showQueue();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println("---------");
				break;
			case "a":
				System.out.print("请输入一个数：");
				int value = scanner.nextInt();
				try {
					queue.addQueue(value);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println("---------");
				break;
			case "g": // 取出数据
				try {
					int res = queue.getQueue();
					System.out.printf("取出的数据是%d\n", res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println("---------");
				break;
			case "h": // 查看队列头的数据
				try {
					int res = queue.headQueue();
					System.out.printf("队列头的数据是%d\n", res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println("---------");
				break;
			case "e": // 退出
				System.out.println("程序退出");
				System.out.println("---------");
				scanner.close();
				loop = false;
				break;
			default:
				System.out.println("---------");
				break;
			}
		}
	}
}

//使用数组模拟队列
class ArrayQueue {
	// 表示数组的最大容量
	private int maxSize;
	// 队列头
	private int front;
	// 队列尾
	private int rear;
	// 该数组用于模拟队列
	private int[] arr;

	// 创建队列的构造器
	public ArrayQueue(int maxSize) {
		this.maxSize = maxSize;
		this.arr = new int[this.maxSize];
		this.front = -1;// front指向队列头的前一个位置
		this.rear = -1;// 指向队列尾部的数据（包含队列最后一个数据）
	}

	// 判断队列是否满
	public boolean isFull() {
		return rear == (maxSize - 1);
	}

	// 判断队列是否为空
	public boolean isEmpty() {
		return rear == front;
	}

	// 添加数据到队列
	public void addQueue(int n) {
		// 判断队列是否满
		if (isFull()) {
			throw new RuntimeException("队列已满，不能加入新数据");
		}
		// 如果队列未满，让rear后移
		rear++;
		arr[rear] = n;
	}

	// 获取队列的数据，出队列
	public int getQueue() {
		// 判断队列是否为空
		if (isEmpty()) {
			// 通过抛出异常来处理
			throw new RuntimeException("队列空，不能读取数据");
		}
		// 如果队列不为空，让front后移
		front++;
		return arr[front];
	}

	// 显示队列的所有数据
	public void showQueue() {
		// 判断队列是否为空
		if (isEmpty()) {
			throw new RuntimeException("队列空，不能读取数据");
		}
		/*for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n", i, arr[i]);
		}*/
		for (int i = (front + 1); i <= front + 1 + size(); i++) {
			System.out.printf("arr[%d]=%d%n", i, arr[i]);
		}
	}

	// 求出当前队列有效数据的个数
	public int size() {
		// rear = 2
		// front = 1
		// maxSize = 3
		return rear % maxSize;
	}

	// 显示队列的头数据
	public int headQueue() {
		// 判断队列是否为空
		if (isEmpty()) {
			throw new RuntimeException("队列空，不能读取数据");
		}
		return arr[front + 1];
	}
}
