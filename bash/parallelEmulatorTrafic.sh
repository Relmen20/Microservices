#!/bin/bash

trafficVolume=${1:-10}

phone=${2:-89134904534}

originatorId_start=${3:-1}
originatorId_end=${4:-5}
originatorId_range=$(($originatorId_end - $originatorId_start))

sessionNames=("FirstSession" "SecondSession" "ThirdSession")

url="http://localhost:8083/api/message"

max_parallel=10

send_request() {
    local phone=$1
    local originatorId=$2
    local sessionName=$3
    local jsonBody="{\"phone\": \"$phone\", \"originatorId\": $originatorId, \"text\": \"Some text in SMS\", \"sessionName\": \"$sessionName\"}"

    curl -X POST "$url" \
         -H "Content-Type: application/json" \
         -d "$jsonBody"
}

for (( i=0; i<trafficVolume; i++ ))
do
    originatorId=$(($originatorId_start + $i % ($originatorId_range + 1)))

    sessionNameIndex=$(($i % ${#sessionNames[@]}))
    sessionName=${sessionNames[$sessionNameIndex]}

    send_request $phone $originatorId $sessionName &

    phone=$(($phone + 1))

    if (( $((i % max_parallel)) == 0 && i > 0 )); then
        wait
    fi
done

wait