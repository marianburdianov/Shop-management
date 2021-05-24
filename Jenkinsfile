pipeline {
    agent any
    stages {
        stage("Read from Maven POM") {
            steps{
                script {
                    projectArtifactId = readMavenPom().getArtifactId()
                    projectVersion = readMavenPom().getVersion()
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
        stage("Build image") {
            steps {
                echo "Building service image and pushing it to DockerHub"
                withCredentials([usernamePassword(credentialsId: 'Docker', usernameVariable: "dockerLogin",
                    passwordVariable: "dockerPassword")]) {
                        bat "docker login -u ${dockerLogin} -p ${dockerPassword}"
                        bat "docker image build -t ${dockerLogin}/${projectArtifactId}:${projectVersion} ."
                        bat "docker push ${dockerLogin}/${projectArtifactId}:${projectVersion}"
                }
                echo "Building image and pushing it to DockerHub is successful done"
            }
        }
        stage("Deploy") {
           steps {
               bat "docker-compose --file D:/MarianOptimal/JavaProjects/MS3ShopManagementSystem/shop-management/docker-compose.yml up --detach"
               timeout(time: 60, unit: 'SECONDS') {
                   waitUntil(initialRecurrencePeriod: 2000) {
                       script {
                           def result =
                           sh script: "curl --silent http://localhost:8282/products",
                           returnStatus: true
                           return (result == 0)
                       }
                   }
               }
               echo "Server is up"
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