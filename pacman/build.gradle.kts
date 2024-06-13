plugins {
    id("java")
}

group = "heig.mcr.visitor"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

task("run", JavaExec::class) {
    mainClass.set("heig.mcr.visitor.Main")
    classpath = sourceSets["main"].runtimeClasspath
}
