pipeline {
    agent any

    stages{

    stage('test') {
    steps {
    junit 'target/surefire-reports/*.xml'
}

}

stage('build') {
    steps {
        bat 'mvn clean package'

        archiveArtifacts artifacts: 'target/*.jar'
}

}

    }}