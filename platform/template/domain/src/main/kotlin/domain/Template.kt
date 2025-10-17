package domain


class Template(
    val id : Long,
    val prompt: String,
    val fileUrl: String?,
    val cards: MutableList<Card>
) {
}
