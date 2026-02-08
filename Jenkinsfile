pipeline {
    agent any

    stages{

    stage('build') {
    steps {
    bat 'mvn clean package'

    archiveArtifacts artifacts: 'target/*.jar'

    junit 'target/surefire-reports/*.xml'
}

}

    }}