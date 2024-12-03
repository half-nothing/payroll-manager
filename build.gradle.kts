import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.text.SimpleDateFormat
import java.util.*

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.git.hooks)
    alias(libs.plugins.detekt)
    alias(libs.plugins.shadow)
}

val props = properties

val srcVersion: String by project
val mavenGroup: String by project
val targetJavaVersion = 17
val javaVersion = JavaVersion.VERSION_17

version = "$srcVersion${getVersionMetadata()}"
group = mavenGroup

repositories {
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://maven.aliyun.com/repository/gradle-plugin/")
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}


base {
    archivesName.set(rootProject.name)
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    withSourcesJar()
}

tasks {
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    withType<KotlinCompile>().configureEach {
        compilerOptions.jvmTarget.set(JvmTarget.fromTarget(targetJavaVersion.toString()))
    }

    test {
        useJUnitPlatform()
    }

    jar {
        from("LICENSE")
        manifest {
            attributes(
                "Build-By" to System.getProperty("user.name"),
                "Build-TimeStamp" to SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Date()),
                "Build-Version" to version,
                "Created-By" to "Gradle ${gradle.gradleVersion}",
                "Build-Jdk" to "${System.getProperty("java.version")} " +
                        "(${System.getProperty("java.vendor")} ${System.getProperty("java.vm.version")})",
                "Build-OS" to "${System.getProperty("os.name")} " +
                        "${System.getProperty("os.arch")} ${System.getProperty("os.version")}"
            )
        }
    }
}

detekt {
    buildUponDefaultConfig = true
    autoCorrect = true
    config.setFrom(rootProject.files("detekt.yml"))
}

gitHooks {
    setHooks(
        mapOf("pre-commit" to "detekt")
    )
}

fun getVersionMetadata(): String {
    val buildId = System.getenv("GITHUB_RUN_NUMBER")
    val workflow = System.getenv("GITHUB_WORKFLOW")

    if (workflow == "Release") {
        return ""
    }

    if (buildId != null) {
        return "+build.$buildId"
    }

    return "+nightly"
}
