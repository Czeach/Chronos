// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

	val kotlin = "2.0.0"

	id("com.android.application") version "8.5.1" apply false
	id("org.jetbrains.kotlin.android") version kotlin apply false
	id("com.google.dagger.hilt.android") version "2.42" apply false
	id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
	id("org.jetbrains.kotlin.plugin.compose") version kotlin apply false

	// NOTE: Do not place your application dependencies here; they belong
	// in the individual module build.gradle files
}

tasks.register<Delete>("clean") {
	delete(rootProject.layout.buildDirectory)
}