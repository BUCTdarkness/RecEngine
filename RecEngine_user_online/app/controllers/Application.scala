package controllers

import models.{RecUserItem, Product}
import play.api.mvc._
import play.api.libs.json._
import scala.math.min
import scala.collection.mutable.ArrayBuffer
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

object Application extends Controller {
	def connectJDBC(clientid:String):List[String]={
		try {
			val t= DB.withConnection("prod"){ implicit connection =>
			        SQL("""SELECT CATEGORY_CODE from ( SELECT distinct c.CATEGORY_CODE from mb_user_category u
					left join mb_category c on u.CATEGORY_ID=c.oid
					where u.CLIENT_ID={clientid} and c.oid is not null ) t ; """)
					.on("clientid"->clientid )
					.as(str("CATEGORY_CODE")*)
					//.as(parser.simple*).map(parser=>List(parser.CATEGORY_CODE.toString))

			}
			t.toList //.map(x=>{if(x.length==1){"00"+x} else if(x.length==2){"0"+x} else x})
		} catch {
			case e:Exception=>List()
		}
	}
	var flag=0; //test
	val recMap2 = RecUserItem.recMap
	val productMap_notfilted = Product.productMap
	val itemsetcate = scala.io.Source.fromFile("/root/data/itemsetcate").getLines.map(x=>x.split("\t"))  // (itemcode, category)
	val cateMap = scala.collection.mutable.Map[String,String]()
	itemsetcate.foreach(x=>cateMap+=(x(0)->x(1)))
	val globalHotItems_notfilted = scala.io.Source.fromFile("/root/data/globalhotitems300").getLines.toArray // the global hottest 200 items.
	val cateHotItems_notfilted = scala.io.Source.fromFile("/root/data/catehostitems300").getLines.toArray.map(x=>x.split(",")) // the 200 hottest items in each category. (category,itemcode)
	val invaild_items = scala.io.Source.fromFile("/root/data/distinct_invalid").getLines.toArray
	val globalHotItems=globalHotItems_notfilted.filterNot(x=>invaild_items.contains(x))
	val cateHotItems=cateHotItems_notfilted.filterNot(x=>invaild_items.contains(x(1)))
	val productMap = productMap_notfilted.filterKeys(x=>(invaild_items.contains(x)==false))
		
	def index(userID: String, pageNum: Int, pageSize: Int) = Action { request =>

		var recItems = Array[String]()
		var returnItems = ""
		var userCates = connectJDBC(userID)  // to connect the mysql database and get the categorys the user like.
		
		try {
			if (recMap2 contains userID) {
				var cateItems=Array[String]()
				var commonItems=Array[String]()
				val Items_notfilted = recMap2(userID).split(",")
				val Items=Items_notfilted.filterNot(x=>invaild_items.contains(x))
				if(Items.length>0){
				cateItems=Items.filter(x=>userCates.contains(cateMap(x))) // the recommended items in rec list
				commonItems=Items.filterNot(x=>userCates.contains(cateMap(x)))
				}
			
				if(cateItems.length>=200) recItems=cateItems.slice(0,200)
				else {
					val itemsNeeded=200-cateItems.length
					if(Items.length>=200){
						recItems=cateItems++commonItems.slice(0,itemsNeeded)
					}
					else {
					if(userCates.length==0) recItems=(cateItems++commonItems)++globalHotItems.slice(0,itemsNeeded-commonItems.length)
					else {
						val num=200-cateItems.length-commonItems.length
						recItems=(cateItems++commonItems)++cateHotItems.filter(x=>userCates.contains(x(0))).map(x=>x(1)).slice(0,num)
						}
					}
				}
			}
			else {
				if(userCates.length==0) recItems=globalHotItems.slice(0,200)
					else {
						//if(userCates.length==1) recItems=cateHotItems.filter(x=>x(0)==userCate(0)).map(x=>x(1)).slice(0,200)
						//else if(userCates.length==2) recItems=cateHotItems.filter(x=>(x(0)==userCates(0) or x(0)==userCates(1))).map(x=>x(1)).slice(0,200) // so i need to make a rank of items.
						//else if(userCates.length==3) recItems=cateHotItems.filter(x=>(x(0)==userCates(0) or x(0)==userCates(1)) or x(0)==userCates(2)).map(x=>x(1)).slice(0,200)
						recItems=cateHotItems.filter(x=>userCates.contains(x(0))).map(x=>x(1)).slice(0,200)
					}
			}

			// to get the 200 itemcodes
			var start = pageNum * pageSize
			var end = min((pageNum + 1) * pageSize - 2, recItems.length - 2)

			if (recItems.length < 2) {
				returnItems = returnItems + recItems(0) + "," + productMap(recItems(0))
			} else if (recItems.length < pageNum * pageSize) {
				returnItems = ""
			} else {

				if (pageNum < 0 || pageSize < 0 || recItems.length < pageSize) {
					start = 0
					end = recItems.length - 2
				} else if (recItems.length > (pageNum + 1) * pageSize) {
					end = (pageNum + 1) * pageSize - 2
				} else if (recItems.length > pageNum * pageSize && recItems.length < (pageNum + 1) * pageSize) {
					end = recItems.length - 2
				}
				flag=end
				var tag=start
				var loop=0
				for (loop <- start to recItems.length ; if tag<=end)  {
					try {
					returnItems = returnItems + recItems(loop) + "," + productMap(recItems(loop)) + ";"
					tag+=1
					} catch {
						case e:Exception=>"ERROR"
					}
				}
				returnItems = returnItems + recItems(end + 1) + "," + productMap(recItems(loop + 1))
				
			}
		}
		catch {
		case e:Exception=>"Error"	
		}
			Ok(Json.obj("status" -> "OK", "total" -> "100", "items" -> returnItems))
			//Ok(views.html.index("test result:"+recItems(0)+recItems(1)+recItems(2)) )// test output
		}
}

