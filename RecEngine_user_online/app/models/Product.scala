package models

import scala.io.Source
import scala.collection.immutable.HashMap

case class Product(ean: Long, title: String, price: String,picUrl: String)

object Product {
//iteminfo_whole item_info_noTitle item_info_addHot  iteminfo_withhot300
  var productMap =  HashMap[String, String]()
  val file=Source.fromFile("/root/data/iteminfo").getLines
  for (line <- file) { //product with title:  item_info_100
  try {
    val itemMap = line split ":"
    productMap += (itemMap(0) -> itemMap(1))
	} catch {
	case e:Exception=>"ERROR"
	}
  }

//
//  var products =     Set(
//    Product(195825377, "Paperclips Large","$0.59 - 0.71","albu_701881492_00/1.0x0.jpg"),
//    Product(190669050, "Giant Paperclips","$6.6 - 6.85","albu_201881192_00/1.0x0.jpg"),
//    Product(194970789, "Paperclip Giant Plain","$0.2 - 0.22","albu_101881192_00/1.0x0.jpg"),
//    Product(197160865, "No Tear Paper Clip", "$0.2 - 0.22","albu_101881192_00/1.0x0.jpg"),
//    Product(197259588, "Zebra Paperclips","$6.6 - 6.85","albu_201881192_00/1.0x0.jpg")
//  )
//  def findAll = products.toList.sortBy(_.ean)

}
