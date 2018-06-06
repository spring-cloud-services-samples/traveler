@echo off
echo Creating services...
cf create-service p-circuit-breaker-dashboard standard traveler-circuit-breaker-dashboard > nul
cf create-service p-service-registry standard traveler-service-registry > nul
:checkCbd
  cf service traveler-circuit-breaker-dashboard | find "succeeded" > nul
  if errorlevel 1 goto :checkCbd
:checkSr
  cf service traveler-service-registry | find "succeeded" > nul
  if errorlevel 1 goto :checkSr
  echo Services created. Pushing applications.
  pushd company
  cf push -p target/company-0.0.1-SNAPSHOT.jar
  popd
  pushd agency
  cf push -p target/agency-0.0.1-SNAPSHOT.jar
  popd
  echo Done!
