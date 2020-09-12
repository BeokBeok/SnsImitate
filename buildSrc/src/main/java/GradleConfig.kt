object Version {
    const val KOTLIN = "1.4.0"
}

object ProjectConfig {
    const val GRADLE = "com.android.tools.build:gradle:4.0.1"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
}

object AndroidConfig {
    const val COMPILE_SDK = 30
    const val APP_ID = "com.beok.snsimitate"
    const val MIN_SDK = 23
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

object Dependencies {
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Version.KOTLIN}"

    const val MATERIAL = "com.google.android.material:material:1.2.1"
    const val CORE_KTX = "androidx.core:core-ktx:1.3.1"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.0.1"
}

object TestDependencies {
    const val JUNIT_JUPITER = "org.junit.jupiter:junit-jupiter:5.6.2"
    const val ASSERTJ_CORE = "org.assertj:assertj-core:3.16.1"
}
