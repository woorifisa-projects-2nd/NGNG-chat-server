# 👮‍♀️ 사기 거래 방지를 위한 중고거래 플랫폼 
# 내꺼니꺼 - 채팅 서버

# 1. 프로젝트 개요
우리 FIS 아카데미 클라우드 서비스 개발반 최종 프로젝트

## 개발 기간 : 2024.03.18 ~ 2024.04.30

## `사기 거래 방지를 위한 중고거래 플랫폼` 주제 선정 배경
국내 중고거래 시장이 급성장함에 따라 사기 피해액도 증가하고 있습니다.

당근마켓이 직거래를 유행시킨 이후 비대면 거래 사기는 줄어들었지만 
`'3자 사기'`(돈거래는 피해자들끼리 하고 범인은 물건을 중간에서 빼돌리는 형태)처럼 
새로운 직거래 사기 유형이 등장했습니다.

이처럼 사기꾼들은 계속해서 수법을 바꿔나가면서 어떻게든 사기를 치기 때문에 
결국 사용자들이 스스로 꼼꼼하게 따져 보고 구매를 해야 하는 게 현실입니다.

이때 사기글 판별 전문가나 실제로 중고거래 사기 피해를 입었던 사람이 
내가 중고 거래를 할 때 옆에서 지켜봐준다면 훨씬 안심하고 거래를 할 수 있지 않을까요?

저희는 이런  아이디어를 바탕으로 프로젝트를 기획했습니다.

# 2. 프로젝트 시스템 아키텍처, ERD
- 채팅 서버 부분 강조하기, 좀 더 자세하게 적기
![image](https://github.com/woorifisa-projects-2nd/NGNG-chat-server/assets/62551858/57b35b0f-2966-49f8-a3ef-d30a87605977)
![image (1)](https://github.com/woorifisa-projects-2nd/NGNG-chat-server/assets/62551858/3b81650b-e00c-4172-95f3-27770b843b19)(https://www.erdcloud.com/d/akdosZBK9b5xTTRDd)

# 3. 기술 및 채택 이유
효과, 다른 기술과 비교
---
- Java 17
- Spring boot 3
- MySQL
- Websocket, STOMP
- Github
- Notion, Slack
- Jenkins, AWS EC2, AWS RDS
- 커밋 컨벤션 
- 코드 컨벤션
  
# 4. 주요 기능 + 사진 추가하기
- 1:1 채팅 과 오픈채팅에 대한 설명

  
- 웹소켓 연결 및 실시간 채팅 메시지 송수신
- 채팅 메시지 DB에 저장
- 채팅 메시지 수신 알림 전송
- 안 읽은 메시지 수 업데이트
- 1:1 채팅방 생성

# 5. 결과 및 성과


<웹소켓 프로토콜 관점에서>
- 생 웹소켓 vs stomp 쓴 거랑 비교
- 아니면 polling으로 했을 때란 비교

# 6. 그외 향후 계획, 배운 점 문제 해결 과정, 개선점
- 롤링 적용
- 메시지 입력하면 바로 채팅창에 뜨도록 수정
- 제대로 저장되지 않았을 경우에 대한 처리
- 이미지 전송 처리
- 동영상 전송 처리
- sockjs 적용
- 중간에 연결 끊겼을 경우에 대한 처리
- RabbitMQ 등 메시지 큐 적용
- 실시간 메시지 저장 속도 비교하기 ???
- redis vs nosql ???

# 7. 팀원 구성
---
- 김주찬 0%
- 문지환 0%
- 송원섭 0%
- 👑 조명하 100%

- 채팅 서버는 `조명하` 담당