#!/bin/bash
#FIXME: передавать параметры при старте
trafficVolume=${1:-10}

phone=${2:-89134904534}

originatorId_start=${3:-1}
originatorId_end=${4:-5}
originatorId_range=$(($originatorId_end - $originatorId_start))

sessionNames=("FirstSession" "SecondSession" "ThirdSession")

url="http://localhost:8083/api/message"

for (( i=0; i<trafficVolume; i++ ))
do
    originatorId=$(($originatorId_start + $i % ($originatorId_range + 1)))

    sessionNameIndex=$(($i % ${#sessionNames[@]}))
    sessionName=${sessionNames[$sessionNameIndex]}

    jsonBody=$(jq -n \
                  --arg phone "$phone" \
                  --argjson originatorId "$originatorId" \
                  --arg text "Some text in SMS" \
                  --arg sessionName "$sessionName" \
                  '{phone: $phone, originatorId: $originatorId, text: $text, sessionName: $sessionName}')

    curl -X POST "$url" \
         -H "Content-Type: application/json" \
         -d "$jsonBody"

    phone=$(($phone + 1))
done