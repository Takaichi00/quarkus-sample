execute_date=`date '+%Y%m%d%H%M%S'`
echo "GET http://localhost:8080/q/health/live" | vegeta attack -rate=$1 -duration=120s > result-rate$1-$execute_date.bin
vegeta report result-rate$1-$execute_date.bin
