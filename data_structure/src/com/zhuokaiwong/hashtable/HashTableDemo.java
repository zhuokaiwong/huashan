package com.zhuokaiwong.hashtable;

import java.util.Scanner;

public class HashTableDemo {

	public static void main(String[] args) {
		// 创建哈希表
		HashTable hashTable = new HashTable(7);
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("add:添加雇员");
			System.out.println("list:显示雇员");
			System.out.println("exit:退出系统");
			System.out.print("请输入:");

			key = scanner.next();
			switch (key) {
			case "add":
				System.out.print("请输入id:");
				int id = scanner.nextInt();
				System.out.print("请输入名字:");
				String name = scanner.next();
				// 创建雇员
				Emp emp = new Emp(id, name);
				hashTable.add(emp);
				System.out.println("---------");
				break;
			case "list":
				hashTable.list();
				System.out.println("---------");
				break;
			case "exit":
				System.out.println("程序退出");
				System.out.println("---------");
				scanner.close();
				System.exit(0);
				return;
			default:
				System.out.println("---------");
				break;
			}
		}
	}

}

//表示一个雇员
class Emp {
	public int id;
	public String name;
	public Emp next;// next默认为null

	public Emp() {
		super();
	}

	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}

//创建EmpLinkedList表示链表
class EmpLinkedList {
	// 头指针执行第一个Emp因此这个链表的head是直接指向第一个Emp
	private Emp head;

	public void add(Emp emp) {
		// 如果是添加第一个雇员
		if (head == null) {
			head = emp;
			return;
		}
		// 如果不是第一个雇员则使用一个辅助指针来帮忙定位到最后
		Emp curEmp = head;
		while (true) {
			if (curEmp.next == null) {// 说明curEmp已经是链表最后节点
				break;
			}
			curEmp = curEmp.next;// 后移
		}
		// 退出时直接将emp加入链表
		curEmp.next = emp;
	}

	// 遍历链表的雇员信息
	public void list(int no) {
		if (head == null) {// 说明链表为空
			System.out.println("第" + (no + 1) + "链表为空");
			return;
		}
		System.out.print("第" + (no + 1) + "链表的信息为:");
		Emp curEmp = head;// 辅助指针
		while (true) {
			System.out.printf("=> id=%d\tname=%s%n", curEmp.id, curEmp.name);
			if (curEmp.next == null) {// 说明curEmp已经是链表最后节点
				break;
			}
			curEmp = curEmp.next;// 后移
		}
	}
}

//创建HashTable来管理多条链表
class HashTable {
	private EmpLinkedList[] ellArr;
	private int size;// 表示共有多少条链表

	// 构造器
	public HashTable(int size) {
		super();
		this.size = size;
		// 初始化ellArr
		ellArr = new EmpLinkedList[size];
		// 重要!!要把哈希表里面的每个EmpLinkedList给初始化
		for (int i = 0; i < size; i++) {
			ellArr[i] = new EmpLinkedList();
		}
	}

	// 添加雇员
	public void add(Emp emp) {
		// 根据员工的id得到该员工应当添加到那条链表
		int ellNO = hashFun(emp.id);
		ellArr[ellNO].add(emp);
	}

	// 编写散列函数，是用一个简单取模法
	public int hashFun(int id) {
		return id % size;
	}

	// 遍历所有hashtable中所有的链表
	public void list() {
		for (int i = 0; i < size; i++) {
			ellArr[i].list(i);
		}
	}
}