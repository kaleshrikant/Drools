package com.kaleshrikant.drools.cep;

import com.kaleshrikant.drools.cep.model.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 📅 EventOverlapsTemporalRuleProgram via Drools CEP
 *
 * 🧾 Executes temporal rules on complex event scenarios:
 * ────────────────────────────────────────────────────────────
 * 🔴 Room Booking Conflict → Same room double-booked
 * ⚠️ High Priority Meeting Overlap → Priority ≥ 8 meetings overlap
 * 🔧 Maintenance During Business Hours → Non-emergency overlaps with meetings (9–17h)
 * 📊 Virtual Meeting Capacity Warning → Virtual meeting > 50 attendees
 * 🚨 Emergency Maintenance Priority → Emergency overlaps with any active event
 * ⏰ Long Meeting Duration Warning → Meeting > 180 minutes
 * 📈 Resource High Utilization → ≥ 3 active bookings for same resource
 * 🏢 Exclusive Location Conflict → Exclusive booking overlaps with other event
 *
 * 📦 Uses `EventConflict` and `EventAlert` objects
 * 📤 Adds conflicts to `global List conflicts`
 * 📤 Adds alerts to `global List alerts`
 * 🧠 Uses `.overlaps()` method for temporal matching
 * 📋 Scenario includes meetings, bookings, maintenance, and virtual events
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 * 📊 EXECUTION RESULTS:
 * Rules fired: 8
 * Conflicts detected: 5
 * Alerts generated: 3
 *
 * 🔥 CONFLICTS DETECTED:
 * 🔴 ROOM_DOUBLE_BOOKING → Resolution: RESCHEDULE_REQUIRED
 * ⚠️ HIGH_PRIORITY_OVERLAP
 * 🚨 EMERGENCY_PRIORITY → Resolution: SUSPEND_OTHER_EVENT
 * 🔧 MAINTENANCE_BUSINESS_HOURS → Resolution: RESCHEDULE_MAINTENANCE
 * 🏢 EXCLUSIVE_LOCATION → Resolution: RELOCATE_EVENT
 *
 * 🚨 ALERTS GENERATED:
 * 📊 CAPACITY_WARNING
 * ⏰ DURATION_WARNING
 * 📈 UTILIZATION_HIGH
 *
 * 📈 ANALYSIS SUMMARY:
 * Critical conflicts (severity ≥ 8): 3
 * High-priority alerts (priority ≥ 7): 1
 * ⚠️ IMMEDIATE ACTION REQUIRED for critical conflicts!
 * ✅ Temporal rule analysis complete.
 *
 * @author Shrikant Kale
 * @Date 15 Sep 2025
 */

public class EventOverlapsTemporalRuleProgram {
	public static void main(String[] args) {
		System.out.println("=== Event Overlaps Temporal Rule Program ===\n");

		EventOverlapsTemporalRuleProgram program = new EventOverlapsTemporalRuleProgram();
		program.runComplexScenario();
	}
	public void runComplexScenario() {
		// Initialize Drools
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("event-temporal-ksession");

		// Initialize global collections
		List<EventConflict> conflicts = new ArrayList<>();
		List<EventAlert> alerts = new ArrayList<>();
		kSession.setGlobal("conflicts", conflicts);
		kSession.setGlobal("alerts", alerts);

		// Create complex event scenario
		List<Event> events = createComplexEventScenario();

		System.out.println("📅 SCHEDULED EVENTS:");
		System.out.println("==================");
		for (Event event : events) {
			System.out.println(event);
		}

		// Insert events into working memory
		for (Event event : events) {
			kSession.insert(event);
		}

		System.out.println("\n🔍 EXECUTING TEMPORAL RULES...\n");

		// Fire all rules
		int rulesFired = kSession.fireAllRules();

		// Display results
		displayResults(conflicts, alerts, rulesFired);

		// Cleanup
		kSession.dispose();
	}

	private List<Event> createComplexEventScenario() {
		List<Event> events = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();

		// Conference Room A conflicts
		ResourceBooking roomA1 = new ResourceBooking("ROOM-A-1", "Team Standup",
				now.plusHours(1), now.plusHours(2));
		roomA1.setResourceType("ROOM");
		roomA1.setResourceId("CONF-A");
		roomA1.setLocation("Building 1, Floor 2");
		roomA1.setBookedBy("team-alpha");
		events.add(roomA1);

		ResourceBooking roomA2 = new ResourceBooking("ROOM-A-2", "Client Presentation",
				now.plusHours(1).plusMinutes(30), now.plusHours(3));
		roomA2.setResourceType("ROOM");
		roomA2.setResourceId("CONF-A");
		roomA2.setLocation("Building 1, Floor 2");
		roomA2.setBookedBy("sales-team");
		roomA2.setPriority(8);
		events.add(roomA2);

		// High-priority meeting conflicts
		Meeting criticalMeeting1 = new Meeting("MEET-CRIT-1", "Board Meeting",
				now.plusHours(4), now.plusHours(6));
		criticalMeeting1.setPriority(9);
		criticalMeeting1.addAttendee("CEO");
		criticalMeeting1.addAttendee("CTO");
		criticalMeeting1.addAttendee("CFO");
		criticalMeeting1.setLocation("Executive Boardroom");
		events.add(criticalMeeting1);

		Meeting criticalMeeting2 = new Meeting("MEET-CRIT-2", "Emergency Response Planning",
				now.plusHours(4).plusMinutes(45), now.plusHours(7));
		criticalMeeting2.setPriority(8);
		criticalMeeting2.addAttendee("Security Chief");
		criticalMeeting2.addAttendee("Operations Manager");
		criticalMeeting2.setLocation("Crisis Room");
		events.add(criticalMeeting2);

		// Virtual meeting with high capacity
		Meeting largeMeeting = new Meeting("MEET-LARGE", "All-Hands Quarterly",
				now.plusHours(2), now.plusHours(5));
		largeMeeting.setVirtual(true);
		largeMeeting.setPriority(6);
		for (int i = 1; i <= 75; i++) {
			largeMeeting.addAttendee("employee" + i);
		}
		events.add(largeMeeting);

		// Emergency maintenance
		MaintenanceEvent emergencyMaint = new MaintenanceEvent("MAINT-EMRG", "Server Farm Emergency Repair",
				now.plusHours(3), now.plusHours(5));
		emergencyMaint.setEmergency(true);
		emergencyMaint.setMaintenanceType("CRITICAL");
		emergencyMaint.setTechnician("senior-tech-1");
		emergencyMaint.addAffectedSystem("DATABASE");
		emergencyMaint.addAffectedSystem("WEB_SERVERS");
		emergencyMaint.setLocation("Data Center");
		events.add(emergencyMaint);

		// Regular maintenance during business hours
		MaintenanceEvent regularMaint = new MaintenanceEvent("MAINT-REG", "HVAC System Check",
				now.plusHours(1).plusMinutes(15), now.plusHours(2).plusMinutes(45));
		regularMaint.setEmergency(false);
		regularMaint.setMaintenanceType("PREVENTIVE");
		regularMaint.setTechnician("facility-tech");
		regularMaint.addAffectedSystem("HVAC");
		regularMaint.setLocation("Building 1, Floor 2");
		events.add(regularMaint);

		// Multiple bookings for same resource type
		for (int i = 1; i <= 4; i++) {
			ResourceBooking projector = new ResourceBooking("PROJ-" + i, "Projector Booking " + i,
					now.plusHours(i), now.plusHours(i + 1));
			projector.setResourceType("PROJECTOR");
			projector.setResourceId("PROJ-001");
			projector.setBookedBy("team-" + i);
			events.add(projector);
		}

		// Exclusive location booking with conflict
		ResourceBooking exclusiveEvent = new ResourceBooking("EXCL-1", "Company Photoshoot",
				now.plusHours(6), now.plusHours(8));
		exclusiveEvent.setResourceType("SPACE");
		exclusiveEvent.setResourceId("LOBBY");
		exclusiveEvent.setExclusive(true);
		exclusiveEvent.setLocation("Main Lobby");
		events.add(exclusiveEvent);

		Meeting lobbyMeeting = new Meeting("MEET-LOBBY", "Visitor Welcome Session",
				now.plusHours(6).plusMinutes(30), now.plusHours(7).plusMinutes(30));
		lobbyMeeting.setLocation("Main Lobby");
		events.add(lobbyMeeting);

		return events;
	}

	private void displayResults(List<EventConflict> conflicts, List<EventAlert> alerts, int rulesFired) {
		System.out.println("📊 EXECUTION RESULTS:");
		System.out.println("====================");
		System.out.println("Rules fired: " + rulesFired);
		System.out.println("Conflicts detected: " + conflicts.size());
		System.out.println("Alerts generated: " + alerts.size());

		if (!conflicts.isEmpty()) {
			System.out.println("\n🔥 CONFLICTS DETECTED:");
			System.out.println("======================");
			conflicts.stream()
					.sorted((c1, c2) -> Integer.compare(c2.getSeverity(), c1.getSeverity()))
					.forEach(conflict -> {
						System.out.println(conflict);
						if (conflict.getResolution() != null) {
							System.out.println("   → Resolution: " + conflict.getResolution());
						}
					});
		}

		if (!alerts.isEmpty()) {
			System.out.println("\n🚨 ALERTS GENERATED:");
			System.out.println("===================");
			alerts.stream()
					.sorted((a1, a2) -> Integer.compare(a2.getPriority(), a1.getPriority()))
					.forEach(System.out::println);
		}

		// Analysis summary
		System.out.println("\n📈 ANALYSIS SUMMARY:");
		System.out.println("===================");

		long criticalConflicts = conflicts.stream()
				.mapToInt(EventConflict::getSeverity)
				.filter(s -> s >= 8)
				.count();

		long highPriorityAlerts = alerts.stream()
				.mapToInt(EventAlert::getPriority)
				.filter(p -> p >= 7)
				.count();

		System.out.println("Critical conflicts (severity ≥ 8): " + criticalConflicts);
		System.out.println("High-priority alerts (priority ≥ 7): " + highPriorityAlerts);

		if (criticalConflicts > 0) {
			System.out.println("\n⚠️ IMMEDIATE ACTION REQUIRED for critical conflicts!");
		}

		System.out.println("\n✅ Temporal rule analysis complete.");
	}
}
