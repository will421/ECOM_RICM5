(
set DERBY_INSTALL=C:\Users\Pingu\Documents\db-derby-10.11.1.1-bin

set CLASSPATH=%DERBY_INSTALL%\lib\derby.jar;%DERBY_INSTALL%\lib\derbytools.jar;

cd %DERBY_INSTALL%\bin
setEmbeddedCP.bat
echo " ------> mvn clean install"
mvn clean install
echo " ------> asadmin start-domain"
asadmin start-domain
echo " ------> asadmin start-database"
asadmin start-database
echo " ------> asadmin undeploy ecomear"
asadmin undeploy ecomear
echo " ------> asadmin deploy --name ecomear --contextroot "ecom" ecomear\target\ecomear-0.1.0-SNAPSHOT.ear"
asadmin deploy --name ecomear --contextroot "ecom" ecomear\target\ecomear-0.1.0-SNAPSHOT.ear
echo " ------> LANCEMENT DU SCRIT SQL"
ij derbyinitadmin.sql
)