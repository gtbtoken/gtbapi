# GTB API

    ./mvnw clean package
    aws ecr get-login --no-include-email --region ap-southeast-1 | bash
    docker build -t gtb-api .
    docker tag gtb-api:latest 227111166007.dkr.ecr.ap-southeast-1.amazonaws.com/gtb-api:latest
    docker push 227111166007.dkr.ecr.ap-southeast-1.amazonaws.com/gtb-api:latest