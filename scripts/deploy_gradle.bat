@echo off
echo Creating services...
cf create-service p-circuit-breaker-dashboard standard circuit-breaker-dashboard > nul 
cf create-service p-service-registry standard service-registry > nul 
:checkCbd
  cf service circuit-breaker-dashboard | find "succeeded" > nul
  if errorlevel 1 goto :checkCbd
:checkSr
  cf service service-registry | find "succeeded" > nul
  if errorlevel 1 goto :checkSr
  echo Services created. Pushing applications.
  pushd company
  cf push -p build/libs/company-0.0.1-SNAPSHOT.jar
  popd
  pushd agency
  cf push -p build/libs/agency-0.0.1-SNAPSHOT.jar
  popd
  echo Done!
