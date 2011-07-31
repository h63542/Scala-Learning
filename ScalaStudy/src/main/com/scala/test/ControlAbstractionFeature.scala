package com.scala.test
import java.io.File
import scala.util.matching.Regex
import java.util.regex.Pattern; 
import java.io.PrintWriter
/**
 * All functions are separated into common parts, which are the same in every
 * invocation of the function, and non-common parts, which may vary from
 * one function invocation to the next
 * 
 * 函数的每一次调用中，你都可以把不同的函数值作为参数传入，于是被调用函数将在每次选用参数的时候调用传入的函数值。
 * 这种高阶函数：higher-order function——带其它函数做参数的函数——给了你额外的机会去组织和简化代码
 * 
 * 高阶函数的一个好处是它们能让你创造控制抽象从而使你减少代码重复
 */
object ControlAbstractionFeature {
	
	val assertionsEnabled = true;
	
	def main(args:Array[String]) : Unit= {
		def filesHere = (new java.io.File(".")).listFiles
		
		
//		for(file <- filesHere)
//			println(file.getName)
//		
//		
//		for(onefile <- filesEnding("svn",filesHere)) println(onefile.getName)
//		
//		for(onefile <- filesMatching("src",filesHere,_.endsWith(_)))  println(onefile.getName)
//		
//		for(onefile <- filesMatching(filesHere,(_.endsWith("svn"))))  println(onefile.getName)
		
		for(onefile <- filesRegex("^s" ,filesHere))println(onefile.getName)
		
		
		println (containNeg(List(1,2,3,-1))) 
		
		println(List(1,2,3,-1).exists(_<0))
		
		//curry化，利用偏应用函数表示新的二维函数
		var sum5 = curriedSum(5)_
		println(sum5(5))
		
		//Scala的任何方法调用，如果你确实只传入一个参数，就能可选地使用大括号替代小括号包围参数
		println("Hello, world!")
		println { "Hello, world!" }
		
		openWithPrintWriter( new File("date.txt"), writer => writer.println(new java.util.Date) )
		withPrintWriter(new File("date2.txt")){
			writer => writer.println(new java.util.Date)
		}
		
		byNameAssert(5 > 3)
	}
	//curry化
	def curriedSum(x:Int)(y:Int) = x+y
	
	
	def openWithPrintWriter(file:File,op:PrintWriter=>Unit) = {
		val writer = new PrintWriter(file) 
		try { 
			op(writer) 
			} 
		finally { 
			writer.close() 
			}
	}
	/**
	 * withPrintWriter方法不同于语言的内建控制结构，如if和while，在于大括号之间的代码带了参数。
	 * withPrintWriter方法需要一个类型为PrintWriter的参数。这个参数以“writer =>”方式显示出来
	 * 
	 * 贷出模式：loan pattern，因为控制抽象函数，如withPrintWriter，打开了资源并“贷出”给函数
	 */
	def withPrintWriter(file:File)(op:PrintWriter=>Unit) = {
		val writer = new PrintWriter(file) 
		try { 
			op(writer) 
			} 
		finally { 
			writer.close() 
			}
	}
	
	def byNameAssert(predicate: => Boolean) = if (assertionsEnabled && !predicate) throw new AssertionError	

	
	
	def filesEnding(query: String,filesHere:Array[File]) = {
		for(file <- filesHere; if(file.getName.endsWith(query)))
			yield file
	}

			
	def filesMatching(query:String,filesHere:Array[File],macther:(String,String)=>Boolean) = {
		for(file <- filesHere; if macther(file.getName,query))
			yield file
		}
	
	def filesMatching(filesHere:Array[File],macther:String => Boolean) = {
		for(file <- filesHere; if macther(file.getName))
			yield file
		
	}
	
	def filesRegex(query:String,filesList:Array[File]) = 
	{
		filesMatching(filesList,_.matches(query))
	}
	
	def containNeg(list:List[Int]):Boolean = {
		var exist = false
		for(num <- list) if(num<0) exist = true
		exist
	}
	
	
	
}