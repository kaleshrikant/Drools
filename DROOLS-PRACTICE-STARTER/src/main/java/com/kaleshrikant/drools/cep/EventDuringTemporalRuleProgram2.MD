# Step-by-Step Explanation: True Drools CEP "During" Temporal Program

## 🎯 **Program Purpose**
This program demonstrates **TRUE Drools CEP temporal reasoning** using the built-in `during` operator to detect when a **PurchaseEvent2** (point event) occurs **DURING** a **LoginEvent2** (interval event).

---

## 📋 **Step 1: Understanding Event Types**

### **Interval Event (LoginEvent2)**
```java
public class LoginEvent2 {
    private String user;       // Who logged in
    private long timestamp;    // When login started
    private long duration;     // How long login lasts (30 minutes)
}
```
**Concept:** Represents a **time period** - login session that spans from start to end

### **Point Event (PurchaseEvent2)**
```java
public class PurchaseEvent2 {
    private String user;       // Who made purchase
    private long timestamp;    // Exact moment of purchase
    // No duration - it's instantaneous
}
```
**Concept:** Represents a **moment in time** - purchase happens at exact timestamp

---

## 🚀 **Step 2: CEP-Enabled Java Program**

### 1️⃣ **CEP Session Setup**
```java
KieSession session = KieServices.Factory.get()
    .getKieClasspathContainer()
    .newKieSession("ksession-cep"); // ← CEP-enabled session
```
**Key Difference:** Uses `"ksession-cep"` instead of `"ksession-rules"`
**Why Important:** Enables **stream processing mode** for temporal operators

### 2️⃣ **Event Timeline Creation**
```java
long now = System.currentTimeMillis();
LoginEvent2 login = new LoginEvent2("Shrikant", now, 30 * 60 * 1000);        // 30min duration
PurchaseEvent2 purchase = new PurchaseEvent2("Shrikant", now + 10 * 60 * 1000); // 10min later
```

**Visual Timeline:**
```
Timeline: |----[Login Session: 30 minutes]----|
          |          |                        |
       now (start) now+10m (purchase)     now+30m (end)
          |<------- DURING relationship ------>|
```

**What Happens:**
- **Login starts**: `now` and lasts 30 minutes
- **Purchase occurs**: 10 minutes after login started
- **Login ends**: 30 minutes after login started
- **Result**: Purchase is DURING the login session

### 3️⃣ **Event Insertion**
```java
session.insert(login);    // Insert interval event
session.insert(purchase); // Insert point event
```
**What Drools Sees:**
- LoginEvent2: An interval from `now` to `now + 30min`
- PurchaseEvent2: A point at `now + 10min`

---

## 📜 **Step 3: True CEP Rule Logic**

### **Event Declarations (The Magic)**
```java
declare LoginEvent2
    @role( event )         // ← This is a temporal event
    @timestamp( timestamp ) // ← Use 'timestamp' field for time
    @duration( duration )   // ← Use 'duration' field for interval length
end

declare PurchaseEvent2
    @role( event )         // ← This is a temporal event
    @timestamp( timestamp ) // ← Use 'timestamp' field for time
    // No @duration - it's a point event
end
```

**What These Annotations Do:**
- **@role(event)**: Tells Drools "treat this as time-aware event, not regular object"
- **@timestamp(timestamp)**: "Use the timestamp field to know WHEN this event occurred"
- **@duration(duration)**: "This event lasts for 'duration' milliseconds"

### **The True CEP Rule**
```java
rule "Purchase DURING Login - True CEP"
when
    $login : LoginEvent2()
    $purchase : PurchaseEvent2(
        getUser().equals($login.getUser()),  // Same user
        this during $login                   // ← MAGIC: True temporal operator
    )
then
    logCollector.add("🎯 TRUE CEP: " + $purchase.getUser() +
                     " purchased DURING login session using Drools temporal operator");
end
```

### **Breaking Down `this during $login`:**
- **`this`**: Refers to the current PurchaseEvent2 being evaluated
- **`during`**: Drools built-in temporal operator
- **`$login`**: The LoginEvent2 interval

**What Drools Checks Automatically:**
1. Is PurchaseEvent2.timestamp >= LoginEvent2.timestamp? ✅
2. Is PurchaseEvent2.timestamp <= (LoginEvent2.timestamp + LoginEvent2.duration)? ✅
3. If both true → PurchaseEvent2 is DURING LoginEvent2 ✅

---

## 🔧 **Step 4: CEP Configuration (kmodule.xml)**

```xml
<kbase name="cep-rules" packages="rules" eventProcessingMode="stream">
    <ksession name="ksession-cep" type="stateful" clockType="realtime"/>
</kbase>
```

### **Key CEP Settings:**
- **eventProcessingMode="stream"**: Enables temporal reasoning
- **clockType="realtime"**: Uses system time for event processing
- **type="stateful"**: Maintains event history for temporal comparisons

---

## 🔍 **Step 5: Execution Flow**

### **In Working Memory:**
```
LoginEvent2: user="Shrikant", timestamp=now, duration=1800000ms
PurchaseEvent2: user="Shrikant", timestamp=now+600000ms
```

### **Rule Evaluation Process:**
1. **Find LoginEvent2** → Found: Shrikant's login session
2. **Find PurchaseEvent2** with matching user → Found: Shrikant's purchase
3. **Apply temporal operator** `this during $login`:
   ```
   Purchase timestamp (now+10min) >= Login start (now) ? ✅ YES
   Purchase timestamp (now+10min) <= Login end (now+30min) ? ✅ YES
   ```
4. **Both conditions met** → Rule fires! 🎉

### **Final Output:**
```
🎯 TRUE CEP: Shrikant purchased DURING login session using Drools temporal operator
```

---

## 🎨 **Key CEP Concepts Demonstrated**

### **1. Stream Processing vs Cloud Processing**
- **Cloud Mode** (regular): Events treated as static facts
- **Stream Mode** (CEP): Events processed as temporal stream with time awareness

### **2. Interval vs Point Events**
- **Interval Event**: Has duration (LoginEvent2 - represents a time period)
- **Point Event**: No duration (PurchaseEvent2 - represents a moment)

### **3. Built-in Temporal Operators**
- **`during`**: Point event occurs within interval event
- **Alternative operators**: `before`, `after`, `meets`, `overlaps`, etc.

### **4. Automatic Temporal Reasoning**
- No manual timestamp math required
- Drools handles all temporal logic automatically
- More readable and maintainable business rules

---

## 🚀 **Real-World Applications**

### **Session Management**
```java
"User action occurred during valid session window"
```

### **Security Monitoring**
```java
"Suspicious activity happened during business hours"
```

### **Performance Analysis**
```java
"Database queries executed during peak load period"
```

### **IoT Event Processing**
```java
"Sensor reading anomaly detected during maintenance window"
```

---

## 🎯 **Summary: Why This Is "True CEP"**

| Aspect | Manual Approach | True CEP Approach |
|--------|----------------|-------------------|
| **Temporal Logic** | Manual timestamp math | Built-in `during` operator |
| **Event Types** | Regular Java objects | Declared events with @role(event) |
| **Processing** | Cloud mode | Stream mode |
| **Readability** | Complex constraints | Simple `this during $login` |
| **Maintenance** | Error-prone calculations | Drools handles automatically |

This program showcases the **true power of Drools CEP** - elegant, readable temporal reasoning that automatically handles complex time-based business logic!
