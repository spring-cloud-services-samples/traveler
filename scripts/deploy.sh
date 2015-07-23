cf create-service p-service-registry standard service-registry
cf create-service p-circuit-breaker-dashboard standard circuit-breaker-dashboard
sleep 60
pushd company && cf push
popd; sleep 30
pushd agency && cf push
popd
echo "" && echo "Done!" && echo ""
