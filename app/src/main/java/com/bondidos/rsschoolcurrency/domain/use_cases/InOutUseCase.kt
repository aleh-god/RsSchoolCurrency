package com.bondidos.rsschoolcurrency.domain.use_cases

interface InOutUseCase<In, Out> {
    operator fun invoke(name: In): Out
}

interface OutUseCase<Out> {
    operator fun invoke(): Out
}