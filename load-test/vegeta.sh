execute_date=`date '+%Y%m%d%H%M%S'`
echo "GET http://localhost:8080/v1/bookmarks/isbn" | vegeta attack -rate=$1 -duration=5s > result-rate$1-$execute_date.bin
vegeta report result-rate$1-$execute_date.bin
