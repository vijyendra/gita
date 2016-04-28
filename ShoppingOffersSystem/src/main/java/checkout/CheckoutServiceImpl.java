package checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.MathUtils;
import offers.OffersServiceImpl;
import offers.OffersService;
import model.Cart;
import model.Offer;
import model.ValidationResponse;
import model.ValidationStatusEnum;
import price.PriceServiceImpl;
import validation.ValidationServiceImpl;

public class CheckoutServiceImpl implements CheckoutService{
	
	
	ValidationServiceImpl validationService = new ValidationServiceImpl();

	PriceServiceImpl priceService = new PriceServiceImpl();
	
	OffersService offerService = new OffersServiceImpl();
	
    public Cart checkout( Cart cart) {
    	
    try{
    	ValidationResponse resp = validationService.validate(cart);
    	
    	
    	if (resp.getValidationStatus().equalsIgnoreCase(ValidationStatusEnum.FAIL.getValidationStatusDescription())){
    		
    		if(cart == null){
        		 cart = new Cart();
        	} 
    		
    		cart.setCartErrors(resp.getValidationErrorsById(cart.getCartId()));
    	}else {
    		cart = this.getCartTotal(cart);
    	}    	 	
        
    }catch (Exception ex ){
    	
    	List<String> errorMessages = new ArrayList<String>();
    	
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0; i < ex.getStackTrace().length -1; i++){
    		sb.append(ex.getStackTrace()[0].toString());
    		sb.append("\n");
    	}
    	errorMessages.add(ex.getMessage()+" ::::::: "+sb.toString());
    	
    	cart.setCartErrors(errorMessages);
    }
    
	return cart;
 }

	private Cart getCartTotal(Cart cart) {
		
		HashMap<String, Double> productPrices = priceService.getPrices(cart.getPurchasedProducts().keySet());
		Map<String, Integer> purchasedProduct = cart.getPurchasedProducts();
		Map<String, List<Offer>> productOffers = offerService.getOffers(cart.getPurchasedProducts().keySet());
		
		for (String productId : purchasedProduct.keySet()){
			Integer quantity = purchasedProduct.get(productId);
			Double unitPrice = productPrices.get(productId);
			List<Offer> offersForProduct = productOffers.get(productId);
			
			Double cartTotal = 0.00;
			if(offersForProduct == null || offersForProduct.isEmpty()){
				cartTotal = getCartTotal(cart,quantity, unitPrice);
			}else{
				for( Offer offer : offersForProduct){
					cartTotal = applyOffer (cart, offer, quantity, unitPrice);
				}
			}			
			cart.setCartTotal(cartTotal);
		}
		
		return cart;
		
	}

	private Double applyOffer(Cart cart, Offer offer, Integer quantity,
			Double unitPrice) {
		Double cartTotal = 0.0;
		if(offer.getOfferName().equalsIgnoreCase("Buy One Get One Free")){
			int factor = 2;
			cartTotal = MathUtils.buyOneGetOneFree(cart.getCartTotal(),quantity, unitPrice, factor);
		}else if (offer.getOfferName().equalsIgnoreCase("3 for the price of 2")){
			int factor = 3;
			cartTotal = MathUtils.threeForTwo(cart.getCartTotal(), quantity, unitPrice, factor);
		}
		return cartTotal ;
	}

	private Double getCartTotal(Cart cart, Integer quantity, Double unitPrice) {
		Double cartTotal = cart.getCartTotal();
		cartTotal = cartTotal + getProdPriceForQuantity(quantity, unitPrice);
		return cartTotal;
	}

	public Double getProdPriceForQuantity(Integer quantity, Double unitPrice) {
		// TODO Auto-generated method stub    
		return MathUtils.multiplyIntegerAndDouble(quantity,unitPrice);
	}

	public Cart purgeCart(Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setValidationService(ValidationServiceImpl validationService) {
		this.validationService = validationService;
	}

	public void setPriceService(PriceServiceImpl priceService) {
		this.priceService = priceService;
	}
    
    	
}
