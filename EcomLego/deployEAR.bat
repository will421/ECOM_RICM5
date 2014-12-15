(
echo " ------> mvn clean install"
mvn clean install
echo " ------> asadmin stop-domain"
asadmin stop-domain
echo " ------> asadmin start-domain"
asadmin start-domain
echo " ------> asadmin stop-database"
asadmin stop-database
echo " ------> asadmin start-database"
asadmin start-database
echo " ------> asadmin undeploy ecomear"
asadmin undeploy ecomear
echo " ------> asadmin deploy --name ecomear --contextroot "ecom" ecomear\target\ecom-0.1.0-SNAPSHOT.ear"
asadmin deploy --name ecomear --contextroot "ecom" ecomear\target\ecomear-0.1.0-SNAPSHOT.ear
echo "------> asadmin get-client-stubs --appname ecomear ecomear\target\"
asadmin get-client-stubs --appname ecomear ecomear\target\
echo "------> appclient -jar ecomear\target\ecomearClient.jar"
appclient -jar ecomear\target\ecomearClient.jar
)