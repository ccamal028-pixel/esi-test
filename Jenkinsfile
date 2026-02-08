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

        bat 'powershell Compress-Archive -Path target/site/* -DestinationPath target/doc.zip'

        archiveArtifacts artifacts: 'target/doc.zip'

}
post{
    always {
 publishHTML (
 target :
 [allowMissing: false,
  alwaysLinkToLastBuild: true,
  keepAll: true,
  reportDir: 'target/site/apidocs',
  reportFiles: 'index.html',
  reportName: 'Documentation',
  reportTitles: 'The Report'
  ])
}
}

}

emailext(subject: " Build réussi: "),
    body: "Le build a réussi",
    to: "andou0590@gmail.com"
)
    }}