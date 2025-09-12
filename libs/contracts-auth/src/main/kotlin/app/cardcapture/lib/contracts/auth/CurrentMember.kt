package app.cardcapture.lib.contracts.auth

/*
*  현재 인증된 사용자의 컨텍스트 추상화
*  - 실제 구현체는 libs/security 모듈에서 제공한다
*/
interface CurrentMember {

    val id: Long

}
