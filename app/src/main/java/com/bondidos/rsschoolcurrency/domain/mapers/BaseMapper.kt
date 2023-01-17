package com.bondidos.rsschoolcurrency.domain.mapers

// Для чего нужен интерфейс? Ты не используешь его в итераторах, не принимаешь в конструкторы.
// Если надо было выстроить иерархию мапперов, тогда для этого используется sealed interface
interface BaseMapper<In,Out> {
    operator fun invoke(params: In) : Out
}
