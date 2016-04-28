package offers;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Offer;

import org.junit.Before;
import org.junit.Test;

public class OffersServiceImplTest {
	
	OffersServiceImpl offersService;

	@Before
	public void setUp() throws Exception {
		 offersService = new OffersServiceImpl();
	}

	@Test
	public void getOffersTest() {
		
		Set<String> productIds = new HashSet<String>();
		productIds.add("1");
		productIds.add("2");
		Map<String, List<Offer>> offersMap = offersService.getOffers(productIds);
		
		assertNotNull(offersMap.get("1"));
		assertEquals(offersMap.get("1").get(0).getOfferName(),"Buy One Get One Free");
	}

}
