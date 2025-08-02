//val tripAdvisorItem = "https://www.tripadvisor.com/AttractionProductReview-g295371-d11449095-Dubrovnik_Ancient_City_Walls_Wars_2hour_Walking_Tour-Dubrovnik_Dubrovnik_Neretva_C.html"
//val airBnb = "https://www.airbnb.com/rooms/530250?source_impression_id=p3_1578854894_7u13GO5lAoVsqfmA"
//val bookings = "https://www.booking.com/hotel/gb/skyline-luxury-warwick-apartment.html?aid=355028;sid=bbb4c9340c7726b1d32dce3e0cfd7761;dest_id=-2601889;dest_type=city;dist=0;group_adults=2;group_children=0;hapos=2;hpos=2;nflt=sth%253D1;no_rooms=1;room1=A%2CA;sb_price_type=total;sr_order=popularity;srepoch=1578856358;srpvid=95fe87122c060079;type=total;ucfs=1&#hotelTmpl"
//val hotels = "https://www.hotels.com/ho332841/?q-check-out=2020-01-15&FPQ=4&q-check-in=2020-01-14&WOE=3&WOD=2&q-room-0-children=0&pa=3&tab=description&JHR=3&q-room-0-adults=2&YGF=2&MGT=1&ZSX=0&SYE=3"
//
//val cultureTrip = "https://theculturetrip.com/europe/germany/articles/20-must-visit-attractions-in-munich/"
//
//import org.jsoup.Jsoup
//
//
//val airbnb = "https://www.airbnb.com/rooms/530250?source_impression_id=p3_1578854894_7u13GO5lAoVsqfmA"
//Jsoup.connect(airBnb).get().body().getElementsByTag("span").forEach(elem => {
////  if(elem.text().contains("$")) println(elem.text())
//  if(elem.text().contains("guests")) println(elem.text())
////  if(elem.text().contains("reviews")) println(elem.text())
//  if(elem.text().contains("bedrooms")) println(elem.text())
//  if(elem.text().contains("beds")) println(elem.text())
//  if(elem.text().contains("baths")) println(elem.text())
//
//})
//
//
//
//
//
//
//
//
//
//
//
//// Flights
//
////val skyscanner = "https://www.skyscanner.com/transport/flights/yyz/hyd/200201/200229/?adultsv2=1&childrenv2=&cabinclass=economy&rtn=1&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false&qp_prevProvider=ins_month&qp_prevCurrency=CAD&qp_prevPrice=1627&priceSourceId=taps-taps&priceTrace=202001120204*I*YYZ*HYD*20200201*fdep*EK%7C202001120204*I*HYD*YYZ*20200229*fdep*EK"
////val flightHub = "https://www.flighthub.com/flight/search?type=roundtrip&seg0_from=SFO&seg0_to=DBV&seg0_date=2020-02-14&seg1_date=2020-02-28&num_adults=1&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy&seg1_from=DBV&seg1_to=SFO&campaign=3851&search_id=dac8c372e31b9933f3ad3b40e32e07b1&flexible_search_id=dac8c372e31b9933f3ad3b40e32e07b1"
////
////val document = Jsoup.connect(flightHub).get()
////
////val formInputHiddenTags  =
////  document.body().getElementsByAttributeValueContaining("class", "container-home-search-from").get(0)
////  .getElementsByAttributeValueContaining("type", "hidden")
////
////val from  = formInputHiddenTags.get(0).attr("value")
////val to  = formInputHiddenTags.get(1).attr("value")
////
////
////document.body().getElementsByAttributeValueContaining("placeholder", "Leaving from").get(0).attr("value")
////document.body().getElementsByAttributeValueContaining("placeholder", "Going to").get(0).attr("value")
////
////document.body().getElementsByAttribute("data-locale-departure").get(0).getElementsByAttributeValueContaining("class", "search-calendar").`val`()
////document.body().getElementsByAttribute("data-locale-return").get(0).getElementsByAttributeValueContaining("class", "search-calendar").`val`()
////
////
////document.body().getElementsByAttributeValueContaining("id", "search-result-lowest-price").attr("data-price")
//
//
//
//
//
////sealed trait Website
////case object Airbnb extends Website
////case object TripAdvisor extends Website
////case object Bookings extends Website
////
////
////def getTitleByUrl(url: String, website: Website): String = {
////  website match {
////    case TripAdvisor =>
////      Jsoup.connect(url).get()
////        .title().split('|').drop(1).head
////    case Airbnb =>
////      Jsoup.connect(url).get().title()
////    case Bookings =>
////      Jsoup.connect(url).get().title()
////  }
////
////}
//
////getTitleByUrl(tripAdvisorItem, TripAdvisor)
////getTitleByUrl(airBnb, Airbnb)
////getTitleByUrl(cultureTrip, Bookings)
//
////Jsoup.connect(tripAdvisorItem).get().body().getElementsByAttribute("data-tab").toArray.toSeq.zipWithIndex.foreach(println)
////
////val gotLink = "https://www.tripadvisor.com/AttractionProductReview-g295371-d11449094-Dubrovnik_Combo_Old_Town_and_Ancient_City_Walls_Historical_Walking_Tour-Dubrovnik_.html"
////val duration = Jsoup.connect(tripAdvisorItem).get().body().getElementsByAttribute("data-tab")
////  .get(3).getAllElements
////  .get(1).getElementsByTag("span").get(1).text()
//
////val departurePoint = Jsoup.connect(tripAdvisorItem).get().body().getElementsByAttribute("data-tab")
////  .get(3).getAllElements.get(1).getElementsByTag("span").get(0).text()
////
////val returnDetails = Jsoup.connect(tripAdvisorItem).get().body().getElementsByAttribute("data-tab")
////  .get(3).getAllElements.get(1).getElementsByTag("span").get(2).text()
////println("title " + getTitleByUrl(tripAdvisorItem, TripAdvisor))
////println("duration " + duration)
//
////
////
////var listOfImages = Seq.empty[String]
////val result =
////  Jsoup.connect(tripAdvisorItem).get().body().getElementsByTag("img")
////
////println("after")
//////println(result.get(0).attr("src"))
////result forEach (element => {
////  val imgLink = element.attr("src")
////  if(imgLink.contains("media")) println(imgLink)
////})
//
//
//
//
////println(Jsoup.connect(tripAdvisorItem).get().body().getElementsByAttributeValueContaining("class", "mainPrice").text())

















