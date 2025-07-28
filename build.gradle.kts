plugins {
    id("java-library")
    id("maven-publish")
    id("project-report")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.google.guava:guava:11.0.2")

    testImplementation("javax.inject:javax.inject:1")
    testImplementation("org.testng:testng:5.14.10")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/myramoki/compilation-toolbox")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}
