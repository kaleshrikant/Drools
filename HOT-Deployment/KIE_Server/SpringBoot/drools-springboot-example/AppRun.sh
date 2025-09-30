#!/bin/bash

# 1. Clean your Maven cache:
mvn dependency:purge-local-repository

# 2. Force update dependencies:
mvn clean compile -U

# 3. Build the project:
mvn clean package

echo "=== Drools Spring Boot Application with Package-Level Rules ==="
echo "Cleaning and building the project..."
mvn clean compile

echo ""
echo "Starting Application..."
echo "Rules are organized in packages:"
echo "  - rules.applicant (5 rules)"
echo "  - rules.loan (6 rules)"
echo ""
echo "Application will be available at: http://localhost:8090"
echo "API Documentation: http://localhost:8090/api/rules/packages"
echo ""

# 4. Run the application:
mvn spring-boot:run