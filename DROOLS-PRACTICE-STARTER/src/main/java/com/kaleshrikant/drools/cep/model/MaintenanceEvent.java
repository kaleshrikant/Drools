package com.kaleshrikant.drools.cep.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintenance event
 *
 * @author Shrikant Kale
 * @Date 16 Sep 2025
 */

public class MaintenanceEvent extends Event{
	private String maintenanceType;
	private String technician;
	private boolean isEmergency;
	private List<String> affectedSystems;

	public MaintenanceEvent(String id, String name, LocalDateTime startTime, LocalDateTime endTime) {
		super(id, name, startTime, endTime);
		this.affectedSystems = new ArrayList<>();
		this.isEmergency = false;
	}

	public String getMaintenanceType() { return maintenanceType; }
	public void setMaintenanceType(String maintenanceType) { this.maintenanceType = maintenanceType; }
	public String getTechnician() { return technician; }
	public void setTechnician(String technician) { this.technician = technician; }
	public boolean isEmergency() { return isEmergency; }
	public void setEmergency(boolean emergency) { isEmergency = emergency; }
	public List<String> getAffectedSystems() { return affectedSystems; }
	public void addAffectedSystem(String system) { affectedSystems.add(system); }
}
