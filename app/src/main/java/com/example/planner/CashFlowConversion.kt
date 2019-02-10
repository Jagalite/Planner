package com.example.planner

data class CashFlowConversion(var frequency: Frequency) {

    fun convertYearToTargetFrequency(cashFlow: CashFlow): Double {
        if (frequency == Frequency.BASE) return cashFlow.amount
        if (frequency == Frequency.DAILY) return extractToYear(cashFlow) / 364
        if (frequency == Frequency.WEEKLY) return extractToYear(cashFlow) / 52
        if (frequency == Frequency.MONTHLY) return extractToYear(cashFlow) / 12
        return extractToYear(cashFlow)
    }

    private fun extractToYear(cashFlow: CashFlow): Double {
        if (cashFlow.frequency == Frequency.DAILY) return cashFlow.amount * 364
        if (cashFlow.frequency == Frequency.WEEKLY) return cashFlow.amount * 52
        if (cashFlow.frequency == Frequency.MONTHLY) return cashFlow.amount * 12
        return cashFlow.amount
    }

}