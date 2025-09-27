[condition][]When a buyer is GOLD member = BuyerProfile( membershipType == "GOLD" )
[condition][]When a buyer is SILVER member = BuyerProfile( membershipType == "SILVER" )
[consequence][]Give GOLD discount = modify($buyer) { setDiscount(20) }; logCollector.add("🏆 GOLD discount applied to " + $buyer.getName());
[consequence][]Give SILVER discount = modify($buyer) { setDiscount(10) }; logCollector.add("🥈 SILVER discount applied to " + $buyer.getName());
