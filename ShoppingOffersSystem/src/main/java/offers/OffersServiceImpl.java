package offers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Offer;

public class OffersServiceImpl implements OffersService {

	@Override
	public Map<String, List<Offer>> getOffers(Set<String> productIds) {
		
		// Go to cache/ repo/ db here and get offers for this product
		//here we simply populate object ourselves instead of fetching 
		
		
		Map<String, List<Offer>> offersMap = new HashMap<String, List<Offer>>();
		
		for(String productId : productIds){
			//For this product populate offers dynamically
			
			populateOffersForThisProduct(productId, offersMap);			
			
		}
		return offersMap;
	}

	private void populateOffersForThisProduct(String productId,
			Map<String, List<Offer>> offersMap) {
		//This can be done dynamically 
		Offer offer1 = new Offer();
		offer1.setOfferId(1);
		offer1.setOfferName("Buy One Get One Free");
		offer1.setOfferParams(null);
		
		Offer offer2 = new Offer();
		offer2.setOfferId(2);
		offer2.setOfferName("3 for the price of 2");
		offer2.setOfferParams(null);
		
		List<Offer> offers = offersMap.get(productId);
		if (offers == null )
		{
			offers = new ArrayList<Offer>();
		}
		if(productId.equalsIgnoreCase("1") && !offers.contains(offer1)){
			offers.add(offer1);
			offersMap.put(productId, offers);
		}
		else if(productId.equalsIgnoreCase("2") && !offers.contains(offer2))
		{	
			offers.add(offer2);
			offersMap.put(productId, offers);
		}	
		
	}

}
