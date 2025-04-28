val mapstructVersion = "1.6.3"
val lombokVersion = "1.18.36"
val lombokMapstructBindingVersion = "0.2.0"
val hibernateModelGenVersion = "6.6.5.Final"
val jjwtVersion = "0.12.6"
val blazeVersion = "1.6.15"
val springDocVersion = "2.6.0"

plugins {
	java
	id("org.springframework.boot") version "3.3.8"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.disoraya"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")
	implementation("com.blazebit:blaze-persistence-integration-spring-data-3.3:$blazeVersion")
	implementation("org.mapstruct:mapstruct:$mapstructVersion")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocVersion")
	compileOnly("org.projectlombok:lombok:$lombokVersion")
	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")
	runtimeOnly("com.blazebit:blaze-persistence-integration-hibernate-6.2:$blazeVersion")
	annotationProcessor("org.hibernate.orm:hibernate-jpamodelgen:$hibernateModelGenVersion")
	annotationProcessor("org.projectlombok:lombok:$lombokVersion")
	annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBindingVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
