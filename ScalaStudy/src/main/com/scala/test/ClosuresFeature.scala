package com.scala.test
/**
 * (x: Int) => x + more // more是多少？
 *  函数把“more”加入参考，但什么是more呢？
 *  从这个函数的视点来看，more是个自由变量：free variable，因为函数文本自身没有给出其含义。
 *  相对的，x变量是一个绑定变量：bound variable，因为它在函数的上下文中有明确意义：被定义为函数的唯一参数，一个Int
 *
 *  函数值是关闭这个开放术语(x: Int) => x + more的行动的最终产物，
 *  得到的函数值将包含一个指向捕获的more变量的参考，因此被称为闭包
 */
object ClosuresFeature {
	def main(args:Array[String]) {
		var more = 1
		//由于函数文本(x: Int) => x + more带有自由变量，所以对应的函数值addMore就叫闭包
		val addMore = (x: Int) => x + more
		println(addMore(10))
		
		more = 11
		println(addMore(10))
		
		/**
		 * Scala的闭包捕获了变量本身，而不是变量指向的值。
		 * 依照(x: Int) => x + more创建的闭包看到了闭包之外做出的对more的变化。
		 * 闭包对捕获变量作出的改变在闭包之外也可见。
		 */
		var sum = 0
		val someNumbers = List(-11, -10, -5, 0, 5, 10)
		someNumbers.foreach(sum += _)
		println(sum)
		
		/**
		 * 可变参数
		 */
		echo("huangzhi","ganyue")
		echo("huangzhi")
		val arr = Array("What's", "up", "doc?")
		
		echo(arr:_*)
		
	}
	/**
		 * 最后一个动作调用自己的函数，被称为尾递归：tail recursive。
		 * Scala编译器检测到尾递归就用新值更新函数参数，然后把它替换成一个回到函数开头的跳转
		 * 如果调试堆栈会比较麻烦，如果需要查看堆栈需要打开参数配置 -g:notailcalls
		 */
	def bang(x: Int): Int = 
		if (x == 0) throw new Exception("bang!") 
		else bang(x-1)
	/**
	 * 可变参数
	 */
	def echo(args : String*){
		for(arg <- args)
		println(arg)
		
	}
}