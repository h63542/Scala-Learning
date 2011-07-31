package com.scala.test
import scala.actors._

object ThreadRing {
	val (nTokens, nProcs) = (20000, 10000)
	def main(args: Array[String]) {
		val last= Ring(null, 1)
		val h = Range(nProcs, 2, -1).foldLeft(last){(acc, i) => Ring(acc, i)}
		last.next = h
		h ! nTokens
	}
	case class Ring(var next: Ring, id: Int) extends Actor {
		start
		def act = loop {
			react {
				case 1 =>
				println("Done")
				println(id)
				System.exit(0)
				case token: Int =>
				next ! token - 1
				println(token)
				//fib(N_FIB)
			}
		}
	}
}