@echo off
cf create-service p-service-registry standard service-registry
cf create-service p-circuit-breaker-dashboard standard circuit-breaker-dashboard
pushd company
cf push -p target/company-0.0.1-SNAPSHOT.jar
popd
pushd agency
cf push -p target/agency-0.0.1-SNAPSHOT.jar
popd
echo Done!
