var spawn = require("child_process").spawn;

var mvnCmd = "mvn.bat";

module.exports = function(grunt) {

	grunt.registerTask("shell:maven-build", "Build the project with Maven", function() {

		var done = this.async();

		grunt.log.subhead(["Building the project using Maven"]);

		var args = ["clean", "install"];

		if (grunt.option("no-test")) {
			args.push("-Dmaven.test.skip=true");
		}

		var build = spawn(mvnCmd, args);

		build.stdout.on("data", function(chunk) {
			grunt.log.write(chunk);
		});

		build.on("close", function(code) {
			if (code != 0) {
				grunt.fail.fatal("The build failed");
			} else {
				grunt.log.ok(["Build successful"]);
				done();
			}
		});


	});

	grunt.registerTask("shell:maven-test", "Run jUnit tests with Maven", function() {

		var done = this.async();

		grunt.log.subhead(["Running jUnit tests using Maven"]);

		var args = ["test"];

		var test = spawn(mvnCmd, args);

		test.stdout.on("data", function(chunk) {
			grunt.log.write(chunk);
		});

		test.stderr.on("data", function(chunk) {
			grunt.log.write(chunk);
		});

		test.on("close", function(code) {
			if (code != 0) {
				grunt.fail.fatal("Tests failed");
			} else {
				grunt.log.ok(["Tests successful"]);
				done();
			}
		});


	});

}