package offers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Offer;

public interface OffersService {
	
	public Map<String, List<Offer>> getOffers(Set<String> productIds);
	

}
