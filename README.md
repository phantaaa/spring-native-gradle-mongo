# Native Spring Boot CRUD App with Gradle and MongoDB

This project demonstrates a simple CRUD application built using Spring Boot Native. The app uses a custom Dockerfile and Docker Compose for ease of setup and deployment.

## Key Features:
 - Spring Boot Native: Take advantage of faster startup times and reduced memory footprint of native applications.
 - MongoDB Integration: Persistent storage backend.
 - Custom Dockerfile: Tailored for nativeCompile for granular control over the resulting artifact.

## External Dependencies:
 - Checkstyle: Ensure code adheres to a predefined set of coding standards.
 - OpenApi Swagger: API documentation and interaction.
 - OpenApi Generator: Generate API client, server stubs, and other API-related code.
 - MapStruct: Simplify the mapping between your data objects, improving code maintainability.

## Docker Notes:
 - The provided Dockerfile fetches the required dependencies to facilitate nativeCompile instead of using bootBuildImage. This offers more control over the final artifact.
 - Architecture Compatibility: The Dockerfile building process is currently only available for x86_64 architecture.
