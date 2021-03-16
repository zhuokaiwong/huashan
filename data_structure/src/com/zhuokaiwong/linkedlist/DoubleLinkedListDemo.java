package com.zhuokaiwong.linkedlist;

public class DoubleLinkedListDemo {
	public static void main(String[] args) {
		// 创建节点
		HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
		HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
		HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
		HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
		DoubleLinkedList dll = new DoubleLinkedList();
		dll.add(hero1);
		dll.add(hero2);
		dll.add(hero3);
		dll.add(hero4);
		System.out.println("------修改前------");
		dll.showList();
		/*System.out.println("------修改后------");
		HeroNode2 newHeroNode = new HeroNode2(3, "小卢", "kirin");
		dll.update(newHeroNode);
		dll.showList();*/
		System.out.println("------删除后------");
//		dll.delete(1);
//		dll.delete(2);
//		dll.delete(3);
		dll.delete(3);
		dll.showList();
	}
}

class DoubleLinkedList {
	private HeroNode2 head = new HeroNode2(0, "", "");

	public HeroNode2 getHead() {
		return head;
	}

	// 显示链表
	public void showList() {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 因为头节点不能动，因此我们需要一个辅助变量来遍历
		HeroNode2 temp = head.next;
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

	// 添加一个节点到双向链表的最后
	public void add(HeroNode2 heroNode) {
		// 因为head节点不能动，因此我们需要一个辅助遍历temp
		HeroNode2 temp = head;
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
		// 形成一个双向链表
		temp.next = heroNode;
		heroNode.pre = temp;
	}

	// 修改节点的信息，根据no编号来修改
	// 根据newHeroNode的no来修改
	public void update(HeroNode2 newHeroNode) {
		// 判断是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 根据no编号，找到需要修改的节点
		HeroNode2 temp = head.next;
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
	// 1. 对于双向链表可以直接找到要删除的这个节点
	// 2. 找到后自我删除即可
	public void delete(int no) {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空，无法删除");
			return;
		}
		HeroNode2 temp = head.next;// 辅助指针
		boolean flag = false;
		while (true) {
			if (temp == null) {// 已经到链表的最后节点的next
				break;
			}
			if (temp.no == no) {// 找到待删除节点的前一个节点temp
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 判断flag
		if (flag) {// 找到
			temp.pre.next = temp.next;
			if (temp.next != null) {
				temp.next.pre = temp.pre;
			}
		} else {
			System.out.printf("要删除的%d节点不存在%n", no);
		}
	}
}

//定义HeroNode，每一个HeroNode对象都是一个节点
class HeroNode2 {
	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next;// 指向下一个节点
	public HeroNode2 pre;// 指向上一个节点

	public HeroNode2() {
	}

	// 构造器
	public HeroNode2(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}

}
