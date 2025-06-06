# 내용정리

처리율 제한 장치는 트래픽의 처리율을 제어하기 위한 장치이다. 임계치를 넘어서면 추가로 도달한 모든 호출은 처리가 중단된다.

- 사용자는 초당 2회 이상 새 글을 올릴 수 없다
- 같은 IP 주소로는 하루에 10개 이상의 계정을 생성할 수 없다
- 같은 디바이스로는 주당 5회 이상 리워드를 요청할 수 없다.

분산 환경에서 처리율 제한장치를 제작하고 이를 행위했을때 사용자에게 알려야 한다

보통 제한 알고리즘은 아래 애들이 인기가 있고 널리 알려져있음

- 토큰 버킷
- 누출 버킷
- 고정 윈도 카운터
- 이동 윈도 로그
- 이동 윈도 카운터

- 중요하다고 생각한 부분
    - 미들웨어에서 트래픽을 구분시켜 처리
        - API 서버 과부하 방지
- 책과 생각이 다른 부분
    - 미들웨어에서는 트래픽 차단에 대한 상태코드를 429(많은 요청)으로 내려주는데 그러면 애플리케이션에서 트래픽을 감지하고 차단을 한다면 상태코드를 어떻게 내리는게 옳을까?
        - 429 (많은 요청)
        - 503 (서버 과부하)
        - 200 (정상) → 저는 이걸 선택할거 같음
- 개인적인 생각
    - **동일한 사용자가 여러번의 트래픽을 발생시키는** 에서 동일 사용자를 체크하는 방법을 어떤식으로 하는지?
        - 만약 자체 서비스에 로그인 기능이 존재하다면 로그인을 한 사용자를 기준으로 동일 사용자를 확인
        - 자체 서비스에 로그인 기능이 없다면 해당 접속자의 IP정보를 확인하여 동일 사용자를 확인
            - VPN을 켜서 IP를 변경하여 우회한 다음 Dos 공격을 하는 경우에는?
                - NginX에 이를 대응할 수 있는 설정인 해외 IP 차단 설정이 있음