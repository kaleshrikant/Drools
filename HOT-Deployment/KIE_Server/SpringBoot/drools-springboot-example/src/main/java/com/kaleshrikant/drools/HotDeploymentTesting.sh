# Application health
curl http://localhost:8090/api/rules/health


    Drools Rule Engine with HOT Deployment is running!%

# System info
curl http://localhost:8090/api/debug/system-info


    System Information
    =================
    Java Version: 21.0.8
    Spring Boot: 3.5.5
    Drools Version: 9.44.0.Final
    Available Processors: 18
    Max Memory: 3862 MB
    Free Memory: 39 MB
    Total Memory: 92 MB

# KIE container status
curl http://localhost:8090/api/debug/kie-info


    === KIE CONTAINER DEBUG INFO ===
    KieContainer Available: true
    ✓ Session Creation: SUCCESS
    ✓ Rules Fired in Test: 0
    ✓ Session Disposal: SUCCESS
    Release ID: org.default:artifact:1.0.0
    =================================

# Check HOT deployment status
curl http://localhost:8090/api/hot-deployment/status


    === HOT Deployment Status ===
    Monitoring Directory: hot-deployment/
    Rules Loaded: 0
    Last Check: Tue Sep 30 23:53:44 IST 2025
    Status: No rules loaded from hot-deployment directory
    Action: Add DRL files to hot-deployment/applicant/ and hot-deployment/loan/

# Check if HOT deployment is active
curl http://localhost:8090/api/hot-deployment/active

    {
      "status": "inactive",
      "message": "HOT Deployment is inactive - no rules loaded from hot-deployment directory",
      "action": "Add DRL files to hot-deployment/applicant/ and hot-deployment/loan/",
      "timestamp": "2025-09-30T23:53:57.373571469"
    }

# Get HOT deployment info
curl http://localhost:8090/api/hot-deployment/info
    HOT Deployment Features
    ======================
    🔥 Dynamic Rule Reloading
    📁 External Rule Directory: hot-deployment/
    ⏰ Automatic Monitoring: Every 5 seconds
    🔄 Manual Reload: POST /api/hot-deployment/reload
    📊 Status Check: GET /api/hot-deployment/status

    How to Use:
    1. Add DRL files to hot-deployment/ directory:
       - hot-deployment/applicant/applicant-rules.drl
       - hot-deployment/loan/loan-rules.drl

    2. Rules auto-reload within 5 seconds of file changes
    3. Or trigger manual reload via API

    4. Test with:
       - POST /api/rules/evaluate-applicant
       - POST /api/rules/evaluate-loan

    Note: The application must be running for HOT deployment to work.
    Rules are reloaded without restarting the application!


# Test Rules (Before HOT Deployment)
# Test applicant evaluation
curl -X POST http://localhost:8090/api/rules/evaluate-applicant \
     -H "Content-Type: application/json" \
     -d '{"name": "John Doe", "age": 28, "yearsOfExperience": 4, "currentSalary": 50000, "expectedSalary": 60000}'

     {"name":"John Doe","age":28,"yearsOfExperience":4.0,"currentSalary":50000.0,"expectedSalary":60000.0,"status":"ERROR","message":"Rule evaluation failed: HOT Deployment not active - add rules to hot-deployment directory"}%


# Test loan evaluation
curl -X POST http://localhost:8090/api/rules/evaluate-loan \
     -H "Content-Type: application/json" \
     -d '{"applicantName": "Jane Smith", "amount": 25000, "durationMonths": 36, "annualIncome": 75000, "creditScore": 780}'


     {"applicantName":"Jane Smith","amount":25000.0,"durationMonths":36,"annualIncome":75000.0,"creditScore":780,"approved":false,"interestRate":0.0,"rejectionReason":"Rule evaluation failed: HOT Deployment not active - add rules to hot-deployment directory"}%


# Setup HOT Deployment
# Create the directory structure (if not auto-created)
mkdir -p hot-deployment/applicant
mkdir -p hot-deployment/loan

# Create sample HOT deployment rules
echo 'package rules.applicant
import com.kaleshrikant.drools.model.Applicant

rule "HOT Junior Applicant"
    when
        $a: Applicant(age < 25, yearsOfExperience < 2)
    then
        $a.setStatus("REJECTED");
        $a.setMessage("[HOT] Too young and insufficient experience");
        System.out.println("[HOT Applicant Rules] " + $a.getName() + " rejected");
end

rule "HOT Experienced Professional"
    when
        $a: Applicant(yearsOfExperience >= 5, expectedSalary <= currentSalary * 1.3)
    then
        $a.setStatus("APPROVED");
        $a.setMessage("[HOT] Experienced professional");
        System.out.println("[HOT Applicant Rules] " + $a.getName() + " approved");
end' > hot-deployment/applicant/applicant-rules.drl

# Trigger manual reload
curl -X POST http://localhost:8090/api/hot-deployment/reload


    {
      "status": "success",
      "message": "Rules reloaded successfully via HOT deployment!",
      "timestamp": "2025-09-30T23:55:25.057581992"
    }


# Test HOT Deployment
# Check HOT deployment is now active
curl http://localhost:8090/api/hot-deployment/active


    {
      "status": "active",
      "message": "HOT Deployment is active and serving rules",
      "rulesLoaded": 173,
      "timestamp": "2025-09-30T23:55:35.713869797"
    }

# Test with HOT deployment rules
curl -X POST http://localhost:8090/api/rules/evaluate-applicant \
     -H "Content-Type: application/json" \
     -d '{"name": "Young Steve", "age": 22, "yearsOfExperience": 1, "currentSalary": 30000, "expectedSalary": 35000}'


{"name":"Young Steve","age":22,"yearsOfExperience":1.0,"currentSalary":30000.0,"expectedSalary":35000.0,"status":"REJECTED","message":"[HOT] Too young and insufficient experience"}%
