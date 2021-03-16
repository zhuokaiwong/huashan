package com.zhuokaiwong.stack;

import java.util.Scanner;

public class ArrayStackDemo {

	public static void main(String[] args) {
		ArrayStack as = new ArrayStack(4);
		String key = "";
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		while (loop) {
			System.out.println("show:显示栈");
			System.out.println("push:添加数据到栈");
			System.out.println("pop:从栈取出数据");
			System.out.println("exit:退出程序");
			System.out.print("请输入你的选择:");
			key = sc.next();
			switch (key) {
			case "show":
				as.showStack();
				System.out.println("---------");
				break;
			case "push":
				System.out.print("请输入一个数:");
				int num = sc.nextInt();
				as.push(num);
				System.out.println("---------");
				break;
			case "pop":
				try {
					int res = as.pop();
					System.out.printf("出栈的数据是%d%n", res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println("---------");
				break;
			case "exit":
				System.out.println("程序退出");
				System.out.println("---------");
				sc.close();
				loop = false;
				break;
			default:
				System.out.println("---------");
				break;
			}
		}
	}

}

//定义一个ArrayStack，表示栈
class ArrayStack {
	private int maxSize;// 栈的大小
	private int[] stack;// 数组模拟栈，数据就放在该数组中
	private int top = -1;// top表示栈顶，初始化为-1

	public ArrayStack() {
	}

	// 构造方法
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}

	// 判断是否栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// 判断是否栈空
	public boolean isEmpty() {
		return top == -1;
	}

	// 入栈(push)
	public void push(int value) {
		if (isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}

	// 出栈(pop)
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈空，没有数据");
		}
		int value = stack[top];
		top--;
		return value;
	}

	// 遍历栈，遍历时需要从栈顶开始显示数据
	public void showStack() {
		if (isEmpty()) {
			System.out.println("栈空，没有数据");
			return;
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]=%d%n", i, stack[i]);
		}
	}
}
