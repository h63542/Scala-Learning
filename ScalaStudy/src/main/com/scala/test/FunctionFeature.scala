package com.scala.test
/**
 * 不可变对象的权衡:
 * 优点：
 * 1、不可变对象常常比可变对象更具逻辑性，因为它们没有随着时间而变化的复杂的状态空间
 * 2、你可以很自由地传递不可变对象，而或许需要在把可变对象传递给其它代码之前，需要先建造个以防万一的副本
 * 3、没有机会能让两个同时访问不可变对象的线程破坏它合理构造的状态
 * 4、不可变对象让哈希表键值更安全
 * 缺点：们有时需要复制很大的对象图而可变对象的更新可以在原地发生。有些情况下这会变得难以快速完成而可能产生性能瓶颈
 */
object FunctionFeature{
	def main(args:Array[String]){
		val a:Rational = new Rational(1,2)
		val b:Rational = new Rational(1,2)
		println( a+b ) ;
		/**
		 * 隐式转换
		 * 你可以创建一个在需要的时候能自动把整数转换为分数的隐式转换
		 * 方法前面的implicit修饰符告诉编译器若干情况下自动调用
		 */
		
		
		implicit def intToRational(x: Int) = new Rational(x)
		println( 3 * a) ;
		
		
		
		/**
		 * identifier
		 * 1\字母数字标识符:起始于一个字母或下划线,$’字符也被当作是字母，但是被保留作为Scala编译器产生的标识符之用
		 * 2\操作符标识符：operator identifier由一个或多个操作符字符组成
		 * 3\混合标识符：mixed identifier由字母数字组成，后面跟着下划线和一个操作符标识符
		 * 4\文本标识符：literal identifier是用反引号`...`包括的任意字串
		 */
		var huangzhi1 = "huangzhi1"
		var ++ = "++"
		var myvar_ = "myvar_"
		var `yield` = "`yield`"	
		println( huangzhi1) ;
		println( ++  ) ;
		println( myvar_ ) ;
		println( `yield` ) ;
		
		
		//函数文本
		var increase = (x: Int) => x + 1
		println(increase(99))
		
		val someNumbers = List(-11, -10, -5, 0, 5, 10)
		//函数参数
		someNumbers.foreach((x: Int) => println(x))
		someNumbers.filter((x: Int) => x > 0)
		//函数文本的短格式
		//一种让函数文本更简短的方式是去除参数类型
		someNumbers.filter((x) => x > 0)
		//第二种去除无用字符的方式是省略类型是被推断的参数之外的括号
		someNumbers.filter(x => x > 0)
		
		//占位符语法
		someNumbers.filter(_ > 0)
		/**
		 * 请注意_ + _将扩展成带两个参数的函数文本。这也是仅当每个参数在函数文本中最多出现一次的情况下你才能使用这种短格式的原因。
		 * 多个下划线指代多个参数，而不是单个参数的重复使用。
		 * 第一个下划线代表第一个参数，第二个下划线代表第二个，第三个……，如此类推
		 */
		val f = (_: Int) + (_: Int)
		/**
		 * 偏应用函数：partially applied function。Scala里，当你调用函数，传入任何需要的参数，你就是在把函数应用到参数上
		 */
		def sum(a: Int, b: Int, c: Int) = a + b + c
		val a_sum = sum _
		a_sum(1,2,3)
		val b_sum = sum(1, _: Int, 3)
		b_sum(0)
		
	}
}

/**
 * 1、当注意到的是如果类没有主体，就不需要指定一对空的大括号（当然你如果想的话也可以）。
 * 2、在类名，Rational，之后括号里的n和d，被称为类参数：class parameter。
 * 3、Scala编译器会收集这两个类参数并创造一个带同样的两个参数的主构造器
 */
class Rational(n:Int,d:Int){
	require(d != 0)
	private val g = gcd(n.abs, d.abs)
	val numer:Int = n/g
	val denom:Int = d/g
	
	/**
	 * 从构造器
	 * Scala里的每一个从构造器的第一个动作都是调用同一个类里面其他的构造器。
	 * 换句话说就是，每个Scala类里的每个从构造器都是以“this(...)”形式开头的
	 * 因此主构造器是类的唯一入口点
	 */
	def this(n:Int) = this(n,1)
	
	override def toString = numer+"/"+denom
	
	def add(that:Rational) :Rational = {
		new Rational(n*that.denom + that.numer*d  ,d*that.denom)
	}
	/**
	 * 操作符方法
	 */
	def +(that:Rational) :Rational =add(that)
	
	def -(that: Rational): Rational = new Rational( numer * that.denom - that.numer * denom, denom * that.denom ) 
	//重载：overload
	def -(i: Int): Rational = new Rational(numer - i* denom, denom)
	
	def *(that: Rational): Rational = new Rational(numer * that.numer, denom * that.denom) 
	def *(i: Int): Rational = new Rational(numer * i, denom)
	
	def lessThan(that: Rational) = this.numer * that.denom < that.numer * this.denom
	
	def max(that:Rational):Rational = {
		if (lessThan(that)) that else this
	}
	private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}



/**
 * 函数文本被编译进一个类，类在运行期实例化的时候是一个函数值：function value。2
2 任何函数值都是某个扩展了若干scala包的FunctionN特质之一的类的实例，如Function0是没有参数的函数，Function1是有一个参数的函数等等。
每个FunctionN特质有一个apply方法用来调用函数。 因此函数文本和值的区别在于函数文本存在于源代码，而函数值存在于运行期对象
 */
class FunctionFeature {

}
