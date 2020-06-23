package model

final case class InlineQuery(id: String, query: String)
final case class UpdateMessage(inline_query: InlineQuery)