plugins {
    id("java")
    id("org.flywaydb.flyway") version "10.7.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.flywaydb:flyway-core:10.6.0")
    implementation("com.h2database:h2:2.2.220")
    implementation("org.hibernate:hibernate-core:6.4.4.Final")
    compileOnly("org.projectlombok:lombok:1.18.30")
}
flyway {
    locations = arrayOf("filesystem:src/main/resources/db/migration")
}

tasks.test {
    useJUnitPlatform()
}