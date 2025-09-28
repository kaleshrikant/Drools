# Drools Hot Deployment - Complete Guide

## 📖 Table of Contents
1. [Architecture Overview](#architecture-overview)
2. [Project Structure](#project-structure)
3. [Step-by-Step Execution Flow](#step-by-step-execution-flow)
4. [Code Explanation](#code-explanation)
5. [Hot Deployment Mechanism](#hot-deployment-mechanism)
6. [Rule Execution Process](#rule-execution-process)
7. [Flow Diagrams](#flow-diagrams)
8. [Output Examples](#output-examples)

## 🏗️ Architecture Overview

### System Architecture
```
┌───────────────────────────────────────────────────────────┐
│ Spring Boot Application                                   │
│ ┌───────────────────────────────────────────────────────┐ │
│ │ HotDeploymentDemo                                     │ │
│ │ (CommandLineRunner - Triggers Demo)                   │ │
│ └───────────────┬───────────────────────────────────────┘ │
│                 │                                         │
│ ┌───────────────▼───────────────────────────────────────┐ │
│ │ RulesEngineManager                                    │ │
│ │ (@Component - Manages Rule Engine)                    │ │
│ └───────────────┬───────────────────────────────────────┘ │
│                 │                                         │
│ ┌───────────────▼───────────────────────────────────────┐ │
│ │ KieContainer                                          │ │
│ │ (Drools Rule Engine Container)                        │ │
│ └───────────────┬───────────────────────────────────────┘ │
│                 │                                         │
│ ┌───────────────▼───────────────────────────────────────┐ │
│ │ Business Rules                                        │ │
│ │ (.drl files - v1.0.0, v2.0.0)                         │ │
│ └───────────────┬───────────────────────────────────────┘ │
│                 │                                         │
│ ┌───────────────▼───────────────────────────────────────┐ │
│ │ Domain Models                                         │ │
│ │ (Customer, Order classes)                             │ │
│ └───────────────────────────────────────────────────────┘ │
└───────────────────────────────────────────────────────────┘
```

## 📁 Project Structure
```
drools-practice/
├── src/main/java/com/kaleshrikant/drools/
│   ├── BusinessRulesApplication.java # Spring Boot main class
│   ├── HotDeploymentDemo.java        # Demo runner
│   ├── RulesEngineManager.java       # Rule engine controller
│   └── model/
│       ├── Customer.java             # Customer domain class
│       └── Order.java                # Order domain class
├── src/main/resources/
│   ├── META-INF/kmodule.xml          # Drools configuration
│   └── rules/discount-rules.drl      # Business rules
└── pom.xml                           # Maven dependencies
```

## 🔄 Step-by-Step Execution Flow

### Application Startup Sequence

```mermaid
sequenceDiagram
    participant SB as Spring Boot
    participant BA as BusinessRulesApplication
    participant SC as Spring Container
    participant REM as RulesEngineManager
    participant HD as HotDeploymentDemo
    participant KC as KieContainer

    SB->>BA: main() method called
    BA->>SC: SpringApplication.run()
    SC->>SC: Component scan "com.kaleshrikant.drools"
    SC->>REM: Create RulesEngineManager bean
    REM->>REM: Constructor - get KieServices
    REM->>REM: @PostConstruct - initializeRulesContainer()
    REM->>KC: Create KieContainer v1.0.0
    KC->>KC: Load and compile rules from .drl files
    SC->>HD: Create HotDeploymentDemo bean
    HD->>REM: Inject RulesEngineManager dependency
    HD->>HD: CommandLineRunner.run() executed
```

### Phase 1: Initial Rule Testing (v1.0.0)
```mermaid
sequenceDiagram
    participant HD as HotDeploymentDemo
    participant REM as RulesEngineManager
    participant KC as KieContainer
    participant KS as KieSession
    participant R as Rules Engine

    HD->>REM: processOrder(order, customer)
    REM->>KC: newKieSession("discountSession")
    KC->>KS: Create new KieSession
    REM->>KS: insert(customer)
    REM->>KS: insert(order)
    REM->>KS: fireAllRules()
    
    Note over KS,R: Rule Execution Process
    KS->>R: Pattern Matching
    R->>R: Evaluate conditions
    R->>R: Activate matching rules
    R->>R: Execute rule actions
    
    KS-->>REM: Return number of rules fired
    REM->>KS: dispose()
    REM-->>HD: Return processed order
```

### Phase 2: Hot Deployment Process
```mermaid
sequenceDiagram
    participant HD as HotDeploymentDemo
    participant REM as RulesEngineManager
    participant KC_Old as KieContainer v1.0.0
    participant KC_New as KieContainer v2.0.0
    participant KS_Test as Test Session

    HD->>REM: hotDeployNewVersion("2.0.0")
    REM->>REM: Create ReleaseId for v2.0.0
    REM->>KC_New: Create new KieContainer
    REM->>KC_New: validateNewContainer()
    KC_New->>KS_Test: newKieSession() - Validation test
    KS_Test->>KS_Test: dispose()
    
    alt Validation Successful
        REM->>KC_Old: dispose() - Cleanup old container
        REM->>REM: Switch to KC_New
        REM-->>HD: Return true - Success
    else Validation Failed
        REM->>KC_New: dispose() - Cleanup failed container
        REM-->>HD: Return false - Failure
    end
```

## 💻 Code Explanation

### 1. BusinessRulesApplication.java
```java
@SpringBootApplication
@ComponentScan(basePackages = "com.kaleshrikant.drools")
public class BusinessRulesApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessRulesApplication.class, args);
    }
}
```

**Purpose:**
- Spring Boot entry point that starts the application context.
- Scans for components in the specified package.
- Autowires dependencies.

### 2. RulesEngineManager.java - Core Component
```java
@Component
public class RulesEngineManager {
    private KieContainer kieContainer;
    private String currentVersion = "1.0.0";
    
    @PostConstruct
    public void initialize() {
        initializeRulesContainer();
    }

    public Order processOrder(Order order, Customer customer) {
        KieSession session = kieContainer.newKieSession("discountSession");
        try {
            session.insert(customer);
            session.insert(order);
            int rulesFired = session.fireAllRules();
            return order;
        } finally {
            session.dispose();
        }
    }
}
```

**Key Methods:**
- Constructor: Gets Drools KieServices instance.
- `@PostConstruct`: Initializes rule container after bean creation.
- `processOrder()`: Executes rules for given order and customer.
- `hotDeployNewVersion()`: Performs hot deployment to new version.

### 3. HotDeploymentDemo.java - Demonstration Logic
```java
@Component
public class HotDeploymentDemo implements CommandLineRunner {
    private final RulesEngineManager rulesEngine;
    
    @Autowired
    public HotDeploymentDemo(RulesEngineManager rulesEngine) {
        this.rulesEngine = rulesEngine;
    }
    
    @Override
    public void run(String... args) {
        // Three-phase demonstration
        testVersion1();
        performHotDeployment();
        testVersion2();
    }
}
```

## 🔥 Hot Deployment Mechanism

**How Hot Deployment Works**

- **Initial State**:  
  `KieContainer v1.0.0 → Active Sessions → Processing Requests`

- **Deployment Process**:
    1. Create KieContainer v2.0.0 → Validate → Test Session
    2. If validation passes → Dispose old container → Switch to v2.0.0
    3. New requests use v2.0.0 rules immediately

- **Final State**:  
  `KieContainer v2.0.0 → New Sessions → Processing with New Rules`

**Key Features:**
- Zero Downtime
- Atomic Switch
- Validation & Rollback

## ⚡ Rule Execution Process

### Drools Rule Engine Internals
1. **Fact Insertion**
2. **Pattern Matching** (Working Memory, Agenda)
3. **Rule Execution** (Conflict resolution, Action execution)
4. **Session Cleanup**

### Example Rule Breakdown
```java
rule "Gold Customer Discount"
when
    $customer: Customer(level == "GOLD")
    $order: Order(amount > 1000)
then
    $order.addDiscount(0.15, "Gold Discount");
    System.out.println("Applied Gold discount");
end
```

**Execution Steps:**
- Insert facts into working memory.
- Pattern matching checks conditions.
- Activate matching rules → Added to agenda.
- Execute actions → Modify objects.

## 📊 Flow Diagrams

### Complete Application Flow
```mermaid
graph TD
    A[Spring Boot Startup] --> B[Component Scan]
    B --> C[Create RulesEngineManager Bean]
    C --> D[Initialize KieContainer v1.0.0]
    D --> E[Load/Compile Rules]
    E --> F[Create HotDeploymentDemo]
    F --> G[Execute CommandLineRunner]
    
    G --> H[Phase 1: Test v1.0.0 Rules]
    H --> I[Process Sample Orders]
    I --> J[Phase 2: Hot Deploy v2.0.0]
    
    J --> K{Validation Successful?}
    K -->|Yes| L[Switch to v2.0.0]
    K -->|No| M[Keep v1.0.0]
    
    L --> N[Phase 3: Test v2.0.0 Rules]
    M --> N
    N --> O[Demo Complete]
```

### Hot Deployment Decision Flow
```mermaid
graph LR
    A[Start Deployment] --> B[Create New Container]
    B --> C[Validate Rules]
    C --> D{Compilation Success?}
    D -->|Yes| E[Dispose Old Container]
    D -->|No| F[Deployment Failed]
    E --> G[Switch to New Container]
    G --> H[Deployment Success]
    F --> I[Keep Old Container]
    I --> J[Deployment Failed]
```

## 🎯 Output Examples

### Expected Console Output
```
=== Starting Drools Hot Deployment Demo ===

=== Phase 1: Testing Initial Rules (v1.0.0) ===
--- New Customer - Small Order ---
Customer: Customer{name='Alice', level='NEW', loyaltyPoints=0, vip=false}
Initial Order: 200.0
v1.0.0: Applied 5% welcome discount for new customer
Fired 1 rules
Final Order: 190.0 (Discount: 5.0%)
Applied Discounts: [Welcome Discount]

=== Phase 2: Performing Hot Deployment ===
Attempting hot deployment to version: 2.0.0
✅ Hot deployment successful! Now using version: 2.0.0

=== Phase 3: Testing Enhanced Rules (v2.0.0) ===
--- New Customer - Small Order ---
Customer: Customer{name='Alice', level='NEW', loyaltyPoints=0, vip=false}
Initial Order: 200.0
v2.0.0: Applied 8% enhanced welcome discount for new customer
Fired 1 rules
Final Order: 184.0 (Discount: 8.0%)
Applied Discounts: [Enhanced Welcome Discount]
```

### Rule Version Comparison
| Aspect                  | Version 1.0.0 | Version 2.0.0 |
|--------------------------|---------------|---------------|
| New Customer Threshold   | Amount > 100  | Amount > 50   |
| New Customer Discount    | 5%            | 8%            |
| Silver Customer Threshold| Amount > 500  | Amount > 300  |
| Silver Customer Discount | 10%           | 12%           |
| Platinum Support         | ❌            | ✅ 25%         |
| VIP Treatment            | ❌            | ✅ 5% bonus    |

## 🚀 Key Benefits Demonstrated
- **Business Agility**: Rules can change without code deployment
- **Zero Downtime**: Updates happen without restarting application
- **Immediate Effect**: New rules apply to next request immediately
- **Safety Mechanisms**: Validation prevents broken rules deployment
- **Version Control**: Clear separation between rule versions
- **Monitoring**: Easy to track which rules are executing

---
✅ This implementation provides a production-ready hot deployment solution for business rules management using Drools and Spring Boot.



## 📊 Visual Diagrams

### 🚀 Execution Flow Diagram
```mermaid
flowchart TD
    A[Spring Boot Startup] --> B[Component Scan]
    B --> C[Create RulesEngineManager Bean]
    C --> D[Initialize KieContainer v1.0.0]
    D --> E[Load/Compile Rules]
    E --> F[Create HotDeploymentDemo]
    F --> G[Execute CommandLineRunner]
    G --> H[Phase 1: Test v1.0.0 Rules]
    H --> I[Process Sample Orders]
    I --> J[Phase 2: Hot Deploy v2.0.0]
    J --> K{Validation Successful?}
    K -->|Yes| L[Switch to v2.0.0]
    K -->|No| M[Keep v1.0.0]
    L --> N[Phase 3: Test v2.0.0 Rules]
    N --> O[Demo Complete]
```

### 🔄 Hot Deployment Decision Flow
```mermaid
flowchart LR
    A[Start Deployment] --> B[Create New Container]
    B --> C[Validate Rules]
    C --> D{Compilation Success?}
    D -->|Yes| E[Dispose Old Container]
    E --> F[Switch to New Container]
    F --> G[Deployment Success]
    D -->|No| H[Deployment Failed]
    H --> I[Keep Old Container]
    I --> J[Deployment Failed]
```