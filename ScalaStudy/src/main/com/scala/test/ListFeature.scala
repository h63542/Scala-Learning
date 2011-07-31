package com.scala.test

import javax.swing.text.DefaultEditorKit.InsertBreakAction
import com.scala.test.ListFeature
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing.Validation
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
import com.sun.corba.se.spi.legacy.connection.LegacyServerSocketEndPointInfo
import javax.jws.Oneway
import javax.management.remote.rmi._RMIConnection_Stub

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-7-28
 * To change this template use File | Settings | File Templates.
 */
object ListFeature{
   def main(args:Array[String]){
     val sortList = List(1,2,3,4,8,9,5)
     val listf :InsertFeature = new InsertFeature();
     val aftersort:List[Int] = listf.isort(sortList)
     aftersort.foreach(one =>print(one))
     val aftersort_1:List[Int] = listf.isortMatch(sortList)
     aftersort_1.foreach(one =>print(one))
     //连接列表
     (aftersort:::aftersort_1).foreach(one=>println(one*2))
     //列表长度
     println("aftersort.lenegth"+aftersort.length)
     //访问列表尾部 init返回除了最后一个元素之外余下的列表
     aftersort.init.foreach(one=>println(one))
     println("aftersort.last"+aftersort.last)
     //循环列表
     var forlist = for{one <-aftersort } yield one*2
     forlist.foreach(one=>println(one*2))
     for(one <- aftersort)println(one*2)
     //反转列表
     val abcde = List('a','b','c','d','e')
     println("反转列表")
     abcde foreach(one=>println(one))
     abcde.reverse foreach(one=>println(one))
     println("前缀与后缀")
     abcde.take(2).foreach(one=>println(one))
     abcde.drop(2).foreach(one=>println(one))
     abcde.splitAt(2)._1.foreach(one=>println(one))

     println("--------------------------------------------------")
     var indexs = List("dsaa","Asd","sadc","Gdfd","gdfdd","gaf","gabdddddd","eg")
     var map = indexs.groupBy(_.head.toUpperCase).mapValues((xs:List[String])=>xs.sort((x:String,y:String)=>x.toUpperCase.compare(y.toUpperCase)<0))
     println(map)
      var map_1 = indexs.groupBy(_.head.toUpperCase).mapValues((xs)=>xs.sort((x,y)=>x.toUpperCase.compare(y.toUpperCase)<0))
          println(map_1)
   }
}
class InsertFeature {
  def isort(xs:List[Int]):List[Int]={
    if (xs.isEmpty) Nil
    else insert(xs.head,isort(xs.tail))
  }
  def insert(x:Int,xs:List[Int]):List[Int] = {
   if (xs.isEmpty || x<=xs.head) x::xs
   else xs.head::insert (x,xs.tail)
  }
  def isortMatch(xs:List[Int]):List[Int]= xs match{
    case List() => List()
    case x::xs1 => insertMatch(x,isortMatch(xs1))

  }
  def insertMatch(x:Int,xs:List[Int]):List[Int] = xs match {
    case List() => x::Nil
    case y::xs1 => if (x<=y) x::xs
                    else y::insertMatch (x,xs1)

  }

  def msort[T](less:(T,T) => Boolean)(xs:List[T]):List[T] = {
    def merge(xs:List[T],ys:List[T]):List[T] = (xs,ys) match{
      case (Nil,_) => ys
      case (_,Nil) => xs
      case (x::xs1,y::ys1) =>
        if (less(x,y))x::merge (xs1,ys)
        else y::merge (xs,ys1)
    }
    val n = xs.length / 2
    if (n==0) xs
    else{
      val (ys,zs) = xs splitAt n
      merge(msort(less)(ys),msort(less)(zs))
    }
  }

  def sortIndex(indexs:List[String])={
     indexs.sort(_.charAt(0)>_.charAt(0)).foreach(one=>println(one))
  }

}