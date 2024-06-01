#!/usr/bin/env bash

# 로그 파일의 디렉토리가 존재하는지 확인하고 없으면 생성
if [ ! -d "/home/ec2-user/app/deploy" ]; then
  sudo mkdir -p /home/ec2-user/app/deploy
  sudo chmod 755 /home/ec2-user/app/deploy
fi

# 로그 파일을 생성하고 권한 설정
sudo touch /home/ec2-user/app/deploy/deploy.log
sudo chmod 666 /home/ec2-user/app/deploy/deploy.log

# 로그 파일에 로그 작성
echo "Stopping application" >> /home/ec2-user/app/deploy/deploy.log
PROJECT_ROOT="/home/ec2-user/app/deploy"
JAR_FILE="$PROJECT_ROOT/build/libs/cmc15th_hackathon-0.0.1-SNAPSHOT.jar"

DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# 현재 구동 중인 애플리케이션 pid 확인
CURRENT_PID=$(pgrep -f $JAR_FILE)

# 프로세스가 켜져 있으면 종료
if [ -z $CURRENT_PID ]; then
  echo "$TIME_NOW > 현재 실행중인 애플리케이션이 없습니다" >> $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행중인 $CURRENT_PID 애플리케이션 종료 " >> $DEPLOY_LOG
  kill -15 $CURRENT_PID
fi
