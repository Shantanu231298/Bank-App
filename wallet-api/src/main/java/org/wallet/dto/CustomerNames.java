package org.wallet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CustomerNames {
	@JsonIgnore
	String getFirstName();
	@JsonIgnore
	String getLastName();
	
	default String getFullName() {
	    return getFirstName().concat(" ").concat(getLastName());
	  }
}
