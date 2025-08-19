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

 Postman :
  URI :
      http://localhost:8080/api/mortgage/apply
  Request  :
    {
        "applicantName": "Alice",
        "loanAmount": 250000,
        "creditScore": 720
    }

  Response :
    {
        "applicantName": "Alice",
        "loanAmount": 250000.0,
        "creditScore": 720,
        "status": "APPROVED"
    }
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
$ ./gradlew clean build --refresh-dependencies
$ ./gradlew bootRun
  5 actionable tasks: 5 executed
--------------------------------------------------------------------
$ docker build  -t beingshrikant/mortgage-rule-engine.jar:1.0.0 .
--------------------------------------------------------------------
$ docker images
REPOSITORY                               TAG        IMAGE ID       CREATED          SIZE
beingshrikant/mortgage-rule-engine.jar   1.0.0      4168378d6d54   26 seconds ago   801MB
--------------------------------------------------------------------
$ docker run -dit --name mortgage-rule-engine -p 9090:8080 beingshrikant/mortgage-rule-engine:2.0.0
	8800736ceaf69a2358d725ba7d3c181e831c715caded8ca1b2f513dad8a67c62

$ docker logs -f mortgage-rule-engine
	  __  __             _
	 |  \/  | ___  _ __ | | _____ _ __ ___  ___  _ __
	 | |\/| |/ _ \| '_ \| |/ / _ \ '__/ __|/ _ \| '_ \
	 | |  | | (_) | | | |   <  __/ |  \__ \ (_) | | | |
	 |_|  |_|\___/|_| |_|_|\_\___|_|  |___/\___/|_| |_|

	Mortgage Rule Engine - Powered by Drools + Spring Boot
	Package: com.kaleshrikant
	Author : Shrikant Kale

$ docker ps
	CONTAINER ID   IMAGE                                      COMMAND                  CREATED          STATUS          PORTS                                         NAMES
	8800736ceaf6   beingshrikant/mortgage-rule-engine:2.0.0   "java -jar mortgage-…"   34 seconds ago   Up 33 seconds   0.0.0.0:9090->8080/tcp, [::]:9090->8080/tcp   mortgage-rule-engine

# If the existing container is stopped and you just want to restart it:
$ docker start mortgage-rule-engine
	mortgage-rule-engine

	POSTMAN :
		POST URI : http://127.0.0.1:9090/api/mortgage/apply
		INPUT :
			{
				"applicantName": "Alice",
				"loanAmount": 250000,
				"creditScore": 720
			}
		OUTPUT :
		{
				"applicantName": "Alice",
				"loanAmount": 250000.0,
				"creditScore": 720,
				"status": "APPROVED"
		}

--------------------------------------------------------------------
## Replace mortgage-rule-engine:2.0 with your actual local image name
$   docker tag beingshrikant/mortgage-rule-engine:1.0.0 beingshrikant/mortgage-rule-engine:2.0.0

## Pushing to Registry
$ docker push beingshrikant/mortgage-rule-engine:2.0.0
--------------------------------------------------------------------
