package com.bondidos.rsschoolcurrency.domain.use_cases

// Тоже замечание, что и к интерфесу маппера. 
// Не используешь в инверсии контроля, не используешь в тестах.
// Если нужно для иерархии классов - то sealed interface
interface InOutUseCase<In, Out> {
    operator fun invoke(name: In): Out
}

interface OutUseCase<Out> {
    operator fun invoke(): Out
}
