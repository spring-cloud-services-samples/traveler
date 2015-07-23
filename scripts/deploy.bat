@echo off
cf create-service p-service-registry standard service-registry
cf create-service p-circuit-breaker-dashboard standard circuit-breaker-dashboard
pushd company
cf push
popd
pushd agency
cf push
popd
echo Done!
