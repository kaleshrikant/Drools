package com.kaleshrikant.drools.cep.model;

import org.kie.api.definition.type.Duration;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

/**
 * @author Shrikant Kale
 * @Date 13 Sep 2025
 */

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Duration("duration")
public class LoginEvent2 {
		private String user;
		private long timestamp;
		private long duration;

		public LoginEvent2(String user, long timestamp, long duration) {
			this.user = user;
			this.timestamp = timestamp;
			this.duration = duration;
		}

		public String getUser() {   // <-- REQUIRED
			return user;
		}

		public long getTimestamp() {   // <-- REQUIRED for @timestamp
			return timestamp;
		}

		public long getDuration() {   // <-- REQUIRED for @duration
			return duration;
		}

		@Override
		public String toString() {
			return "LoginEvent2{user='" + user + "', ts=" + timestamp + ", duration=" + duration + "}";
		}
}
