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

## Login to AWS
1. Navigate to [AWS](https://236749792102.signin.aws.amazon.com/console) to sign in to console.
2. Username: Workshop
3. Password: *Will be provided in the workshop*

## Region
In the navigation bar, top right corner, make sure region **Frankfurt** (eu-central-1) is selected.

## Setup the required services

To setup the pipeline we first need to create the required AWS services that are part of the pipeline.
The following services can be found by searching in the **Services** tab found in the top left navigation bar of your
browser.

### ECR
The ECR is our docker image repository. This is where we will publish our application image after it has been build.

1. Create a new repository with name 'workshop-<*teamname*>', where you select your own unique team name. You do not
need to do any other configurations.

### CodeBuild
This service will build the code, test it, create a docker image and publish it
to ECR.

1. Create build project
2. Fill in the required fields:

*Project Configuration*
- **Project name**: workshop-<*teamname*>

*Source*
- **Source provider**: GitHub
- **Repository**: Repository in my GitHub account
- **GitHub repository**: Mercell/deployment-pipeline-workshop

*Environment*
- **Operating system**: Ubuntu
- **Runtime(s)**: Standard
- **Image**: aws/codebuild/standard:4.0
- **Privileged (checkbox)**: Set checked/enabled
- **Service role**: Existing service role
- **Role ARN**: codebuild-workshop-demo-service-role
- **Allow AWS CodeBuild to modify this service role so it can be used with this build project (checkbox)**: uncheck/disable
- **Additional configuration**
    - Environment variables
    
        | Name | Value |
        | ---  | ----- |
        | IMAGE_REPO_NAME | workshop-<*teamname*> |
     
### ECS
This is where we define the cluster where our application will run in.

#### In the 'Clusters' section

1. Create cluster
    - **Cluster template**: Networking only
    - **Cluster name**: workshop-<*teamname*>
    
#### In the 'Task Definitions' section

1. Create new Task Definition
    1. **Select launch type compatibility**: FARGATE
    2. **Task definition name**: workshop-<*teamname*>
    3. **Task Role**: ecsTaskExecutionRole
    3. Task size
        - **Task memory (GB)**: 0.5GB
        - **Task CPU (vCPU)**: 0.25vCPU
    4. Container Definitions (Click add container)
        - **Container name**: workshop-<*teamname*>
        - **Image**: 236749792102.dkr.ecr.eu-central-1.amazonaws.com/workshop-<*teamname*>:latest
        - **Port mappings**: 
            - Click 'Add port mapping'
            - Write '8080' in the 'Container port' field
        - Click Add button
    5. Click Create button
    
#### In the 'Clusters' section

1. Navigate to your cluster
2. Find the 'Services' tab (should be open by default) 
3. Click Create button
    - **Launch type**: FARGATE
    - **Task definition**: workshop-<*teamname*> (The one you just created)
    - **Service name**: workshop-<*teamname*>
    - **Number of tasks**: 1
    - Click Next step button
    - **Cluster VPC**: Select 'vpc-c32dd7a9'
    - **Subnets**: Select any one of the available
    - Click Next step button
    - Click Next step button
    - Click Create service button

### CodePipeline
This is where we connect the build and deployment services in AWS.

1. Click Create pipeline
    - **Pipeline name**: workshop-<*teamname*>
    - **Service role**: Existing service role
    - **Role name**: AWSCodePipelineServiceRole-eu-central-1-workshop-demo
    - Click Next button
    - **Source provider**: GitHub
    - Click Connect to GitHub
    - **Repository**: Mercell/deployment-pipeline-workshop
    - **Branch**: master 
    - Click Next button
    - **Build provider**: AWS CodeBuild
    - **Project name**: workshop-<*teamname*> (The CodeBuild project created)
    - Click Next button
    - **Deploy provider**: Amazon ECS
    - **Cluster name**: workshop-<*teamname*> (The cluster you created)
    - **Service name**: workshop-<*teamname*> (The service you created)
    - Click Next button
    - Click Create pipeline button
    
Congratulations! That's it. If you've come this far you have set up a continuous deployment pipeline in AWS that will
build and deploy the code to ECS for each update to the master branch.