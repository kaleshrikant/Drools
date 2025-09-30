# Test the endpoints:

# Check health
curl http://localhost:8090/api/rules/health

# Get package info
curl http://localhost:8090/api/rules/packages

# Test applicant rules
curl -X POST http://localhost:8090/api/rules/evaluate-applicant \
     -H "Content-Type: application/json" \
     -d '{"name": "John Doe", "age": 28, "yearsOfExperience": 4, "currentSalary": 50000, "expectedSalary": 60000}'

# Test loan rules
curl -X POST http://localhost:8090/api/rules/evaluate-loan \
     -H "Content-Type: application/json" \
     -d '{"applicantName": "Jane Smith", "amount": 25000, "durationMonths": 36, "annualIncome": 75000, "creditScore": 780}'