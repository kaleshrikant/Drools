# 📘 Drools Practice with Spring Boot (Java 21)

## 🔹 Project Overview
This project is a **Spring Boot 3 + Drools** practice setup designed for learning and experimenting with **Drools Rule Engine** using **Java 21**.

It provides a **clean Gradle-based structure** to practice Drools concepts like:

- Writing simple `.drl` rules
- Using decision tables (`.xls` / `.csv`)
- Applying rule templates (`.drt`)
- Working with CEP (Complex Event Processing) features like sliding windows and timers
- Testing rules with Spring Boot context

The project uses **Spring Boot** as the application framework, making it easy to run rules inside a real-world environment (with dependency injection, services, configs, etc.).

---

## 🔹 Tech Stack
- ☕ **Java 21** (latest LTS)
- 🌱 **Spring Boot 3.3.x**
- ⚙️ **Drools 9.44.0.Final**
- 📦 **Gradle Build Tool**
- 🧪 **JUnit 5** for testing

---

# Drools Practice Starter Template – ASCII Table

```
╔════════════════════════════════════════════════════════════════════════════════════════════╗
║ 🔹 DROOLS PRACTICE STARTER TEMPLATE – PROGRAM STRUCTURE                       			 ║
╠════════════════════════════════════════════════════════════════════════════════════════════╣
║ 📂 Drools-Practice/                                                           			 ║
║ ├── 🟦 core/                                                                  			 ║
║ │   ├─ ☕ HelloWorldRuleProgram.java          📜 HelloWorldRule.drl           			 ║
║ │   ├─ ☕ PersonFactInsertionProgram.java     📜 PersonFactInsertion.drl      			 ║
║ │   ├─ ☕ AdultCheckRuleProgram.java          📜 AdultCheckRule.drl           			 ║
║ │   ├─ ☕ MultiRuleExecutionProgram.java      📜 MultiRuleExecution.drl       			 ║
║ ├── 🟪 keywords/                                                              			 ║
║ │   ├─ ☕ MinorCheckNotKeywordProgram.java    📜 MinorCheckNotKeyword.drl     			 ║
║ │   ├─ ☕ EvenAgeEvalKeywordProgram.java      📜 EvenAgeEvalKeyword.drl       			 ║
║ │   ├─ ☕ CityExtractionFromKeywordProgram.java 📜 CityExtractionFromKeyword.drl 			 ║
║ │   ├─ ☕ CollectAdultsIntoListProgram.java   📜 CollectAdultsIntoList.drl    			 ║
║ │   ├─ ☕ AllAdultsForallKeywordProgram.java  📜 AllAdultsForallKeyword.drl   			 ║
║ │   ├─ ☕ SeniorCountAccumulateKeywordProgram.java 📜 SeniorCountAccumulateKeyword.drl 	 ║
║ │   ├─ ☕ InsertDiscountFactProgram.java      📜 InsertDiscountFact.drl       			 ║
║ │   ├─ ☕ UpdatePersonAgeProgram.java         📜 UpdatePersonAge.drl          			 ║
║ │   ├─ ☕ RemoveChildFactProgram.java         📜 RemoveChildFact.drl          			 ║
║ ├── 🟧 control/                                                               			 ║
║ │   ├─ ☕ PrioritySalienceRuleProgram.java           📜 PrioritySalienceRule.drl 			 ║
║ │   ├─ ☕ SafeUpdateNoLoopProgram.java              📜 SafeUpdateNoLoop.drl      			 ║
║ │   ├─ ☕ LockActiveGroupRuleProgram.java           📜 LockActiveGroupRule.drl   			 ║
║ │   ├─ ☕ BillingAgendaGroupRuleProgram.java        📜 BillingAgendaGroupRule.drl			 ║
║ │   ├─ ☕ ExclusiveActivationGroupRuleProgram.java  📜 ExclusiveActivationGroupRule.drl 	 ║
║ │   ├─ ☕ VisaCheckRuleflowGroupProgram.java        📜 VisaCheckRuleflowGroup.drl			 ║
║ ├── 🟩 cep/                                                                   			 ║
║ │   ├─ ☕ LoginBeforeTemporalRuleProgram.java        📜 LoginBeforeTemporalRule.drl 		 ║
║ │   ├─ ☕ LoginAfterTemporalRuleProgram.java         📜 LoginAfterTemporalRule.drl  		 ║
║ │   ├─ ☕ EventDuringTemporalRuleProgram.java        📜 EventDuringTemporalRule.drl 	 	 ║
║ │   ├─ ☕ EventOverlapsTemporalRuleProgram.java      📜 EventOverlapsTemporalRule.drl 	 ║
║ │   ├─ ☕ EventMeetsTemporalRuleProgram.java         📜 EventMeetsTemporalRule.drl         ║
║ │   ├─ ☕ TemperatureSlidingTimeWindowProgram.java   📜 TemperatureSlidingTimeWindow.drl   ║
║ │   ├─ ☕ TemperatureSlidingLengthWindowProgram.java 📜 TemperatureSlidingLengthWindow.drl ║
║ │   ├─ ☕ LoginEventExpirationProgram.java           📜 LoginEventExpiration.drl           ║
║ │   ├─ ☕ HeartbeatTimerRuleProgram.java             📜 HeartbeatTimerRule.drl             ║
║ │   ├─ ☕ MultipleLoginDetectionProgram.java         📜 MultipleLoginDetection.drl         ║
║ │   ├─ ☕ WorkingHoursCalendarRuleProgram.java       📜 WorkingHoursCalendarRule.drl       ║
║ ├── 🟨 decision/                                                                           ║
║ │   ├─ ☕ LoanEligibilityDecisionTableXLSProgram.java                                      ║
║ │   ├─ ☕ DiscountDecisionTableCSVProgram.java                                             ║
║ │   ├─ ☕ GuidedLoanDecisionTableProgram.java                                              ║
║ │   ├─ ☕ CustomerDiscountTemplateProgram.java                                             ║
║ ├── 🟫 advanced/                                                                           ║
║ │   ├─ ☕ GlobalResultsLoggingProgram.java                                                 ║
║ │   ├─ ☕ DoubleAgeFunctionRuleProgram.java                                                ║
║ │   ├─ ☕ QueryAllAdultsProgram.java                                          			 ║
║ │   ├─ ☕ DSLDiscountRulesProgram.java                                        			 ║
║ │   ├─ ☕ PatientDiagnosisRuleUnitsProgram.java                               			 ║
║ │   ├─ ☕ LogicalFactTMSExampleProgram.java                                   			 ║
║ ├── 🟪 sessions/                                                              			 ║
║ │   ├─ ☕ StatefulPersonSessionProgram.java                                   			 ║
║ │   ├─ ☕ StatelessPersonSessionProgram.java                                  			 ║
║ │   ├─ ☕ KieBaseSetupProgram.java                                            			 ║
║ │   ├─ ☕ KieSessionExecutionProgram.java                                     			 ║
║ │   ├─ ☕ KieContainerInitializationProgram.java                              			 ║
║ │   ├─ ☕ HotDeploymentKieScannerProgram.java                                 			 ║
║ └── 📁 resources/                                                             			 ║
║     ├─ rules/                  📜 .drl files                                 				 ║
║     ├─ decision-tables/        📊 XLS/CSV/Guided tables                      				 ║
║     └─ templates/              🏷️ .drt template files                        				 ║
╠════════════════════════════════════════════════════════════════════════════════════════════╣
║ ✅ TOTAL PROGRAMS: 46                                                       				 ║
╚════════════════════════════════════════════════════════════════════════════════════════════╝
```


👉 [Generate project on Spring Initializr](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.5.5&packaging=jar&jvmVersion=21&groupId=com.kaleshrikant.drools&artifactId=Drools-Practice&name=Drools-Practice&description=This%20project%20is%20a%20Spring%20Boot%203%20%2B%20Drools%20practice%20setup%20designed%20for%20learning%20and%20experimenting%20with%20Drools%20Rule%20Engine%20using%20Java%2021.&packageName=com.kaleshrikant.drools&dependencies=)

