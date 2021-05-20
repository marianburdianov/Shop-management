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
                bat "newman run --disable-unicode https://www.getpostman.com/collections/4ed4489c4178ef86bfe4"
            }
        }
        stage("test") {
            steps {
                echo 'testing the application...'
            }
        }
        stage("deploy") {
            steps {
                echo 'deploying the application...'
            }
        }
    }
}
// pipeline {
//     agent {
//         docker {
//             image 'maven:docker-jenkins-integration'
//             args '-v /root/.m2:/root/.m2'
//         }
//     }
//     stages {
//         stage('Build') {
//             steps {
//                 sh 'mvn -B -DskipTests clean package'
//             }
//         }
//         stage('Test') {
//             steps {
//                 sh 'mvn test'
//             }
//             post {
//                 always {
//                     junit 'target/surefire-reports/*.xml'
//                 }
//             }
//         }
//     }
// }