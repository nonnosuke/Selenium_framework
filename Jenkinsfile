pipeline {
    agent any

    environment {
        APP_REPO = 'git@github.com:nonnosuke/Selenium_framework.git'
        JENKINS_SSH_KEY = 'jenkins-ssh-key'
    }

    stages {
        stage('Checkout Application') {
            steps {
                git url: "${APP_REPO}",
                    branch: 'main',
                    credentialsId: "${JENKINS_SSH_KEY}"
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            junit '**/surefire-reports/*.xml'
        }
    }
}
