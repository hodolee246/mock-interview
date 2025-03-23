# 내용정리

key - value

모든 데이터를 메모리에 보관은 불가능

- **개선책으로 데이터압축, 자주 쓰이는건 메모리 나머진 디스크 저장**

CAP

- 일관성(Consistency)
   - 모든 클라이언트는 어떤 노드에 접속했냐 관계없이 모두 동일한 데이터를 봐야함
- 가용성(Availability)
   - 분산 시스템에 접속한 클라이언트는 장애 상관없이 항상 응답을 받아야함
- 파티션 감내(Partition Tolerance Theorem)
   - 파티션은 두 노드 사이 통신장애 발생 시에도 계속 동작해야함

key-value 는 위에 2가지 만족 시 아래로 분류됨

- CP
   - 일관성, 파티션 감내를 지원, 가용성 희생
- AP
   - 가용성 파티션감내 지원, 일관성 희생
- CA
   - 일관성, 가용성 지원, 파티션감내 희생 (파티션 감내 미지원 시 **네트워크 장애를 피하질 못해 사실 없는거나 다름없음**)

---

- 중요하다고 생각한 부분
   - 결국에는 CAP 부분이 가장 중요하다고 생각이듬
      - 클라이언트는 동일한 데이터를 전달받아야함
      - 클라이언트는 장애 상관없이 항상 응답을 받아야함
      - 파티션은 두 노드 사이 통신장애 발생 시에도 계속 동작해야함

     ### GPT

     ## **1️⃣ 왜 CAP에서 하나를 포기해야 하는가?**

     **CAP 이론(Consistency, Availability, Partition Tolerance)은 이론적으로 3개를 동시에 만족할 수 없음**을 주장해요.

     핵심 이유는 **네트워크 장애(Network Partition)가 발생했을 때** 어떻게 대응할 것이냐의 문제 때문이에요.

     ### **✅ 예제 1: Consistency(일관성) vs. Availability(가용성) 충돌**

      - 네트워크가 단절되었을 때(Partition 발생)
      - **모든 노드가 동일한 데이터를 제공하려면(Consistency)** 일부 요청을 거부해야 함 → **Availability 포기**
      - **모든 요청을 처리하려면(Availability)** 일관성이 깨질 수 있음 → **Consistency 포기**

     ### **✅ 예제 2: CAP을 3개 다 잡으려면?**

     CAP을 모두 만족하려면 **네트워크 장애가 발생하지 않는 환경**이어야 하지만,

     현실적으로 **네트워크 장애는 항상 발생 가능하기 때문에 Partition Tolerance는 필수적**이에요.

     즉, 결국 **Consistency(C)와 Availability(A) 중 하나를 선택해야 하는 상황이 발생**하는 거죠.

     ## **CAP을 포기하지 않는 구조가 있을까?**

     ### **💡 NewSQL과 Spanner: 3개를 만족하려는 시도**

     Google의 **Spanner** 같은 **NewSQL 데이터베이스**는 **CAP 이론을 극복하려는 구조**를 갖추고 있어요.

     완전히 CAP을 넘어서지는 못했지만, 기존보다 훨씬 **균형 잡힌 해결책**을 제공해요.

     | 시스템 | CAP 선택 | 특징 |
             | --- | --- | --- |
     | Spanner | **강한 CA** (P 최소화) | TrueTime(물리적 시간 동기화) 사용하여 네트워크 단절 최소화 |
     | FaunaDB | **CA-like** | 트랜잭션 지원하면서 높은 가용성 유지 |
     | CockroachDB | **CP + 높은 가용성** | Raft consensus 사용, 높은 가용성 유지 |

     ### **🔹 Google Spanner: TrueTime을 이용한 CA 유지**

      - Spanner는 **TrueTime API(하드웨어 기반 클럭 동기화)**를 사용해서 일관성을 유지
      - 트랜잭션을 글로벌하게 동기화하여 네트워크 장애를 최소화
      - 하지만 **완전히 CAP을 초월한 것은 아니고, 높은 비용과 복잡한 인프라가 필요**

     ### **🔹 CRDT와 Eventually Consistent Databases: AP+Eventually Consistent**

      - DynamoDB, Cassandra 같은 시스템은 **Eventual Consistency**를 활용
      - 데이터를 비동기적으로 동기화하여 **완벽한 일관성은 아니지만, 결국 데이터가 일치하도록 보장**
      - 즉, **엄격한 C 대신 "언젠가 C"를 제공하는 방식**

       ---

     ## **📌 결론: CAP을 포기하지 않는 DB는 있을까?**

      - **완벽하게 CAP을 모두 충족하는 시스템은 없음.**
      - 하지만 Google Spanner 같은 시스템이 **Partition의 영향을 최소화하여 C와 A를 모두 강화하려는 시도**를 함.
      - CRDT 같은 방식은 **Eventually Consistent를 통해 유연한 C를 제공하여 현실적인 균형을 맞춤**.

     즉, **현대 기술은 CAP의 한계를 극복하려는 다양한 시도를 하고 있지만, 완벽한 해결책은 아직 없음!**

- 책과 생각이 다른 부분
   - X
- 개인적인 생각
   - 그냥 막연히 Key-Value 어 ? 이거 완전… 이게 아니라 CAP 위주로 분산 Key-Value 저장소 설계 위주로 이야기함
      - Redis는 CAP 이론에서 P가 존재하지 않음 (네트워크 장애 시 대처 불가)
      - → 그렇기에 보통 우리가 Redis 사용하듯이 많이 사용한다고함
         - Redis 자체는 캐시로 사용 그리고 데이터는 RDBMS에 저장
         - 이 부분이 저번에 말씀드림 Write-through임 (데이터 저장 시 Redis, RDBMS 동시에 저장)
            - → 이러면 Redis 장애나도 RDBMS에 저장되긴한다.