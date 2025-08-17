Here we need to add 3 dependencies.

1.  Drools :: Core
2.  Drools :: Compiler
3.  Drools :: Kei

🧩 Key Interfaces/Classes in kie-api
  KieServices: Factory for accessing KIE APIs
  KieContainer: Loads KIE bases from the classpath or Maven repo
  KieSession: Interface to interact with the rule engine (insert facts, fire rules)
  KieBase: Represents a compiled set of rules and processes
  ReleaseId: Defines artifact coordinates for your knowledge base

📦 kmodule.xml – KIE Module Config
  Save this under:
    src/main/resources/META-INF/kmodule.xml
🚀 DroolsWithKie.java – Run the rule engine

Create Stateful Session i.e. "first-ksession-rule"
--------

🔧  KieService?
factory and entry point to get other important components in the KIE ecosystem.
This interface allows developers to:
  Create KIE containers.
  Load and manage knowledge bases.
  Fire rules and run processes.
  Build and manage KIE modules.
  It follows the Service Loader pattern and can be accessed as a singleton.

./gradlew clean
./gradlew build