:: Project Structure ::

mortgage-rule-engine/
├── build.gradle
├── settings.gradle
├── Dockerfile
├── .dockerignore
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── kaleshrikant
│       │           ├── MortgageRuleApp.java
│       │           ├── config
│       │           │   └── KieConfig.java
│       │           ├── controller
│       │           │   └── MortgageController.java
│       │           └── model
│       │               └── MortgageApplication.java
│       └── resources
│           ├── application.yaml
│           ├── banner.txt
│           └── rules
│               └── mortgage-rules.drl
├── k8s
│   ├── deployment.yaml
│   └── service.yaml
└── gradle
    └── wrapper
        ├── gradle-wrapper.jar
        └── gradle-wrapper.properties

