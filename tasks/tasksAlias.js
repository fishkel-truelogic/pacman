module.exports = function(grunt) {
	grunt.registerTask("run", "shell:java-jar");
	grunt.registerTask('build', ['shell:maven-build']);
	grunt.registerTask('test', ['shell:maven-test']);
	grunt.registerTask("default", ["build", "run"]);
}