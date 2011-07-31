package com.scala.test
/**
 * 主要理念：
 * 1、方法不应该有副作用，方法的唯一效果应该是计算并返回值
 * 2、满眼看不到副作用或者var
 * 3、崇尚val，不可变对象和没有副作用的方法，只有在特定需求和判断后才选择var，可变对象和有副作用的方法
 * @author Administrator
 *
 */
object CommonFeature {
/**
	 * 主函数
	 * @param args
	 */
	def main(args:Array[String]) : Unit = {
		//函数文本	
		functionLiteral();
		//通用特性
		commonfeature();
		//列表特性
		listFeature()
		//元祖特性
		tupleFeature()
		//Set特性
		setFeature()
		//Map特性 
		mapFeature()
	}
	/**
	 * 函数格式
	 * def 函数名（参数列表）：返回值 ={函数体 }
	 */
	def max(x:Int,y:Int):Int = {
		if(x>y)x
		else y
	}
	/**
	 * 函数文本
	 * （函数参数列表）=》函数体 
	 * (x:Int,y:Int) => x+y
	 */
	def functionLiteral() :Unit = {
		val lists : Array[String] = new Array[String](3);
		lists(0) = "huangzhi";
		lists(1) = "ganyue";
		lists(2) = "baobao";
		//类型推断
		//val lists  = Array{"huangzhi","ganyue","baobao"}
		//==var lists  = Array.apply("huangzhi","ganyue","baobao")
		
		//lists.foreach((one:String) => println(one));
		lists.foreach(one => println(one));
	}
	
	/**
	 * 1、如果方法仅带一个参数，你可以不带点或括号的调用它
	 * 2、Scala里所有的操作符都是方法调用
	 * 3、当在一个或者多个值或者变量外使用括号时，Scala会自动把它转换为对名为apply的方法调用test(i)==test.aplly(i)
	 * 4、当带有括号并包括一到若干参数的变量赋值时，编译器会自动把它转化为对带有括号里参数和等号右边的对象的update方法的调用
	 * lists(0) = "huangzhi"; == lists.update(0,"huangzhi")
	 */
	def commonfeature():Unit = {
		//for(i <- 1.to(2))
		for(i <- 1 to 2)
		{
			println(i);
		}
	}
	/**
	 * @列表
	 * 具有不可变性
	 * 错误用法：list(0)=4
	 */
	def listFeature() = {
		val list = List(1,2,3)
		println(list(0))
	}
	/**
	 * @元组
	 * 元组具有不可变性
	 * 1、元组可以包含不同的类型的元素
	 * 2、使用点、下划线和基于1的元素索引访问
	 */
	def tupleFeature() :Unit = {
		var pairs = (99,"oh my god")
		println(pairs._1)
		println(pairs._2)
	}
	
	/**
	 * @Set
	 * Set在Scala中有两种特质：可变集合&不可变集合
	 * 默认为不可变集合
	 */
	def setFeature() : Unit = {
		import scala.collection.immutable.Set
		val immutableSet = Set("huangzhi","ganyue")
		immutableSet + "huangzhihuangzhi" 
		println(immutableSet)
		
		val mutableSet = Set("huangzhi","ganyue")
		mutableSet + "huangzhihuangzhi" 
		println(mutableSet)
	}
	/**
	 * @Map
	 * 不可变集合@可变集合
	 */
	def mapFeature(){
		import scala.collection.mutable.Map
		val treasureMap = Map[Int, String]()
		treasureMap +=(1 -> "Go to island.") 
		treasureMap +=(2 -> "Find big X on ground.") 
		treasureMap +=(3 -> "Dig.")
		println(treasureMap(1));
		
		//Map循环
		treasureMap.foreach(one => println(one._1+" "+one._2))
		treasureMap.foreach({case (a, b) => println(a + " " + b) })
		for ((k, v) <- treasureMap) println(k + " " + v) 
	}
}