# 🔥 Drools HOT Deployment Implementation Guide

## 📋 Table of Contents
- [Overview](#-overview)
- [Architecture](#️-architecture)
- [Before HOT Deployment](#-before-hot-deployment)
- [After HOT Deployment](#-after-hot-deployment)
- [Code Changes](#-code-changes-summary)
- [Testing Guide](#-testing-guide)
- [API Endpoints](#-api-endpoints)
- [Benefits Summary](#-benefits-summary)

---

## 🎯 Overview
HOT Deployment enables dynamic rule updates at runtime without application restart.

---

## 🏗️ Architecture

### Before HOT Deployment:
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   REST API      │ -> │  Rule Engine    │ -> │ Static Rules    │
│                 │    │                 │    │ (classpath)     │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### After HOT Deployment:
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   REST API      │ -> │  Rule Engine    │ -> │ HOT Deployment  │
│                 │    │                 │    │ Service         │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                                        │
                                                        ▼
                                               ┌─────────────────┐
                                               │ External Rules  │
                                               │ (hot-deployment/)│
                                               └─────────────────┘
```

---

## ⏰ Before HOT Deployment

### 📁 Project Structure (Before)
```
src/
├── main/
│   ├── java/
│   │   └── com/kaleshrikant/drools/
│   │       ├── DroolsSpringbootExampleApplication.java
│   │       ├── config/DroolsConfig.java
│   │       ├── controller/RuleEngineController.java
│   │       ├── model/Applicant.java, LoanApplication.java
│   │       └── service/RuleEngineService.java
│   └── resources/
│       ├── META-INF/kmodule.xml
│       ├── application.yaml
│       └── rules/applicant/, loan/
```

### 🔧 Key Components (Before)

#### 1. DroolsConfig.java (Static)
```java
@Bean
public KieContainer kieContainer() {
    return KieServices.get().getKieClasspathContainer();
}
```

#### 2. RuleEngineService.java (Static)
```java
@Service
public class RuleEngineService {
    private final KieContainer kieContainer;
    // Uses static rules only
}
```

---

## 🚀 After HOT Deployment

### 📁 Project Structure (After)
```
src/
├── main/
│   ├── java/
│   │   └── com/kaleshrikant/drools/
│   │       ├── DroolsSpringbootExampleApplication.java  ⬆️ Modified
│   │       ├── config/DroolsConfig.java
│   │       ├── controller/
│   │       │   ├── RuleEngineController.java
│   │       │   ├── HotDeploymentController.java         🆕 NEW
│   │       │   └── DebugController.java
│   │       ├── model/Applicant.java, LoanApplication.java
│   │       └── service/
│   │           ├── RuleEngineService.java               ⬆️ Modified
│   │           └── HotDeploymentService.java            🆕 NEW
│   └── resources/
│       ├── META-INF/kmodule.xml
│       ├── application.yaml                             ⬆️ Modified
│       └── rules/ (static fallback)
├── hot-deployment/                                      🆕 NEW
│   ├── applicant/applicant-rules.drl
│   └── loan/loan-rules.drl
```

---

## 🔄 Code Changes Summary

### 📊 Files Changed Table

| File | Change Type | Purpose |
|------|-------------|---------|
| DroolsSpringbootExampleApplication.java | Modified | Added @EnableScheduling |
| RuleEngineService.java | Modified | Integrated HOT Deployment |
| HotDeploymentService.java | NEW | Core HOT deployment logic |
| HotDeploymentController.java | NEW | HOT deployment API endpoints |

---

### 1. 🆕 HotDeploymentService.java
```java
@Service
public class HotDeploymentService {
    private KieContainer kieContainer;
    private final String hotDeploymentPath = "hot-deployment/";

    @Scheduled(fixedRate = 5000)
    public void checkForRuleUpdates() {
        if (scanForChanges()) {
            reloadRules(); // 🔥 Dynamic reload
        }
    }

    public synchronized void reloadRules() {
        // Load from external directory
        // Build new rules
        // Replace container without restart
    }
}
```

---

### 2. ⬆️ Modified RuleEngineService.java
```java
@Service
public class RuleEngineService {
    private final HotDeploymentService hotDeploymentService; // 🔥 Use HOT deployment

    public Applicant evaluateApplicant(Applicant applicant) {
        // 🎯 Try HOT deployment first, fallback to static
        if (hotDeploymentService.isHotDeploymentActive()) {
            session = hotDeploymentService.newKieSession();
        } else {
            session = kieContainer.newKieSession();
        }
    }
}
```

---

### 3. ⬆️ Modified Main Application
```java
@SpringBootApplication
@EnableScheduling // 🕐 Enable HOT deployment monitoring
public class DroolsSpringbootExampleApplication {
    // ... main method with HOT deployment info
}
```

---

### 4. 🆕 HotDeploymentController.java
```java
@RestController
@RequestMapping("/api/hot-deployment")
public class HotDeploymentController {

    @PostMapping("/reload")
    public String reloadRules() {
        hotDeploymentService.reloadRulesManually();
        return "Rules reloaded via HOT deployment!";
    }

    @GetMapping("/status")
    public String getStatus() {
        return hotDeploymentService.getHotDeploymentStatus();
    }
}
```

---

## 🧪 Testing Guide

### 1. 🏁 Initial Setup
```bash
# Check application health
curl http://localhost:8090/api/rules/health

# Check HOT deployment status
curl http://localhost:8090/api/hot-deployment/status

# Test with static rules
curl -X POST http://localhost:8090/api/rules/evaluate-applicant \
  -H "Content-Type: application/json" \
  -d '{"name": "John", "age": 28, "yearsOfExperience": 4, "currentSalary": 50000, "expectedSalary": 60000}'
```

---

### 2. 🔥 Activate HOT Deployment
```bash
# Create directory structure
mkdir -p hot-deployment/{applicant,loan}

# Add dynamic rules
cat > hot-deployment/applicant/applicant-rules.drl << 'EOF'
package rules.applicant
import com.kaleshrikant.drools.model.Applicant

rule "HOT Dynamic Rule"
when
    $a: Applicant(age < 30)
then
    $a.setStatus("APPROVED");
    $a.setMessage("[HOT] Dynamic rule applied!");
end
EOF

# Trigger reload
curl -X POST http://localhost:8090/api/hot-deployment/reload
```

---

### 3. 🔄 Test Dynamic Updates
```bash
# Test with HOT rules
curl -X POST http://localhost:8090/api/rules/evaluate-applicant \
  -d '{"name": "Test", "age": 25, "yearsOfExperience": 2, "currentSalary": 40000, "expectedSalary": 45000}'

# Modify rules in hot-deployment/ directory
# Wait 5 seconds (auto-reload) or trigger manual reload
# Test again - rules updated without restart! 🎉
```

---

## 🌐 API Endpoints

### 📋 Available Endpoints

| Method | Endpoint | Purpose |
|--------|----------|---------|
| GET | `/api/rules/health` | Health check |
| POST | `/api/rules/evaluate-applicant` | Evaluate applicant |
| POST | `/api/rules/evaluate-loan` | Evaluate loan |
| GET | `/api/hot-deployment/status` | HOT deployment status |
| POST | `/api/hot-deployment/reload` | Manual rule reload |
| GET | `/api/hot-deployment/active` | Check if active |

---

## 📈 Benefits Summary

### 🎯 Before vs After Comparison

| Aspect | Before HOT Deployment | After HOT Deployment |
|--------|----------------------|---------------------|
| Rule Updates | 🔄 Application restart required | 🔥 Runtime updates |
| Deployment Time | ⏱️ Minutes | ⚡ Seconds |
| Development Speed | 🐢 Slow iteration | 🚀 Rapid iteration |
| Production Impact | 🔴 Service downtime | 🟢 Zero downtime |

---

### 🚀 Key Advantages

1. **⚡ Zero Downtime Updates** - Rules update without restart
2. **🔧 Business Flexibility** - Business users can modify rules
3. **🚀 Faster Iteration** - Test rule changes instantly
4. **🔄 Fallback Support** - Static rules as backup

---

## 🛠️ Implementation Flow

```
Application Start → Initialize HOT Deployment → Monitor Directory
                                                        ↓
                                    File Changed? → Yes → Reload Rules → Build → Replace Container
                                                        ↓
                                                       No → Continue Monitoring
```

---

## 🎉 Conclusion

The HOT Deployment implementation transforms your Drools application from a static rule engine to a dynamic, zero-downtime system.

**Business Impact:** Enables business users to modify rules directly while developers maintain the core application.

---

## 📅 Document Information

**Author:** Shrikant Kale  
**Last Updated:** 30 Sep 2025  
**Implementation:** Spring Boot 3.5.5 + Drools 9.44.0.Final + Java 21

---

*🛠️ Built with ❤️ for dynamic rule management*