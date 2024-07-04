
plugins {
     id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"
    java
}

group = "com.baps.kms"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
     maven { url = uri("https://repo.spring.io/release") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.postgresql:postgresql")
    // implementation("org.jetbrains.kotlin:kotlin-reflect")
    // implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-devtools")
	implementation("jakarta.persistence:jakarta.persistence-api:2.2.3")
    implementation("org.hibernate:hibernate-core")
    implementation("javax.persistence:javax.persistence-api:2.2")
    implementation("org.projectlombok:lombok") // Optional for reducing boilerplate code
// https://mavenlibs.com/maven/dependency/com.twilio.sdk/twilio-java-sdk
implementation("com.twilio.sdk:twilio:8.31.0")
    runtimeOnly("org.postgresql:postgresql")

    implementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}

// java {
//     sourceCompatibility = JavaVersion.VERSION_1_8
//     targetCompatibility = JavaVersion.VERSION_1_8
// }

tasks.wrapper {
    gradleVersion = "8.8"
}

tasks.register<Jar>("customJar") {
    from(sourceSets.main.get().allSource)
}
