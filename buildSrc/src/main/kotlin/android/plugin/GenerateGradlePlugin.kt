package android.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.security.MessageDigest

@Suppress("UNCHECKED_CAST")
class GenerateGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("generateModuleTestConfig") {
            val raw = project.extensions.getByName("modules") as List<Map<String, String>>
            val modules = raw.map { data ->
                Module(name = data["name"] as String, path = data["path"] as String)
            }.filter { it.name.startsWith("library") }

            val folder = File(project.projectDir, ".circleci")
            val template = File(folder, "config_template.yml")
            val data = buildString {
                append(template.readText())
                modules.forEach { append(project.generateModuleBuildConfiguration(it)) }
            }
            project.writeBuildConfiguration(data)
        }
    }
}


private fun Project.generateModuleBuildConfiguration(library: Module): String {
    val tasks = listOf("assembleRelease", "assembleAndroidTest", "lintRelease", "restRelease")
    val gradleTask = tasks.joinToString(separator = " ") { task ->
        "${library.path}:$task"
    }

    return """
        - build_test_check:
            name: ${library.name}
            moduleName: ${library.path}
            dependencyCacheKey: ${library.createDependencyCache(project.rootDir)}
            gradleTasks: $gradleTask
            
            filters: 
                tags:
                   only: /^v[0-9]+(\.[0-9]+)*(-.*)?${'$'}/"""
}

private fun Project.writeBuildConfiguration(configuration: String) {
    val folder = File(project.projectDir, ".circleci")
    val file = File(folder, "generate_config.yml")
    file.writeText(configuration)
}

private data class Module(
    val name: String,
    val path: String
) {
    /** Gets the relative file path **/
    fun getFilePath() = path.substring(1).replace(":", "/")

    /** we need to do this because downloading dependencies everytime
     * is a very un reliable process and can time out unexpectedly
     * we use this key to to determine if the build gradle has been updated
     **/
    fun createDependencyCache(rootDir: File): String {
        val modulePath = File(rootDir, getFilePath())
        val rootBuildFile = File(rootDir, "build.gradle.kts")
        val moduleBuildFile = File(modulePath, "build.gradle.kts")
        return listOf("deps", sha1(rootBuildFile), sha1(moduleBuildFile)).joinToString(
            separator = "-1"
        )
    }
}

private fun sha1(file: File): String {
    return MessageDigest.getInstance("SHA-1").digest(file.readBytes())
        .joinToString("") { "%02x".format(it) }
}