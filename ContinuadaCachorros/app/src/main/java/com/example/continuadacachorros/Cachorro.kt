package com.example.continuadacachorros

// Nesta classe coloquei Double para precoMedio.
// A causa é a API, pois caso alguém tenha inserido um valor Double nela e eu tiver feito
// o data class com Int para precoMedio irá sempre falhar na pesquisa de cachorros para a lista.
// Assim sendo para funcionar sempre eu deixei como Double no data class, mas gostaria de ter deixado Int
// Pois é o tipo utilizado por padrão na API
data class Cachorro(val id: Int?, val raca: String, val precoMedio: Double, val indicadoCriancas: Boolean)