package com.zhuokaiwong.mydemo;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class TestNonBlockingNIO {
	@Test
	public void client() throws IOException {
		// 获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		// 切换非阻塞模式
		sChannel.configureBlocking(false);
		// 分配指定大小的缓冲区
		ByteBuffer bb = ByteBuffer.allocate(1024);
		// 发送数据给服务器
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入:");
		while (sc.hasNext()) {
			System.out.println("请输入:");
			String str = sc.next();
			bb.put((new Date().toString() + "\n" + str).getBytes());
			bb.flip();
			sChannel.write(bb);
			bb.clear();
		}
		// 关闭通道
		sChannel.close();
	}

	@Test
	public void server() throws IOException {
		// 获取通道
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		// 切换到非阻塞模式
		ssChannel.configureBlocking(false);
		// 绑定连接
		ssChannel.bind(new InetSocketAddress(9898));
		// 获取选择器
		Selector selector = Selector.open();
		// 将通道注册到选择器上，并且指定监听事件
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		// 轮询式地获取选择器上已经准备就绪的事件
		while (selector.select() > 0) {
			// 获取当前选择其中所有已经注册的选择键(已经准备就绪的监听事件)
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey sk = it.next();// 获取准备就绪的事件
				if (sk.isAcceptable()) {// 判断具体是什么事件准备就绪
					SocketChannel sChannel = ssChannel.accept();// 若接受就绪则获取客户端连接
					// 切换到非阻塞模式
					sChannel.configureBlocking(false);
					// 将该通道注册到选择器上
					sChannel.register(selector, SelectionKey.OP_READ);
				} else if (sk.isReadable()) {
					// 获取当前选择器上读就绪状态的通道
					SocketChannel sChannel = (SocketChannel) sk.channel();
					ByteBuffer bb = ByteBuffer.allocate(1024);
					int len = 0;
					while ((len = sChannel.read(bb)) > 0) {
						bb.flip();
						System.out.println(new String(bb.array(), 0, len));
						bb.clear();
					}
				}
				it.remove();// 取消选择键SelectionKey
			}
		}
	}
}
