pipeline {
    agent any

    tools {
        maven 'Maven'
    }
    
    stages {
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
             allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results']]
             ])
             junit '**/surefire-reports/*.xml'
        }
    }
}
