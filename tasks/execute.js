var spawn = require("child_process").spawn;

module.exports = function(grunt) {

	grunt.registerTask("shell:java-jar", "Run the .jar file", function() {

		var done = this.async();

		var jar = spawn("java", ["-jar", "target/pacman-3.0.jar"]);

		jar.stdout.on("data", function(chunk) {
			grunt.log.write(chunk);
		});

		jar.on("close", function(code) {
			done();
		})

	});

}