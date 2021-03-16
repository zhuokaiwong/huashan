package com.zhuokaiwong.mydemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface ICalc {
	int add(int a, int b);

	int sub(int a, int b);

	int mul(int a, int b);

	int div(int a, int b);
}

class MyHandler implements InvocationHandler {
	private Object target;

	public MyHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(method.getName() + "方法开始");
		Object result = method.invoke(target, args);
		System.out.println(method.getName() + "方法结束，结果是" + result);
		return result;
	}

}

class CalcImpl implements ICalc {

	@Override
	public int add(int a, int b) {
		return a + b;
	}

	@Override
	public int sub(int a, int b) {
		return a - b;
	}

	@Override
	public int mul(int a, int b) {
		return a * b;
	}

	@Override
	public int div(int a, int b) {
		return a / b;
	}

}

/**
 * 代理模式
 * 
 * @author timeb
 *
 */
public class AppTest {
	public static void main(String[] args) {
		ICalc c = new CalcImpl();// 目标对象(真实对象)
//		Class<?>[] interfaces = c.getClass().getInterfaces();
		/*int add = c.add(3, 8);
		System.out.println(add);*/
		// 创建代理对象
		// 第1个参数:ClassLoader对象
		ClassLoader classLoader = AppTest.class.getClassLoader();
		ICalc proxy = (ICalc) Proxy.newProxyInstance(classLoader, new Class[] { ICalc.class }, new MyHandler(c));
		System.out.println(proxy.add(11, 22));
		System.out.println(proxy.div(11, 22));
	}
}
