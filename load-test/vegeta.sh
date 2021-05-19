echo "GET http://localhost:8080/v1/books/9784865942248" | vegeta attack -rate=40 -duration=30s > result.bin
vegeta report result.bin
