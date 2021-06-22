#/bin/bash
execute_date=`date '+%Y%m%d%H%M%S'`
echo "Warmup..."
echo "GET http://localhost:8080/v1/bookmarks/isbn" | vegeta attack -rate=10 -duration=60s > result-rate$1-$execute_date.bin
echo "Warmup End"
echo "Start Attack..."
echo "GET http://localhost:8080/v1/bookmarks/isbn" | vegeta attack -rate=$1 -duration=600s > result-rate$1-$execute_date.bin
echo "Start Report..."
vegeta report result-rate$1-$execute_date.bin
