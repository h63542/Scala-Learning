package com.scala.test

object CompositionInheritance {

}

class CompositionInheritance {

}

/**
 * 无参数方法在Scala里是非常普通的。相对的，带有空括号的方法定义，如def height(): Int，被称为空括号方法：empty-paren method。
 * 推荐的惯例是在没有参数并且方法仅通过读含有对象的方式访问可变状态（专指其不改变可变状态）时，使用无参数方法。
 * 这个惯例支持统一访问原则：uniform access principle，
 * 
 * Scala里定义不带参数也没有副作用的方法为无参数方法，也就是说，省略空的括号，是鼓励的风格。
 * 另一方面，永远不要定义没有括号的带副作用的方法，因为那样的话方法调用看上去会像选择一个字段
 */
import Element.elem
abstract class Element { 
	def contents: Array[String] 
	def height: Int = contents.length 
	def width: Int = if (height == 0) 0 else contents(0).length 
	def above(that: Element): Element = elem(this.contents ++ that.contents)
	def beside(that: Element): Element = elem(
			for ( (line1, line2) <- this.contents zip that.contents ) yield line1 + line2 
			)
	override def toString = contents mkString "\n"
}

object Element { 
	def elem(contents: Array[String]): Element = new ArrayElement(contents) 
	def elem(chr: Char, width: Int, height: Int): Element = new UniformElement(chr, width, height) 
	def elem(line: String): Element = new LineElement(line) 
	}

abstract class OtherElement { 
	def contents: Array[String] 
	val height = contents.length 
	val width = if (height == 0) 0 else contents(0).length 
}

/**
 * 使类ArrayElement从类Element继承所有非私有的成员，
 * 并且使ArrayElement成为Element的子类型
 * 
 * 你省略extends子句，Scala编译器隐式地假设你的类扩展自scala.AnyRef，在Java平台上与java.lang.Object一致。因此，类Element隐式地扩展了类AnyRef
 * 
 * Scala仅为定义准备了两个命名空间，而Java有四个。Java的四个命名空间是字段，方法，类型和包。与之相对，
 * Scala的两个命名空间是1、 值（字段，方法，包还有单例对象） 2、类型（类和特质名）
 */
class ArrayElement(conts: Array[String]) extends Element {
	def contents: Array[String] = conts 
}

class LineElement(s: String) extends ArrayElement(Array(s)) { 
	override def width = s.length 
	override def height = 1 
}

class UniformElement( ch: Char, override val width: Int, override val height: Int ) extends Element {
	private val line = ch.toString * width 
	def contents = Array.make(height, line)
}
