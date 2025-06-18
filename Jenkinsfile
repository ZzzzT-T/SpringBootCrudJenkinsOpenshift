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
                script {
                    def imageFull = "${env.OPENSHIFT_REGISTRY}/${env.OPENSHIFT_PROJECT}/${env.IMAGE_NAME}:${env.IMAGE_TAG}"
                    sh "docker build -t ${imageFull} ."
                }
            }
        }

        stage('Push to OpenShift Registry') {
            steps {
                script {
                    def imageFull = "${env.OPENSHIFT_REGISTRY}/${env.OPENSHIFT_PROJECT}/${env.IMAGE_NAME}:${env.IMAGE_TAG}"
                    sh """
                        echo $OPENSHIFT_TOKEN | docker login -u openshift --password-stdin ${env.OPENSHIFT_REGISTRY}
                        docker push ${imageFull}
                    """
                }
            }
        }

        stage('Deploy to OpenShift') {
            steps {
                sh '''
                    oc login --token=$OPENSHIFT_TOKEN --server=$OPENSHIFT_CLUSTER
                    oc project $OPENSHIFT_PROJECT

                    oc set image deployment/springboot-app springboot-app=${OPENSHIFT_REGISTRY}/${OPENSHIFT_PROJECT}/${IMAGE_NAME}:${IMAGE_TAG} --record
                    oc rollout restart deployment/springboot-app
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
