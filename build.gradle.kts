

allprojects {
    apply(plugin = "java")

    group = "by.yemelyanenko"

    repositories {
        mavenLocal()
        mavenCentral()
    }

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
    }
}