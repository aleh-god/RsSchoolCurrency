package com.bondidos.rsschoolcurrency.domain.mapers

interface BaseMapper<In,Out> {
    operator fun invoke(params: In) : Out
}