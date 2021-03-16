package com.zhuokaiwong.singleresponsibility;

import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;

public class negative {

	public static void main(String[] args) throws Exception {
		Reader in = new FileReader("D:\\io\\test1.txt");
		int n = in.read();
		System.out.println((char) n);
		n = in.read();
		System.out.println(n);
		in.close();

		String s = "京";
		byte[] b = s.getBytes("gbk");
		System.out.println(Arrays.toString(b));
	}

}
