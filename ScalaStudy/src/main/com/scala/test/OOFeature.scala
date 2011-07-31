package com.scala.test
/**
 * Single Object
 * 伴生对象
 * 当单例对象与某个类共享同一个名称时，他被称作是这个类的伴生对象：companion object。
 * 你必须在同一个源文件里定义类和它的伴生对象。
 * 类被称为是这个单例对象的伴生类：companion class。
 * 类和它的伴生对象可以互相访问其私有成员
 * 单例对象会在第一次被访问的时候初始化
 * 不与伴生类共享名称的单例对象被称为孤立对象
 */
object OOFeature {
	//引入可变Map
	import scala.collection.mutable.Map
	private val cache = Map[String,String]()
	def put(key:String,value:String){
		cache +=(key -> value)
	}
	def printself():Unit = {
		//(A,B)元组
		cache.foreach(entry => println(entry._2))
	}
	
}
object OOFeatureMain{
	def main(args : Array[String]) :Unit ={
		val ooInstance = new OOFeature()
		
		OOFeature.put("1","huangzhi");
		OOFeature.printself();
		ooInstance.operationFeature();
	}
}
/**
 * 伴生类
 * @author Administrator
 *
 */
class OOFeature{
	//instance variable
	//在Scala里把成员公开的方法是不显式地指定任何访问修饰符。
	var sum = 0;
	/**
		*1、Scala里方法参数的一个重要特征是它们都是val，不是var
		*2、Scala方法将返回方法中最后一个计算得到的值
		*3、假如某个方法仅计算单个结果表达式，则可以去掉大括号
		*4、定义副作用为在方法外部某处改变状态或者执行I/O活动
		*5、是当你去掉方法体前面的等号时，它的结果类型将注定是Unit。不论方法体里面包含什么都不例外，因为Scala编译器可以把任何类型转换为Unit
		*   注意与def add(x:Int){sum += x}区别
	 */
	def add(x:Int):Unit={
		sum += x;
	}
	def checksum(): Int = ~(sum & 0xFF) + 1
	//分号推断规则
	//除非以下情况的一种成立，否则行尾被认为是一个分号：
	//1、疑问行由一个不能合法作为语句结尾的字结束，如句点或中缀操作符。
	//2、下一行开始于不能作为语句开始的字。
	//3、行结束于括号(...)或方框[...]内部，因为这些符号不可能容纳多个语句。
	def semicolon(x:Int,y:Int){
		x + 
		y
		(x
				+
				y)
	}
	/**
	 * The Predef object provides definitions that are accessible in all Scala compilation units without explicit qualification
	 * Scala隐式引用了包java.lang和scala的成员，和名为Predef的单例对象的成员，到每个Scala源文件中。
	 * Scala的发布包里还包括了一个叫做fsc（快速Scala编译器）的Scala编译器后台服务：daemon。
	 * 你可以这样使用： $ fsc ChecksumAccumulator.scala Summer.scala 
	 * 第一次执行fsc时，会创建一个绑定在你计算机端口上的本地服务器后台进程。
	 * 然后它就会把文件列表通过端口发送给后台进程去编译，后台进程完成编译。下一次你执行fsc时，
	 * 后台进程就已经在运行了，于是fsc将只是把文件列表发给后台进程，它会立刻开始编译文件。
	 * 使用fsc，你只需要在第一次等待Java运行时环境的启动。
	 * 如果想停止fsc后台进程，可以执行fsc -shutdown来关闭
	 */
	def perdefFeature(){
		
	}

  def createTime():Long = {
    1234;
  }

	/**
	 * Scala里的操作符不是特殊的语言语法：任何方法都可以是操作符。使用方法的方式使它成为操作符。
	 * 如果写成s.indexOf('o')，indexOf就不是操作符。
	 * 不过如果写成，s indexOf 'o'，那么indexOf就是操作符了，因为你以操作符标注方式使用它
	 */
	def operationFeature() :Unit = {
		var s = "huangzhi Test Baobei"
		//下面两种写法分别代表方法调用方式和操作符使用方式
		//中缀操作符：调用的方法位于对象和传递给方法的参数或若干参数之间，如“7 + 2”
		//另外两种操作符标注：前缀和后缀。
		//前缀标注中，方法名被放在调用的对象之前，如，-7里的‘-’。
		//后缀标注中，方法放在对象之后，如，“7 toLong”里的“toLong”。	
		//前缀和后缀操作符都是一元：unary的：它们仅带一个操作数	
		println(s.indexOf('z'))	
		println(s indexOf 'z' )	
		
		//-2 == 2.unary_-
		//可以当作前缀操作符用的标识符只有+，-，!和~
		//后缀操作符是不用点或括号调用的不带任何参数的方法
		println(-2)
		println(2.unary_-)
		
		println(s.toLowerCase)
		println(s toLowerCase)
	}
	/**
	 * ==已经被仔细地加工过
	 * 首先检查左侧是否为null，如果不是，调用equals方法。
	 * 由于equals是一个方法，因此比较的精度取决于左手边的参数
	 * 这种类型的比较对于不同的对象也会产生true，只要他们的内容是相同的并且它们的equals方法是基于内容编写的
	 */
	def equalFeature(){
		
	}
}
