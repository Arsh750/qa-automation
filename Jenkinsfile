<<<<<<< HEAD
pipeline {
    agent any

    tools {
        maven 'Maven_3.9'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                sh 'allure generate target/allure-results -o target/allure-report --clean'
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/allure-results/**', fingerprint: true
        }
    }
}
=======
pipeline {
    agent any

    tools {
        maven 'Maven_3.9'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                sh 'allure generate target/allure-results -o target/allure-report --clean'
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/allure-results/**', fingerprint: true
        }
    }
}
>>>>>>> 30832f0ccbd6290ac58f4b5ab15b1095a77c1c4d
