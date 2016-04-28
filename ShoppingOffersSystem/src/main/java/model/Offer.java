/**
 * 
 */
package model;

import java.util.List;

/**
 * @author vijay
 *
 */
public class Offer {
	
	 	private Integer  offerId;
	    
	    private String offerName;
	    
	    private List<String> offerParams;

		public Integer getOfferId() {
			return offerId;
		}

		public void setOfferId(Integer offerId) {
			this.offerId = offerId;
		}

		public String getOfferName() {
			return offerName;
		}

		public void setOfferName(String offerName) {
			this.offerName = offerName;
		}

		public List<String> getOfferParams() {
			return offerParams;
		}

		public void setOfferParams(List<String> offerParams) {
			this.offerParams = offerParams;
		}

		@Override
		public String toString() {
			return "Offer [offerId=" + offerId + ", offerName=" + offerName
					+ ", offerParams=" + offerParams + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((offerId == null) ? 0 : offerId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Offer other = (Offer) obj;
			if (offerId == null) {
				if (other.offerId != null)
					return false;
			} else if (!offerId.equals(other.offerId))
				return false;
			return true;
		}
	    
	    


}
