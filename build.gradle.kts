// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.library")) {
            extensions.configure<org.gradle.api.publish.PublishingExtension> {
                publications {
                    register<org.gradle.api.publish.maven.MavenPublication>("release") {
                        groupId = "com.yourorg.toolkit"
                        version = providers.gradleProperty("toolkit.version")
                            .orElse("0.1.0").get()
                        afterEvaluate {
                            from(components["release"])
                        }
                    }
                }
                repositories {
                    mavenLocal()
                }
            }
        }
    }
}
