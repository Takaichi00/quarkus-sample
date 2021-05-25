echo "GET http://localhost:8080/v1/bookmarks/isbn" | vegeta attack -rate=40 -duration=30s > result.bin
vegeta report result.bin
