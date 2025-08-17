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
--------------------------------------------------------------------
Build & Run Locally
 $ ./gradlew bootJar
 $  java -jar build/libs/mortgage-rule-engine-1.0.0.jar
--------------------------------------------------------------------
Test the API with curl or Postman:
curl -X POST http://localhost:8080/api/mortgage/apply \
 -H "Content-Type: application/json" \
 -d '{"applicantName":"Alice","loanAmount":250000,"creditScore":720}'
--------------------------------------------------------------------
Build & run Docker image:
  $ docker build -t kaleshrikant/mortgage-rule-engine:latest .
  $ docker run -p 8080:8080 kaleshrikant/mortgage-rule-engine
--------------------------------------------------------------------
Apply to your cluster:
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
--------------------------------------------------------------------
 $ ls -lh build/libs/
     total 57M
     -rw-rw-r-- 1 shrikant shrikant 5.3K Aug 17 21:04 mortgage-rule-engine-1.0.0-plain.jar
     -rw-rw-r-- 1 shrikant shrikant  57M Aug 17 21:04 mortgage-rule-engine.jar
--------------------------------------------------------------------
Note : .dockerignore
!build
or
!build/libs/mortgage-rule-engine.jar
--------------------------------------------------------------------
$ ./gradlew clean build
BUILD SUCCESSFUL in 1s
5 actionable tasks: 5 executed
--------------------------------------------------------------------
docker build  -t beingshrikant/mortgage-rule-engine.jar:1.0.0 .
--------------------------------------------------------------------
$ docker images
REPOSITORY                               TAG        IMAGE ID       CREATED          SIZE
beingshrikant/mortgage-rule-engine.jar   1.0.0      4168378d6d54   26 seconds ago   801MB
--------------------------------------------------------------------
