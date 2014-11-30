#!/bin/bash
# Rodolphe Freby
if [ -z "$1" ] 
  then
    echo "No argument supplied"
  else
		if [[ "$1" == 'deploy' || "$1" == 'undeploy' || "$1" == 'redeploy' ]]
			then
				cd ecomear
				mvn glassfish:"$1"
				cd ..
			else
				echo "Wrong argument"
		fi
fi
