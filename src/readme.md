# 코틀린 스프링 설정 테스트 (3.xx 버전) 

# 1. kotlin + jpa + dsl

# 2. swagger

# 3. graphQL

# 4. noSQL 연동 



---

<details> <summary> kotlin + jpa + dsl  </summary>

build.gradle.kts 추가
    
        implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
        kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
        kapt("jakarta.annotation:jakarta.annotation-api")
        kapt("jakarta.persistence:jakarta.persistence-api")

        // Q클래스 위치 지정
    kotlin.sourceSets.main {
        println("kotlin sourceSets builDir:: $buildDir")
        setBuildDir("$buildDir")
    }

config.kt 파일 작성

    @Configuration
        class QueryDslConfig(
        val em: EntityManager
        ) {

    @Bean
    fun queryFactory(): JPAQueryFactory {
        return JPAQueryFactory(em)
        }
    }

repo 서포터 파일 작성

    package com.stargazer.graphqltest.repository

    import jakarta.persistence.EntityManager
    import jakarta.persistence.PersistenceContext
    import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
    import org.springframework.stereotype.Repository
    
    @Repository
    abstract class CustomQueryDslSupport(domainClass: Class<*>) : QuerydslRepositorySupport(domainClass) {
    @PersistenceContext
    override fun setEntityManager(entityManager: EntityManager) {
    super.setEntityManager(entityManager)
        }
    }
</details>

<details>
<summary>swagger</summary>

build.gradle.kts 의존성 추가

    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

swagger config 추가 
    
    package com.stargazer.graphqltest.config

    import io.swagger.v3.oas.models.OpenAPI
    import io.swagger.v3.oas.models.info.Info
    import org.springframework.context.annotation.Bean
    import org.springframework.context.annotation.Configuration
    
    @Configuration
    class SwaggerConfig {
        @Bean
        fun openAPI(): OpenAPI {
        return OpenAPI()
        .info(apiInfo())
    }


        private fun apiInfo(): Info {
            return Info()
                .title("swagger test API")
                .version("0.0.1")
                .description("swagger test API")
        }
    }

실행후 작동 확인 

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

</details>


<details>
    <summary>graphql</summary>


build.gradle.kts 의존성 추가
    
       implementation("org.springframework.boot:spring-boot-starter-graphql")

resources/graphql/schema.graphqls 파일 작성
    
    type Music{
    id: ID!
    title: String!
    artist: String
    }
    
    type Query {
    music(title:String!): [Music]
    }
    
    type Mutation{
    createMusic(title:String!, artist:String): Music
    }

yml 파일 내용 추가
    
    graphql:
        graphiql:
          enabled: true # web browser 접속
        schema:
          printer:
            enabled: true #graphql 콘솔 출력

controller 매칭

    @RestController("/music")
    class MusicController(
    private val musicService: MusicService,
    ) {
    
        @QueryMapping
        fun music(@Argument title: String): List<Music> {
            return musicService.findMusic(title)
        }
    
        @MutationMapping
        fun createMusic(@Argument title: String, @Argument artist: String): Long {
            return musicService.registerMusic(title, artist)
        }
    }

[http://localhost:8080/graphiql](http://localhost:8080/graphiql) 접속후 확인  
</details>