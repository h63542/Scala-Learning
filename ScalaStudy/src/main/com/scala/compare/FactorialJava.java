package com.scala.compare;

import java.math.BigInteger;
import java.util.Date;
public class FactorialJava{
	public static BigInteger factorial(BigInteger n){
		if (n.equals(BigInteger.ZERO))
		return BigInteger.ONE;
		else if (n.equals(BigInteger.ONE))
		return BigInteger.ONE;
		else
		return n.multiply(factorial(n.subtract(BigInteger.ONE)));
	}
	public static void main(String[] args){
		BigInteger n = new BigInteger("50000");
		Date start = new Date();
		System.out.println(factorial(n));
		long duration = (new Date()).getTime() - start.getTime();
		System.out.println("duration java="+duration);
	}
}