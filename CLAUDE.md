# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This repository contains the **Emergent.org Parent POM**, a standardized Maven parent POM configuration published by Emergent.org. It provides plugin management and configuration for Java projects.

**Group ID:** `org.emergent.maven.parent`
**Artifact ID:** `emergent-parent`
**Current Version:** `0.5.10-SNAPSHOT`
**License:** Apache License, Version 2.0

## Project Structure

The repository has a multi-module structure:

- **`emergent-parent`** (root): Base parent POM with core plugin management
  - **`emergent-parent-java`**: Extended parent for standard Java projects with additional configuration (sortpom, git-versioner, jib)
  - **`emergent-parent-java-legacy`**: Parent for legacy Java projects, includes example modules
    - `example-java8`: Example Java 8 project
    - `example-java11`: Example Java 11 project
  - **`emergent-parent-java8`**: Specialized parent for Java 8 projects with toolchain configuration

The root POM packages a resources JAR containing `emergent-sortpom-order.xml` via the assembly plugin.

## Common Commands

### Building

```bash
# Build using Maven wrapper
./mvnw clean install

# Build without running tests
./mvnw clean install -DskipTests

# Verify build (compile, test)
./mvnw clean verify
```

### Testing

```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=MainTest

# Run tests with coverage (JaCoCo)
./mvnw clean verify
# Coverage report: target/site/jacoco/index.html
```

### Version Management

```bash
# Print current project version
./mvnw help:evaluate -q -DforceStdout -Dexpression=project.version

# Print with git-versioning debug info
./mvnw help:evaluate -Dorg.slf4j.simpleLogger.log.me.qoomon.maven.gitversioning=debug -Dexpression=project.version
```

### Code Formatting

```bash
# Sort POM files using sortpom-maven-plugin
./mvnw sortpom:sort

# Verify POM sorting
./mvnw sortpom:verify
```

### Dependency Management

```bash
# Analyze dependencies
./mvnw dependency:analyze

# Display dependency tree
./mvnw dependency:tree

# Check for dependency updates
./mvnw versions:display-dependency-updates

# Check for plugin updates
./mvnw versions:display-plugin-updates
```

### Releasing

The project uses GitHub workflows for releases:

- **maven-verify.yaml**: Runs verification builds (Java 8)
- **maven-deploy.yaml**: Deploys to distribution servers (central, github, emergent-nexus)
  - Supports dry-run mode
  - Configurable push-changes option
  - Uses Java 11 for deployment

```bash
# GPG signing during release (uses Bouncy Castle signer)
./mvnw clean install -Prelease
```

## Key Configuration Files

- **`pom.xml`**: Root parent POM defining plugin versions and enforcement rules
- **`emergent-sortpom-order.xml`**: Custom POM element ordering for sortpom plugin
- **`.editorconfig`**: Code formatting standards (indent_size=2 for XML/YAML/JSON)
- **`.mvn/`**: Maven wrapper configuration

## Maven Profiles

### Root POM Profiles

- **`release`**: Activates GPG signing (bc signer) and javadoc generation
- **`central-distmgt`**: Activates central-publishing-maven-plugin when `DEPLOY_SERVER=central`

## Key Maven Plugins

The parent POM manages versions for:

- **maven-enforcer-plugin** (3.5.0): Enforces Maven >= 3.6.3 and requireUpperBoundDeps
- **maven-surefire-plugin** (3.5.3): Test execution
- **maven-compiler-plugin** (3.14.0): Java compilation
- **maven-source-plugin** (3.3.1): Attaches source JARs
- **maven-dependency-plugin** (3.8.1): Dependency analysis with failOnWarning=true
- **jacoco-maven-plugin** (0.8.13): Code coverage
- **jib-maven-plugin** (3.4.5): Container image building (configured in java parent)
- **sortpom-maven-plugin** (3.5.1): POM file formatting
- **git-versioner-maven-plugin** (0.11.0): Git-based versioning
- **central-publishing-maven-plugin** (0.8.0): Maven Central publishing
- **versions-maven-plugin** (2.18.0): Version management

## Build Requirements

- **Maven:** >= 3.6.3 (enforced by maven-enforcer-plugin)
- **Java:** Varies by module (Java 8+ supported)
- **Encoding:** UTF-8 (project.build.sourceEncoding and project.reporting.outputEncoding)

## Example Projects

The `emergent-parent-java-legacy` module includes example projects demonstrating:
- JUnit 5 and AssertJ usage
- Test JAR creation and consumption
- Source JAR attachment for tests
- BOM imports for dependency management