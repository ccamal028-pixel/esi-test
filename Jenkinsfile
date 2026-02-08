pipeline {
    agent any

    stages{

    stage('init') {
    steps {
    bat 'mvn clean'
}}

    stage('test') {
    steps {
    bat 'mvn test'
junit 'target/surefire-reports/*.xml'

    }
}



stage('build') {
    steps {
        bat 'mvn package'

        archiveArtifacts artifacts: 'target/*.jar'
}

}

    }}