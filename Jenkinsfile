pipeline {
    agent any

    tools {
        maven 'M3'
    }

    environment {
        IMAGE_NAME = 'springboot-app'
        IMAGE_TAG = 'latest'
        OPENSHIFT_PROJECT = 'zzzzt-dev'
        OPENSHIFT_REGISTRY = 'image-registry.openshiftapps.com'
        OPENSHIFT_CLUSTER = 'https://api.openshift.example.com:6443'
        OPENSHIFT_TOKEN = credentials('OPENSHIFT_TOKEN') // Add this in Jenkins Credentials
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/ZzzzT-T/SpringBootCrudJenkinsOpenshift.git', branch: 'main'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                 sh """
                    docker build -t ${REGISTRY}/${PROJECT}/${IMAGE_NAME}:${IMAGE_TAG} .
                """
            }
        }

        stage('Push to OpenShift Registry') {
            steps {
                withCredentials([string(credentialsId: 'OPENSHIFT_TOKEN', variable: 'OPENSHIFT_TOKEN')]) {
                    sh '''
                        echo "$OPENSHIFT_TOKEN" | docker login -u openshift --password-stdin ${REGISTRY}
                        docker push ${REGISTRY}/${PROJECT}/${IMAGE_NAME}:${IMAGE_TAG}
                    '''
                }
            }
        }

        stage('Deploy to OpenShift') {
            steps {
                sh '''
                    oc patch deployment springboot-app -p '{"spec":{"template":{"metadata":{"annotations":{"date":"'''$(date)'''"} }}}}'
                '''
            }
        }
    }

    post {
        success {
            echo 'Deployment to OpenShift completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Check logs.'
        }
    }
}
