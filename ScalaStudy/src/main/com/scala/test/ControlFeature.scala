package com.scala.test

object ControlFeature {
	def main(args:Array[String]){
		/**
		 * 尽可能寻找使用val的机会。它们能让你的代码既容易阅读又容易重构。下面第二种用法优于第一种用法
		 * 好处：
		 * 1、使用val是函数式的风格，并能以差不多与Java的final变量同样的方式帮到你。它让代码的读者确信这个变量将永不改变，节省了他们扫描变量字段的所有代码以检查它是否改变的工
		 * 2、能更好地支持等效推论：equational reasoning。在表达式没有副作用的前提下，引入的变量等效于计算它的表达式
		 */
		var filename = "default.txt" 
		if (!args.isEmpty) filename = args(0)
		//Scala的if是能返回值的表达式
		val valfilename = if (!args.isEmpty) args(0) else "default.txt";
		
		forfeature();
	}
	
	/**
	 * while和do-while结构被称为“循环”，不是表达式，因为它们不产生有意义的结果，结果的类型是Unit
	 * 比较下面两个方法
	 * gcdLoop写成了指令式风格，使用了var和while循环，而gcd更函数式风格，采用了递归（gcd调用自身）并且不需要var
	 * @param x
	 * @param y
	 * @return
	 */
	def gcdLoop(x: Long, y: Long): Long = { 
		var a = x 
		var b = y 
		while (a != 0) 
		{ 
			val temp = a 
			a = b % a 
			b = temp 
		} 
		b
	}
	def gcd(x: Long, y: Long): Long = if (y == 0) x else gcd(y, x % y)
	
	def forfeature()
	{
		//枚举集合类
		val filesHere = (new java.io.File(".")).listFiles 
		for (file <- filesHere) println(file)
		for (i <- 1 to 4) println("Iteration " + i)
		
		//过滤
		//可以通过把过滤器：filter：一个if子句加到for的括号里做到
		for (file <- filesHere if file.getName.length()>1 ) println(file)
		
		//如果在发生器中加入超过一个过滤器，if子句必须用分号分隔
		for ( file <- filesHere 
				if file.isFile; 
				if file.getName.endsWith(".scala") 
			)
		println(file)
		
		grep("svn*")
		//本地函数	
		def fileLines(file:java.io.File) = {
			scala.io.Source.fromFile(file).getLines.toList
		}
		//两种嵌套循环方式
		def grep(pattern:String) ={
			
			for{
				file <- filesHere
				if file.getName.endsWith(".svn")
				i <- 1 to 4
				if i<3
			}
			println(file + ": " + i)
			
			for{ file <- filesHere if file.getName.endsWith(".svn")}
			{
				println(file )
				for(i <- 1 to 4 if i<3 ) println(file + "+ " + i)
			}
			//变量绑定
			for{
				file <- filesHere
				if file.getName.endsWith(".svn")
				i <- 1 to 4
				//绑定的变量被当作val引入和使用，不过不用带关键字val
				testval = i*3;
				if i<3
			}
			println(file + ": " + testval)
			
			//可以创建一个值去记住每一次的迭代。只要在for表达式之前加上关键字yield
			//for {子句} yield {循环体}
			val secFiles = for( file <- filesHere if file.getName.length>4 ) yield file
			for( file <- secFiles )println(file)
			
		}
		
		def tryFeature(n:Int){
			val half = if (n % 2 == 0) n / 2 else throw new RuntimeException("n must be even")
			//大多数Scala控制结构一样，try-catch-finally也产生
			//通常最好还是避免从finally子句中返回值。最好是把finally子句当作确保某些副作用，如关闭打开的文件，发生的途径
			def f(): Int = try { return 1 } finally { return 2 }
			def g(): Int = try { 1 } finally { 2 }
		}
		
		//与Java的switch相比最显着的差别，或许是match表达式也能产生值
		def matchFeature()
		{
			val firstArg = "salt"
			val friend = firstArg match { 
				case "salt" => "pepper" 
				case "chips" => "salsa" 
				case "eggs" => "bacon" 
				case _ => "huh?" } 
			println(friend)
		}
		
	    val args = Array("huangzhi","test")
		//离开break和continue
		def nobreakcontinue(){
			val args = Array("huangzhi","test")
			var i = 0 
			var foundIt = false 
			while (i < args.length && !foundIt) 
			{ 
				if (!args(i).startsWith("")) 
				{ 
					if (args(i).endsWith(".scala")) 
						foundIt = true 
				} 
				i = i+1
			}
		}
		def searchFrom(i: Int): Int = {
			if (i >= args.length) -1// 不要越过最后一个参数 
			else if (args(i).startsWith("-")) searchFrom(i + 1)// 跳过选项 
			else if (args(i).endsWith(".scala")) i // 找到！ 
			else searchFrom(i + 1) // 继续找 
		}
	}
}