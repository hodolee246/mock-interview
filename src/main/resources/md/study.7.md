# 13장 검색어 자동완성 시스템


### 정리
검색어 Top5 검색
select * from table where query = 'prefix%'
order by frequency desc
limit 5;

적은양에서는 좋은 설계안이지만 데이터가 많아질 경우 병목현상이 발생
 -> 트라이 자료구조 사용

# 14 유튜브설계, 15 구글드라이브 설계
...