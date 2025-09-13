# Step-by-Step Explanation: Drools "During" Temporal Rule Program

## 🎯 **Program Purpose**
This program demonstrates how to detect when a **UserActionEvent** occurs **DURING** a specific **SessionWindow** time period using Drools CEP.

---

## 📋 **Step 1: Model Classes**

### SessionWindow.java
```java
public class SessionWindow {
    private long startTime;  // Window start timestamp
    private long endTime;    // Window end timestamp
}
```
**Purpose:** Represents a time window/period (e.g., user session, maintenance window, business hours)

### UserActionEvent.java
```java
public class UserActionEvent {
    private String userId;   // Who performed the action
    private long timestamp;  // When the action occurred
}
```
**Purpose:** Represents a user action that happens at a specific moment in time

---

## 🚀 **Step 2: Java Main Program**

### 1️⃣ **Drools Setup**
```java
KieServices kieServices = KieServices.Factory.get();
KieContainer kieContainer = kieServices.getKieClasspathContainer();
KieSession kieSession = kieContainer.newKieSession("ksession-rules");
```
**What happens:** Initializes Drools engine and loads rules from classpath

### 2️⃣ **Logger Setup**
```java
List<String> logCollector = new ArrayList<>();
kieSession.setGlobal("logCollector", logCollector);
```
**What happens:** Creates a list to collect rule execution results

### 3️⃣ **Event Creation & Timeline**
```java
long now = System.currentTimeMillis();
kieSession.insert(new SessionWindow(now - 10000, now + 10000)); // 20s window
kieSession.insert(new UserActionEvent("Shrikant", now));        // occurs during
```

**Timeline Visualization:**
```
Timeline: |----[Session Window: 20 seconds]----| 
          |                  |                 |
    now-10s              now (action)       now+10s
    START              USER ACTION          END
```

**What happens:**
- Creates a 20-second session window: 10 seconds before `now` to 10 seconds after `now`
- Creates a user action that happens exactly at `now` (which is DURING the window)

### 4️⃣ **Rule Execution**
```java
kieSession.fireAllRules();
```
**What happens:** Drools evaluates all facts against all rules and fires matching ones

---

## 📜 **Step 3: The Rule Logic**

```java
declare UserActionEvent
    @role(event)           // ← Treat as time-aware event
    @timestamp(timestamp)  // ← Use 'timestamp' field for time operations
end

rule "User action occurred during session window"
when
    $session : SessionWindow($start : startTime, $end : endTime)
    $action : UserActionEvent(timestamp >= $start && timestamp <= $end)
then
    logCollector.add("✅ Valid action by " + $action.getUserId() + " during session window.");
end
```

### **Rule Breakdown:**

#### **Pattern Matching:**
1. **Find a SessionWindow** and extract its start/end times
2. **Find a UserActionEvent** whose timestamp falls between start and end times

#### **Condition Logic:**
```java
timestamp >= $start && timestamp <= $end
```
This checks: `startTime ≤ actionTimestamp ≤ endTime`

#### **When Rule Fires:**
- Adds a success message to the logCollector
- Message includes the user who performed the action

---

## 🔍 **Step 4: Execution Flow**

### **Facts in Working Memory:**
```
SessionWindow: startTime=now-10000, endTime=now+10000
UserActionEvent: userId="Shrikant", timestamp=now
```

### **Rule Evaluation:**
1. ✅ **SessionWindow found** → Extract start=now-10000, end=now+10000
2. ✅ **UserActionEvent found** → Check: now >= (now-10000) && now <= (now+10000)
3. ✅ **Condition passes** → now IS between start and end
4. ✅ **Rule fires** → Add message to logCollector

### **Final Result:**
```
Results:
✅ Valid action by Shrikant during session window.
```

---

## 🎨 **Key Concepts Demonstrated**

### **1. Temporal Relationships**
- **"During"**: An event occurs within a time period
- Alternative to complex date/time calculations in Java

### **2. Event Declaration**
- `@role(event)`: Makes UserActionEvent time-aware
- `@timestamp(timestamp)`: Tells Drools which field contains the time

### **3. Constraint-Based Matching**
- Uses boolean expressions (`>=`, `<=`) instead of temporal operators
- More explicit than built-in temporal operators like `during`

### **4. Real-World Applications**
- **Session Validation**: "Did user action occur during valid session?"
- **Security Monitoring**: "Did suspicious activity happen during business hours?"
- **Performance Tracking**: "Which actions occurred during peak load window?"

---

## 🔧 **Alternative Approaches**

### **Using Built-in Temporal Operator:**
```java
$action : UserActionEvent(this during $session)
```
(But SessionWindow would need to be declared as an event with @role(event))

### **Using Time Windows:**
```java
$action : UserActionEvent() over window:time(20s)
```
(For sliding time windows instead of fixed periods)

---

## 🎯 **Summary**
This program shows how Drools can elegantly handle **time-based business rules** by:
1. **Modeling time periods** as facts (SessionWindow)
2. **Declaring events** with temporal awareness (@role, @timestamp)
3. **Using constraints** to check temporal relationships
4. **Automatically firing rules** when conditions match

The result is clean, maintainable business logic for complex temporal scenarios!