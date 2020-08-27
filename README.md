# 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술
김영한님의 인프런 무료강의 따라하기

## 학습 내용 정리

### Section 1~2
> 스프링 웹 개발 기초

3가지 종류

- 정적 컨텐츠
- MVC 와 템플릿 엔진
- API

> 기본적인 스프링 부트의 흐름

- 내부에 tomcat 을 가지고 있다.
- 요청이 들어오면 부트가 컨트롤러 어노테이션을 우선적으로 찾는다.
- 컨트롤러에서 없으면 template 나 static 을 전달
 
### Section 3
> 비즈니스 요구사항 정리

<저장소, 레파지토리, 테스트 케이스>

서비스 구현
- 비즈니스 로직

테스트
- Junit

데이터 저장소가 선정되지 않음(가상 시나리오)

> 일반적인 웹 어플리케이션 계층 구조

컨트롤러 -> 서비스 -> 리포지토리 -> DB
도메인

**작업 내용**
1. 회원 저장소는 interface 로 구현 클래스를 변경할 수 있도록 설
2. 가벼운 메모리 기반의 데이터 저장소 사용

> 테스트 
- given, when, then 패턴으로 처음 시작

> 스프링 빈과 의존관계

**스프링 빈을 등록하는 2가지 방법**
- 컴포넌트 스캔과 자동 의존관계 설정
- 자바 코드로 직접 스프링 빈 등록하기

### 컴포넌트 스캔과 자동 의존관계 설정

**Controller**
- 스프링이 뜰때 스프링컨테이너 안에 @Controller 애노테이션이 있으면 해당 객체를 넣어서 관리
- Service 등을 사용할때 사용할 객체를 new 로 생성하는 대신에 스프링 컨테이너에 등록해서 사용

**Annotation**
- @Controller
    - @Autowired - 스프링 컨테이너가 뜰때 생성자를 실행하는데 스프링 컨테이너의 서비스와 연결을 해준다.
- @Service - 순수한 자바코드에 @Service 를 넣어주면 스프링이 뜰때 스프링 컨테이너에 서비스를 등록
- @Repository

**컴포넌트 스캔과 자동 의존관계 설정**
- `@Component` 애노테이션이 있으면 스프링 빈으로 자동 등록
- `@Controller` 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문이다.
- `@Component` 를 포함하는 애노테이션도 스프링 빈으로 자동 등록
    - `@Controller`, `@Service`, `@Repository`

**참고**
- Application 이 포함된 패키지 하위 까지만 컴포넌트 스캔 자동등록 대상
- 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록(하나만 등록해서 공유)

### 자바 코드로 직접 스프링 빈 등록하기

새로운 클래스를 생성하고 @Configuration 으로 자동으로 @Bean 을 생성

@Bean 애노테이션이 붙은것을 스프링빈에 등록


### 컴포넌트 스캔과, 직접 스프링 빈 등록 참고사항
컴포넌트 스캔
- 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용
- 다른 객체를 주입하고 싶을 때 클래스의 애노테이션들을 직접 다 변경해야한다.

직접 스프링 빈 등록
- 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
- 다른 객체를 주입하고 싶을 때 클래스만 변경해주면 된다.

주의
- `@Autowired` 를 통한 DI 는 컨트롤러, 서비스 등과 같이 스프링이 관리한느 객체에서만 동작
- 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.

### Web MVC 로 화면 추가

컨트롤러 @GetMapping("/"), 기본 화면으로 생성한 static/index.html
- 우선 순위는 컨트롤러가 먼저 갖는다. 

### H2 DB
- h2 데이터베이스 버전은 스프링 부트 버전에 맞춘다.
- 권한 주기: chmod 755 h2.sh 실행: ./h2.sh
- 데이터베이스 파일 생성 방법
    - jdbc:h2:~/test (최초 한번)
    - ~/test.mv.db 파일 생성 확인
    - 이후부터는 jdbc:h2:tcp://localhost/~/test 이렇게 접속


### 스프링 통합 테스트

@SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행한다.

@Transactional : 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스 트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
 
Service, Repository 에 @Autowired 를 사용해서 inject
- interface 로 선언된 클래스를 사용


### 스프링 JdbcTemplate

순수 Jdbc와 동일한 환경설정을 하면 된다.

스프링 JdbcTemplate과 MyBatis 같은 라이브러리는 JDBC API에서 본 반복 코드를 대부분 제거해준 다. 하지만 SQL은 직접 작생해야 한다.


### JPA
- JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다.
- JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환을 할 수 있다. 
- JPA를 사용하면 개발 생산성을 크게 높일 수 있다.

Annotation
- @Entity
- @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    - DB가 알아서 생성해주는 것을 IDENTITY 라고 한다.
- @Column()

**참고사항**
- JPA 를 사용하려면 EntityManager 를 주입 받아야 한다.
- JPA 는 트랜잭션이 변경될때 


## 단축키

파라미터 정보 툴팁 보기
- cmd + p

변수로 만들기 
- cmd + option + v

선택영역 메서드로 추출하기
- ctl + t -> Extract Method.. -> 변수명 입력
