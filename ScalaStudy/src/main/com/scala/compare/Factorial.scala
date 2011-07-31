package com.scala.compare


object Factorial {
	val ZERO = BigInt.int2bigInt(0)
	val ONE = BigInt.int2bigInt(1)
	def factorial(n:BigInt):BigInt= n match {
		case ZERO => ONE
		case ONE => ONE
		case _ => n*factorial(n-1)
	}
	def main(args:Array[String]){
		val i = 50000
		val n = BigInt.int2bigInt(i)
		val start = new java.util.Date
		println(factorial(n))
		val duration = (new java.util.Date()).getTime - start.getTime
		println("duration scala ="+duration)
	}
}