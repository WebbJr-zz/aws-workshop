# Welcome to the Aws & Terraform Workshop!

## Building the project locally
This guide will help you build and run the project locally.

### Building the jar
Requirements: JDK8 or later installed

**Windows terminal**: ``gradlew build``

*or*

**Linux terminal**: ``./gradlew build``

#### Running the jar

**Windows terminal**: ``java -jar build\libs\aws-workshop-1.0-SNAPSHOT.jar``

*or*

**Linux terminal**: ``java -jar build/libs/aws-workshop-1.0-SNAPSHOT.jar``

 ---
 
Navigate to *localhost:8080* to view the application

### Building the Docker image
Requirements: Docker installed

Building the Docker image depends on having previously build the jar.

**Terminal**: ``docker build -t aws-workshop .``

### Running the Docker image

**Terminal**: ``docker run -p 8080:8080 aws-workshop``

---
 
Navigate to *localhost:8080* to view the application