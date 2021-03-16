package com.zhuokaiwong.linkedlist;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		// 创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

		// 创建链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		// 加入
//		singleLinkedList.addByOrder(hero1);
//		singleLinkedList.addByOrder(hero4);
//		singleLinkedList.addByOrder(hero2);
//		singleLinkedList.addByOrder(hero3);
//		singleLinkedList.addByOrder(hero3);
//		singleLinkedList.showList();
		/*// 显示修改之前
		System.out.println("------节点修改之前------");
		singleLinkedList.showList();
		
		// 测试修改节点的代码
		//		HeroNode newHeroNode = new HeroNode(3, "小卢", "kirin");
		//		singleLinkedList.update(newHeroNode);
		singleLinkedList.delete(1);
		singleLinkedList.delete(2);
		singleLinkedList.delete(3);
		singleLinkedList.delete(4);
		
		// 显示修改之后
		System.out.println("------节点修改之后------");
		singleLinkedList.showList();*/
		singleLinkedList.delete(2);
		System.out.println(singleLinkedList.getLength(singleLinkedList.getHead()));
	}

}

//定义SingleLinkedList来管理英雄
class SingleLinkedList {
	// 先定义一个头节点，头节点不要动，不存放具体的数据
	private HeroNode head = new HeroNode(0, "", "");

	public HeroNode getHead() {
		return head;
	}

	// 添加节点到单向链表
	// 不考虑编号顺序时
	// 1.找到当前链表的最后节点
	// 2.将这个节点的next域指向新的节点
	public void add(HeroNode heroNode) {
		// 因为head节点不能动，因此我们需要一个辅助遍历temp
		HeroNode temp = head;
		// 遍历链表，找到最后
		while (true) {
			// 找到链表的最后
			if (temp.next == null) {
				break;
			}
			// 如果没有找到最后，则将temp后移
			temp = temp.next;
		}
		// 当退出while循环时，temp就将指向了链表的最后
		// 将最后这个节点的next指向新的节点
		temp.next = heroNode;
	}

	// 添加节点到单向链表
	// 考虑编号顺序时
	public void addByOrder(HeroNode heroNode) {
		HeroNode temp = head;
		boolean flag = false;// flag标记要添加的节点的编号是否存在，默认为false
		while (true) {
			if (temp.next == null) {// 表示temp已经在链表的最后
				break;
			}
			if (temp.next.no > heroNode.no) {// 位置找到，就在temp的后面插入
				break;
			}
			if (temp.next.no == heroNode.no) {// 表示要添加的heroNode的编号已经存在
				flag = true;// 说明编号存在
				break;
			}
			temp = temp.next;// 后移，遍历当前链表
		}
		// 判断flag的值
		if (flag) {
			System.out.printf("准备添加的英雄编号%d已经存在了，不能添加\n", heroNode.no);
		} else {
			// 插入到链表中，temp的后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}

	// 修改节点的信息，根据no编号来修改
	// 根据newHeroNode的no来修改
	public void update(HeroNode newHeroNode) {
		// 判断是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 根据no编号，找到需要修改的节点
		HeroNode temp = head.next;
		// 定义一个辅助节点temp
		boolean flag = false;// 表示是否找到节点，默认为false
		while (true) {
			if (temp == null) {
				break;// 已经遍历完链表
			}
			if (temp.no == newHeroNode.no) {
				flag = true;// 找到
				break;
			}
			temp = temp.next;
		}
		if (flag) {// 根据flag来判断是否知道到要修改的节点
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		} else {// 没有找到
			System.out.printf("没有找到编号为%d的节点，不能修改\n", newHeroNode.no);
		}
	}

	// 删除思路
	// 1. head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个结点
	// 2. 说明我们在比较时，是temp.next.no和需要删除的节点的no比较
	public void delete(int no) {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空，无法删除");
			return;
		}
		HeroNode temp = head;
		boolean flag = false;
		while (true) {
			if (temp.next == null) {// 已经到链表的最后节点
				break;
			}
			if (temp.next.no == no) {// 找到待删除节点的前一个节点temp
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 判断flag
		if (flag) {// 找到
			temp.next = temp.next.next;
		} else {
			System.out.printf("要删除的%d节点不存在%n", no);
		}
	}

	// 显示链表
	public void showList() {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 因为头节点不能动，因此我们需要一个辅助变量来遍历
		HeroNode temp = head.next;
		while (true) {
			// 判断是否到链表最后
			if (temp == null) {
				break;
			}
			// 输出节点的信息
			System.out.println(temp);
			// 将temp后移
			temp = temp.next;
		}
	}

	/**
	 * 获取单链表的节点个数
	 * 
	 * @param head 链表的头节点
	 * @return 返回有效结点的个数
	 */
	public int getLength(HeroNode head) {
		if (head.next == null) {
			return 0;
		}
		int length = 0;
		// 定义一个辅助变量，这里没有统计head节点
		HeroNode cur = head.next;
		while (cur != null) {
			length++;
			cur = cur.next;// 遍历
		}
		return length;
	}
}

//定义HeroNode，每一个HeroNode对象都是一个节点
class HeroNode {
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;// 指向下一个节点

	public HeroNode() {
	}

	// 构造器
	public HeroNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}

}