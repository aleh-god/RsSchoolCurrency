package com.bondidos.rsschoolcurrency.domain.models

interface Currency {
     val date: Long
     val rate: Double
     val baseName: String
}