package com.kaleshrikant.drools.cep.model;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

/**
 * @author Shrikant Kale
 * @Date 13 Sep 2025
 */

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
public class PurchaseEvent2 {
	private String user;
	private long timestamp;

	public PurchaseEvent2(String user, long timestamp) {
		this.user = user;
		this.timestamp = timestamp;
	}

	public String getUser() {   // <-- REQUIRED
		return user;
	}

	public long getTimestamp() {   // <-- REQUIRED for @timestamp
		return timestamp;
	}

	@Override
	public String toString() {
		return "PurchaseEvent2{user='" + user + "', ts=" + timestamp + "}";
	}
}
