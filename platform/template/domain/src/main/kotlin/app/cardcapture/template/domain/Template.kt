package app.cardcapture.template.domain

/*
*  title, description 값을 따로 FE로 받지 않는다.
*  title -> purpose,
*  description -> texts
*  purpose, texts, prompt, color 는 추후 table 분리 가능성.
*/

class Template(
    val id : Long,
    val userId: Long,
    val title: String,
    val description: String,
    val prompt: String,
    val fileUrl: String?,
    val purpose: String,
    val texts: List<String>,
    val color: String,
    val editorPayload: EditorPayload
) {
}
