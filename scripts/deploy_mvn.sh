echo -n "Creating services..."
{
  cf create-service p-service-registry standard service-registry
  cf create-service p-circuit-breaker-dashboard standard circuit-breaker-dashboard
} &> /dev/null
until [ `cf service circuit-breaker-dashboard | grep -c "succeeded"` -eq 1  ]
do
  echo -n "."
done
until [ `cf service service-registry | grep -c "succeeded"` -eq 1  ]
do
  echo -n "."
done
echo
echo "Services created. Pushing applications."
pushd company && cf push -p target/company-0.0.1-SNAPSHOT.jar
popd
pushd agency && cf push -p target/agency-0.0.1-SNAPSHOT.jar
popd
echo "" && echo "Done!" && echo ""
