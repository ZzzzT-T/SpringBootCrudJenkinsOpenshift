pipeline {
    agent any

    tools {
        maven 'M3'  // Make sure 'M3' is configured in Jenkins as a Maven installation
    }

    stages {
        stage('Clone') {
            steps {
                git url: 'https://github.com/ZzzzT-T/SpringBootCrudJenkinsOpenshift.git', branch: 'main' //
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        // Optional: Run app locally (only for internal testing)
        stage('Deploy (Run Spring Boot)') {
            steps {
                sh 'nohup java -jar target/*.jar > app.log 2>&1 &'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
