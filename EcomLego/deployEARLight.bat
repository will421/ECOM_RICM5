(
echo " ------> mvn clean install"
mvn clean install
echo " ------> asadmin start-domain"
asadmin start-domain
echo " ------> asadmin start-database"
asadmin start-database
echo " ------> asadmin undeploy ecomear"
asadmin undeploy ecomear
echo " ------> asadmin deploy --name ecomear --contextroot "ecom" ecomear\target\ecom-0.1.0-SNAPSHOT.ear"
asadmin deploy --name ecomear --contextroot "ecom" ecomear\target\ecom-0.1.0-SNAPSHOT.ear
)