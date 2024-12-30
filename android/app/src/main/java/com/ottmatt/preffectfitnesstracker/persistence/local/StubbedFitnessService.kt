package com.ottmatt.preffectfitnesstracker.persistence.local

class StubbedFitnessService: FitnessService {
    override fun getStepCount(): Int {
        return 100
    }
}