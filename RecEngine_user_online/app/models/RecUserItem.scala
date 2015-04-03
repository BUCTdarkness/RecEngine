package models
import scala.io.Source
import scala.collection.immutable.HashMap
case class RecUserItem (userID: String, items: String)
object RecUserItem {
  var recMap =  HashMap[String, String]()
  for (line <- Source.fromFile("/root/data/user_Recitem_100").getLines) {
  try {
    val itemMap = line split ":"
    recMap += (itemMap(0) -> itemMap(1))
	} catch {
	case e:Exception=>"ERROR"
	}
  }
}
