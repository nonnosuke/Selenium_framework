pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }
    
    stages {
        stage('Clean') {
            steps {
                bat 'mvn clean'
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
             archiveArtifacts artifacts: 'target/surefire-reports/**', allowEmptyArchive: true
        }

        failure {
                    echo "❌ Tests failed"
        }

        success {
                    echo "✅ Tests passed"
        }
    }
}
