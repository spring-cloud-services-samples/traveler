echo -n "Creating services..."
{
  cf create-service p-service-registry standard traveler-service-registry
  cf create-service p-circuit-breaker-dashboard standard traveler-circuit-breaker-dashboard
} &> /dev/null
until [ `cf service traveler-circuit-breaker-dashboard | grep -c "succeeded"` -eq 1  ]
do
  echo -n "."
done
until [ `cf service traveler-service-registry | grep -c "succeeded"` -eq 1  ]
do
  echo -n "."
done
echo
echo "Services created. Pushing applications."
pushd company && cf push -p build/libs/company-0.0.1-SNAPSHOT.jar
popd
pushd agency && cf push -p build/libs/agency-0.0.1-SNAPSHOT.jar
popd
echo "" && echo "Done!" && echo ""
