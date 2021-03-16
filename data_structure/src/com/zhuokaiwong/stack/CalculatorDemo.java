package com.zhuokaiwong.stack;

public class CalculatorDemo {
	public static void main(String[] args) {
		String exp = "700 + 2 * 6 - 4";
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);
		int index = 0;// 用于扫描
		int num1 = 0;
		int num2 = 0;
		char oper = ' ';// 符号栈中已经存在的运算符
		int res = 0;
		char ch = ' ';// 将每次扫描得到的表达式中的数字或者运算符保存到ch
		String keepNum = "";// 用于拼接多位数
		exp = exp.replaceAll(" ", "");
		// 开始while循环的扫描expression
		while (true) {
			ch = exp.substring(index, index + 1).charAt(0);
			if (operStack.isOper(ch)) {// 判断是否为运算符
				if (!operStack.isEmpty()) {// 判断当前的符号栈是否为空
					// 如果不为空进行处理，如果当前的操作符的优先级小于或等于栈中的操作符，就需要从数栈中pop出两个数
					// 再从符号栈中pop出一个符号进行运算得到结果，然后将结果入数栈，并将当前的运算符入符号栈
					if (operStack.priority(ch) <= operStack.priority((char) operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = (char) operStack.pop();
						res = numStack.cal(num1, num2, oper);
						// 把运算结果入数栈
						numStack.push(res);
						// 把当前的运算符入符号栈
						operStack.push(ch);
					} else {
						// 如果当前的运算符的优先级大于栈中的运算符，就直接入符号栈
						operStack.push(ch);
					}
				} else {// 如果为空则直接入符号栈
					operStack.push(ch);
				}
			} else {// 如果是数字，则直接入数栈
				// 处理多位数
				keepNum += ch;
				// 如果ch已经是表达式的最后一位就直接入数栈
				if (index == exp.length() - 1) {
					numStack.push(Integer.parseInt(keepNum));
				} else {
					// 判断下一个字符是不是数字，如果是数字就继续扫描，如果是运算符则入数栈
					// 注意是看后一位，因此不能是index++
					if (operStack.isOper(exp.substring(index + 1, index + 2).charAt(0))) {
						numStack.push(Integer.parseInt(keepNum));
						// 重要!!,keepNum要清空
						keepNum = "";
					}
				}
//				numStack.push(ch - 48);
			}
			// 让index+1并判断是否扫描到表达式的最后一位
			index++;
			if (index == exp.length()) {
				break;
			}
		}
		// 当表达式扫描完毕，就按照顺序从数栈和符号栈中pop出相对应的数和符号进行运算
		while (true) {
			// 如果符号栈为空，则已经计算到最后的结果了，此时数栈中只有一个数字即是结果
			if (operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = (char) operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);
		}
		int res2 = numStack.pop();
		System.out.printf("表达式%s = %d", exp, res2);
	}
}

//定义一个ArrayStack，表示栈
class ArrayStack2 {
	private int maxSize;// 栈的大小
	private int[] stack;// 数组模拟栈，数据就放在该数组中
	private int top = -1;// top表示栈顶，初始化为-1

	public ArrayStack2() {
	}

	// 构造方法
	public ArrayStack2(int maxSize) {
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

	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("栈空，没有数据");
		}
		return stack[top];
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

	// 返回运算符的优先级，优先级使用数字表示
	// 数字越大则优先级越高
	public int priority(char oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1;
		}
	}

	// 判断是否是一个运算符
	public boolean isOper(char oper) {
		return oper == '+' || oper == '-' || oper == '*' || oper == '/';
	}

	// 计算方法
	public int cal(int num1, int num2, int oper) {
		int res = 0;
		switch (oper) {
		case '+':
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1;
			break;
		case '*':
			res = num1 * num2;
			break;
		case '/':
			res = num2 / num1;
			break;
		default:
			break;
		}
		return res;
	}
}
