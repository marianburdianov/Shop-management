pipeline {
    agent any
    stages {
        stage("Read form Maven POM") {
            steps {
                script {
                    projectArtifactId = readMavenPom().getArtifactId()
                    projectVersion = readMavenPom.getModelVersion()
                }
                echo 'building the application...'
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