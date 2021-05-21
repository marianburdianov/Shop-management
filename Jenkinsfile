pipeline {
    agent any
    stages {
        stage("Read from Maven POM") {
            steps{
                script {
                    projectArtifactId = readMavenPom().getArtifactId()
                    projectVersion = readMavenPom().getModelVersion()
                }
                echo "Building ${projectArtifactId}:${projectVersion}"
            }
        }
        stage("Test") {
            steps {
                bat "mvn -version"
                bat "mvn test"
            }
        }
        stage("Build JAR file") {
            steps {
                bat "mvn install -Dmaven.test.skip=true"
            }
        }
        stage("Newman Test") {
            steps {
                echo "Starting Newman Test"
                bat "C:/Users/maria/AppData/Roaming/npm/newman run --disable-unicode https://www.getpostman.com/collections/4ed4489c4178ef86bfe4"
            }
        }
        stage("JMeter Loading Test") {
            steps {
                echo "Starting the JMeter Loading Test"
                bat "C:/Users/maria/Downloads/apache-jmeter-5.4.1/apache-jmeter-5.4.1/bin/jmeter -jjmeter.save.saveservice.output_format.xml -n -t D:/MarianOptimal/JMeter/shop-management.jmx -l D:/report.jtl"
            }
        }
    }
}