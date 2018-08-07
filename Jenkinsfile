pipeline {
   agent any
   environment{
       password = credentials('cf-creds')
   }
   stages {
       stage('Build') {
           steps {
               echo 'Building Application: accounts...'
               sh 'cd applications/account'
               sh './gradlew build'
               sh 'cd ..'
               echo 'Building Application: orders...'
               sh 'cd order'
               sh './gradlew build'
               sh 'cd ..'
               echo 'Building Application: products...'
               sh 'cd product'
               sh './gradlew build'
               sh 'cd ..'
               echo 'Building Application: shipments...'
               sh 'cd shipment'
               sh './gradlew build'
               sh 'cd ..'
           }
       }
       stage('Deploy') {
           steps {
               echo 'Logging in to CF...'
               sh 'cf login -a https://api.run.pivotal.io -u kabounassif@solstice.com -p $password -o solstice-org -s kabounassif-cnt'
               echo 'Deploying....'

               sh 'cf push Account --random-route -p applications/account/build/libs/account-0.0.1-SNAPSHOT.jar'
               sh 'cf push Order --random-route -p applications/order/build/libs/order-0.0.1-SNAPSHOT.jar'
               sh 'cf push Product --random-route -p applications/product/build/libs/product-0.0.1-SNAPSHOT.jar'
               sh 'cf push Shipment --random-route -p applications/shipment/build/libs/shipment-0.0.1-SNAPSHOT.jar'
           }
       }
   }
}