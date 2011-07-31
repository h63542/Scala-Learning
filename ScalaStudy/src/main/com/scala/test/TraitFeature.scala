package com.scala.test

object TraitFeature {

}
/**
 * 第一点，特质不能有任何“类”参数
 * 
 * 
 * 类和特质的另一个差别在于不论在类的哪个角落，super调用都是静态绑定的，在特质中，它们是动态绑定的。
 * 如果你在类中写下“super.toString”，你很明确哪个方法实现将被调用。
 * 然而如果你在特质中写了同样的东西，在你定义特质的时候super调用的方法实现尚未被定义。
 * 调用的实现将在每一次特质被混入到具体类的时候才被决定。
 * 这种处理super的有趣的行为是使得特质能以可堆叠的改变：stackable modifications方式工作的关键
 * 
 * 特质的一种主要应用方式是可以根据类已有的方法自动为类添加方法。
 * 也就是说，特质可以丰满一个瘦接口，把它变成胖接口
 * @author Administrator
 *
 */
trait Philosophical { 
	def philosophize() { 
		println("I consume memory, therefore I am!") 
		}
}

class Animal 
trait HasLegs 
class Frog extends Animal with Philosophical with HasLegs { 
	override def toString = "green" 
	override def philosophize() { 
		println("It ain't easy being "+ toString +"!") 
	}		
}