var	parseString = require('xml2js').parseString;
    spawn = require("child_process").spawn,
    fs = require("fs"),

module.exports = function(grunt) {

    grunt.registerTask("shell:java-jar", "Run the .jar file", function() {

        var done = this.async(),
            cmd = "java";
            pom = "pom.xml", 
            jarPath = "target/",
            args = ["-jar"];

        if (fs.existsSync(pom)) {

            fs.readFile(pom, function(err, data) {

                parseString(data, function (err, result) {

                jarPath += result.project.artifactId + '-' + result.project.version + '.jar';

                if (fs.existsSync(jarPath)) {

	                args.push(jarPath);
			        
			        var jar = spawn(cmd, args);

			        jar.stdout.on("data", function(chunk) {
			            grunt.log.write(chunk);
					});

			        jar.stderr.on("data", function(chunk) {
			            grunt.log.error(chunk);
					});

			        jar.on("close", function(code) {
			            done();
			        });
                } else {
                	grunt.log.error("There is no jar file. Please run 'grunt build' and try again or just 'grunt'");
                	done();
                }

                });
	        });	
        }


    });

}