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
stage('documentation') {
    steps {
        bat 'mvn javadoc:javadoc'
//        archiveArtifacts artifacts: 'target/site/**' , fingerprint: true

        bat 'powershell Compress-Archive -Path target/site/* -DestinationPath target/site.zip'

        archiveArtifacts artifacts: 'target/site.zip'

}

}

    }}