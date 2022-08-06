package com.ayo.movies.utils

import com.ayo.api.utils.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler


class TestSchedulerProvider(private val mTestScheduler: TestScheduler) : SchedulerProvider {
    override fun ui(): Scheduler {
        return mTestScheduler
    }

    override fun computation(): Scheduler {
        return mTestScheduler
    }

    override fun io(): Scheduler {
        return mTestScheduler
    }
}