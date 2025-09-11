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
